package com.example.nrlminfo.ui.video.beans;

import java.io.Serializable;
import java.util.List;

public class VideoResponseBean implements Serializable {

    private List<VideosDetails> data;

    public List<VideosDetails> getData() {
        return data;
    }

    public void setData(List<VideosDetails> data) {
        this.data = data;
    }

  public static class VideosDetails{
       private int tittle_id;
       private String tittle_name;
       private int video_id;
        private String video_tittle;
        private String video_link;

        public int getTittle_id() {
            return tittle_id;
        }

        public void setTittle_id(int tittle_id) {
            this.tittle_id = tittle_id;
        }

        public String getTittle_name() {
            return tittle_name;
        }

        public void setTittle_name(String tittle_name) {
            this.tittle_name = tittle_name;
        }

        public int getVideo_id() {
            return video_id;
        }

        public void setVideo_id(int video_id) {
            this.video_id = video_id;
        }

        public String getVideo_tittle() {
            return video_tittle;
        }

        public void setVideo_tittle(String video_tittle) {
            this.video_tittle = video_tittle;
        }

        public String getVideo_link() {
            return video_link;
        }

        public void setVideo_link(String video_link) {
            this.video_link = video_link;
        }
    }
}

    /*{
        "data": [{
            "video_tittle": "NRLM",
            "video_link": "https//nrlm.gov.in",
            "tittle_id": "1",
            "tittle_name": "Nrlm Introduction video",
            "video_id": "1"
        }]
    }*/
