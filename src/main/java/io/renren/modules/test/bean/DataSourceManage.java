package io.renren.modules.test.bean;

import java.sql.Connection;
import java.sql.SQLException;

public interface DataSourceManage {

    Connection getConnection(String url, String user,String pwd) throws SQLException;
//    { return DriverManager.getConnection(url, username, password); }


}
