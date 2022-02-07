package com.example.nrlminfo.repo;

import com.example.nrlminfo.networkclient.Result;

public interface RepositoryCallback <T> {
    void onComplete(Result<T> result);
}
