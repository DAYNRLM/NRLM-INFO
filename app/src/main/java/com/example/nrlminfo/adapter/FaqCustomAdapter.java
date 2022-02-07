package com.example.nrlminfo.adapter;

import android.app.Application;
import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nrlminfo.databinding.CustomLayoutForFaqBinding;
import com.example.nrlminfo.model.QuestionAnswer;
import com.mapzen.speakerbox.Speakerbox;

import java.util.List;
import java.util.Locale;

public  class FaqCustomAdapter extends RecyclerView.Adapter<FaqCustomAdapter.MyViewHolder> {
    List<QuestionAnswer> dataItems;
    Context context;
    TextToSpeech textToSpeech;

    public FaqCustomAdapter(List<QuestionAnswer> dataItems, Context context) {
        this.dataItems = dataItems;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       // CustomAdapterLanguageLayoutBinding rootView = CustomAdapterLanguageLayoutBinding.inflate(LayoutInflater.from(context), parent, false);

        CustomLayoutForFaqBinding rootView = CustomLayoutForFaqBinding.inflate(LayoutInflater.from(context),parent,false);

        return new MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        textToSpeech = new TextToSpeech(context.getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {

                // if No error is found then only it will run
                if(i!=TextToSpeech.ERROR){
                    // To Choose language of speech
                   // textToSpeech.setLanguage(Locale.UK);
                    textToSpeech.setLanguage(Locale.forLanguageTag("hin"));
                  /*  textToSpeech.setLanguage(new Locale("pan","IN"));
                    textToSpeech.setSpeechRate(0.5f);*/
                   // textToSpeech.setLanguage(Locale.forLanguageTag("pan"));
                }
            }
        });


        holder.itemBinding.tvQuestion.setText("Q"+(position+1)+". "+dataItems.get(position).getQuestion());
        holder.itemBinding.tvAnswer.setText(dataItems.get(position).getAnswer());

        holder.itemBinding.imageVolumeOff.setOnClickListener(view -> {
           // textToSpeech.speak(Text.getText().toString(),TextToSpeech.QUEUE_FLUSH,null);
            holder.itemBinding.imageVolumeUp.setVisibility(View.VISIBLE);
            holder.itemBinding.imageVolumeOff.setVisibility(View.GONE);
            textToSpeech.speak(holder.itemBinding.tvAnswer.getText().toString(),TextToSpeech.QUEUE_ADD,null);
        });

        holder.itemBinding.imageVolumeUp.setOnClickListener(view -> {
            holder.itemBinding.imageVolumeUp.setVisibility(View.GONE);
            holder.itemBinding.imageVolumeOff.setVisibility(View.VISIBLE);
            textToSpeech.stop();
        });

    }

    @Override
    public int getItemCount() {
        return dataItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CustomLayoutForFaqBinding itemBinding;
        public MyViewHolder(@NonNull CustomLayoutForFaqBinding itemView) {
            super(itemView.getRoot());
            itemBinding=itemView;
        }
    }
}
