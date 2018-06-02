package com.example.duhongwang20180601.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created   by   Dewey .
 * 搜索框输入数据封装为 GreenDao  bean 类
 */
@Entity
public class SearchDaoBean {
    @Id(autoincrement = true)
    private Long id;
    private String uid;
    private String uname;
    private String selectGoods;
    @Generated(hash = 1024397953)
    public SearchDaoBean(Long id, String uid, String uname, String selectGoods) {
        this.id = id;
        this.uid = uid;
        this.uname = uname;
        this.selectGoods = selectGoods;
    }
    @Generated(hash = 1406499419)
    public SearchDaoBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUid() {
        return this.uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
    public String getUname() {
        return this.uname;
    }
    public void setUname(String uname) {
        this.uname = uname;
    }
    public String getSelectGoods() {
        return this.selectGoods;
    }
    public void setSelectGoods(String selectGoods) {
        this.selectGoods = selectGoods;
    }


}
