package io.renren.modules.test.entity;


import io.renren.datasources.annotation.DataSource;
import lombok.Data;

@Data
public class OrderManEntity {

    private String interfaceUrl;

    private DataManEntity dataManEntity;
    private DataSourceEntity dataSourceEntity;
}
