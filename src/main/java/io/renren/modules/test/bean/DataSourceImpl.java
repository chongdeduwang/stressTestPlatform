package io.renren.modules.test.bean;

import io.renren.modules.test.entity.DataSourceEntity;
import org.hibernate.validator.constraints.NotBlank;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

public class DataSourceImpl implements DataSource {

    private DataSourceEntity connParam;
    private List<_Connection> conns;
    
    
    public DataSourceImpl(DataSourceEntity connParam) {
        this.connParam = connParam;
    }

    public Connection getConnection(String user, String password) throws SQLException
    {
        //首先从连接池中找出空闲的对象
        Connection conn = getFreeConnection(0);
        if(conn == null){
            //判断是否超过最大连接数,如果超过最大连接数
            //则等待一定时间查看是否有空闲连接,否则抛出异常告诉用户无可用连接
            if(getConnectionCount() >= connParam.getMaxConnection())
                conn = getFreeConnection(connParam.getWaitTime());
            else{//没有超过连接数，重新获取一个数据库的连接
                connParam.setUser(user);
                connParam.setPassword(password);
                Connection conn2 = DriverManager.getConnection(connParam.getUrl(),
                        user, password);
                //代理将要返回的连接对象
                _Connection _conn = new _Connection(conn2,true);
                synchronized(conns){
                    conns.add(_conn);
                }
                conn = _conn.getConnection();
            }
        }
        return conn;
    }

    private int getConnectionCount() {
        return
    }

    /**
     * 从连接池中取一个空闲的连接
     * @param nTimeout	如果该参数值为0则没有连接时只是返回一个null
     * 否则的话等待nTimeout毫秒看是否还有空闲连接，如果没有抛出异常
     * @return Connection
     * @throws SQLException
     */
    protected synchronized Connection getFreeConnection(long nTimeout)
            throws SQLException
    {
        Connection conn = null;
        Iterator iter = conns.iterator();
        while(iter.hasNext()){
            _Connection _conn = (_Connection)iter.next();
            if(!_conn.isInUse()){
                conn = _conn.getConnection();
                _conn.setInUse(true);
                break;
            }
        }
        if(conn == null && nTimeout > 0){
            //等待nTimeout毫秒以便看是否有空闲连接
            try{
                Thread.sleep(nTimeout);
            }catch(Exception e){}
            conn = getFreeConnection(0);
            if(conn == null)
                throw new SQLException("没有可用的数据库连接");
        }
        return conn;
    }



    @Override
    public Connection getConnection() throws SQLException {
        return null;
    }


    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    public void initConnection() {


    }


    class _Connection implements InvocationHandler {
        private final static String CLOSE_METHOD_NAME = "close";
        private Connection conn = null;
        // 数据库的忙状态
        private boolean inUse = false;
        // 用户最后一次访问该连接方法的时间
        private long lastAccessTime = System.currentTimeMillis();

        _Connection(Connection conn, boolean inUse) {
            this.conn = conn;
            this.inUse = inUse;
        }

        /**
         * Returns the conn.
         *
         * @return Connection
         */
        public Connection getConnection() {
            // 返回数据库连接 conn 的接管类，以便截住 close 方法
            Connection conn2 = (Connection) Proxy.newProxyInstance(
                    conn.getClass().getClassLoader(),
                    conn.getClass().getInterfaces(), this);
            return conn2;
        }

        /**
         * 该方法真正的关闭了数据库的连接
         *
         * @throws SQLException
         */
        void close() throws SQLException {
            // 由于类属性 conn 是没有被接管的连接，因此一旦调用 close 方法后就直接关闭连接
            conn.close();
        }

        /**
         * Returns the inUse.
         *
         * @return boolean
         */
        public boolean isInUse() {
            return inUse;
        }

        /**
         * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object,
         * java.lang.reflect.Method, java.lang.Object)
         */
        public Object invoke(Object proxy, Method m, Object[] args)
                throws Throwable {
            Object obj = null;
            // 判断是否调用了 close 的方法，如果调用 close 方法则把连接置为无用状态
            if (CLOSE_METHOD_NAME.equals(m.getName()))
                setInUse(false);
            else
                obj = m.invoke(conn, args);
            // 设置最后一次访问时间，以便及时清除超时的连接
            lastAccessTime = System.currentTimeMillis();
            return obj;
        }

        /**
         * Returns the lastAccessTime.
         *
         * @return long
         */
        public long getLastAccessTime() {
            return lastAccessTime;
        }

        /**
         * Sets the inUse.
         *
         * @param inUse The inUse to set
         */
        public void setInUse(boolean inUse) {
            this.inUse = inUse;
        }
    }
}
