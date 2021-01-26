package dao.impl;

import dao.UserDao;
import domain.User;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

public class UserDaoImpl implements UserDao{
    /*
   一、 JdbcTemplate是Spring对JDBC的封装，目的是使JDBC更加易于使用。JdbcTemplate是Spring的一部分。
    JdbcTemplate处理了资源的建立和释放。他帮助我们避免一些常见的错误，比如忘了总要关闭连接。
    他运行核心的JDBC工作流，如Statement的建立和执行，而我们只需要提供SQL语句和提取结果。
    在JdbcTemplate中执行SQL语句的方法大致分为3类：
        execute：可以执行所有SQL语句，一般用于执行DDL语句。
        update：用于执行INSERT、UPDATE、DELETE等DML语句。
        queryXxx：用于DQL数据查询语句。
     */


    /*
   二、 JdbcTemplate使用步骤
    1、准备DruidDataSource连接池
    2、导入依赖的jar包
        spring-beans-4.1.2.RELEASE.jar
        spring-core-4.1.2.RELEASE.jar
        spring-jdbc-4.1.2.RELEASE.jar
        spring-tx-4.1.2.RELEASE.jar
        com.springsource.org.apache.commons.logging-1.1.1.jar

     3、创建JdbcTemplate对象，传入Druid连接池
     4、调用execute、update、queryXxx等方法
     */
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public User findByUserName(String username) {
        User user =null;
        try{
            System.out.println(1);

            String sql ="select * from tab_user where username=?";

            /*queryForObject：当需要返回是什么类型，那么就在第三个参数指定什么类型，
            例如：需要返回int类型，就写Integer.class,需要返回long类型就写long.class.
             */
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),username);


            System.out.println(2);
        }catch (Exception e){

        }
        if (user ==null){
            System.out.println("没有用户");
        }
        return user;
    }

    @Override
    public void save(User user) {
        System.out.println(3);
            String sql ="insert into tab_user (username,password,name ,birthday,sex,telephone,email,code,status)"+
                    "values(?,?,?,?,?,?,?,?,?)";
            //执行
        System.out.println(4);
            template.update(sql,user.getUsername(),user.getPassword(),user.getName(),user.getBirthday(),user.getSex(),
                    user.getTelephone(),user.getEmail(),user.getCode(),user.getStatus()
            );
        System.out.println(5);


    }
    //根据激活码查询用户
    @Override
    public User findByCode(String code) {
        String sql ="select * from  tab_user where code =?";
        User user=null;
        try {
             user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), code);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return user ;
    }
    //修改状态
    @Override
    public void updateStatus(User user) {
        String sql ="update tab_user set status='Y' where uid =?";
        template.update(sql,user.getUid());
    }
}
