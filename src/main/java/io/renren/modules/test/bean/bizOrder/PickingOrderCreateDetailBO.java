package io.renren.modules.test.bean.bizOrder;





import io.swagger.annotations.ApiModelProperty;

import lombok.Data;


import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;



import javax.validation.constraints.NotNull;


@Data
public class PickingOrderCreateDetailBO{

    private Long pickingOrderId;

    private Long tenantId;

    @Length(max = 50)
    @NotBlank
    private String ownerCode;

    @NotNull
    private Long skuId;

    private Long lotId;

    private Long packId;

    @Length(max = 100)
    private String packCode;

    @Length(max = 50)
    private String state;

    @NotNull
    private Integer quantity;

    private Integer fulfillQuantity;

    @Length(max = 255)
    private String lotAtt01;

    @Length(max = 255)
    private String lotAtt02;

    @Length(max = 255)
    private String lotAtt03;

    @Length(max = 255)
    private String lotAtt04;

    @Length(max = 255)
    private String lotAtt05;

    @Length(max = 255)
    private String lotAtt06;

    @Length(max = 255)
    private String lotAtt07;

    @Length(max = 255)
    private String lotAtt08;

    @Length(max = 255)
    private String lotAtt09;

    @Length(max = 255)
    private String lotAtt10;

    @Length(max = 255)
    private String lotAtt11;

    @Length(max = 255)
    private String lotAtt12;

    private Long zoneId;

    private String zoneCode;


}

