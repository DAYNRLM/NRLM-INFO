package com.example.nrlminfo.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavDirections;

import com.example.nrlminfo.HomeNavGraphDirections;
import com.example.nrlminfo.R;
import com.example.nrlminfo.databinding.FragmentHomeBinding;
import com.example.nrlminfo.repo.HomeRepository;
import com.example.nrlminfo.ui.BaseFragment;
import com.example.nrlminfo.ui.video.VideoActivity;
import com.example.nrlminfo.utils.AppExecutor;

public class HomeFragment extends BaseFragment<HomeViewModel, FragmentHomeBinding, HomeRepository, HomeViewModelFactory> {

    @Override
    public Class<HomeViewModel> getViewModel() {
        return HomeViewModel.class;
    }

    @Override
    public FragmentHomeBinding getFragmentBinding(LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentHomeBinding.inflate(inflater, container, false);
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       /* binding.ivProfile.setOnClickListener(v -> {
            NavDirections navDirections = HomeFragmentDirections.actionHomeFragment2ToProfileFragment();
            navController.navigate(navDirections);
        });*/

        //update noe
        binding.nrlmFragment.setOnClickListener(v -> {

            NavDirections navDirections = HomeFragmentDirections.actionHomeFragment2ToAboutNrlmFragment();
            navController.navigate(navDirections);

        });

        binding.backgroungFragment.setOnClickListener(view1 -> {
            NavDirections navDirections = HomeFragmentDirections.actionHomeFragment2ToBackgroundFragment();
            navController.navigate(navDirections);
        });

        binding.referFragment.setOnClickListener(view1 -> {
            NavDirections navDirections = HomeFragmentDirections.actionHomeFragment2ToReferFragment2();
            navController.navigate(navDirections);
        });

        binding.video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), VideoActivity.class));
            }
        });

        binding.contactLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(HomeFragmentDirections.actionHomeFragment2ToContactsFragment());
            }
        });

        binding.feedbackLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(HomeFragmentDirections.actionHomeFragment2ToFeedbackFragment());
            }
        });



        binding.cardViewObjective.setOnClickListener(view1 -> {
            navController.navigate(HomeFragmentDirections.actionHomeFragment2ToObjectiveFragment());
        });

        binding.cvFaq.setOnClickListener(view1 -> {
            navController.navigate(HomeFragmentDirections.actionHomeFragment2ToFAQFragment());
        });

        binding.cvComponentFragment.setOnClickListener(view1 -> {
            navController.navigate(HomeFragmentDirections.actionHomeFragment2ToActivityFragment());
        });

        binding.cvLogoutFragment.setOnClickListener(view1 -> {
            navController.navigate(R.id.logoutFragment2);
        });


    }
}
