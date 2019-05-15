package io.renren.modules.test.bean;

import lombok.Data;

import java.sql.Connection;
import java.sql.ResultSet;


public class RepCustomTemplate extends DataTemplate {

    private String customer;
    private long goodsKindsScope;
    private long goodsAmountScope;
    private long repOrderAmount;
    private String repOrderState;
    private boolean limitPartAndWhole;
    public void setCustomer(String customer) {
        this.customer = customer;
    }
    public String getCustomer() {
        return customer;
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

    public void setRepOrderAmount(long repOrderAmount) {
        this.repOrderAmount = repOrderAmount;
    }
    public long getRepOrderAmount() {
        return repOrderAmount;
    }

    public void setRepOrderState(String repOrderState) {
        this.repOrderState = repOrderState;
    }
    public String getRepOrderState() {
        return repOrderState;
    }

    public void setLimitPartAndWhole(boolean limitPartAndWhole) {
        this.limitPartAndWhole = limitPartAndWhole;
    }
    public boolean getLimitPartAndWhole() {
        return limitPartAndWhole;
    }



    @Override
    public ResultSet queryBySql(String sql, Connection con) {
        return null;
    }


    @Override
    public void generateOrder(String url, Connection connection) {

    }

    @Override
    public void stopGenerate() {

    }
}
