package io.renren.modules.test.entity;


import lombok.Data;

@Data
public class DataSourceEntity {
    //数据库驱动程序
    private String driver;
    //数据连接的URL
    private String url;
    //数据库用户名
    private String user;
    //数据库密码
    private String password;
    //初始化连接数
    private int minConnection = 0;
    private int initConnectionSqls;
    //最大连接数
    private int maxConnection = 50;
    private int maxActive;
    //连接的最大空闲时间
    private long timeoutValue = 600000;
    private long maxIdle;
    //取连接的时候如果没有可用连接最大的等待时间
    private long waitTime = 30000;
    private long maxWait;




}
