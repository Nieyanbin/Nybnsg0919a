package com.example.dell.nybnsg.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by dell on 2017/9/13.
 */
@Entity
public class Greendaobean {
    @Id(autoincrement = true)
    private Long id;
    @Property
    public String url;
    public String name;
    public float money;
    public String num;
    @Generated(hash = 1662684681)
    public Greendaobean(Long id, String url, String name, float money, String num) {
        this.id = id;
        this.url = url;
        this.name = name;
        this.money = money;
        this.num = num;
    }
    @Generated(hash = 264190974)
    public Greendaobean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public float getMoney() {
        return this.money;
    }
    public void setMoney(float money) {
        this.money = money;
    }
    public String getNum() {
        return this.num;
    }
    public void setNum(String num) {
        this.num = num;
    }

}
