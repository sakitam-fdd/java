import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Created by FDD on 2016/11/19.
 */
public class DBConnector{
    /**
     * 连接MySQL数据库的方法
     */
    public static Connection getMySQLConnection(){
        Connection con = null;
        try{
            String Driver="com.mysql.jdbc.Driver";    //驱动程序
            String URL="jdbc:mysql://localhost:3306/gsdb";    //连接的URL,db_name为数据库名
            String Username="root";    //用户名
            String Password="admin";    //密码
            Class.forName(Driver);
            con=DriverManager.getConnection(URL,Username,Password);
        }catch( SQLException e ){
            e.printStackTrace();
        }catch( ClassNotFoundException e ){
            e.printStackTrace();
        }

        return con;
    }

    /**
     * @功能：测试连接是否正常
     * @param args
     */
    public static void main( String[] args ){
        try {
            Connection con = null;
            con = DBConnector.getMySQLConnection();
            if( con != null ){
                System.out.println( "MySQL 数据库连接成功！" );
            }else{
                System.out.println( "MySQL 数据库连接失败！" );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}