package com.example.duhongwang20180601.bean;

/**
 * Created   by   Dewey .
 * 自定义实体类VpBean,保存图片和url
 */

public class VpBean {
    private String img;
    private String desc;

    public VpBean(String img, String desc) {
        this.img = img;
        this.desc = desc;
    }


    public VpBean() {
        super();
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
                "img='" + img + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public VpBean(String img) {
        this.img = img;
    }
}
