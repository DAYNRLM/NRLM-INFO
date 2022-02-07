package com.example.nrlminfo.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.example.nrlminfo.databinding.FragmentComponentActivityBinding;
import com.example.nrlminfo.databinding.FragmentFaqBinding;
import com.example.nrlminfo.repo.HomeRepository;
import com.example.nrlminfo.ui.BaseFragment;
import com.example.nrlminfo.utils.AppExecutor;

public class ActivityFragment extends BaseFragment<HomeViewModel, FragmentComponentActivityBinding, HomeRepository, HomeViewModelFactory> {
    @Override
    public Class<HomeViewModel> getViewModel() {
        return HomeViewModel.class;
    }

    @Override
    public FragmentComponentActivityBinding getFragmentBinding(LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentComponentActivityBinding.inflate(inflater, container, false);
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
    public HomeViewModelFactory getFactory() {
        return new HomeViewModelFactory();
    }

    @Override
    public void onFragmentReady() {

    }
}
