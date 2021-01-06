import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

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
    private String DBUrl = "jdbc:mysql://localhost:3306/qq_chat?autoReconnect=true&useSSL=false";

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
            System.out.println("SERVER: Start to receive information from client.");

            boolean accepted = false;

            /**
             * 客户端的第一个动作：
             * 登录，注册或者直接退出
             */
            while(!accepted) {
                // 读入第一条消息，并判断类型
                String msg = client.read();

                if (msg.equals("QUIT")) {
                    System.out.println("SERVER: Client disconnected directly.");
                    client.disconnect();
                    return;
                } else if (msg.startsWith("NEWUSER: ")) {
                    createUser(msg);
                } else if (msg.startsWith("LOGIN: ")) {
                    accepted = userLogin(msg);
                } else {
                    continue;
                }
            }

            /**
             *  从客户端读取消息，然后广播
             */
            while (true) {
                String line = client.read();
                if (line == null || line.equals("QUIT"))
                    break;
                else {
                    Date nowTimeStamp = new Date();
                    try {
                        db.insertMessage(line.split(": ")[0], line.split(": ")[1], nowTimeStamp.toString());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    broadcast(nowTimeStamp.toString() + "::"+line, true);
                }
            }
        }

        /**
         * 创建用户
         */
        private synchronized void createUser(String clientMsg) {

            /** 把用户名和密码分离 */
            clientMsg = clientMsg.split(" ")[1];
            String username = clientMsg.split(",")[0];
            String password = clientMsg.split(",")[1];

            /**
             * 查询数据库是否存在该用户名，不存在则创建
             */
            try {
                if (db.userExists(username)) {
                    client.write("TAKEN");
                } else {
                    db.createUser(username, password);
                    client.write("USERCREATED");
                }
            } catch (Exception e) {
                System.err.println(e);
                e.printStackTrace();
            }
        }

        /**
         * 验证用户名和密码，登陆时使用
         */
        private synchronized boolean userLogin(String clientMsg) {
            boolean accepted = false;

            /**取出用户名和密码*/
            clientMsg = clientMsg.split(" ")[1];
            String username = clientMsg.split(",")[0];
            String password = clientMsg.split(",")[1];

            /**与数据库里的进行比对验证*/
            try {
                if (db.userLogin(username, password)) {
                    accepted = true;

                    client.setUsername(username);
                    client.write("ACCEPTED");
                    clientList.add(client);

                    // 广播用户发生的变化
                    updateClientUserList();

                    System.out.println("SERVER: Client logged in with username -> " + client.getUsername());
                    broadcast("ROOM: User " + client.getUsername() + " has joined the chat.", false);
                } else
                    client.write("DENIED");

            } catch (Exception e) {
                e.printStackTrace();
            }

            return accepted;
        }

        /**
         * 更新在线用户list
         */
        private synchronized void updateClientUserList() {
            String userList = "USERLIST:";
            for (int i = 0; i < clientList.size(); i++) {
                userList += " " + clientList.get(i).getUsername();
            }
            broadcast(userList, false);
        }

        /**
         * 广播信息
         */
        private synchronized void broadcast(String msg, boolean isMsg) {
            if(isMsg) {
                // 发送用户发送的消息，带有时间戳
                for (int i = 0; i < clientList.size(); i++) {
                    String t = msg.split("::")[0];
                    String m = msg.substring(msg.indexOf("::")+2);
                    clientList.get(i).write(t, m);
                }
            }
            else {
                for (int i = 0; i < clientList.size(); i++) {
                    clientList.get(i).write(msg);
                }
            }
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
