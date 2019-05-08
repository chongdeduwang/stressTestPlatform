package io.renren.modules.test.bean;

import java.util.concurrent.*;

public class PickAutoTemplate extends DataTemplate {

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
        LinkedBlockingQueue queue= new LinkedBlockingQueue();
        ThreadPoolExecutor pool = new ThreadPoolExecutor(1,);
    }
}
