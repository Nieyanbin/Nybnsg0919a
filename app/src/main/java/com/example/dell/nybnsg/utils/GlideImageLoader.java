package com.example.dell.nybnsg.utils;


import android.content.Context;
import android.widget.ImageView;

import com.example.dell.nybnsg.http.App;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by dell on 2017/9/8.
 */
public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        App app= (App) context.getApplicationContext();
        app.getImageLoader().displayImage(path.toString(),imageView);
    }
}
