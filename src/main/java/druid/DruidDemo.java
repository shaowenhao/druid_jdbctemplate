package druid;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * <p>Create Time: 2022年02月17日 14:11</p>
 **/
public class DruidDemo {
    public static void main(String[] args) {
        //1. 导入jar包 druid 1.0.9
        Properties props = new Properties();
        InputStream is = DruidDemo.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
            props.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取连接池对象
        try {
            DataSource ds = DruidDataSourceFactory.createDataSource(props);
            //获取连接
            Connection connection = ds.getConnection();
            System.out.println(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
