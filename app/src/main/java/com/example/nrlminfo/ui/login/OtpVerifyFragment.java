package com.example.nrlminfo.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nrlminfo.SplashScreenActivity;
import com.example.nrlminfo.databinding.FragmentLoginBinding;
import com.example.nrlminfo.databinding.FragmentOtpVerifyBinding;
import com.example.nrlminfo.repo.HomeRepository;
import com.example.nrlminfo.ui.BaseFragment;
import com.example.nrlminfo.ui.home.HomeActivity;
import com.example.nrlminfo.utils.AppExecutor;
import com.example.nrlminfo.utils.ViewUtilsKt;

public class OtpVerifyFragment extends BaseFragment<LoginViewModel, FragmentOtpVerifyBinding, HomeRepository, LoginViewModelFactory> {
    @Override
    public Class<LoginViewModel> getViewModel() {
        return LoginViewModel.class;
    }

    @Override
    public FragmentOtpVerifyBinding getFragmentBinding(LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentOtpVerifyBinding.inflate(inflater, container, false);
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
    public LoginViewModelFactory getFactory() {
        return new LoginViewModelFactory();
    }

    @Override
    public void onFragmentReady() {

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.tvOtpTitle.setText("OTP is sent to "+appSharedPreferences.getMobileNumber());

        binding.btnVerify.setOnClickListener(v -> {
            if (binding.pinviewGetOTP.getText().toString().equalsIgnoreCase(appSharedPreferences.getGenOTP())) {
                appSharedPreferences.setLogin("ok");
                Intent i = new Intent(getCurrentContext(), HomeActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            } else {
                binding.pinviewGetOTP.setText("");
                ViewUtilsKt.tost(getContext(), "Enter OTP is wrong");
            }
        });
    }
}
