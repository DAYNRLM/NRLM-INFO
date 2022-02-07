package com.example.nrlminfo.ui.home.feedback;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nrlminfo.R;

import java.util.ArrayList;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.MyViewHolder> {

    private Fragment context;
    private ArrayList<FeedbackModel> feedbackModelList;


    public FeedbackAdapter(Fragment context, ArrayList<FeedbackModel> feedbackModelList) {
        this.context = context;
        this.feedbackModelList = feedbackModelList;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view_feedback, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.radiogroup.setOrientation(LinearLayout.VERTICAL);
        holder.text_heading.setText(feedbackModelList.get(position).getHeading());
        //
        for (int i = 0; i < feedbackModelList.get(position).getOptionCount(); i++) {
            RadioButton rdbtn = new RadioButton(context.getActivity());
            rdbtn.setId(View.generateViewId());
            rdbtn.setText(feedbackModelList.get(position).getOptionList().get(i));
            //rdbtn.setText("Radio " + rdbtn.getId());
            rdbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            holder.radiogroup.addView(rdbtn);
        }

    }



    @Override
    public int getItemCount() {
        return feedbackModelList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView text_heading;
        RadioGroup radiogroup;

        public MyViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            text_heading = (TextView) itemView.findViewById(R.id.text_heading);

            radiogroup = (RadioGroup) itemView.findViewById(R.id.radiogroup);
        }
    }
}

