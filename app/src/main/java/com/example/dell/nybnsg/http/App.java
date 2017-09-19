package com.example.dell.nybnsg.http;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.dell.nybnsg.R;
import com.example.dell.nybnsg.adapter.GouwucheAdapter;
import com.koma.greendao.gen.DaoMaster;
import com.koma.greendao.gen.DaoSession;
import com.koma.greendao.gen.GreendaobeanDao;
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
    private ImageLoader imageLoader;
    private GreendaobeanDao greendaobeanDao;

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
    init();
        httputils=Httputils.httputils(this);

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "lha.db", null);
        DaoMaster daoMaster=new DaoMaster(helper.getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        greendaobeanDao = daoSession.getGreendaobeanDao();
    }

    public GreendaobeanDao getGreendaobeanDao() {
        return greendaobeanDao;
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

    imageLoader= ImageLoader.getInstance();
    imageLoader.init(con);



    }
    public ImageLoader getImageLoader() {
        return imageLoader;
    }
}
