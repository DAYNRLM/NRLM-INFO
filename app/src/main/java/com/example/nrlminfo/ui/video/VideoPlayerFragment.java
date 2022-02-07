package com.example.nrlminfo.ui.video;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nrlminfo.databinding.FragmentVideoPlayerBinding;
import com.example.nrlminfo.repo.HomeRepository;
import com.example.nrlminfo.ui.BaseFragment;
import com.example.nrlminfo.utils.AppExecutor;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerUtils;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class VideoPlayerFragment extends BaseFragment<VideoViewModel, FragmentVideoPlayerBinding, HomeRepository,VideoViewModelFactory> {

    private YouTubePlayerView youTubePlayerView;

    @Override
    public Class<VideoViewModel> getViewModel() {
        return VideoViewModel.class;
    }

    @Override
    public FragmentVideoPlayerBinding getFragmentBinding(LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentVideoPlayerBinding.inflate(inflater,container,false);
    }
    //S0Q4gqBUs7c
    //q29flc1p4aw

    @Override
    public HomeRepository getFragmentRepository() {
        return HomeRepository.getInstance(AppExecutor.getExecutorServiceInstance());
    }

    @Override
    public Context getCurrentContext() {
        return getContext();
    }

    @Override
    public VideoViewModelFactory getFactory() {
        return new VideoViewModelFactory();
    }

    @Override
    public void onFragmentReady() {

        initYouTubePlayerView();
    }

    private void initYouTubePlayerView() {
        // The player will automatically release itself when the fragment is destroyed.
        // The player will automatically pause when the fragment is stopped
        // If you don't add YouTubePlayerView as a lifecycle observer, you will have to release it manually.
        getLifecycle().addObserver(binding.youtubePlayerView);

        binding.youtubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                //setPlayNextVideoButtonClickListener(youTubePlayer);

                YouTubePlayerUtils.loadOrCueVideo(
                        youTubePlayer, getLifecycle(),
                        VideoIdsProvider.getNextVideoId(),0f
                );
            }
        });


    }
}
