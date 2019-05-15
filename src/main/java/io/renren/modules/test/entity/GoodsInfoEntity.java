package io.renren.modules.test.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
public class GoodsInfoEntity {
    @Column(name="sku_id")
    private long skuId;
    @Column(name="owner_code")
    private String ownerCode;
    @Column(name="lot_id")
    private long lotId;
    @Column(name="quantity")
    private int quantity;
    @Column(name="zone_code")
    private String zoneCode;
    @Column(name="pack_id")
    private long packId;
    @Column(name="id")
    private long zoneId;




}
