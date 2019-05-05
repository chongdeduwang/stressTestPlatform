package io.renren.modules.test.entity;


import lombok.Data;

import java.util.Date;

@Data
public class TemplateEntity {
    private int templateId;
    private String templateName;
    private String templateType;
    private int Status;
    private String content;
    private String addBy;
    private String updateBy;
    private Date updateTime;
    private Date addTime;
}
