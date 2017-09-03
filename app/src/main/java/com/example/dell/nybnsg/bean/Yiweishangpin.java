package com.example.dell.nybnsg.bean;

import java.util.List;

/**
 * Created by dell on 2017/9/2.
 */
public class Yiweishangpin extends Basebean{

    /**
     * code : 200
     * datas : {"class_list":[{"gc_id":"4","gc_name":"女装"},{"gc_id":"5","gc_name":"男装"},{"gc_id":"6","gc_name":"内衣"},{"gc_id":"7","gc_name":"运动"},{"gc_id":"8","gc_name":"女鞋"},{"gc_id":"9","gc_name":"男鞋"},{"gc_id":"10","gc_name":"配饰"},{"gc_id":"11","gc_name":"童装"}]}
     */

    public DatasBean datas;

    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        public List<ClassListBean> class_list;

        public List<ClassListBean> getClass_list() {
            return class_list;
        }

        public void setClass_list(List<ClassListBean> class_list) {
            this.class_list = class_list;
        }


    }
}
