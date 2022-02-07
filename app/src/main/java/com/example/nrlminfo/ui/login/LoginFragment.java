package com.example.nrlminfo.ui.login;

import android.app.PendingIntent;
import android.content.Context;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavDirections;

import com.android.volley.VolleyError;
import com.example.nrlminfo.databinding.FragmentLoginBinding;
import com.example.nrlminfo.networkclient.vollyCall.VolleyResult;
import com.example.nrlminfo.repo.HomeRepository;
import com.example.nrlminfo.ui.BaseFragment;
import com.example.nrlminfo.utils.AppExecutor;
import com.example.nrlminfo.utils.ViewUtilsKt;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginFragment extends BaseFragment<LoginViewModel, FragmentLoginBinding, HomeRepository, LoginViewModelFactory> {


    @Override
    public Class<LoginViewModel> getViewModel() {
        return LoginViewModel.class;
    }

    @Override
    public FragmentLoginBinding getFragmentBinding(LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentLoginBinding.inflate(inflater, container, false);
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

        binding.cvSetOtp.setOnClickListener(v -> {
            String adid = appDeviceInfoUtils.getIMEINo1();
            appUtils.showLog("ADID----"+adid,LoginFragment.class);
            ViewUtilsKt.tost(getContext(), adid);
            if(binding.etMobileNumber.getText().toString().isEmpty()||(binding.etMobileNumber.getText().toString().length()<10)){
                ViewUtilsKt.tost(getContext(), "Enter valid Mobile Number");
            }else {
                String genOTP =appUtils.getRandomOtp();
                customProgressDialog.showProgress("Loading...",false);
                JSONObject otpObject =new JSONObject();
                try {
                    otpObject.accumulate("mobileno",binding.etMobileNumber.getText().toString());
                    otpObject.accumulate("message",genOTP);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                mResultCallBack=new VolleyResult() {
                    @Override
                    public void notifySuccess(String requestType, JSONObject response) {
                        customProgressDialog.hideProgress();
                        appSharedPreferences.setGenOTP(genOTP);
                        appSharedPreferences.setMobileNumber(binding.etMobileNumber.getText().toString());
                        ViewUtilsKt.tost(getContext(), genOTP);
                        navController.navigate(LoginFragmentDirections.actionLoginFragmentToOtpVerifyFragment());
                    }

                    @Override
                    public void notifyError(String requestType, VolleyError error) {
                        customProgressDialog.hideProgress();
                        ViewUtilsKt.tost(getContext(), "Try againg.. Server error");
                    }
                };
                volleyService.postDataVolley("sendOTP","https://nrlm.gov.in/nrlmwebservice/services/forgotpassword/message",otpObject,mResultCallBack);

            }
        });
    }

}
