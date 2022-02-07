package com.example.nrlminfo.repo;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nrlminfo.networkclient.JsonApiClient;
import com.example.nrlminfo.networkclient.Result;
import com.example.nrlminfo.ui.BaseFragment;
import com.example.nrlminfo.ui.home.contacts.ContactsAdapter;
import com.example.nrlminfo.ui.home.contacts.ContactsFragment;
import com.example.nrlminfo.ui.home.contacts.ContactsRequestBean;
import com.example.nrlminfo.ui.home.contacts.ContactsResponseBean;
import com.example.nrlminfo.utils.AppConstant;
import com.example.nrlminfo.utils.AppUtils;
import com.google.gson.Gson;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeRepository extends BaseRepository {

    private Executor executor;
    private static HomeRepository instance = null;

    private HomeRepository(Executor executor) {
        this.executor = executor;
    }

    public static HomeRepository getInstance(Executor executor) {
        synchronized (instance) {
            if (instance == null) {
                instance = new HomeRepository(executor);
            }
        }
        return instance;
    }

    public void makeContactsRequest(final ContactsRequestBean contactsRequestBean,
                                    final RepositoryCallback<ContactsResponseBean> callback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                 try {

                 }catch (Exception e){

                 }
            }
        });
    }

    public void makeContactsCall(ContactsRequestBean contactsRequestBean,
            final RepositoryCallback<ContactsResponseBean> callback){

        Gson gson = new Gson();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConstant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
        JsonApiClient jsonApiClient = retrofit.create(JsonApiClient.class);
        Call<ContactsResponseBean> jsonApiClientConTacts = jsonApiClient.getConTacts(contactsRequestBean);

        jsonApiClientConTacts.enqueue(new Callback<ContactsResponseBean>() {
            @Override
            public void onResponse(Call<ContactsResponseBean> call, Response<ContactsResponseBean> response) {
                if (response.isSuccessful()) {
                    AppUtils.getInstance().showLog("ContactsResponse" + response.body().toString(), ContactsFragment.class);
                    ContactsResponseBean contactsResponseBean= response.body();
                    callback.onComplete(new Result.Success<ContactsResponseBean>(contactsResponseBean));
                    AppUtils.getInstance().showLog("ContactsListSize" + contactsResponseBean.getData().size(), ContactsFragment.class);
                }
            }

            @Override
            public void onFailure(Call<ContactsResponseBean> call, Throwable t) {
                AppUtils.getInstance().showLog("ContactsResponseExp" + t.toString(), ContactsFragment.class);
                callback.onComplete(new Result.Error<ContactsResponseBean>(new Exception(t)));
            }
        });
    }

}
