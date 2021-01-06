import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.ConnectException;
import java.net.UnknownHostException;

public class Client {
    private Socket server;
    private PrintWriter outputWriter;
    private BufferedReader inputBuffer;
    private String username;

    /**
     * 用户登陆
     */
    public boolean login(String user, String pass) {
        // 登陆是否成功
        boolean accepted = false;

        // 传送用户名和密码
        outputWriter.println("LOGIN: " + user + "," + pass);
        outputWriter.flush();

        //服务器返回消息
        String response;
        try {
            response = inputBuffer.readLine();
            System.out.println("SERVER: " + response);
            if(response.equals("ACCEPTED")) {
                accepted = true;
                username = user;
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        return accepted;
    }

    /**
     * 用户连接
     */
    Client(String IP, int PORT) throws IOException {
        server = new Socket(IP, PORT);
        try {
            inputBuffer = new BufferedReader(new InputStreamReader(server.getInputStream()));
            outputWriter = new PrintWriter(server.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭连接和输入输出流
     */
    public boolean disconnect() {
        try {
            server.close();
            inputBuffer.close();
        } catch(IOException e) {
            e.printStackTrace();
            return false;
        }
        outputWriter.close();
        return true;
    }

    /**
     * 通过socket写消息
     */
    public void write(String msg) {
        outputWriter.println(msg);
        outputWriter.flush();
    }

    public void write(String time, String msg) {
        outputWriter.println(time);
        outputWriter.println(msg);
        outputWriter.flush();
    }

    /**
     * 通过socket读消息
     */
    public String read() {
        String line = null;
        try {
            line = inputBuffer.readLine();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    /**
     * 发送消息
     */
    public void sendChatMessage(String msg) {
        write(username + ": " + msg);
    }

    /**
     * 退出登陆,关闭连接
     */
    public void sendQuitMessage() {
        write("QUIT");
    }

}
