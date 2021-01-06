import javax.swing.*;
import java.io.IOException;

public class ClientStart {
    public static void main(String args[]) {
        // 设置UI风格
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException |
                IllegalAccessException | InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        }

        /**
         * 设置ip和监听的端口
         */
        java.awt.EventQueue.invokeLater( () -> {

            Client client = null;
            try {
                client = new Client("127.0.0.1", 6666);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 使内容位于屏幕中央
            ChatRoom chatRoom = new ChatRoom(client);
            chatRoom.setLocationRelativeTo(null);

            // 显示登陆界面
            Login login = new Login(chatRoom, true, client);
            login.setLocationRelativeTo(null);
            login.setVisible(true);
        });
    }
}
