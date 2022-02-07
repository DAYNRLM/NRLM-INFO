package com.example.nrlminfo.ui.video.beans;

import java.io.Serializable;

public class VideoCategoryBean implements Serializable {

    private int titleId;
    private String titleName;

    public int getTitleId() {
        return titleId;
    }

    public void setTitleId(int titleId) {
        this.titleId = titleId;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }
}
