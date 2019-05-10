package io.renren.modules.test.bean;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import io.renren.modules.test.bean.bizOrder.PickingOrderCreateBO;
import io.renren.modules.test.entity.DataSourceEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class PickAutoTemplate extends DataTemplate {

    private ScheduledExecutorService threadpool;


    private long gap;
    private long orderAmountPer;
    private String customer;
    private long nonExeOrderMax;
    private long nonExeOrderMin;
    private boolean bucketPriGoods;
    private boolean preSetInven;
    private boolean onlyBatchGoods;
    private boolean goodsOnForbidBucket;
    private long goodsKindsScope;
    private long goodsAmountScope;
    private boolean ignoreInvenExce;
    private boolean InvenExceGenerRepl;
    public void setGap(long gap) {
        this.gap = gap;
    }
    public long getGap() {
        return gap;
    }

    public void setOrderAmountPer(long orderAmountPer) {
        this.orderAmountPer = orderAmountPer;
    }
    public long getOrderAmountPer() {
        return orderAmountPer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }
    public String getCustomer() {
        return customer;
    }

    public void setNonExeOrderMax(long nonExeOrderMax) {
        this.nonExeOrderMax = nonExeOrderMax;
    }
    public long getNonExeOrderMax() {
        return nonExeOrderMax;
    }

    public void setNonExeOrderMin(long nonExeOrderMin) {
        this.nonExeOrderMin = nonExeOrderMin;
    }
    public long getNonExeOrderMin() {
        return nonExeOrderMin;
    }

    public void setBucketPriGoods(boolean bucketPriGoods) {
        this.bucketPriGoods = bucketPriGoods;
    }
    public boolean getBucketPriGoods() {
        return bucketPriGoods;
    }

    public void setPreSetInven(boolean preSetInven) {
        this.preSetInven = preSetInven;
    }
    public boolean getPreSetInven() {
        return preSetInven;
    }

    public void setOnlyBatchGoods(boolean onlyBatchGoods) {
        this.onlyBatchGoods = onlyBatchGoods;
    }
    public boolean getOnlyBatchGoods() {
        return onlyBatchGoods;
    }

    public void setGoodsOnForbidBucket(boolean goodsOnForbidBucket) {
        this.goodsOnForbidBucket = goodsOnForbidBucket;
    }
    public boolean getGoodsOnForbidBucket() {
        return goodsOnForbidBucket;
    }

    public void setGoodsKindsScope(long goodsKindsScope) {
        this.goodsKindsScope = goodsKindsScope;
    }
    public long getGoodsKindsScope() {
        return goodsKindsScope;
    }

    public void setGoodsAmountScope(long goodsAmountScope) {
        this.goodsAmountScope = goodsAmountScope;
    }
    public long getGoodsAmountScope() {
        return goodsAmountScope;
    }

    public void setIgnoreInvenExce(boolean ignoreInvenExce) {
        this.ignoreInvenExce = ignoreInvenExce;
    }
    public boolean getIgnoreInvenExce() {
        return ignoreInvenExce;
    }

    public void setInvenExceGenerRepl(boolean InvenExceGenerRepl) {
        this.InvenExceGenerRepl = InvenExceGenerRepl;
    }
    public boolean getInvenExceGenerRepl() {
        return InvenExceGenerRepl;
    }

    @Override
    public void generateOrder() {

        threadpool = new ScheduledThreadPoolExecutor(Math.toIntExact(getOrderAmountPer()));

        List goodsAll = new ArrayList();
        if (getGoodsKindsScope() !=0){

//            List goodsList=database.getGoods();
        }


        for (int x = 0;x<getOrderAmountPer();x++ ){
            Runnable orderThread = new Runnable() {
                @Override
                public void run() {
                    List list = new ArrayList();
                    list = getListUnique(goodsAll, Math.toIntExact(getGoodsKindsScope()));
                    PickingOrderCreateBO
                }
            };
            threadpool.scheduleAtFixedRate(orderThread, 0,getGap(), TimeUnit.SECONDS);
        }

//        threadpool.schedule()
    }

    @Override
    public void stopGenerate() {
        while (threadpool.isShutdown()){
            threadpool.shutdown();
        }
    }


    public static List getListUnique(List sources, int num) {

        if (sources.size() <= num) {
            return sources;
        }

        // 复制一份，以免对原数据造成影响
        List _sources = new ArrayList(sources);

        List targetList = new ArrayList(num);
        Random random = new Random();
        for (int k = 0; k < num; k++) {
            int i = random.nextInt(_sources.size() - k);
            Object tmp = _sources.get(i);
            targetList.add(tmp);
            // 取完后，把list size - k 的元素 放到本次取到的index位置
            _sources.set(i, _sources.get(_sources.size() - k - 1));

        }
        return targetList;
    }

    @Override
    public Connection getConnection(String url, String user, String pwd) throws SQLException {
        return null;

    }
}
