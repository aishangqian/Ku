package com.bawei.xff.weidu.entity;

import java.util.List;

public class ImageEntity {
    public String message;
    public String status;
    public List<ResultBean> result;
    public static class ResultBean {
        public String imageUrl;
        public String jumpUrl;
        public int rank;
        public String title;
    }
}
