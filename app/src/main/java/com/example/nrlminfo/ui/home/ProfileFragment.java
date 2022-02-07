package com.example.nrlminfo.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.navigation.NavDirections;

import com.example.nrlminfo.R;
import com.example.nrlminfo.databinding.FragmentHomeBinding;
import com.example.nrlminfo.databinding.FragmentProfileBinding;
import com.example.nrlminfo.repo.HomeRepository;
import com.example.nrlminfo.ui.BaseFragment;
import com.example.nrlminfo.utils.AppExecutor;

public class ProfileFragment extends BaseFragment<HomeViewModel, FragmentProfileBinding, HomeRepository, HomeViewModelFactory> {

    @Override
    public Class<HomeViewModel> getViewModel() {
        return HomeViewModel.class;
    }

    @Override
    public FragmentProfileBinding getFragmentBinding(LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentProfileBinding.inflate(inflater, container, false);
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
        binding.tvUserMobile.setText(appSharedPreferences.getMobileNumber());

        binding.changeLanguageLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(ProfileFragmentDirections.actionProfileFragmentToChangeLanguageFragment());
            }
        });

        binding.aboutUsLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(ProfileFragmentDirections.actionProfileFragmentToAboutUsFragment());
            }
        });
        binding.btnLogout.setOnClickListener(view -> {
            navController.navigate(R.id.logoutFragment2);
        });
    }
}
