import javax.swing.*;

/**
 *
 *注册用户
 */
@SuppressWarnings("serial")
public class SignUp extends javax.swing.JDialog {
    private Client client;


    private javax.swing.JButton cancelButton;
    private javax.swing.JPasswordField confirmField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JButton submitButton;
    private javax.swing.JTextField usernameField;



    /**
     * 新建一个注册页面
     */
    public SignUp(java.awt.Frame parent, boolean modal, Client cli) {
        super(parent, modal);
        initFrame();
        client = cli;
    }

    /**
     * 注册
     */
    @SuppressWarnings("unchecked")
    private void initFrame() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        usernameField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        confirmField = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        submitButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 36)); // NOI18N
        jLabel1.setText("Multi-User Chat");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setText("Username:");

        jLabel4.setText("Password:");

        jLabel5.setText("Confirm:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup().addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel4).addComponent(jLabel5).addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 221,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(confirmField, javax.swing.GroupLayout.PREFERRED_SIZE, 221,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 221,
                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(16, Short.MAX_VALUE)));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup().addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3).addComponent(usernameField,
                                        javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4).addComponent(passwordField,
                                        javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5).addComponent(confirmField,
                                        javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(32, Short.MAX_VALUE)));

        jLabel2.setText("Signup");

        submitButton.setText("Submit");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButton(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButton(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                javax.swing.GroupLayout.Alignment.TRAILING,
                layout.createSequentialGroup().addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(submitButton).addGap(18, 18, 18).addComponent(cancelButton).addGap(151, 151, 151))
                .addGroup(layout.createSequentialGroup().addGroup(layout
                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup().addGap(133, 133, 133).addComponent(jLabel1))
                        .addGroup(layout.createSequentialGroup().addGap(56, 56, 56).addGroup(layout
                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup().addGap(9, 9, 9).addComponent(jLabel2)))))
                        .addContainerGap(57, Short.MAX_VALUE)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jLabel1).addGap(18, 18, 18)
                        .addComponent(jLabel2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(submitButton).addComponent(cancelButton))
                        .addContainerGap(20, Short.MAX_VALUE)));

        pack();
    }
    /**提交*/
    private void submitButton(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_submitButton
        String pass1 = new String(passwordField.getPassword());
        String pass2 = new String(confirmField.getPassword());
        /**密码是否匹配*/
        if (!pass1.equals(pass2)) {
            JOptionPane.showMessageDialog(this, "Password fields do not match.", "Warning", 0);
        } else if (pass1.length() < 8) {
            /**密码长度大于8位*/
            JOptionPane.showMessageDialog(this, "Password must be at least 8 characters long.", "Warning", 0);
        } else if (usernameField.getText().length() < 4) {
            /**用户名大于三位*/
            JOptionPane.showMessageDialog(this, "Username must be at least 4 characters long.", "Warning", 0);
        } else {
            client.write("NEWUSER: " + usernameField.getText() + "," + new String(passwordField.getPassword()));
            String response = client.read();
            /**服务器返回消息，TAKEN代表已经存在用户 USERCREATED代表成功*/
            if (response.equals("TAKEN"))
                JOptionPane.showMessageDialog(this, "Username is already taken.", "Warning", 0);
            else if (response.equals("USERCREATED")) {
                JOptionPane.showMessageDialog(this, "Your account has been created.", "Warning", 1);
                dispose();
            }
        }

    }
    /**取消按钮*/
    private void cancelButton(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cancelButton
        dispose();
    }



}
