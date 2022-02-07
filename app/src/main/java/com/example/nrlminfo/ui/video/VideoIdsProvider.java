package com.example.nrlminfo.ui.video;

import java.util.Random;

public class VideoIdsProvider {

    private static String[] videoIds = {"3vku3RvlAdc", "3vku3RvlAdc", "3vku3RvlAdc", "3vku3RvlAdc"};
    private static String[] liveVideoIds = {"3vku3RvlAdc"};
    private static Random random = new Random();

    public static String getNextVideoId() {
        return videoIds[random.nextInt(videoIds.length)];
    }

    public static String getNextLiveVideoId() {
        return liveVideoIds[random.nextInt(liveVideoIds.length)];
    }
}
