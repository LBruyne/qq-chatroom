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
    private final int PORT = 6071;

    /**
     * 连接的客户端
     */
    private ArrayList<ConnectionInfo> clientList = new ArrayList<ConnectionInfo>();

    /**
     * 内部类，
     * 处理客户线程，
     * 对每一个客户端，服务器都会开一个线程进行处理
     */
    protected class ClientThread implements Runnable {

        private ConnectionInfo client;

        ClientThread(Socket clientSocket) {
            client = new ConnectionInfo(clientSocket);
            System.out.println("SERVER: Client connected, new thread created.");
        }

        public void run() {

        }
    }


    /**
     * Server构造函数，
     * 连接Socket
     */
    Server() {
        try {
            listener = new ServerSocket(PORT);
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
             * 阻塞
             */
            Socket client = listener.accept();

            /**
             * 对连接数量进行判断，
             * 限制最大连接数量
             */
            if (connections + 1 > MAX_CONNECTIONS) {
                System.out.println("SERVER: Client number overflow.");
                System.out.println("SERVER: Reject this connection.");
                continue;
            }

            connections++;

            /**
             * 使用线程处理客户端消息
             */
            new Thread(new ClientThread(client)).start();
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Server s = new Server();
        s.run();
    }
}
