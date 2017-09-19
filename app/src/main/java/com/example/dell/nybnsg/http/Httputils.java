package com.example.dell.nybnsg.http;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.example.dell.nybnsg.bean.Basebean;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.Map;

/**
 * Created by dell on 2017/8/31.
 */
public class Httputils {
    private static Httputils httputils;
    private final Gson gson;
private Context context;
    private Httputils(Context context){
        gson = new Gson();
        this.context=context;
    }
    public static Httputils httputils(Context context){
        if(httputils==null){
    synchronized (Httputils.class){
        if(httputils==null){
            httputils=new Httputils(context);
        }
    }
        }
        return httputils;

    }
/**
 * post请求
 */
    public void post(String url,Map<String,String> params, final Class clzz, final NetListnter netListnter){
        PostFormBuilder postfb = OkHttpUtils.post().url(url);
        for (Map.Entry<String,String> entry: params.entrySet()) {
           postfb.addParams(entry.getKey(),entry.getValue());

        }
postfb.build().execute(new StringCallback() {
    @Override
    public void onError(Request request, Exception e) {

    }

    @Override
    public void onResponse(String response) {
        Basebean bean = gson.fromJson(response, Basebean.class);
        String code = bean.getCode();
        if(code.equals("400")){
            AlertDialog.Builder ab=new AlertDialog.Builder(context);
            ab.setTitle("标题");
            ab.setMessage("网络错误，请重新加载");
        }else{
            if(netListnter!=null){
                Basebean basebean = (Basebean) gson.fromJson(response, clzz);
                netListnter.onSuccess(basebean);
            }
        }
    }
});
    }
    /**
     * get请求
     * @param url
     */
    public void get(String url, Map<String,String> params){
        StringBuffer sb=new StringBuffer();
        sb.append(url);
        sb.append("?");
        for (Map.Entry<String,String> entry: params.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            sb.append(entry.getValue());
            sb.append("&");

        }
        OkHttpUtils.get().url(sb.toString());
    }


    public void get(String url, final Class clzz, final NetListnter netListnter){
   OkHttpUtils.get().url(url).build().execute(new StringCallback() {
       @Override
       public void onError(Request request, Exception e) {
           Log.d("Httputils","onError");
       }

       @Override
       public void onResponse(String response) {
           Log.d("Httputils",response);
           Basebean bean = gson.fromJson(response, Basebean.class);
           String code = bean.getCode();
           if(code.equals("400")){
               AlertDialog.Builder ab=new AlertDialog.Builder(context);
               ab.setTitle("标题");
               ab.setMessage("网络错误，请重新加载");
           }else if(code.equals("200")){
                if(netListnter!=null){
                    Basebean basebean = (Basebean) gson.fromJson(response, clzz);
                    netListnter.onSuccess(basebean);
                }
           }
       }
   });

    }

    /**
     * get请求一维数组里的东西
     * @param gc_id
     * @param clzz
     * @param netListnter
     */
//    public void get(String gc_id, final Class clzz, final NetListnter netListnter){
////        StringBuffer sb=new StringBuffer();
//        String url="http://169.254.41.208/mobile/index.php?act=goods_class";
//        sb.append(url);
//        sb.append("&");
//        sb.append("gc_id=");
//        sb.append(gc_id);
//        Log.e("asasadasda","asdadadadadada"+sb.toString());
//       OkHttpUtils.get().url(sb.toString()).build().execute(new StringCallback() {
//           @Override
//           public void onError(Request request, Exception e) {
//
//           }
//
//           @Override
//           public void onResponse(String response) {
//               Basebean bean = gson.fromJson(response, Basebean.class);
//               String code = bean.getCode();
//               if(code.equals("400")){
//                   AlertDialog.Builder ab=new AlertDialog.Builder(context);
//                   ab.setTitle("标题");
//                   ab.setMessage("网络错误，请重新加载");
//               }else if(code.equals("200")){
//                   if(netListnter!=null){
//                       Basebean basebean = (Basebean) gson.fromJson(response, clzz);
//                       netListnter.onSuccess(basebean);
//                   }
//               }
//           }
//       });
//
//    }
}
