package com.example.nrlminfo.ui.video;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import androidx.annotation.Nullable;

import com.example.nrlminfo.databinding.FragmentVideoListBinding;
import com.example.nrlminfo.networkclient.JsonApiClient;
import com.example.nrlminfo.repo.HomeRepository;
import com.example.nrlminfo.ui.BaseFragment;
import com.example.nrlminfo.ui.video.beans.VideoResponseBean;
import com.example.nrlminfo.ui.video.beans.VideoCategoryBean;
import com.example.nrlminfo.ui.video.beans.VideoDetailsBean;
import com.example.nrlminfo.ui.video.beans.VideoRequestBean;
import com.example.nrlminfo.utils.AppConstant;
import com.example.nrlminfo.utils.AppExecutor;
import com.example.nrlminfo.utils.AppUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VideoTitlesFragment extends BaseFragment<VideoViewModel, FragmentVideoListBinding, HomeRepository, VideoViewModelFactory> {
    private HashMap<String, List<VideoDetailsBean>> expandableListDetail;

    @Override
    public Class<VideoViewModel> getViewModel() {
        return VideoViewModel.class;
    }

    @Override
    public FragmentVideoListBinding getFragmentBinding(LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentVideoListBinding.inflate(inflater, container, false);
    }

    @Override
    public HomeRepository getFragmentRepository() {
        return HomeRepository.getInstance(AppExecutor.getExecutorServiceInstance());
    }

    @Override
    public Context getCurrentContext() {
        return getContext();
    }

    @Override
    public VideoViewModelFactory getFactory() {
        return new VideoViewModelFactory();
    }

    @Override
    public void onFragmentReady() {
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

        VideoRequestBean videoRequestBean=new VideoRequestBean();
        videoRequestBean.setStatus("Active");

        Call<VideoResponseBean> videoResponseBeanCall=jsonApiClient.getVideos(videoRequestBean);
        videoResponseBeanCall.enqueue(new Callback<VideoResponseBean>() {
            @Override
            public void onResponse(Call<VideoResponseBean> call, Response<VideoResponseBean> response) {
                if (response.isSuccessful()){
                    AppUtils.getInstance().showLog("VideoResponse"+response.body(),VideoTitlesFragment.class);
                    VideoResponseBean videoResponseBean=response.body();
                   List<VideoResponseBean.VideosDetails> videoDetailsBeanList= videoResponseBean.getData();
                   String msg="";
                    AppUtils.getInstance().showLog("VideoResponsegetVideo_tittle"+videoDetailsBeanList.get(0).getVideo_tittle(),VideoTitlesFragment.class);


                }
            }

            @Override
            public void onFailure(Call<VideoResponseBean> call, Throwable t) {
                AppUtils.getInstance().showLog("VideoResponseError"+t.getMessage(),VideoTitlesFragment.class);

            }
        });

        List<VideoCategoryBean> categoryBeanList = new ArrayList<>();
        VideoCategoryBean categoryBean = new VideoCategoryBean();
        categoryBean.setTitleId(1);
        categoryBean.setTitleName("A");

        VideoCategoryBean categoryBean1 = new VideoCategoryBean();
        categoryBean1.setTitleId(2);
        categoryBean1.setTitleName("B");

        VideoCategoryBean categoryBean2 = new VideoCategoryBean();
        categoryBean2.setTitleId(3);
        categoryBean2.setTitleName("C");

        VideoCategoryBean categoryBean3 = new VideoCategoryBean();
        categoryBean3.setTitleId(4);
        categoryBean3.setTitleName("D");

        VideoCategoryBean categoryBean4 = new VideoCategoryBean();
        categoryBean4.setTitleId(5);
        categoryBean4.setTitleName("E");

        VideoCategoryBean categoryBean5 = new VideoCategoryBean();
        categoryBean5.setTitleId(6);
        categoryBean5.setTitleName("F");

        VideoCategoryBean categoryBean6 = new VideoCategoryBean();
        categoryBean6.setTitleId(7);
        categoryBean6.setTitleName("G");

        VideoCategoryBean categoryBean7 = new VideoCategoryBean();
        categoryBean7.setTitleId(8);
        categoryBean7.setTitleName("H");

        categoryBeanList.add(categoryBean);
        categoryBeanList.add(categoryBean1);
        categoryBeanList.add(categoryBean2);
        categoryBeanList.add(categoryBean3);
        categoryBeanList.add(categoryBean4);
        categoryBeanList.add(categoryBean5);
        categoryBeanList.add(categoryBean6);
        categoryBeanList.add(categoryBean7);


        List<VideoDetailsBean> detailsBeanList = new ArrayList<>();

        VideoDetailsBean videoDetailsBean1 = new VideoDetailsBean();
        videoDetailsBean1.setVideoId(1);
        videoDetailsBean1.setVideoTitle("What is aajeevika ?");
        videoDetailsBean1.setVideoLink("6REBAz2tomc");


        VideoDetailsBean videoDetailsBean = new VideoDetailsBean();
        videoDetailsBean.setVideoId(2);
        videoDetailsBean.setVideoTitle("What is NRLM ?");
        videoDetailsBean.setVideoLink("q29flc1p4aw");

        detailsBeanList.add(videoDetailsBean);

        expandableListDetail = new HashMap<String, List<VideoDetailsBean>>();
        for (VideoCategoryBean inactiveReason : categoryBeanList) {
            expandableListDetail.put(inactiveReason.getTitleName(), detailsBeanList);
        }
        VideoTitlesExpendableAdapter videoTitlesExpendableAdapter = new VideoTitlesExpendableAdapter(getContext(), categoryBeanList, expandableListDetail);

        binding.videoEL.setAdapter(videoTitlesExpendableAdapter);

        binding.videoEL.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int i) {
                int categoryTitleId = categoryBeanList.get(i).getTitleId();
            }
        });

        binding.videoEL.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int i) {

            }
        });

        binding.videoEL.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {

                String videoLink = expandableListDetail.get(categoryBeanList.get(i).getTitleName())
                        .get(i1).getVideoLink();

                navController.navigate(VideoTitlesFragmentDirections.actionVideoTitlesFragmentToVideoPlayerFragment());
                return true;
            }
        });
    }
}
