package com.example.nrlminfo.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nrlminfo.databinding.FragmentChangeLanguageBinding;
import com.example.nrlminfo.repo.BaseRepository;
import com.example.nrlminfo.repo.HomeRepository;
import com.example.nrlminfo.ui.BaseFragment;
import com.example.nrlminfo.utils.AppExecutor;

public class ChangeLanguageFragment  extends BaseFragment<HomeViewModel, FragmentChangeLanguageBinding, BaseRepository,HomeViewModelFactory> {



    @Override
    public Class<HomeViewModel> getViewModel() {
        return HomeViewModel.class;
    }

    @Override
    public FragmentChangeLanguageBinding getFragmentBinding(LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentChangeLanguageBinding.inflate(inflater,container,false);

    }

    @Override
    public BaseRepository getFragmentRepository() {
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
