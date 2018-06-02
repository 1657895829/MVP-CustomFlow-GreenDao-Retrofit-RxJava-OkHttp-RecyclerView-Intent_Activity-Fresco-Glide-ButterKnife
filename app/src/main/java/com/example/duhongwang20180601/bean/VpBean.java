package com.example.duhongwang20180601.bean;

/**
 * Created   by   Dewey .
 * 自定义实体类VpBean,保存图片和url
 */

public class VpBean {
    private String desc;

    public VpBean(String desc) {
        this.desc = desc;
    }


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "VpBean{" +
                "desc='" + desc + '\'' +
                '}';
    }
}
