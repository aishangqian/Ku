package com.bawei.xff.weidu.utils;


public interface OkhttpCallback {
    void failure(String msg);//服务器请求失败：断网，服务器宕机等等因素
    void success(String result);//数据合法
}
