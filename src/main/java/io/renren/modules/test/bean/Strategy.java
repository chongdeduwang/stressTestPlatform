package io.renren.modules.test.bean;

import java.sql.Connection;

public interface Strategy {

    void generateOrder(long id,String url,Connection connection);

    void stopGenerate();


}
