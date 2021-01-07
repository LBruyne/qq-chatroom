import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

import javax.swing.*;

public class ChatRoom extends javax.swing.JFrame {

    private Client client;

    private javax.swing.JTextPane chatTextPane;
    private javax.swing.JTextField sendTextField;
    private javax.swing.JList userList;

    /**
     * 初始化界面
     */
    public ChatRoom(Client client) {
        initFrame();

        this.client = client;
    }

    private void initFrame() {

        JButton sendButton = new JButton();
        sendTextField = new javax.swing.JTextField();
        JScrollPane chatScrollPane = new JScrollPane();
        chatTextPane = new javax.swing.JTextPane();
        JScrollPane userListPane = new JScrollPane();
        userList = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("QQ群聊");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        sendButton.setText("发送");
        sendButton.setName("SendButton");
        sendButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pressSendButton(evt);
            }
        });

        sendTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pressSendText(evt);
            }
        });

        chatTextPane.setEditable(false);
        chatScrollPane.setViewportView(chatTextPane);

        userListPane.setViewportView(userList);
        /**布局*/
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(sendTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(sendButton))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(chatScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 408,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(userListPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(chatScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 265,
                                        Short.MAX_VALUE)
                                .addComponent(userListPane))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(sendButton).addComponent(sendTextField,
                                        javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.PREFERRED_SIZE))));

        pack();
    }

    /**
     * 点击发送按钮
     */
    private void pressSendButton(java.awt.event.MouseEvent evt) {
        client.sendChatMessage(sendTextField.getText());
        sendTextField.setText("");
    }
    /**
     * 按下回车发送*/
    private void pressSendText(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            client.sendChatMessage(sendTextField.getText());
            sendTextField.setText("");
        }
    }

    private void formWindowClosed(java.awt.event.WindowEvent evt) {
        client.sendQuitMessage();
        client.disconnect();
        dispose();
        System.exit(0);
    }
    /**
     * 开始一个监听线程
     */
    public void startChatListener() {
        new Thread(new ClientThread(client, chatTextPane, userList)).start();
    }

    class ClientThread implements Runnable {

        private JTextPane chatBox;
        private JList usernameList;
        private Client client;

        ClientThread(Client cli, JTextPane chatTextPane, JList<?> jList1) {
            chatBox = chatTextPane;
            usernameList = jList1;
            client = cli;
        }


        public void run() {
            while (true) {
                String line;
                if ((line = client.read()) != null) {
                    /**
                     * 更新在线用户，通过 USERLIST 字符串来说明是在线用户列表
                     */
                    if (line.startsWith("USERLIST: ")) {
                        String[] usernames = line.substring(line.indexOf(' ')).split(" ");
                        usernameList.setListData(usernames);
                    } else {
                        chatBox.setText(chatBox.getText() + line + "\n");/*更新聊天框的内容*/
                    }
                }
            }
        }
    }
}

