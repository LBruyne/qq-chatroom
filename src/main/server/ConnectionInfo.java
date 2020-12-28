import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

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

    /** 写到该socket */
    public void write(String msg) {
        outputWriter.println(msg);
        outputWriter.flush();
    }
    /**重载，处理具有时间的消息*/
    public void write(String time, String msg) {
        outputWriter.println(time);
        outputWriter.println(msg);
        outputWriter.flush();
    }
}
