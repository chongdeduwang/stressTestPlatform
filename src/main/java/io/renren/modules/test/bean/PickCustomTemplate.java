package io.renren.modules.test.bean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PickCustomTemplate extends DataTemplate {
    private String customer;
    private long goodsKindsScope;
    private long goodsAmountScope;
    private long orderAmount;
    private boolean assembleOrderGen;
    private AssembleOrder assembleOrder;
    private boolean ignoreInvenExce;
    private boolean InvenExceGenerRepl;
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

    public void setOrderAmount(long orderAmount) {
        this.orderAmount = orderAmount;
    }
    public long getOrderAmount() {
        return orderAmount;
    }

    public void setAssembleOrderGen(boolean assembleOrderGen) {
        this.assembleOrderGen = assembleOrderGen;
    }
    public boolean getAssembleOrderGen() {
        return assembleOrderGen;
    }

    public void setAssembleOrder(AssembleOrder assembleOrder) {
        this.assembleOrder = assembleOrder;
    }
    public AssembleOrder getAssembleOrder() {
        return assembleOrder;
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
    public void generateOrder(long id,String url, Connection connection) {

    }

    @Override
    public void stopGenerate() {

    }

    @Override
    public ResultSet queryBySql(String sql, Connection con) {
        return null;
    }
}
