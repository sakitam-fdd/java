/**
 * Created by FDD on 2016/12/4.
 */

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(description = "tian ditu tile", urlPatterns = {"/tdttile"})
public class TiandituTile extends HttpServlet {
    private String DataPath = "E:/GISDATA/";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tile_column = request.getParameter("X");
        String tile_row = request.getParameter("Y");
        String zoom_level = request.getParameter("L");
        String layer = request.getParameter("T");
        System.out.println(layer);
        // TODO Auto-generated method stub
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
            System.out.println("数据库驱动未找到!");
        }
        // 得到连接 会在你所填写的目录建一个你命名的文件数据库
        Connection conn;
        try {
            String conurl = "jdbc:sqlite:" + DataPath + layer + ".mbtiles";
            conn = DriverManager.getConnection(conurl, null, null);
            // 设置自动提交为false
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();
            //判断表是否存在
            ResultSet rsTables = conn.getMetaData().getTables(null, null, "tiles", null);
            if (!rsTables.next()) {
                System.out.println("表不存在");
            }
            // 得到结果集
            String sql = "SELECT * FROM tiles WHERE zoom_level = " + zoom_level +
                    " AND tile_column = " + tile_column +
                    " AND tile_row = " + tile_row;
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                byte[] imgByte = (byte[]) rs.getObject("tile_data");
                InputStream is = new ByteArrayInputStream(imgByte);
                OutputStream os = response.getOutputStream();
                try {
                    int count = 0;
                    byte[] buffer = new byte[1024 * 1024];
                    while ((count = is.read(buffer)) != -1) {
                        os.write(buffer, 0, count);
                    }
                    os.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    os.close();
                    is.close();
                }
            } else {
                System.out.println(sql);
                System.out.println("未找到图片！");
            }
            rs.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL异常!");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}