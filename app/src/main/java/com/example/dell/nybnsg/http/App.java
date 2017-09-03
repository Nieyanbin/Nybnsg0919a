package com.example.dell.nybnsg.http;

import android.app.Application;

import com.example.dell.nybnsg.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.xutils.DbManager;
import org.xutils.x;

/**
 * Created by dell on 2017/8/31.
 */
public class App extends Application{
    private Httputils httputils;
    private DbManager db1;

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
        DbManager.DaoConfig db=new DbManager.DaoConfig()
                .setDbName("nyb.db")
                .setAllowTransaction(true)
                .setDbVersion(1);
        db1 = x.getDb(db);
    init();
        httputils=Httputils.httputils(this);
    }

    public DbManager getDb1() {
        return db1;
    }

    public Httputils getHttputils() {
        return httputils;
    }

private void init() {
        DisplayImageOptions options=new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .showImageOnLoading(R.mipmap.ic_launcher)
                .build();

       ImageLoaderConfiguration con=new ImageLoaderConfiguration.Builder(this)
               .defaultDisplayImageOptions(options)
               .build();

        ImageLoader.getInstance().init(con);

    }

}
