package com.example.lenovo.fengyue0102.app;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class Image extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderConfiguration image=ImageLoaderConfiguration.createDefault(this);
        ImageLoader.getInstance().init(image);
    }
}
