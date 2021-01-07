import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    private javax.swing.JLabel enrollLabel;
    private javax.swing.JLabel codeLabel;
    private javax.swing.JButton loginButton;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JTextField usernameField;
    private javax.swing.JCheckBox rememberPswd;
    private javax.swing.JCheckBox autoLogin;


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
    }

    private JPanel createEastPanel() {
        JPanel jp = new JPanel();

        jp.setLayout(null);
        jp.setPreferredSize(new Dimension(130,0));

        // 注册账号
        enrollLabel = new JLabel("注册账号");
        enrollLabel.setBounds(0, 10, 100, 30);
        enrollLabel.setFont(new Font("宋体",Font.BOLD,15));
        enrollLabel.setForeground(new Color(100,149,238));
        enrollLabel.addMouseListener(new LabelHandler(enrollLabel) );
        enrollLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));      //将鼠标设置为手型

        // 忘记密码
        codeLabel=new JLabel("忘记密码");
        codeLabel.setBounds(0, 42, 100, 30);
        codeLabel.setFont(new  Font("宋体",Font.BOLD,15));
        codeLabel.setForeground(new Color(100,149,238));
        // codeLabel.addMouseListener(new LabelHandler(codeLabel));                 TODO: 实现忘记密码的功能
        codeLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        jp.add(enrollLabel);
        jp.add(codeLabel);

        return jp;
    }

    private JPanel createSouthPanel() {
        JPanel jp = new JPanel();

        jp.setLayout(null);
        jp.setPreferredSize(new Dimension(0,40));

        // 登录按钮
        loginButton = new JButton(new ImageIcon(Login.class.getResource("/imgs/login-icon.png")));
        loginButton.setBounds(160,0,200,30);
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));      //将鼠标设置为手掌型
        loginButton.setContentAreaFilled(false);
        loginButton.setFocusPainted(false);
        loginButton.addActionListener(evt -> loginButtonActionPerformed(evt));

        jp.add(loginButton);

        return jp;
    }

    private JPanel createCenterPanel() {
        JPanel jp = new JPanel();

        jp.setLayout(null);
        jp.setPreferredSize(new Dimension(0,220));

        // 帐号框
        usernameField = new JTextField(10);                         //最多存放10个字
        usernameField.setBounds(0,10,200,30);
        usernameField.setFont(new Font("宋体",Font.BOLD,17));     //字体和字体大小

        // 记住密码
        rememberPswd = new JCheckBox("记住密码", false);
        rememberPswd.setBounds(0,78,80,18);

        // 自动登录
        autoLogin = new JCheckBox("自动登录", false);
        autoLogin.setBounds(110, 78, 80, 18);

        // 密码框
        passwordField=new JPasswordField(18);
        passwordField.setBounds(0,42, 200, 30);
        passwordField.setFont(new Font("宋体",Font.BOLD,17));
        passwordField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passwordFieldKeyPressed(evt);
            }
        });
        passwordField.setOpaque(false);

        jp.add(rememberPswd);
        jp.add(passwordField);
        jp.add(autoLogin);
        jp.add(usernameField);

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
        if(usernameField.getText().equals("") || passwordField.getPassword().equals("")) {
            JOptionPane.showMessageDialog(this, "请填写账号和密码", "Warning", 0);
            return ;
        }
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
    public class LabelHandler extends MouseAdapter {
        private JLabel label;

        public LabelHandler(JLabel label) {
            this.label = label;
        }

        public void mouseClicked(MouseEvent e) {
            SignUp SignUp = new SignUp(chatRoom, true, client);
            SignUp.setLocationRelativeTo(null);
            SignUp.setVisible(true);
        }

        public void mouseEntered(MouseEvent e) {
            label.setForeground(new Color(255,77,35));
        }

        public void mousePressed(MouseEvent e) {
            label.setForeground(new Color(255,77,35));
        }

        public void mouseExited(MouseEvent e) {
            label.setForeground(new Color(100,149,238));
        }
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
