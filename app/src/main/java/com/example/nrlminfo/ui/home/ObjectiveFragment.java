package com.example.nrlminfo.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.example.nrlminfo.databinding.FragmentHomeBinding;
import com.example.nrlminfo.databinding.FragmentObjectiveBinding;
import com.example.nrlminfo.repo.HomeRepository;
import com.example.nrlminfo.ui.BaseFragment;
import com.example.nrlminfo.utils.AppExecutor;

public class ObjectiveFragment extends BaseFragment<HomeViewModel, FragmentObjectiveBinding, HomeRepository, HomeViewModelFactory> {
    @Override
    public Class<HomeViewModel> getViewModel() {
        return HomeViewModel.class;
    }

    @Override
    public FragmentObjectiveBinding getFragmentBinding(LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentObjectiveBinding.inflate(inflater, container, false);
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
