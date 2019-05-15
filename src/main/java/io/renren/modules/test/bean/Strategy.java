package io.renren.modules.test.bean;

import java.sql.Connection;

public interface Strategy {

    void generateOrder(String url,Connection connection);

    void stopGenerate();


}
