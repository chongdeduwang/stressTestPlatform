package io.renren.modules.test.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

public class DataManEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Setter
    @Getter
    private long id;
    @Setter
    @Getter
    @NotBlank(message = "数据实例名不能为空")
    private String dataName;
    /**
     * 备注
     */
    @Setter
    @Getter
    private String remark;
    /**
     * 操作人
     */
    @Setter
    @Getter
    @NotBlank(message="操作人不能为空")
    private String operator;

    /**
     * 用例进行的状态  0：未执行(停止状态)  1：正在执行
     */
    @Setter
    @Getter
    private Integer status = 0;
    /**
     * 提交数据的人
     */
    @Setter
    @Getter
    private String addBy;
    /**
     * 修改数据的人
     */
    @Setter
    @Getter
    private String updateBy;
    /**
     * 提交的时间
     */
    @Setter
    @Getter
    private Date addTime;
    /**
     * 更新的时间
     */
    @Setter
    @Getter
    private Date updateTime;
    @Setter
    @Getter
    private String templateName;

    @Setter
    @Getter
    @NotNull(message = "数据生成方式需要配置")
    private  int templateId;
    @Setter
    @Getter
    @NotBlank( message = "未配置数据生成方式")
    private String content;
}
