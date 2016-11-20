/**
 * Created by FDD on 2016/11/19.
 */
package com.util.dbmethod;

import java.sql.*;

/**
 * @author FDD
 * @功能：数据库函数集合
 * @要求：需要引入mysql-jdbc驱动
 */
public class Database {
    private static final String user = "root";// 数据库用户名
    private static final String password = "123";// 数据库密码
    private static String dbname = "shop";// 数据库名

    static PreparedStatement prestate=null;
    private static final String driver = "com.mysql.jdbc.Driver";
    private static Connection connect = null;// 数据库连接
    private static final String url = "jdbc:mysql://localhost:3306/" + dbname
            + "?useUnicode=true&characterEncoding=UTF-8";

    static {
        try {
            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("错误：加载驱动失败，请检查驱动是否加载。错误信息：" + e.getMessage());
        }
    }

    /**
     * @return boolean 检查是否设置了数据库
     */
    public static boolean isSetDbname() {
        if (dbname == null || dbname.equals(""))
            return false;
        else
            return true;
    }

    /**
     * @return Connection 数据库连接对象
     */
    public static Connection getConnection() {
        if (connect == null) {
            try {
                if (isSetDbname())
                    connect = DriverManager.getConnection(url, user, password);
                else {
                    System.out.println("错误：未设置数据库名");
                }
            } catch (SQLException e) {
                System.out.println("错误：数据库连接失败，请检查数据库名，密码，用户名是否正确");
                e.printStackTrace();
            }
            return connect;
        }
        return connect;
    }
    /**
     * @param string sql SQL语句
     * @功能 执行SQL查询
     * @返回 结果集
     */
    public static ResultSet doQuery(String sql){
        if((connect=getConnection())==null)
            return null;
        else{

            try {
                ResultSet resultset = prestate.executeQuery(sql);
                return resultset;
            } catch (SQLException e) {
                System.out.println("错误：sql语句执行出错");
                e.printStackTrace();
            }
        }
        return null;
    }
    /**
     * 下面是示例程序(可以删除)
     */
    public static void main(String[] args) {
        try {
            Connection con = Database.getConnection();
            if (con != null) {
                System.out.println("数据库连接正常");
            } else {
                System.out.println("数据库连接失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}