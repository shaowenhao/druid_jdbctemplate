package jdbctemplate;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.testng.annotations.Test;
import utils.JDBCUtils;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *     Create Time: 2022年02月17日 16:13
 * </p>
 *
 * Spring框架对JDBC的简单封装
 * 1.导入jar
 * 2.创建JdbcTemplate对象 依赖于DataSource
 * JdbcTemplate template = new JdbcTemplate(ds);
 * 3.调用JdbcTemplate的方法来完成CRUD
 *
 * update() 执行 真删改
 * queryForMap() 查询结果集封装为map集合
 * queryForList() 查询结果集封装为list集合
 * query() 查询结果 封装为JavaBean对象
 **/
public class jdbcdemo {

  // 1 导包
  // 2 创建JdbcTemplate对象
  DataSource ds = JDBCUtils.getDataSource();
  JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);

  @Test
  public void test1(){

    // 3 调用方法
    String sql = "update account set balance = 5000 where id = ?";
    int count = jdbcTemplate.update(sql, 3);
    System.out.println(count);
    // 自动化建立连接 释放资源 大大简化
  }


  /**查询结果封装为Map
   * 查询到的结果  该方法查询的结果集长度只能是 1
   * {id=50350672863941ff36ea38875660325d, subsentence=subscription { SensorData (cond: "{Siid:{_in:[34159,37879,34153,34135,34155,33024,34133,34143,34149,34129,34131,34141,36012]}}") {Siid updateTime value } }, replyto=50350672863941ff36ea38875660325d, entities={SensorData}, active=true, clients={iEMS}}
   */
  @Test
  public void test2(){
       String sql = "select * from subscription where id = ?";
        Map<String, Object> map = jdbcTemplate.queryForMap(sql, "50350672863941ff36ea38875660325d");
        System.out.println(map);
  }

  /**
   * 查询所有记录封装为 list集合
   * {id=50350672863941ff36ea38875660325d, subsentence=subscription { SensorData (cond: "{Siid:{_in:[34159,37879,34153,34135,34155,33024,34133,34143,34149,34129,34131,34141,36012]}}") {Siid updateTime value } }, replyto=50350672863941ff36ea38875660325d, entities={SensorData}, active=true, clients={iEMS}}
   * {id=0a8d6f26ea564dff414b6784340bca3d, subsentence=subscription {HeatPumpKpiData(cond: "{deviceName:{_in:[\"1#\u5236\u51b7\u673a\",\"3#\u5236\u51b7\u673a\"]}}") {deviceName updateTime compressor_poly_efficiency condensor_hot_outlet_pressure condensor_hot_outlet_temperature condensor_hot_sat_temperature condensor_water_inlet_temperature condensor_water_outlet_temperature dt_condensor dt_evaporator evaporator_cold_inlet_pressure evaporator_cold_inlet_temperature evaporator_heat evaporator_water_back_temperature evaporator_water_leave_temperature lubricate_press_diff motor_current_percent motor_work oil_tank_press_high oil_tank_press_low run_time start_up_time}}, replyto=0a8d6f26ea564dff414b6784340bca3d, entities={HeatPumpKpiData}, active=true, clients={iEMS}}
   * {id=1944b76fb4fc766c451a9d8de43488d9, subsentence=subscription { SensorData (cond: "{Siid:{_in:[34155,34159,34155]}}") {Siid updateTime value } }, replyto=1944b76fb4fc766c451a9d8de43488d9, entities={SensorData}, active=true, clients={iEMS}}
   */
  @Test
  public void test3(){
    String sql = "select * from subscription ";
    List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
    for (Map<String, Object> map : maps) {
      System.out.println(map);
    }
  }

  /**
   * 查询结果封装为 Sub对象的list集合
   * Subscription{id='1944b76fb4fc766c451a9d8de43488d9', subsentence='subscription { SensorData (cond: "{Siid:{_in:[34155,34159,34155]}}") {Siid updateTime value } }', replyto='1944b76fb4fc766c451a9d8de43488d9', entities='{SensorData}', active=true, clients='{iEMS}'}
   * Subscription{id='50350672863941ff36ea38875660325d', subsentence='subscription { SensorData (cond: "{Siid:{_in:[34159,37879,34153,34135,34155,33024,34133,34143,34149,34129,34131,34141,36012]}}") {Siid updateTime value } }', replyto='50350672863941ff36ea38875660325d', entities='{SensorData}', active=true, clients='{iEMS}'}
   * Subscription{id='0a8d6f26ea564dff414b6784340bca3d', subsentence='subscription {HeatPumpKpiData(cond: "{deviceName:{_in:[\"1#\u5236\u51b7\u673a\",\"3#\u5236\u51b7\u673a\"]}}") {deviceName updateTime compressor_poly_efficiency condensor_hot_outlet_pressure condensor_hot_outlet_temperature condensor_hot_sat_temperature condensor_water_inlet_temperature condensor_water_outlet_temperature dt_condensor dt_evaporator evaporator_cold_inlet_pressure evaporator_cold_inlet_temperature evaporator_heat evaporator_water_back_temperature evaporator_water_leave_temperature lubricate_press_diff motor_current_percent motor_work oil_tank_press_high oil_tank_press_low run_time start_up_time}}', replyto='0a8d6f26ea564dff414b6784340bca3d', entities='{HeatPumpKpiData}', active=true, clients='{iEMS}'}
   */
  @Test
  public void test4(){
    String sql = "select * from subscription ";
    List<Subscription> subscriptionList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Subscription>(Subscription.class));
    for (Subscription subscription : subscriptionList) {
      System.out.println(subscription);
    }
  }

  /**
   * 查询记录数
   */
  @Test
  public void test5(){
    String sql = "select count(id) from subscription";
    Long count = jdbcTemplate.queryForObject(sql, Long.class);
    System.out.println(count);
  }
}
