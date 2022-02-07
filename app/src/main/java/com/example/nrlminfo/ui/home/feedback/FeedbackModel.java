package com.example.nrlminfo.ui.home.feedback;

import java.util.ArrayList;

public class FeedbackModel {

    private int optionCount;
    private String heading;
    private ArrayList<String> optionList;

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public ArrayList<String> getOptionList() {
        return optionList;
    }

    public void setOptionList(ArrayList<String> optionList) {
        this.optionList = optionList;
    }

    public int getOptionCount() {
        return optionCount;
    }

    public void setOptionCount(int optionCount) {
        this.optionCount = optionCount;
    }
}
