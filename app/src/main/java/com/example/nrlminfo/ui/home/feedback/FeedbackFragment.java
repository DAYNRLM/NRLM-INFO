package com.example.nrlminfo.ui.home.feedback;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nrlminfo.R;
import com.example.nrlminfo.databinding.FragmentFeedbackBinding;
import com.example.nrlminfo.repo.HomeRepository;
import com.example.nrlminfo.ui.BaseFragment;
import com.example.nrlminfo.ui.home.HomeViewModel;
import com.example.nrlminfo.ui.home.HomeViewModelFactory;
import com.example.nrlminfo.utils.AppExecutor;

import java.util.ArrayList;

public class FeedbackFragment extends BaseFragment<HomeViewModel, FragmentFeedbackBinding, HomeRepository, HomeViewModelFactory>{

    private RecyclerView recyclerView;
    private LinearLayoutManager mLayoutManager;
    @Override
    public Class<HomeViewModel> getViewModel() {
        return HomeViewModel.class;
    }

    @Override
    public FragmentFeedbackBinding getFragmentBinding(LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentFeedbackBinding.inflate(inflater,container,false);
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

        FeedbackFragment.this.getActivity().setTitle(FeedbackFragment.this.getActivity().getResources().getString(R.string.menu_feedback));


            recyclerView=binding.recyclerview;
            mLayoutManager = new LinearLayoutManager(FeedbackFragment.this.getActivity());
            mLayoutManager.setOrientation(RecyclerView.VERTICAL);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());

        ArrayList<FeedbackModel> feedbackModelList=new ArrayList<>();

        FeedbackModel feedbackModel=new FeedbackModel();

        ArrayList<String> list=new ArrayList<>();

        feedbackModel.setOptionCount(5);
        feedbackModel.setHeading(FeedbackFragment.this.getActivity().getString(R.string.feedback_heading_1));

        list.add(FeedbackFragment.this.getActivity().getString(R.string.feedback_heading_1_option_1));
        list.add(FeedbackFragment.this.getActivity().getString(R.string.feedback_heading_1_option_2));
        list.add(FeedbackFragment.this.getActivity().getString(R.string.feedback_heading_1_option_3));
        list.add(FeedbackFragment.this.getActivity().getString(R.string.feedback_heading_1_option_4));
        list.add(FeedbackFragment.this.getActivity().getString(R.string.feedback_heading_1_option_5));
        feedbackModel.setOptionList(list);

        feedbackModelList.add(feedbackModel);



        feedbackModel=new FeedbackModel();
        list=new ArrayList<>();
        feedbackModel.setOptionCount(4);
        feedbackModel.setHeading(FeedbackFragment.this.getActivity().getString(R.string.feedback_heading_2));

        list.add(FeedbackFragment.this.getActivity().getString(R.string.feedback_heading_2_option_1));
        list.add(FeedbackFragment.this.getActivity().getString(R.string.feedback_heading_2_option_2));
        list.add(FeedbackFragment.this.getActivity().getString(R.string.feedback_heading_2_option_3));
        list.add(FeedbackFragment.this.getActivity().getString(R.string.feedback_heading_2_option_4));
        feedbackModel.setOptionList(list);

        feedbackModelList.add(feedbackModel);


        feedbackModel=new FeedbackModel();
        list=new ArrayList<>();
        feedbackModel.setOptionCount(5);
        feedbackModel.setHeading(FeedbackFragment.this.getActivity().getString(R.string.feedback_heading_3));

        list.add(FeedbackFragment.this.getActivity().getString(R.string.feedback_heading_3_option_1));
        list.add(FeedbackFragment.this.getActivity().getString(R.string.feedback_heading_3_option_2));
        list.add(FeedbackFragment.this.getActivity().getString(R.string.feedback_heading_3_option_3));
        list.add(FeedbackFragment.this.getActivity().getString(R.string.feedback_heading_3_option_4));
        list.add(FeedbackFragment.this.getActivity().getString(R.string.feedback_heading_3_option_5));

        feedbackModel.setOptionList(list);

        feedbackModelList.add(feedbackModel);


        feedbackModel=new FeedbackModel();
        list=new ArrayList<>();
        feedbackModel.setOptionCount(5);
        feedbackModel.setHeading(FeedbackFragment.this.getActivity().getString(R.string.feedback_heading_4));

        list.add(FeedbackFragment.this.getActivity().getString(R.string.feedback_heading_4_option_1));
        list.add(FeedbackFragment.this.getActivity().getString(R.string.feedback_heading_4_option_2));
        list.add(FeedbackFragment.this.getActivity().getString(R.string.feedback_heading_4_option_3));
        list.add(FeedbackFragment.this.getActivity().getString(R.string.feedback_heading_4_option_4));
        list.add(FeedbackFragment.this.getActivity().getString(R.string.feedback_heading_4_option_5));

        feedbackModel.setOptionList(list);

        feedbackModelList.add(feedbackModel);


        feedbackModel=new FeedbackModel();
        list=new ArrayList<>();
        feedbackModel.setOptionCount(2);
        feedbackModel.setHeading(FeedbackFragment.this.getActivity().getString(R.string.feedback_heading_5));

        list.add(FeedbackFragment.this.getActivity().getString(R.string.feedback_heading_5_option_1));
        list.add(FeedbackFragment.this.getActivity().getString(R.string.feedback_heading_5_option_2));

        feedbackModel.setOptionList(list);

        feedbackModelList.add(feedbackModel);


        FeedbackAdapter feedbackAdapter = new FeedbackAdapter(FeedbackFragment.this, feedbackModelList);
        recyclerView.setAdapter(feedbackAdapter); // set the Adapter to RecyclerView



    }
}
