import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Server.java
 * 数据库的用户名和密码在这里设置
 * 服务器端口在main函数中设置，默认 6071
 */
public class Server {

    /**
     * 连接的客户端的数量和最大数量
     */
    private int connections = 0;
    private int MAX_CONNECTIONS = 10;

    /**
     * 数据库
     */
    private DBManager db;

    /**
     * 数据库设置
     */
    private String DBUserName = "root";
    private String DBPassword = "lxmxx1008";
    private String DBUrl = "jdbc:mysql://localhost:3306/qq_chat";

    /**
     * 服务器的socket
     */
    private ServerSocket listener;

    /**
     * 连接的客户端
     */
    private ArrayList<ConnectionInfo> ConnectionList = new ArrayList<ConnectionInfo>();

    /**
     * Server构造函数，
     * 连接Socket
     */
    Server() {
        try {
            listener = new ServerSocket(6071);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 服务器运行，
     * 连接数据库并对连接进行监听
     */
    public void run() throws IOException, ClassNotFoundException {

        db = new DBManager(DBUrl, DBUserName, DBPassword);

        /**
         * 监听Socket,
         * 等待下一个连接的访问
         */
        while (true) {

            /**
             * 对连接数量进行判断，
             * 限制最大连接数量
             */
            if (connections + 1 > MAX_CONNECTIONS) {
                System.out.println("SERVER: Client number overflow.");
                System.out.println("Reject this connection.");
                continue;
            }

            /**
             * 阻塞
             */
            Socket client = listener.accept();

            /**
             * 使用线程处理客户端消息
             */
            // new Thread(new processEachClientThread(client, clientList, db)).start();
        }
    }

}
