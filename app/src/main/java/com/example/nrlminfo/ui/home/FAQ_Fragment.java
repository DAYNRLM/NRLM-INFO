package com.example.nrlminfo.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nrlminfo.R;
import com.example.nrlminfo.adapter.FaqCustomAdapter;
import com.example.nrlminfo.databinding.FragmentFaqBinding;
import com.example.nrlminfo.databinding.FragmentHomeBinding;
import com.example.nrlminfo.model.QuestionAnswer;
import com.example.nrlminfo.repo.HomeRepository;
import com.example.nrlminfo.ui.BaseFragment;
import com.example.nrlminfo.utils.AppExecutor;

import java.util.ArrayList;
import java.util.List;

public class FAQ_Fragment extends BaseFragment<HomeViewModel, FragmentFaqBinding, HomeRepository, HomeViewModelFactory> {

    FaqCustomAdapter faqCustomAdapter;

    @Override
    public Class<HomeViewModel> getViewModel() {
        return HomeViewModel.class;
    }

    @Override
    public FragmentFaqBinding getFragmentBinding(LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentFaqBinding.inflate(inflater, container, false);
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


        List<QuestionAnswer> dataItemList = new ArrayList<>();

        String[] mQuestionArray =getResources().getStringArray(R.array.array_question);
        String[] mAnswerArray =getResources().getStringArray(R.array.array_answer);


        for(int i=0;i<mQuestionArray.length;i++){
          QuestionAnswer questionAnswer = new QuestionAnswer(mQuestionArray[i],mAnswerArray[i]);
          dataItemList.add(questionAnswer);
        }


        faqCustomAdapter=new FaqCustomAdapter(dataItemList,getContext());
        binding.rvFaq.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvFaq.setAdapter(faqCustomAdapter);
        faqCustomAdapter.notifyDataSetChanged();


       /* recyclerView=root.findViewById(R.id.recyclerview);
        mLayoutManager = new LinearLayoutManager(FAQFragment.this.getActivity());
        mLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);*/
    }
}
