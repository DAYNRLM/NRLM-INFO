package com.example.nrlminfo.dialog;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nrlminfo.R;
import com.example.nrlminfo.SplashScreenActivity;
import com.example.nrlminfo.ui.home.HomeActivity;
import com.example.nrlminfo.ui.login.AuthActivity;
import com.example.nrlminfo.utils.AppSharedPreferences;
import com.example.nrlminfo.utils.CustomProgressDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class LogoutFragment extends BottomSheetDialogFragment {
    CustomProgressDialog customProgressDialog;
    AppSharedPreferences appSharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.dialog_layout_logout,container,false);
        customProgressDialog = CustomProgressDialog.newInstance(getContext());
        appSharedPreferences = AppSharedPreferences.getInstance(getContext());
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        view.findViewById(R.id.btn_logout).setOnClickListener(view1 -> {

            customProgressDialog.showProgress("Loading...",false);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    customProgressDialog.hideProgress();
                    dismiss();
                    appSharedPreferences.setLogin("");
                    Intent i = new Intent(getContext(), AuthActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }
            }, 4000);
        });

        view.findViewById(R.id.btn_cancel).setOnClickListener(view1 -> {
            dismiss();
        });
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

}
