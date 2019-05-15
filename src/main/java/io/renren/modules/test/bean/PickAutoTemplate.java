package io.renren.modules.test.bean;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.fastjson.JSON;
import io.renren.modules.test.bean.bizOrder.PickingOrderCreateBO;
import io.renren.modules.test.bean.bizOrder.PickingOrderCreateDetailBO;
import io.renren.modules.test.entity.DataSourceEntity;
import io.renren.modules.test.entity.GoodsInfoEntity;
import io.renren.modules.test.entity.ResultSetMapper;
import io.renren.modules.test.utils.HttpUtil;
import ucar.httpservices.HTTPUtil;

import java.sql.*;
import java.util.*;
import java.util.Date;
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
    public void generateOrder(String interfaceurl,Connection cn) {
        threadpool = new ScheduledThreadPoolExecutor(Math.toIntExact(getOrderAmountPer()));
        ResultSetMapper<GoodsInfoEntity> resultSetMapper = new ResultSetMapper<>();
        List<GoodsInfoEntity> goodsAll = new ArrayList<>();
        if (getGoodsKindsScope() !=0){
            String goodsInfo= "SELECT a.sku_id,a.pack_id, a.owner_code, a.lot_id, a.quantity, b.zone_code, c.id as zone_id " +
                    "FROM evo_wes_inventory.level3_inventory a, evo_wes_inventory.level2_inventory b, evo_basic.basic_zone c " +
                    "WHERE a.sku_id = b.sku_id and b.zone_code = c.zone_code ";
            System.out.println(goodsInfo);
            ResultSet rs = queryBySql(goodsInfo,cn);
            goodsAll=resultSetMapper.mapRersultSetToObject(rs,GoodsInfoEntity.class);
        }


        for (int x = 0;x<getOrderAmountPer();x++ ){
            List<GoodsInfoEntity> finalGoodsAll = goodsAll;
            Runnable orderThread = () -> {

                List<PickingOrderCreateBO> orderCreateBOS = new ArrayList<>();
                PickingOrderCreateBO p = new PickingOrderCreateBO();
//                p.setPickingOrderNumber(String.valueOf(genJobID()));
                p.setPickingOrderNumber(String.valueOf(UUID.randomUUID()));
                p.setDoneDate(new Date());
                p.setId(Long.valueOf(1));
                p.setOrderDate(new Date());
                p.setOrderType("PURCHASE");
                p.setOwnerCode("KC");
                List<GoodsInfoEntity> list = new ArrayList<>();
                Random rd = new Random();
                list = getListUnique(finalGoodsAll, rd.nextInt(Math.toIntExact(getGoodsKindsScope())+1));
                List detailList = new ArrayList<>();

                for (int i = 0;i<list.size();i++){


                    PickingOrderCreateDetailBO detailBO = new PickingOrderCreateDetailBO();

                    detailBO.setFulfillQuantity(0);
                    detailBO.setOwnerCode("KC");
                    detailBO.setLotId(list.get(i).getLotId());
                    detailBO.setQuantity(rd.nextInt(Math.toIntExact(getGoodsAmountScope())));
                    detailBO.setPackId(list.get(i).getPackId());
                    detailBO.setTenantId(0L);
                    detailBO.setZoneCode(list.get(i).getZoneCode());
                    detailBO.setSkuId(list.get(i).getSkuId());
                    detailBO.setZoneId(list.get(i).getZoneId());
                    detailBO.setPickingOrderId(0L);
                    detailList.add(detailBO);

                }
                p.setPickingOrderDetails(detailList);
                orderCreateBOS.add(p);
                String json = JSON.toJSONString(orderCreateBOS);
                System.out.println("json: "+json);
                try {
                    String response = HttpUtil.doPost(interfaceurl,json);
                    System.out.println(response);
                } catch (Exception e) {
                    e.printStackTrace();
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
    public ResultSet queryBySql(String sql,Connection con) {
        Statement sttm = null;
        ResultSet rs = null;
        try {
            sttm = con.createStatement();
            rs = sttm.executeQuery(sql);
            //System.out.println("当前连接编号是:" + connName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

//    @Override
//    public Connection getConnection(String url, String user, String pwd) throws SQLException {
//        return null;
//
//    }
}
