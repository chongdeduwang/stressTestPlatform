package io.renren.modules.test.entity;


import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;


@Data
public class DataEntity {
    @NotBlank(message="单据类型不能为空")
    private String oderType;
//    private Strategy strategy;
    @NotBlank(message="订单间时间间隔不能为空")
    private long orderGap;
    @NotBlank(message="单次下发订单数量不能为空")
    private long orderAmount;
    @NotBlank(message = "客户需要选择")
    private String customer;
    @NotBlank(message = "最大未处理订单不能为空")
    private long maxOrderNonExe;
    private long minOrderNonExe;
    @NotBlank(message = "商品种类不能为空")
    private String goodsType;
    @NotBlank(message = "商品数量不能为空")
    private long goodsAmount;

    private boolean bucketPri;
    private boolean setInventory;
    private boolean onlyLotGoods;
    private boolean allowForbidBucketGoods;






}
