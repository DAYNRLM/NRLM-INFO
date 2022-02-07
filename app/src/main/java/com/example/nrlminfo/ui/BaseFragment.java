package com.example.nrlminfo.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.viewbinding.ViewBinding;

import com.example.nrlminfo.networkclient.vollyCall.VolleyResult;
import com.example.nrlminfo.networkclient.vollyCall.VolleyService;
import com.example.nrlminfo.repo.BaseRepository;
import com.example.nrlminfo.utils.AppDeviceInfoUtils;
import com.example.nrlminfo.utils.AppSharedPreferences;
import com.example.nrlminfo.utils.AppUtils;
import com.example.nrlminfo.utils.CustomProgressDialog;

public abstract class BaseFragment<VM extends ViewModel,
        B extends ViewBinding,
        R extends BaseRepository,
        VMF extends ViewModelProvider.Factory > extends Fragment {

    public B binding;
    public VM viewModel;
   /* public RetrofitClient client;


    public AppDeviceInfoUtils deviceUtils;*/
   public NavController navController;
   public AppUtils appUtils ;
    public AppSharedPreferences appSharedPreferences;
    public CustomProgressDialog customProgressDialog;
    public VolleyResult mResultCallBack = null;
    public VolleyService volleyService;
    public AppDeviceInfoUtils appDeviceInfoUtils;
   /*  public GetAllInstance getAllInstance;

   */


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = getFragmentBinding(inflater, container);
        //AuthViewModelfactory factory = new AuthViewModelfactory(getFragmentRepository());
       // viewModel = new ViewModelProvider(this,getFactory()).get(getViewModel());
       /*

        deviceUtils = AppDeviceInfoUtils.getInstance(getContext());*/
        navController = NavHostFragment.findNavController(this);
        customProgressDialog = CustomProgressDialog.newInstance(getContext());
      /*  client = RetrofitClient.getInstance();

        getAllInstance =GetAllInstance.getInstance(getCurrentContext());
       */

        appUtils =AppUtils.getInstance();
        appSharedPreferences =AppSharedPreferences.getInstance(getCurrentContext());
        volleyService=VolleyService.getInstance(getCurrentContext());
        appDeviceInfoUtils =AppDeviceInfoUtils.getInstance(getCurrentContext());

        this.onFragmentReady();
        return binding.getRoot();
    }

    public abstract Class<VM> getViewModel();
    public abstract B getFragmentBinding(LayoutInflater inflater, @Nullable ViewGroup container);
    public abstract R getFragmentRepository();
    public abstract Context getCurrentContext();
    public abstract  VMF getFactory();
    public abstract  void onFragmentReady();
}
