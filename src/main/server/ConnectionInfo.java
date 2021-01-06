import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

public class ConnectionInfo {
    private String username;
    private Socket clientSocket;
    private PrintWriter outputWriter;
    private BufferedReader inputBuffer;


    ConnectionInfo(Socket clientSocket) {
        this.clientSocket = clientSocket;
        try {
            inputBuffer = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
            outputWriter = new PrintWriter(this.clientSocket.getOutputStream(), true);
        } catch(IOException e) {
            System.err.println(e);
            e.printStackTrace();
        }
    }

    /**
     * 设置用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() { return this.username; }

    /**
     * 关闭连接和输入输出流
     */
    public boolean disconnect() {
        try {
            clientSocket.close();
            inputBuffer.close();
        } catch(IOException e) {
            e.printStackTrace();
            return false;
        }
        outputWriter.close();
        return true;
    }

    /**
     * 从socket读取消息
     */
    public String read() {
        String line = null;
        try {
            line = inputBuffer.readLine();
        }
        catch(SocketException e) {
            e.printStackTrace();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    /**
     * 写到该socket
     */
    public void write(String msg) {
        outputWriter.println(msg);
        outputWriter.flush();
    }

    /**
     * 重载，处理具有时间的消息
     */
    public void write(String time, String msg) {
        outputWriter.println(time);
        outputWriter.println(msg);
        outputWriter.flush();
    }
}
