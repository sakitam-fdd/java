/**
 * Created by FDD on 2016/11/20.
 */
//java 用jdbc连接mysql 并执行sql语句
import java.sql.Connection;  //sql连接
import java.sql.DriverManager;//jdbc驱动
import java.sql.SQLException;//sql表达式
import java.sql.Statement;

public class JDBCMySQL {
    public static final String DBDRIVER = "com.mysql.jdbc.Driver";//采用jdbc连接，必须导入驱动
    public static final String DBURL = "jdbc:mysql://localhost:3306/gsdb";//gsdb:本地数据库
    public static final String USERNAME = "root";
    public static final String PASSWORD = "admin";
    public static void main(String[] args) {
        //数据库连接对象
        Connection conn = null;
        //数据库操作对象
        Statement stmt = null;
        //1、加载驱动程序
        try {
            Class.forName(DBDRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //2、连接数据库
        //通过连接管理器连接数据库
        try {
            //在连接的时候直接输入用户名和密码才可以连接
            conn = DriverManager.getConnection(DBURL,USERNAME,PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //3、向数据库中插入一条数据
        String sql = "INSERT INTO uesr(name,password,id,laopo) VALUES(\"asus1\",\"asus1\",\"05\",\"mine1\")";
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //4、执行语句
        try {
            stmt.executeUpdate(sql);
            System.out.println("插入成功！");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //5、关闭操作，步骤相反哈~
        try {
            stmt.close();
            conn.close();
            System.out.println("关闭成功！");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}