package io.renren.modules.test.bean;



import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 数据连接的自封装，屏蔽了 close 方法
 *
 * @author Liudong
 */
