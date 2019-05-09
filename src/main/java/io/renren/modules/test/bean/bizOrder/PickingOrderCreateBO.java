package io.renren.modules.test.bean.bizOrder;


import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
public class PickingOrderCreateBO {

    @Valid
    private List<PickingOrderCreateDetailBO> pickingOrderDetails;

    private Long id;

    @NotBlank
    @Length(max = 255)
    private String pickingOrderNumber;

    private Long active;

    private Long tenantId;

    @NotBlank
    @Length(max = 50)
    private String ownerCode;

    @NotBlank
    @Length(max = 255)
    private String externalId;


    @NotBlank
    @Length(max = 100)
    private String orderType;

    @Length(max = 50)
    private String state;

    @Length(max = 100)
    private String outOfStockFlag;

    @Length(max = 100)
    private String urgentFlag;

    @Length(max = 100)
    private String pickingAvailableFlag;

    @Length(max = 50)
    private String priorityType;

    private Integer priorityValue;

    @NotNull
    private Long pickingOrderGroupId;

    private String pickingOrderGroupCode;

    private Date orderDate;

    private Date shipDeadline;

    private Date doneDate;

    private Integer splittable;

    private Long stationId;

    private Long stationSlotId;

    private Integer workCount;

    @Length(max = 500)
    private String remark;

    @Length(max = 255)
    private String udf1;

    @Length(max = 255)
    private String udf2;

    @Length(max = 255)
    private String udf3;

    @Length(max = 255)
    private String udf4;

    @Length(max = 255)
    private String udf5;

    @NotNull
    private Long zoneId;

    @Length(max = 50)
    private String zoneCode;

    private Long warehouseId;








    String jsonContent(PickingOrderCreateBO pickingOrderCreateBO,int goodsScope, int goodsAmount){

        return JSON.toJSONString(pickingOrderCreateBO);
    }
}
