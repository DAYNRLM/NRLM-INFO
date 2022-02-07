package com.example.nrlminfo.ui.video.beans;

import java.io.Serializable;

public class VideoRequestBean implements Serializable {
    /*
{"status":"Active"}*/

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
