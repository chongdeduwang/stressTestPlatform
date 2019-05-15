//package io.renren.modules.test.bean;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.sql.DataSource;
//
//import io.renren.config.DynamicRoutingDataSource;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.annotation.MapperScan;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import com.alibaba.druid.pool.DruidDataSourceFactory;
//
///**
// * 多数据源配置
// *
// * @author Taven
// *
// */
//@Configuration
//public class DataSourceConfigurer {
//
//	private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceConfigurer.class);
//
//
//
//
//
//    /**
//     * 注册动态数据源
//     *
//     * @return
//     */
//    @Bean("dynamicDataSource")
//    public DataSource dynamicDataSource() {
//        DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();
//        Map<Object, Object> dataSourceMap = new HashMap<>();
////        dataSourceMap.put("dynamic_db0", dataSource0());
////        dataSourceMap.put("dynamic_db1", dataSource1());
////        dynamicRoutingDataSource.setDefaultTargetDataSource(dataSource0());// 设置默认数据源
//        dynamicRoutingDataSource.setTargetDataSources(dataSourceMap);
//        return dynamicRoutingDataSource;
//    }
//
//    /**
//     * Sql session factory bean.
//     * Here to config datasource for SqlSessionFactory
//     * <p>
//     * You need to add @{@code @ConfigurationProperties(prefix = "mybatis")}, if you are using *.xml file,
//     * the {@code 'mybatis.type-aliases-package'} and {@code 'mybatis.mapper-locations'} should be set in
//     * {@code 'application.properties'} file, or there will appear invalid bond statement exception
//     *
//     * @return the sql session factory bean
//     */
//    @Bean
//    @ConfigurationProperties(prefix = "mybatis")
//    public SqlSessionFactoryBean sqlSessionFactoryBean() {
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        // 必须将动态数据源添加到 sqlSessionFactoryBean
//        sqlSessionFactoryBean.setDataSource(dynamicDataSource());
//        return sqlSessionFactoryBean;
//    }
//
//    /**
//     * 事务管理器
//     *
//     * @return the platform transaction manager
//     */
//    @Bean
//    public PlatformTransactionManager transactionManager() {
//        return new DataSourceTransactionManager(dynamicDataSource());
//    }
//}
