package io.renren.modules.test.bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ScheduledExecutorService;

public abstract class DataTemplate implements Strategy, DataSourceManage{
    public long genJobID() {
//        int r1 = (int) (Math.random() * (9) + 1);//产生2个0-9的随机数
//        int r2 = (int) (Math.random() * (10));

        SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyMMddHHmmssSSS");
        String format = yyyyMMddHHmmss.format(new Date());
        return Long.parseLong(format);

    }
}
