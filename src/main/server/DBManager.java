import java.io.IOException;
import java.sql.*;

/**
 * DBManager.java
 * 实现数据库操作
 */
public class DBManager {

    /**
     * 数据库连接
     */
    private Connection db;

    /**
     * 数据库设置
     */
    private String DBUserName;
    private String DBPassword;
    private String DBUrl;

    /**
     * MySQL 8.0 以下版本: JDBC 驱动名
     */
    final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    DBManager(String DBUrl, String DBUserName, String DBPassword) throws ClassNotFoundException {
        this.DBUserName = DBUserName;
        this.DBPassword = DBPassword;
        this.DBUrl = DBUrl;

        System.out.println("SERVER: Connect Database...");

        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            db = DriverManager.getConnection(this.DBUrl, this.DBUserName, this.DBPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("SERVER: Connection established");
    }

    /**
     * 判断用户是否存在
     * @param username  用户/系统输入的用户名
     * @return          该判断的返回结果布尔值
     */
    public boolean userExists(String username) {
        boolean isExists = false;

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = db.prepareStatement("SELECT * FROM users WHERE u_name = " + username);
            rs = ps.executeQuery();
            if (rs.next())
                isExists = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs);
            close(ps);
        }
        return isExists;
    }

    /**
     * Closes the current statement
     */
    public void close(Statement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Closes the current result set
     */
    public void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
