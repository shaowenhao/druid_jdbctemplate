package utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.security.Permission;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * <p>Create Time: 2022年02月17日 15:23          </p>
 * Druid连接池工具类
 **/
public class JDBCUtils {
    //定义成员变量 DataSource
    private static DataSource ds = null;

    static{
        try {
            // 1. 加载配置文件
            Properties pro = new Properties();
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            pro.load(is);
            // 2. 获取DataSource
             ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
          return ds.getConnection();
    }

    /**
     * 释放资源
     */
    public static void closeResource(Statement stmt, Connection con){
       if(stmt != null){
           try {
               stmt.close();
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
        if(con != null){
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取连接池方法
     */
    public static DataSource getDataSource(){
        return ds;
    }
}
