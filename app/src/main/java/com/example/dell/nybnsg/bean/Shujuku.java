package com.example.dell.nybnsg.bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by dell on 2017/9/1.
 */
@Table(name="nyb")
public class Shujuku extends Basebean{
    @Column(name = "id", isId = true, autoGen = true)
    public int id;
    @Column(name = "name")
    public String name;
    @Column(name = "pwd")
    public int pwd;
    @Column(name = "zcpwd")
    public int zcpwd;
    @Column(name = "email")
    public String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPwd() {
        return pwd;
    }

    public void setPwd(int pwd) {
        this.pwd = pwd;
    }

    public int getZcpwd() {
        return zcpwd;
    }

    public void setZcpwd(int zcpwd) {
        this.zcpwd = zcpwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
