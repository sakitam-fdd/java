/**
 * Created by FDD on 2016/11/20.
 */
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 使用jdbc连接mySQL执行简单查询
 */
public class DoQuery {
    /**
     * 主类
     * @param args
     */
    public static void main(String[] args) {
        String Driver="com.mysql.jdbc.Driver";    //驱动程序
        String URL="jdbc:mysql://localhost:3306/gsdb";    //连接的URL
        String Username="root";    //用户名
        String Password="admin";    //密码
        Statement stmt = null;
        ResultSet res = null;
        Connection conn = null;
        CallableStatement proc = null;
        String sql = "SELECT * FROM uesr WHERE id='02'";

        try {
            Class.forName(Driver);
            conn = DriverManager.getConnection(URL,Username,Password);
            stmt = conn.createStatement();
            res = stmt.executeQuery(sql);//执行sql语句
            while (res.next()) {
                String rec = res.getString("name");
                String airline = res.getString("password");
                String dept = res.getString("id");
                String arr = res.getString("laopo");
                System.out.println(rec + " " + airline + " " + dept + " " + arr);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}