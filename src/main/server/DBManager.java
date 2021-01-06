import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.*;
import java.util.Arrays;

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
     * 创建用户
     */
    public boolean createUser(String username, String password) throws SQLException, NoSuchAlgorithmException {
        PreparedStatement ps = null;
        try {
            if (username != null && password != null) {
                // 把用户名和密码插入到用户表中
                ps = db.prepareStatement("INSERT INTO users (u_name, u_pwd) VALUES (?,?)");
                ps.setString(1, username);
                ps.setString(2, password);
                ps.executeUpdate();
            } else {
                return false;
            }
        } finally {
            close(ps);
        }
        return true;
    }

    /**
     * 验证用户的用户名和密码
     */
    public boolean userLogin(String username, String password) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean userExist = true;
        try {
            ps = db.prepareStatement("SELECT * FROM users WHERE u_name = " + username + " and u_pwd = " + password);
            rs = ps.executeQuery();
            String digest, salt;
            if (!rs.next()) {
                userExist = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs);
            close(ps);
        }
        return userExist;
    }

    /**
     * 将发送的消息存入数据库
     */
    public boolean insertMessage(String username, String content, String time) throws SQLException {
        PreparedStatement ps = null;
        try{
            if(username != null && content != null){
                ps = db.prepareStatement("INSERT INTO msgs (msg_content, msg_sendfrom, msg_sendtime) VALUES (?,?,?)");
                ps.setString(1, content);
                ps.setString(2, username);
                ps.setString(3, time);
                ps.executeUpdate();
            }
            else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            close(ps);
        }
        return true;
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
