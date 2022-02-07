package com.example.nrlminfo.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppExecutor {
    private static  ExecutorService executorService =null;
    private static final int NUMBER_OF_THREADS=4;

    public static ExecutorService getExecutorServiceInstance(){
        synchronized (executorService){
            if (executorService==null){
                executorService=Executors.newFixedThreadPool(NUMBER_OF_THREADS);
            }
        }
        return executorService;
    }


}
