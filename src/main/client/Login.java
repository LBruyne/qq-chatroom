import java.awt.event.KeyEvent;
import javax.swing.*;

/**
 * 登陆管理
 */
public class Login extends javax.swing.JDialog {
    private Client client;
    private ChatRoom chatRoom;
    private boolean loggedIn = false;

    private javax.swing.JButton exitButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton loginButton;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JButton signupButton;
    private javax.swing.JTextField usernameField;


    /**
     * 登陆的界面
     */
    public Login(java.awt.Frame parent, boolean modal, Client client) {
        super(parent, modal);
        initFrame();
        this.client = client;
        this.chatRoom = (ChatRoom) parent;
    }

    /**
     * 初始化界面
     */
    private void initFrame() {

        jLabel1 = new javax.swing.JLabel();
        usernameField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        loginButton = new javax.swing.JButton();
        signupButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 36)); // NOI18N
        jLabel1.setText("Multi-User Chat");

        jLabel2.setText("Username:");

        passwordField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passwordFieldKeyPressed(evt);
            }
        });

        jLabel3.setText("Password:");

        loginButton.setText("Login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        signupButton.setText("Signup");
        signupButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signupButtonActionPerformed(evt);
            }
        });

        exitButton.setText("Exit");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
                .createSequentialGroup().addContainerGap(70, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel2).addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 181,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 181,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(62, 62, 62))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                layout.createSequentialGroup().addComponent(loginButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(signupButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(exitButton).addGap(111, 111, 111))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                layout.createSequentialGroup().addComponent(jLabel1).addGap(100, 100, 100)))));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addGap(29, 29, 29).addComponent(jLabel1).addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(loginButton).addComponent(signupButton).addComponent(exitButton))
                        .addContainerGap(61, Short.MAX_VALUE)));

        pack();
    }

    private void attemptLogin() {
            if (client.login(usernameField.getText(), new String(passwordField.getPassword()))) {
                chatRoom.setVisible(true);
                chatRoom.startChatListener();
                loggedIn = true;
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect username or password.", "Warning", 0);
            }
    }

    /**登陆按钮*/
    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_loginButtonActionPerformed
        attemptLogin();
    }// GEN-LAST:event_loginButtonActionPerformed
    /**注册按钮*/
    private void signupButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_signupButtonActionPerformed
        SignUp SignUp = new SignUp(chatRoom, true, client);
        SignUp.setLocationRelativeTo(null);
        SignUp.setVisible(true);
    }
    /**退出按钮*/
    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_exitButtonActionPerformed
        client.sendQuitMessage();
        dispose();
        System.exit(0);
    }
    /**输入完密码，按下回车后，登陆*/
    private void passwordFieldKeyPressed(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_passwordFieldKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            attemptLogin();
        }
    }
    /**关闭窗口*/
    private void formWindowClosed(java.awt.event.WindowEvent evt) {// GEN-FIRST:event_formWindowClosed
        dispose();

        if (!loggedIn) {
            client.sendQuitMessage();
            client.disconnect();
            System.exit(0);
        }
    }




}