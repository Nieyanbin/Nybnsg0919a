package com.example.dell.nybnsg.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by dell on 2017/9/6.
 */
public class Registerbean {
    public int id;
      public String zhanghao;
    public String pwd;
    public String zcpwd;
    public String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getZhanghao() {
        return zhanghao;
    }

    public void setZhanghao(String zhanghao) {
        this.zhanghao = zhanghao;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getZcpwd() {
        return zcpwd;
    }

    public void setZcpwd(String zcpwd) {
        this.zcpwd = zcpwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
