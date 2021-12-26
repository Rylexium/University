package my_package;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.JOptionPane;

public class Login extends javax.swing.JFrame {

    public Login() {
        initComponents();
        try{Class.forName("org.postgresql.Driver");} //проверяем наличие нужного драйвера
        catch(Exception e){JOptionPane.showMessageDialog(null, "Нет нужно драйвера!!!");}      
        
        try{//делаем коннект к субд
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Masterskay", "postgres", "123");
            System.out.println("Connect");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        ImageBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Вход");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocation(new java.awt.Point(500, 280));
        setMaximumSize(new java.awt.Dimension(467, 460));
        setMinimumSize(new java.awt.Dimension(467, 460));
        setPreferredSize(new java.awt.Dimension(467, 460));
        setResizable(false);
        setSize(new java.awt.Dimension(467, 480));
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Логин");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(100, 110, 80, 29);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("Пароль");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(100, 190, 80, 29);

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });
        getContentPane().add(jTextField1);
        jTextField1.setBounds(180, 110, 164, 29);

        jPasswordField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPasswordField1KeyPressed(evt);
            }
        });
        getContentPane().add(jPasswordField1);
        jPasswordField1.setBounds(180, 190, 164, 29);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setText("Вход");
        jButton1.setName(""); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(100, 250, 248, 49);

        ImageBackground.setIcon(new javax.swing.ImageIcon("C:\\Users\\aleks\\Documents\\NetBeansProjects\\Program\\imgonline-com-ua-Resize-e10lVF43arQXP.jpg")); // NOI18N
        ImageBackground.setText("jLabel3");
        getContentPane().add(ImageBackground);
        ImageBackground.setBounds(0, 0, 470, 420);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        LogIn();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jPasswordField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField1KeyPressed
        if(evt.getKeyChar() == '\n'){ LogIn(); }//если был нажат enter
    }//GEN-LAST:event_jPasswordField1KeyPressed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        try{
            char elem = jTextField1.getText().charAt(jTextField1.getText().length()-1);
            if(elem == ' ' || elem == '\'' || elem ==';' || elem == '-')
                jTextField1.setText(jTextField1.getText().length()>1? jTextField1.getText().substring(0, jTextField1.getText().length()-1): "");}
        catch(Exception e){};
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        try{
            char elem = jTextField1.getText().charAt(jTextField1.getText().length()-1);
            if(elem == ' ' || elem == '\'' || elem ==';' || elem == '-')
                jTextField1.setText(jTextField1.getText().length()>1? jTextField1.getText().substring(0, jTextField1.getText().length()-1): "");}
        catch(Exception e){};
    }//GEN-LAST:event_jTextField1KeyPressed
    public void LogIn(){
        String login = jTextField1.getText();
        String password=String.valueOf(jPasswordField1.getPassword());
        if(!login.equals("") && !password.equals("")){
            try{
                ResultSet result = conn.createStatement().executeQuery("select login, hash_passw, \"ID_мастера\" from \"Users\" "
                                                + "where login='" + login + "' and hash_passw='" + md5(password) + "'");
                if (result.next()){
                    System.out.println("Авторизация успешна!");
                    if(login.equals("admin")){
                        admin = new Admin();//conn);
                        admin.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                        admin.setVisible(true);
                        admin.setParent(this);
                    }
                    else{
                        id = result.getString(3);
                        result = conn.createStatement().executeQuery("select \"Фамилия мастера\" || ' ' || \"Имя мастера\" || ' '|| \"Отчество мастера\" "
                                + "from \"Мастер\" where \"ID_мастера\"=" + id);
                        result.next();
                        fullName = result.getString(1);
                        master = new Master(id, fullName);//conn);
                        master.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                        master.setVisible(true);
                        master.setParent(this);
                    }
                    this.setVisible(false);
                    conn.prepareStatement("UPDATE \"Users\" SET \"Last_LogIn\"=(SELECT date_trunc('second', now()::timestamp with time zone))\n" +
"	WHERE \"login\"='" + login + "'").executeUpdate();
                    jTextField1.setText(""); //обнуляем введеные данные
                    jPasswordField1.setText("");
                    //2-ая форма появится там, где исчезла 1-ая               
                }
                else{System.out.println("Вход запрещен!");}
            }catch(Exception e){e.printStackTrace();};
        }
    }
    public String md5(String in) {
            String result = null;
            try
            {
                MessageDigest digest = MessageDigest.getInstance("MD5");
                digest.reset();
                digest.update(in.getBytes());
                BigInteger bigInt = new BigInteger(1, digest.digest());
                result = bigInt.toString(16);
            } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
            }
            return result;
        }
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() { //login
            public void run() {
                new Login().setVisible(true);
            }
        });
    }
    private Admin admin = null;
    private Master master = null;
    private static String id = null, fullName = null;
    private Request request = null;
    private static Connection conn = null;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ImageBackground;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
