package com.example.nrlminfo.networkclient;

import com.example.nrlminfo.ui.home.contacts.ContactsRequestBean;
import com.example.nrlminfo.ui.home.contacts.ContactsResponseBean;
import com.example.nrlminfo.ui.home.contacts.Example;
import com.example.nrlminfo.ui.video.beans.VideoResponseBean;
import com.example.nrlminfo.ui.video.beans.VideoRequestBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonApiClient {

    @POST("mmu/detail")
    Call<ContactsResponseBean> getConTacts(@Body ContactsRequestBean contactsRequestBean);

    @POST("video/detail")
    Call<VideoResponseBean> getVideos(@Body VideoRequestBean videoRequestBean);




    /*text example*/
    @GET("posts")
    Call<List<Example>> test();




}
