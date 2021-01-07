import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.*;

/**
 * 登陆管理
 */
public class Login extends javax.swing.JFrame {
    private Client client;
    private ChatRoom chatRoom;
    private boolean loggedIn = false;

    private javax.swing.JButton exitButton;
    private javax.swing.JButton miniButton;
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

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 36)); // NOI18N
        jLabel1.setText("QQ2021");
        jLabel2.setText("帐号:");
        jLabel3.setText("密码:");

        // 密码框事件
        passwordField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passwordFieldKeyPressed(evt);
            }
        });

        // 登录按钮
        loginButton.setText("登录");
        loginButton.addActionListener(evt -> loginButtonActionPerformed(evt));

        // 注册按钮
        signupButton.setText("注册");
        signupButton.addActionListener(evt -> signupButtonActionPerformed(evt));

        // Border Layout
        Toolkit t = Toolkit.getDefaultToolkit();
        Dimension d = t.getScreenSize();

        this.setBounds(d.width/3,d.height/3,500,330);
        this.setIconImage(t.getImage(Login.class.getResource("/imgs/QQ-icon.jpg")));
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setUndecorated(true);
        this.setResizable(false);

        JPanel northPanel = createNorthPanel();
        JPanel westPanel = createWestPanel();
        JPanel centerPanel = createCenterPanel();
        JPanel southPanel = createSouthPanel();
        JPanel eastPanel = createEastPanel();
        this.add(northPanel,BorderLayout.NORTH);
        this.add(westPanel,BorderLayout.WEST);
        this.add(southPanel,BorderLayout.SOUTH);
        this.add(centerPanel,BorderLayout.CENTER);
        this.add(eastPanel,BorderLayout.EAST);

        this.setVisible(true);
        /*
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

         */
    }

    private JPanel createEastPanel() {
        JPanel jp = new JPanel();

        return jp;
    }

    private JPanel createSouthPanel() {
        JPanel jp = new JPanel();

        return jp;
    }

    private JPanel createCenterPanel() {
        JPanel jp = new JPanel();

        return jp;
    }

    private JPanel createWestPanel() {
        JPanel jp = new JPanel();
        jp.setLayout(null);
        jp.setPreferredSize(new Dimension(160,0));

        // 背景
        JLabel QQIconLabel = new JLabel(new ImageIcon(Login.class.getResource("/imgs/QQ-max-icon.jpg")));
        QQIconLabel.setBounds(35,0,100,100);

        jp.add(QQIconLabel);
        return jp;
    }

    private JPanel createNorthPanel() {
        JPanel jp = new JPanel();
        jp.setLayout(null);
        jp.setPreferredSize(new Dimension(0,190));

        // 背景
        JLabel bgLabel = new JLabel(new ImageIcon(Login.class.getResource("/imgs/header-bg-icon.png")));
        bgLabel.setBounds(0,0,500,190);
        bgLabel.setOpaque(false);               // 透明

        // 退出按钮
        exitButton = new JButton(new ImageIcon(Login.class.getResource("imgs/quit-icon.jpg")));
        exitButton.setRolloverIcon(new ImageIcon(Login.class.getResource("imgs/quit-on-icon.png")));
        exitButton.setPressedIcon(new ImageIcon(Login.class.getResource("imgs/quit-on-icon.png")));
        exitButton.setContentAreaFilled(false); //设置按钮透明
        exitButton.setBorderPainted(false);     //取消按钮的边框
        exitButton.setFocusPainted(false);      //消除按钮的焦点，即点击按钮时不出现边框
        exitButton.setBounds(468, 0, 30, 30);
        exitButton.setToolTipText("关闭");
        exitButton.addActionListener(evt -> exitButtonActionPerformed(evt));

        // 最小化按钮
        miniButton = new JButton(new ImageIcon(Login.class.getResource("imgs/minimize-icon.jpg")));
        miniButton.setRolloverIcon(new ImageIcon(Login.class.getResource("imgs/minimize-on-icon.png")));
        miniButton.setPressedIcon(new ImageIcon(Login.class.getResource("imgs/minimize-on-icon.png")));
        miniButton.setContentAreaFilled(false);
        miniButton.setBorderPainted(false);
        miniButton.setFocusPainted(false);
        miniButton.setBounds(437, 0, 30, 30);
        miniButton.addActionListener(evt -> miniButtonActionPerformed(evt));

        jp.add(exitButton);
        jp.add(miniButton);
        jp.add(bgLabel);
        return jp;
    }

    private void attemptLogin() {
        if (client.login(usernameField.getText(), new String(passwordField.getPassword()))) {
            chatRoom.setVisible(true);
            chatRoom.startChatListener();
            loggedIn = true;
            dispose();
        } else {
            // TODO: QQ的提示窗
            JOptionPane.showMessageDialog(this, "帐号或密码错误", "Warning", 0);
        }
    }

    /**
     * 登陆按钮
     */
    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {
        attemptLogin();
    }

    /**
     * 注册按钮
     */
    private void signupButtonActionPerformed(java.awt.event.ActionEvent evt) {
        SignUp SignUp = new SignUp(chatRoom, true, client);
        SignUp.setLocationRelativeTo(null);
        SignUp.setVisible(true);
    }

    /**
     * 退出按钮
     */
    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {
        client.sendQuitMessage();
        dispose();
        System.exit(0);
    }

    /**
     * 最小化按钮
     */
    private void miniButtonActionPerformed(java.awt.event.ActionEvent evt) {
        this.setExtendedState(ICONIFIED);
    }

    /**
     * 输入完密码，按下回车后，登陆
     */
    private void passwordFieldKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            attemptLogin();
        }
    }
}
