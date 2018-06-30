package com.laughing.android.myapplication.common;

/**
 * Created by laughing on 2015/12/11.
 * <p/>
 * 配置程序当中所有的接口请求地址
 */
public class AppNetConfig {

//    public static final String HOST = "http://192.168.23.1:8080/p2p/index.json";
    public static final String HOST = "http://192.168.23.1";//之前项目主机地址

    public static final String BASE_URL = HOST + ":8080/p2p/";

    public static final String LOGIN = BASE_URL +"login.json";

    public static final String PRODUCT = BASE_URL +"product.json";

    public static final String INDEX  = BASE_URL +"index.json";



}
