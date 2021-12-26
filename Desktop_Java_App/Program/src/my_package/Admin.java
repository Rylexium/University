
package my_package;

import java.awt.Color;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Admin extends javax.swing.JFrame {

    public Admin(){//Connection _conn) {
        initComponents();
        //conn = _conn;
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

        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldPhone = new javax.swing.JTextField();
        jTextFieldEmail = new javax.swing.JTextField();
        jTextFieldOtchestvo = new javax.swing.JTextField();
        jTextFieldName = new javax.swing.JTextField();
        jTextFieldFamily = new javax.swing.JTextField();
        jPasswordField2 = new javax.swing.JPasswordField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jTextFieldLogin = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jComboBox5 = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        jButton9 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jComboBox6 = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Кабинет администратора");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jTabbedPane2.setToolTipText("");
        jTabbedPane2.setName(""); // NOI18N
        jTabbedPane2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane2StateChanged(evt);
            }
        });
        jTabbedPane2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTabbedPane2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTabbedPane2FocusLost(evt);
            }
        });

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID_мастера", "login", "Last_LogIn", "Last_LogOut", "Create", "hash_password"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMinWidth(85);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(85);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(85);
            jTable1.getColumnModel().getColumn(1).setMinWidth(130);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(130);
            jTable1.getColumnModel().getColumn(1).setMaxWidth(130);
            jTable1.getColumnModel().getColumn(2).setMinWidth(135);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(135);
            jTable1.getColumnModel().getColumn(2).setMaxWidth(135);
            jTable1.getColumnModel().getColumn(3).setMinWidth(135);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(135);
            jTable1.getColumnModel().getColumn(3).setMaxWidth(135);
            jTable1.getColumnModel().getColumn(4).setMinWidth(135);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(135);
            jTable1.getColumnModel().getColumn(4).setMaxWidth(135);
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Пользователи", jPanel3);

        jPanel4.setMaximumSize(new java.awt.Dimension(7, 23));
        jPanel4.setMinimumSize(new java.awt.Dimension(7, 23));
        jPanel4.setName(""); // NOI18N

        jTable2.setAutoCreateRowSorter(true);
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID_мастера", "Фамилия мастера", "Имя мастера", "Отчество мастера", "Email мастера", "Телефон мастера"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable2MousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Логин");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Пароль");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Повторите пароль");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Фамилия");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Имя");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Отчество");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Почта");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Номер телефона");

        jTextFieldPhone.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextFieldPhone.setMaximumSize(new java.awt.Dimension(150, 25));
        jTextFieldPhone.setMinimumSize(new java.awt.Dimension(150, 25));
        jTextFieldPhone.setName(""); // NOI18N
        jTextFieldPhone.setPreferredSize(new java.awt.Dimension(150, 28));
        jTextFieldPhone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldPhoneKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldPhoneKeyReleased(evt);
            }
        });

        jTextFieldEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextFieldEmail.setMaximumSize(new java.awt.Dimension(150, 25));
        jTextFieldEmail.setMinimumSize(new java.awt.Dimension(150, 25));
        jTextFieldEmail.setName(""); // NOI18N
        jTextFieldEmail.setPreferredSize(new java.awt.Dimension(150, 28));

        jTextFieldOtchestvo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextFieldOtchestvo.setMaximumSize(new java.awt.Dimension(150, 25));
        jTextFieldOtchestvo.setMinimumSize(new java.awt.Dimension(150, 25));
        jTextFieldOtchestvo.setName(""); // NOI18N
        jTextFieldOtchestvo.setPreferredSize(new java.awt.Dimension(150, 28));

        jTextFieldName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextFieldName.setMaximumSize(new java.awt.Dimension(150, 25));
        jTextFieldName.setMinimumSize(new java.awt.Dimension(150, 25));
        jTextFieldName.setName(""); // NOI18N
        jTextFieldName.setPreferredSize(new java.awt.Dimension(150, 28));

        jTextFieldFamily.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextFieldFamily.setMaximumSize(new java.awt.Dimension(150, 25));
        jTextFieldFamily.setMinimumSize(new java.awt.Dimension(150, 25));
        jTextFieldFamily.setName(""); // NOI18N
        jTextFieldFamily.setPreferredSize(new java.awt.Dimension(150, 28));

        jPasswordField2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPasswordField2.setMaximumSize(new java.awt.Dimension(150, 25));
        jPasswordField2.setMinimumSize(new java.awt.Dimension(150, 25));
        jPasswordField2.setName(""); // NOI18N
        jPasswordField2.setPreferredSize(new java.awt.Dimension(150, 28));
        jPasswordField2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPasswordField2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jPasswordField2FocusLost(evt);
            }
        });

        jPasswordField1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPasswordField1.setMaximumSize(new java.awt.Dimension(150, 25));
        jPasswordField1.setMinimumSize(new java.awt.Dimension(150, 25));
        jPasswordField1.setName(""); // NOI18N
        jPasswordField1.setPreferredSize(new java.awt.Dimension(150, 28));

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setText("Зарегистрировать");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextFieldLogin.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextFieldLogin.setMaximumSize(new java.awt.Dimension(150, 25));
        jTextFieldLogin.setMinimumSize(new java.awt.Dimension(150, 25));
        jTextFieldLogin.setName(""); // NOI18N
        jTextFieldLogin.setPreferredSize(new java.awt.Dimension(150, 28));
        jTextFieldLogin.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldLoginFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldLoginFocusLost(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton4.setText("Удалить");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton5.setText("Обновить");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                    .addComponent(jTextFieldOtchestvo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldFamily, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldPhone, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPasswordField2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(37, 37, 37)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jTextFieldLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jPasswordField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldFamily, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jTextFieldOtchestvo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jTextFieldPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Мастера", jPanel4);

        jPanel5.setMaximumSize(new java.awt.Dimension(7, 23));
        jPanel5.setMinimumSize(new java.awt.Dimension(7, 23));

        jTable3.setAutoCreateRowSorter(true);
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID_клиента", "Фамилия клиента", "Имя клиента", "Отчество клиента", "Email клиента", "Телефон клиента"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable3MousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Фамилия клиента");
        jLabel10.setMaximumSize(new java.awt.Dimension(75, 17));
        jLabel10.setMinimumSize(new java.awt.Dimension(75, 17));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Имя клиента");
        jLabel11.setMaximumSize(new java.awt.Dimension(75, 17));
        jLabel11.setMinimumSize(new java.awt.Dimension(75, 17));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Отчество клиента");
        jLabel12.setMaximumSize(new java.awt.Dimension(75, 17));
        jLabel12.setMinimumSize(new java.awt.Dimension(75, 17));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Email клиента");
        jLabel13.setMaximumSize(new java.awt.Dimension(75, 17));
        jLabel13.setMinimumSize(new java.awt.Dimension(75, 17));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Телефон клиента");
        jLabel14.setMaximumSize(new java.awt.Dimension(75, 17));
        jLabel14.setMinimumSize(new java.awt.Dimension(75, 17));

        jTextField3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField3.setMaximumSize(new java.awt.Dimension(7, 23));

        jTextField9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField9.setMaximumSize(new java.awt.Dimension(7, 23));

        jTextField10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField10.setMaximumSize(new java.awt.Dimension(7, 23));

        jTextField11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField11.setMaximumSize(new java.awt.Dimension(7, 23));

        jTextField12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField12.setMaximumSize(new java.awt.Dimension(7, 23));

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setText("Обновить");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton3.setText("Удалить");
        jButton3.setMaximumSize(new java.awt.Dimension(111, 31));
        jButton3.setMinimumSize(new java.awt.Dimension(111, 31));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 912, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 139, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Клиенты", jPanel5);

        jTable4.setAutoCreateRowSorter(true);
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID_Заказа", "Гарантийный ремонт", "Клиент", "Мастер", "Проблема клиента", "Предыстория поломоки", "Дата принятия заказ", "Дата окончания заказа", "Гарантия ремонта", "Общая стоимость заказа", "Статус выполнения", "Тип часов", "Название пола", "Название производителя", "Название модели"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTable4);

        jComboBox5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Все заказы", "В обработке", "Выполняется", "Выполнено", "Завершено" }));
        jComboBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 912, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Заказы", jPanel6);

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBox1.setMaximumRowCount(110000);
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Все" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jComboBox2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Все" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jTable5.setAutoCreateRowSorter(true);
        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID_мастера", "Мастер", "Часы"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable5MousePressed(evt);
            }
        });
        jScrollPane5.setViewportView(jTable5);
        if (jTable5.getColumnModel().getColumnCount() > 0) {
            jTable5.getColumnModel().getColumn(0).setMinWidth(85);
            jTable5.getColumnModel().getColumn(0).setPreferredWidth(85);
            jTable5.getColumnModel().getColumn(0).setMaxWidth(85);
        }

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Просмотр категорий");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel15.setText("Добавить категорию");

        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jComboBox3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jComboBox4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jButton9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton9.setText("Добавить");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 763, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox1, 0, 133, Short.MAX_VALUE)
                            .addComponent(jComboBox2, 0, 133, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(363, 363, 363)
                        .addComponent(jLabel15))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(366, 366, 366)
                        .addComponent(jLabel9)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton9)))
                .addContainerGap(223, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Квалификации", jPanel1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        jTabbedPane2.addTab("Работа с УЗ", jPanel2);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 917, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 508, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Регистрация заказа", jPanel7);

        jTable6.setAutoCreateRowSorter(true);
        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Название материала", "Стоимость", "Кол-во материала", "Использований"
            }
        ));
        jTable6.setEnabled(false);
        jScrollPane6.setViewportView(jTable6);

        jComboBox6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBox6.setMaximumRowCount(15);
        jComboBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox6ActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("Мастер");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 917, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addGap(0, 239, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Учет расходных материалов", jPanel8);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jTabbedPane2))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void updateTable(String table) throws SQLException{
        DefaultTableModel model = null;
        ResultSet res = null;
        switch(table){
            case "Users":
                model = (DefaultTableModel)jTable1.getModel(); 
                model.setRowCount(0);
                res = conn.createStatement().executeQuery("select * from \"Users\"");
                while(res.next()){
                    Object[] row = {res.getString("ID_мастера"), res.getString("login"), res.getString("Last_LogIn"),
                                    res.getString("Last_LogOut"), res.getString("Create"), res.getString("hash_passw")};
                    model.addRow(row);
                }
                break;
            case "Мастер":
                model = (DefaultTableModel)jTable2.getModel(); model.setRowCount(0);
                res = conn.createStatement().executeQuery("select \"ID_мастера\", \"Фамилия мастера\", \"Имя мастера\", \"Отчество мастера\", "
                    + "\"Email мастера\", \"Телефон мастера\" from \"Мастер\" order by \"Фамилия мастера\"");
                while(res.next()){
                    Object[] row = {res.getInt("ID_мастера"), res.getString("Фамилия мастера"),
                        res.getString("Имя мастера"), res.getString("Отчество мастера"),
                        res.getString("Email мастера"), res.getString("Телефон мастера")};
                    model.addRow(row);
                }
                break;
            case "Клиент":
                model = (DefaultTableModel)jTable3.getModel(); model.setRowCount(0);
                res = conn.createStatement().executeQuery("select * from \"Клиент\" order by \"Фамилия клиента\"");
                while(res.next()){
                    Object[] row = {res.getInt("ID_клиента"), res.getString("Фамилия клиента"),
                        res.getString("Имя клиента"), res.getString("Отчество клиента"),
                        res.getString("Email клиента"), res.getString("Телефон клиента")};
                    model.addRow(row);
                }
                break;
            case "Заказ":
                model = (DefaultTableModel)jTable4.getModel(); model.setRowCount(0);
                res = conn.createStatement().executeQuery("SELECT \"ID_заказа\", \n" +
                    " \"Гарантийный ремонт\", \n" +
                    "(select \"Фамилия клиента\"||' '|| SUBSTR(\"Имя клиента\",1,1)|| '.' ||SUBSTR(\"Отчество клиента\",1,1) || '.' as Клиент From \"Клиент\"\n" +
                    " where Клиент.\"ID_клиента\"=Заказ.\"ID_клиента\"),\n" +
                    "(select \"Фамилия мастера\"||' '|| SUBSTR(\"Имя мастера\",1,1)|| '.' ||SUBSTR(\"Отчество мастера\",1,1) || '.' as Мастер From \"Мастер\"\n" +
                    " where Мастер.\"ID_мастера\"=Заказ.\"ID_мастера\"),\n" +
                    "\"Проблема клиента\", \"Предыстория поломки\", \"Дата принятия заказа\", \"Дата окончания заказа\", \"Гарантия на ремонт\", \n" +
                    "\"Общая стоимость заказа\", \n" +
                    "\"Статус выполнения\", \n" +
                    "(select \"Название типа\"||' '||\"Портативность\" as \"Тип часов\" from \"Тип часов\" where Заказ.\"Тип часов\"=\"Тип часов\".\"ID_типа\"),\n" +
                    "(select \"Название пола\" from \"Пол часов\" where Заказ.\"Пол часов\"=\"Пол часов\".\"ID_пола\"),\n" +
                    "(select \"Название производителя\" from \"Производитель часов\" where Заказ.\"Производитель часов\"=\"Производитель часов\".\"ID_производителя\"), \n" +
                    "(select \"Название модели\" from \"Модель часов\" where Заказ.\"Модель часов\"=\"Модель часов\".\"ID_модели\")\n" +
                    "	FROM \"Заказ\";");
                while(res.next()){
                    Object[] row = {res.getInt(1), res.getBoolean(2), res.getString(3), res.getString(4),
                        res.getString(5), res.getString(6), res.getString(7), res.getString(8),
                        res.getString(9), res.getString(10),res.getString(11), res.getString(12),
                        res.getString(13), res.getString(14), res.getString(15)
                    };
                    model.addRow(row);
                }
                break;
        }
    }
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if(parent!=null)parent.setVisible(true);
        try {
            conn.prepareStatement("UPDATE \"Users\" SET \"Last_LogOut\"=(SELECT date_trunc('second', now()::timestamp with time zone))"
                    + " WHERE \"login\"='admin'").executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowClosing

    private void jTabbedPane2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTabbedPane2FocusLost
        //При добавление чего в БД необходимо обновлять данные
        //Таблица Users
        if(jTable1.getHeight()!=0){ try {
            //таблица заполнена?
            updateTable("Users");
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        }

        //Таблица мастер
        if(jTable2.getHeight()!=0){ try {
            //таблица заполнена?
            updateTable("Мастер");
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        }

        //Таблица клиент
        if(jTable3.getHeight()!=0){ try {
            //таблица заполнена?
            updateTable("Клиент");
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        }

        //Таблица заказ
        if(jTable4.getHeight()!=0){ try {
            //таблица заполнена?
            updateTable("Заказ");
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        }

        if(jTable5.getHeight() != 0){
            try {
                filljTable5();
            } catch (SQLException ex) {
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jTabbedPane2FocusLost

    private void jTabbedPane2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTabbedPane2FocusGained
        //Когда дали фокус необходимо заполнить данные
        //Таблица Users
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        try {
            if(jTable1.getHeight() == 0){ //таблица заполнена?
                ResultSet res = conn.createStatement().executeQuery("select * from \"Users\"");
                while(res.next()){
                    Object[] row = {res.getString("ID_мастера"), res.getString("login"), res.getString("Last_LogIn"),
                        res.getString("Last_LogOut"), res.getString("Create") ,res.getString("hash_passw")};
                    model.addRow(row);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Таблица мастер
        model = (DefaultTableModel)jTable2.getModel();
        try {
            if(jTable2.getHeight() == 0){ //таблица заполнена?
                ResultSet res = conn.createStatement().executeQuery("select \"ID_мастера\", \"Фамилия мастера\", \"Имя мастера\", \"Отчество мастера\", "
                    + "\"Email мастера\", \"Телефон мастера\" from \"Мастер\" order by \"Фамилия мастера\"");
                while(res.next()){
                    Object[] row = {res.getInt("ID_мастера"), res.getString("Фамилия мастера"),
                        res.getString("Имя мастера"), res.getString("Отчество мастера"),
                        res.getString("Email мастера"), res.getString("Телефон мастера")};
                    model.addRow(row);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Таблица клиент
        model = (DefaultTableModel)jTable3.getModel();
        try {
            if(jTable3.getHeight() == 0){ //таблица заполнена?
                ResultSet res = conn.createStatement().executeQuery("select * from \"Клиент\" order by \"Фамилия клиента\"");
                while(res.next()){
                    Object[] row = {res.getInt("ID_клиента"), res.getString("Фамилия клиента"),
                        res.getString("Имя клиента"), res.getString("Отчество клиента"),
                        res.getString("Email клиента"), res.getString("Телефон клиента")};
                    model.addRow(row);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Таблица заказ
        model = (DefaultTableModel)jTable4.getModel();
        try {
            if(jTable4.getHeight() == 0){ //таблица заполнена?
                ResultSet res = conn.createStatement().executeQuery("SELECT \"ID_заказа\", \n" +
                    " \"Гарантийный ремонт\", \n" +
                    "(select \"Фамилия клиента\"||' '|| SUBSTR(\"Имя клиента\",1,1)|| '.' ||SUBSTR(\"Отчество клиента\",1,1) || '.' as Клиент From \"Клиент\"\n" +
                    " where Клиент.\"ID_клиента\"=Заказ.\"ID_клиента\"),\n" +
                    "(select \"Фамилия мастера\"||' '|| SUBSTR(\"Имя мастера\",1,1)|| '.' ||SUBSTR(\"Отчество мастера\",1,1) || '.' as Мастер From \"Мастер\"\n" +
                    " where Мастер.\"ID_мастера\"=Заказ.\"ID_мастера\"),\n" +
                    "\"Проблема клиента\", \"Предыстория поломки\", \"Дата принятия заказа\", \"Дата окончания заказа\", \"Гарантия на ремонт\", \n" +
                    "\"Общая стоимость заказа\", \n" +
                    "\"Статус выполнения\", \n" +
                    "(select \"Название типа\"||' '||\"Портативность\" as \"Тип часов\" from \"Тип часов\" where Заказ.\"Тип часов\"=\"Тип часов\".\"ID_типа\"),\n" +
                    "(select \"Название пола\" from \"Пол часов\" where Заказ.\"Пол часов\"=\"Пол часов\".\"ID_пола\"),\n" +
                    "(select \"Название производителя\" from \"Производитель часов\" where Заказ.\"Производитель часов\"=\"Производитель часов\".\"ID_производителя\"), \n" +
                    "(select \"Название модели\" from \"Модель часов\" where Заказ.\"Модель часов\"=\"Модель часов\".\"ID_модели\")\n" +
                    "	FROM \"Заказ\";");
                while(res.next()){
                    Object[] row = {res.getInt(1), res.getBoolean(2), res.getString(3), res.getString(4),
                        res.getString(5), res.getString(6), res.getString(7), res.getString(8),
                        res.getString(9), res.getString(10),res.getString(11), res.getString(12),
                        res.getString(13), res.getString(14), res.getString(15)
                    };
                    model.addRow(row);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(jTable5.getHeight() == 0){
            try{
                filljTable5();
            } catch (SQLException ex) {
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //Заполняем название типа
        if(jComboBox1.getItemCount()==1){
            try{
                ResultSet res = conn.createStatement().executeQuery("select distinct \"Название типа\" from \"Тип часов\"");
                while(res.next()){
                    jComboBox1.addItem(res.getString(1));
                    jComboBox3.addItem(res.getString(1));
                }
            } catch (SQLException ex) {
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
            jComboBox3.addItem("Все");
        }
        //Заполняем портативность
        if(jComboBox2.getItemCount()==1){
            try{
                ResultSet res = conn.createStatement().executeQuery("select distinct \"Портативность\" from \"Тип часов\"");
                while(res.next()){
                    jComboBox2.addItem(res.getString(1));
                    jComboBox4.addItem(res.getString(1));
                }
            } catch (SQLException ex) {
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
            jComboBox4.addItem("Все");
        }

        if(jComboBox6.getItemCount()==0){
            jComboBox6.addItem("Все");
            try {
                ResultSet res = conn.createStatement().executeQuery("SELECT \"ID_мастера\" || ' ' || \"Фамилия мастера\" || ' ' || \"Имя мастера\" || ' '|| \"Отчество мастера\"\n" +
                        "	FROM \"Мастер\" order by \"ID_мастера\"");
                while(res.next()){
                    jComboBox6.addItem(res.getString(1));
                }
            } catch (SQLException ex) {
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(jTable6.getHeight()==0) jComboBox6ActionPerformed(null);
    }//GEN-LAST:event_jTabbedPane2FocusGained

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        try {
            int first = 0, second = 0;
            switch((String)jComboBox3.getSelectedItem()){
                case "Все":
                first = 0;
                break;
                case "Механические":
                first = 1;
                break;
                case "Кварцевые":
                first = 2;
                break;
                case "Электронные":
                first = 3;
                break;
            }
            switch((String)jComboBox4.getSelectedItem()){
                case "Все":
                second = 0;
                break;
                case "Настольные":
                second = 1;
                break;
                case "Наручные":
                second = 2;
                break;
                case "Напольные":
                second = 3;
                break;
                case "Карманные":
                second = 4;
                break;
            }
            if(first == 0 && second != 0){
                for(int i=1; i < jComboBox3.getItemCount()-1; i++){
                    conn.prepareStatement("INSERT INTO \"Мастер и часы\"(\n" +
                        "	\"ID\", \"ID_мастера\", \"ID_типа\")\n" +
                        "	VALUES ( (select max(\"ID\")+1 from \"Мастер и часы\"), " + (String) jTable5.getValueAt(jTable5.getSelectedRow(), 0) +
                        ", " + String.format("%d", i) + String.format("%d", second) + ")").execute();
                }
            }
            else if(first != 0 && second == 0){
                for(int i=1; i < jComboBox4.getItemCount()-1; i++){
                    conn.prepareStatement("INSERT INTO \"Мастер и часы\"(\n" +
                        "	\"ID\", \"ID_мастера\", \"ID_типа\")\n" +
                        "	VALUES ( (select max(\"ID\")+1 from \"Мастер и часы\"), " + (String) jTable5.getValueAt(jTable5.getSelectedRow(), 0) +
                        ", " + String.format("%d", first) + String.format("%d", i) + ")").execute();
                }
            }
            else if(first == 0 && second == 0){
                for(int i=1;i<jComboBox3.getItemCount()-1;i++){
                    for(int j=1; j < jComboBox4.getItemCount()-1; j++){
                        conn.prepareStatement("INSERT INTO public.\"Мастер и часы\"(\n" +
                            "	\"ID\", \"ID_мастера\", \"ID_типа\")\n" +
                            "	VALUES ( (select max(\"ID\")+1 from \"Мастер и часы\"), " + (String) jTable5.getValueAt(jTable5.getSelectedRow(), 0) +
                            ", " + String.format("%d", i) + String.format("%d", j)+")").execute();
                    }
                }
            }
            else if(first != 0 && second != 0){
                conn.prepareStatement("INSERT INTO public.\"Мастер и часы\"(\n" +
                    "	\"ID\", \"ID_мастера\", \"ID_типа\")\n" +
                    "	VALUES ( (select max(\"ID\")+1 from \"Мастер и часы\"), " + (String) jTable5.getValueAt(jTable5.getSelectedRow(), 0) +
                    ", "+ String.format("%d", first) + String.format("%d", second) +");").executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jTable5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable5MousePressed
        jTextField1.setText((String) jTable5.getValueAt(jTable5.getSelectedRow(), 1));
    }//GEN-LAST:event_jTable5MousePressed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        try{
            filljTable5();
        }
        catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        try{
            filljTable5();
        }
        catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try{
            if(jTable3.getSelectedRow()==-1)return;
            DeleteRowInJTable(jTable3, "Клиент");
            jTextField3.setText("");jTextField9.setText("");
            jTextField10.setText("");jTextField11.setText("");jTextField12.setText("");
        }catch(Exception e){}
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //Обновить данные и в таблице, и в БД
        int index = jTable3.getSelectedRow();
        if(index==-1)return;
        String family = jTextField3.getText();
        String name = jTextField9.getText();
        String otchestvo = jTextField10.getText();
        String email = jTextField11.getText();
        String phone = jTextField12.getText();
        try{
            conn.prepareStatement("UPDATE \"Клиент\" "
                + "SET \"ID_клиента\"=" + jTable3.getValueAt(index, 0).toString()
                + ", \"Фамилия клиента\"='" + family + "', "
                + "\"Имя клиента\"='" + name + "', "
                + "\"Отчество клиента\"='" + otchestvo + "', "
                + "\"Email клиента\"='" + email + "', "
                + "\"Телефон клиента\"='" + phone + "' "
                + " WHERE \"ID_клиента\"=" + jTable3.getValueAt(index, 0).toString()).executeUpdate();

            jTable3.setValueAt(jTextField3.getText(), index, 1);
            jTable3.setValueAt(jTextField9.getText(), index, 2);
            jTable3.setValueAt(jTextField10.getText(), index, 3);
            jTable3.setValueAt(jTextField11.getText(), index, 4);
            jTable3.setValueAt(jTextField12.getText(), index, 5);
        }catch(Exception e){e.printStackTrace();};
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MousePressed
        jTextField3.setText("");jTextField9.setText("");
        jTextField10.setText("");jTextField11.setText("");jTextField12.setText("");
        int index = jTable3.getSelectedRow();
        jTextField3.setText(jTable3.getValueAt(index, 1).toString());
        jTextField9.setText(jTable3.getValueAt(index, 2).toString());
        jTextField10.setText(jTable3.getValueAt(index, 3).toString());
        jTextField11.setText(jTable3.getValueAt(index, 4)!=null? jTable3.getValueAt(index, 4).toString(): "");
        jTextField12.setText(jTable3.getValueAt(index, 5).toString());
    }//GEN-LAST:event_jTable3MousePressed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        int index = jTable2.getSelectedRow();
        if(index==-1)return;
        try{
            conn.prepareStatement("UPDATE \"Мастер\" "
                + "SET \"ID_мастера\"=" + jTable2.getValueAt(index, 0).toString()
                + ", \"Фамилия мастера\"='" + jTextFieldFamily.getText() + "', "
                + "\"Имя мастера\"='" + jTextFieldName.getText() + "', "
                + "\"Отчество мастера\"='" + jTextFieldOtchestvo.getText() + "', "
                + "\"Email мастера\"='" + jTextFieldEmail.getText() + "', "
                + "\"Телефон мастера\"='" + jTextFieldPhone.getText() + "' "
                + " WHERE \"ID_мастера\"=" + jTable2.getValueAt(index, 0).toString()).executeUpdate();

            jTable2.setValueAt(jTextFieldFamily.getText(), index, 1);
            jTable2.setValueAt(jTextFieldName.getText(), index, 2);
            jTable2.setValueAt(jTextFieldOtchestvo.getText(), index, 3);
            jTable2.setValueAt(jTextFieldEmail.getText(), index, 4);
            jTable2.setValueAt(jTextFieldPhone.getText(), index, 5);
        }catch(Exception e){e.printStackTrace();};
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {//удаление мастера из таблиц Мастер и Пользователи
            if(jTable2.getSelectedRow()==-1)return;
            conn.prepareStatement("Delete from \"Users\" where \"ID_мастера\"=" + jTable2.getModel().getValueAt(jTable2.getSelectedRow(), 0).toString()).executeUpdate();
            DeleteRowInJTable(jTable2, "Мастер");
            updateTable("Users");
            jTextFieldLogin.setText(""); jPasswordField2.setText(""); jPasswordField1.setText("");
            jTextFieldFamily.setText(""); jTextFieldName.setText(""); jTextFieldOtchestvo.setText("");
            jTextFieldEmail.setText(""); jTextFieldPhone.setText("");
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextFieldLoginFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldLoginFocusLost
        try{
            ResultSet res = conn.createStatement().executeQuery("Select \"login\" from \"Users\" where \"login\"='" +  jTextFieldLogin.getText() + "'");
            if(res.next()){isTrueLogin = false; jTextFieldLogin.setForeground(Color.red);}
            else{ isTrueLogin = true; jTextFieldLogin.setForeground(Color.black);}
        }
        catch(Exception e){};
    }//GEN-LAST:event_jTextFieldLoginFocusLost

    private void jTextFieldLoginFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldLoginFocusGained
        jTextFieldLogin.setForeground(Color.black);
    }//GEN-LAST:event_jTextFieldLoginFocusGained

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Regestration();
        try{
            updateTable("Мастер");
            updateTable("Users");}
        catch(Exception e){};
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jPasswordField2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordField2FocusLost
        if(String.valueOf(jPasswordField2.getPassword()).equals(String.valueOf(jPasswordField1.getPassword()))){
            jPasswordField2.setForeground(Color.black); equalsPasswords = true;}
        else {jPasswordField2.setForeground(Color.red); equalsPasswords = false;}
    }//GEN-LAST:event_jPasswordField2FocusLost

    private void jPasswordField2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordField2FocusGained
        jPasswordField2.setForeground(Color.black);
    }//GEN-LAST:event_jPasswordField2FocusGained

    private void jTextFieldPhoneKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPhoneKeyReleased
        if( evt.getKeyChar()== '\b' && jTextFieldPhone.getText().length()==0 ){ return; } //если пуста строка и нажали backspace
        if( jTextFieldPhone.getText().length() > 11 ){
            jTextFieldPhone.setText(jTextFieldPhone.getText().substring(0, jTextFieldPhone.getText().length()-1));
            return;
        }
        char elem = jTextFieldPhone.getText().charAt(jTextFieldPhone.getText().length()-1);//получаем последний символ строки
        if(!('0' <= elem && elem <= '9')) //это не число
        jTextFieldPhone.setText(jTextFieldPhone.getText().length()>1? jTextFieldPhone.getText().substring(0, jTextFieldPhone.getText().length()-1): "");
    }//GEN-LAST:event_jTextFieldPhoneKeyReleased

    private void jTextFieldPhoneKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPhoneKeyPressed
        if(evt.getKeyChar()=='\n'){ Regestration(); }
        if( evt.getKeyChar()== '\b' && jTextFieldPhone.getText().length()==0 ){ return; } //если пуста строка и нажали backspace
        if( jTextFieldPhone.getText().length() > 11 ){
            jTextFieldPhone.setText(jTextFieldPhone.getText().substring(0, jTextFieldPhone.getText().length()-1));
            return;
        }
        char elem = jTextFieldPhone.getText().charAt(jTextFieldPhone.getText().length()-1);//получаем последний символ строки
        if(!('0' <= elem && elem <= '9')) //это не число
        jTextFieldPhone.setText(jTextFieldPhone.getText().length()>1? jTextFieldPhone.getText().substring(0, jTextFieldPhone.getText().length()-1): "");
    }//GEN-LAST:event_jTextFieldPhoneKeyPressed

    private void jTable2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MousePressed
        jTextFieldLogin.setText(""); jPasswordField2.setText(""); jPasswordField1.setText("");
        jTextFieldFamily.setText(""); jTextFieldName.setText(""); jTextFieldOtchestvo.setText("");
        jTextFieldEmail.setText(""); jTextFieldPhone.setText("");

        int index = jTable2.getSelectedRow();

        ResultSet res = null;
        try {
            res = conn.createStatement().executeQuery("SELECT login FROM \"Users\" where \"ID_мастера\"=" + jTable2.getValueAt(index, 0).toString());
            res.next();
            jTextFieldLogin.setText(res.getString("login"));
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        jTextFieldFamily.setText(jTable2.getValueAt(index, 1).toString());
        jTextFieldName.setText(jTable2.getValueAt(index, 2).toString());
        jTextFieldOtchestvo.setText(jTable2.getValueAt(index, 3).toString());
        jTextFieldEmail.setText(jTable2.getValueAt(index, 4)!=null? jTable2.getValueAt(index, 4).toString(): "");
        jTextFieldPhone.setText(jTable2.getValueAt(index, 5).toString());
    }//GEN-LAST:event_jTable2MousePressed

    private void jTabbedPane2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane2StateChanged
        if(jTabbedPane2.getSelectedIndex() == 1){
            Request request = new Request(conn);
            request.setVisible(true);
            request.setParent(this);
            this.setVisible(false);
            jTabbedPane2.setSelectedIndex(0);
        }
    }//GEN-LAST:event_jTabbedPane2StateChanged

    private void jComboBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox5ActionPerformed
        DefaultTableModel model = null;
        ResultSet res = null;
        try {
            if(jComboBox5.getSelectedItem().toString().equals("Все заказы")){
                model = (DefaultTableModel)jTable4.getModel();
                model.setRowCount(0);
                res = conn.createStatement().executeQuery("SELECT \"ID_заказа\", \n" +
                        " \"Гарантийный ремонт\", \n" +
                        "(select \"Фамилия клиента\"||' '|| SUBSTR(\"Имя клиента\",1,1)|| '.' ||SUBSTR(\"Отчество клиента\",1,1) || '.' as Клиент From \"Клиент\"\n" +
                        " where Клиент.\"ID_клиента\"=Заказ.\"ID_клиента\"),\n" +
                        "(select \"Фамилия мастера\"||' '|| SUBSTR(\"Имя мастера\",1,1)|| '.' ||SUBSTR(\"Отчество мастера\",1,1) || '.' as Мастер From \"Мастер\"\n" +
                        " where Мастер.\"ID_мастера\"=Заказ.\"ID_мастера\"),\n" +
                        "\"Проблема клиента\", \"Предыстория поломки\", \"Дата принятия заказа\", \"Дата окончания заказа\", \"Гарантия на ремонт\", \n" +
                        "\"Общая стоимость заказа\", \n" +
                        "\"Статус выполнения\", \n" +
                        "(select \"Название типа\"||' '||\"Портативность\" as \"Тип часов\" from \"Тип часов\" where Заказ.\"Тип часов\"=\"Тип часов\".\"ID_типа\"),\n" +
                        "(select \"Название пола\" from \"Пол часов\" where Заказ.\"Пол часов\"=\"Пол часов\".\"ID_пола\"),\n" +
                        "(select \"Название производителя\" from \"Производитель часов\" where Заказ.\"Производитель часов\"=\"Производитель часов\".\"ID_производителя\"), \n" +
                        "(select \"Название модели\" from \"Модель часов\" where Заказ.\"Модель часов\"=\"Модель часов\".\"ID_модели\")\n" +
                        "	FROM \"Заказ\";");
            }
            else{
                model = (DefaultTableModel)jTable4.getModel(); 
                model.setRowCount(0);
                res = conn.createStatement().executeQuery("SELECT \"ID_заказа\", \n" +
                    " \"Гарантийный ремонт\", \n" +
                    "(select \"Фамилия клиента\"||' '|| SUBSTR(\"Имя клиента\",1,1)|| '.' ||SUBSTR(\"Отчество клиента\",1,1) || '.' as Клиент From \"Клиент\"\n" +
                    " where Клиент.\"ID_клиента\"=Заказ.\"ID_клиента\"),\n" +
                    "(select \"Фамилия мастера\"||' '|| SUBSTR(\"Имя мастера\",1,1)|| '.' ||SUBSTR(\"Отчество мастера\",1,1) || '.' as Мастер From \"Мастер\"\n" +
                    " where Мастер.\"ID_мастера\"=Заказ.\"ID_мастера\"),\n" +
                    "\"Проблема клиента\", \"Предыстория поломки\", \"Дата принятия заказа\", \"Дата окончания заказа\", \"Гарантия на ремонт\", \n" +
                    "\"Общая стоимость заказа\", \n" +
                    "\"Статус выполнения\", \n" +
                    "(select \"Название типа\"||' '||\"Портативность\" as \"Тип часов\" from \"Тип часов\" where Заказ.\"Тип часов\"=\"Тип часов\".\"ID_типа\"),\n" +
                    "(select \"Название пола\" from \"Пол часов\" where Заказ.\"Пол часов\"=\"Пол часов\".\"ID_пола\"),\n" +
                    "(select \"Название производителя\" from \"Производитель часов\" where Заказ.\"Производитель часов\"=\"Производитель часов\".\"ID_производителя\"), \n" +
                    "(select \"Название модели\" from \"Модель часов\" where Заказ.\"Модель часов\"=\"Модель часов\".\"ID_модели\")\n" +
                    "	FROM \"Заказ\" where \"Статус выполнения\"='" + jComboBox5.getSelectedItem().toString() + "'");
            }
            while(res.next()){
                Object[] row = {res.getInt(1), res.getBoolean(2), res.getString(3), res.getString(4),
                    res.getString(5), res.getString(6), res.getString(7), res.getString(8),
                    res.getString(9), res.getString(10),res.getString(11), res.getString(12),
                    res.getString(13), res.getString(14), res.getString(15)
                };
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jComboBox5ActionPerformed

    private void jComboBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox6ActionPerformed
        try {
            ResultSet res = null;
            DefaultTableModel model = (DefaultTableModel)jTable6.getModel();
            model.setRowCount(0);
            if(jComboBox6.getSelectedItem().toString().equals("Все")){
                res = conn.createStatement().executeQuery("select \n" +
                                                        "(select \"Название материала\" from \"Расходный материал\" where \"Работа\"=\"ID_работы\"), \n" +
                                                        "(select \"Стоимость\" from \"Расходный материал\" where \"Работа\"=\"ID_работы\"), \n" +
                                                        "(select \"Количество инструмента\" from \"Расходный материал\" where \"Работа\"=\"ID_работы\"),\n" +
                                                        "count(\"Работа\")\n" +
                                                        "from \"Заказ и работы\" group by \"Работа\"");
            }
            else{
                String[] arr = jComboBox6.getSelectedItem().toString().split(" ");
                res = conn.createStatement().executeQuery("select\n" +
                                "            (select \"Название материала\" from \"Расходный материал\" where \"Работа\"=\"ID_работы\"),\n" +
                                "            (select \"Стоимость\" from \"Расходный материал\" where \"Работа\"=\"ID_работы\"),\n" +
                                "            (select \"Количество инструмента\" from \"Расходный материал\" where \"Работа\"=\"ID_работы\"),\n" +
                                "            count(\"Работа\")\n" +
                                "            from \"Заказ и работы\", \"Заказ\"\n" +
                                "            where \"ID_мастера\"=" + arr[0] + " " +
                                "            group by \"Работа\"");
            }
            while(res.next()){
                Object[] row = {res.getString(1), res.getString(2), res.getString(3), res.getString(4)};
                model.addRow(row);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jComboBox6ActionPerformed
    private void filljTable5() throws SQLException {
        DefaultTableModel model = (DefaultTableModel) jTable5.getModel();
        model.setRowCount(0);
        ResultSet res = null;
        if(jComboBox1.getSelectedItem().equals("Все") && jComboBox2.getSelectedItem().equals("Все")){
            res = conn.createStatement().executeQuery("SELECT \"ID_мастера\", "
                    + "(select \"Фамилия мастера\" || ' ' || \"Имя мастера\" ||' '||\"Отчество мастера\" from \"Мастер\" "
                    + "where \"Мастер и часы\".\"ID_мастера\"=\"Мастер\".\"ID_мастера\") as \"Мастер\", " +
                    "(select \"Название типа\" || ' ' || \"Портативность\" from \"Тип часов\" "
                    + "where \"Мастер и часы\".\"ID_типа\"=\"Тип часов\".\"ID_типа\") as Часы "
                    + " FROM \"Мастер и часы\";");
        }
        else if(jComboBox1.getSelectedItem().equals("Все") && !jComboBox2.getSelectedItem().equals("Все")){
            res = conn.createStatement().executeQuery("SELECT \"ID_мастера\", " +
                "(select \"Фамилия мастера\" || ' ' || \"Имя мастера\" ||' '||\"Отчество мастера\" from \"Мастер\" where \"Мастер и часы\".\"ID_мастера\"=\"Мастер\".\"ID_мастера\") as \"Мастер\",\n" +
                "(select \"Название типа\" || ' ' || \"Портативность\" from \"Тип часов\" \n" +
                " where \"Мастер и часы\".\"ID_типа\"=\"Тип часов\".\"ID_типа\" and \"Портативность\"='" + jComboBox2.getSelectedItem() + "') as Часы\n" +
                "	FROM \"Мастер и часы\"");
        }
        else if(!jComboBox1.getSelectedItem().equals("Все") && jComboBox2.getSelectedItem().equals("Все")){
            res = conn.createStatement().executeQuery("SELECT \"ID_мастера\", " +
                "(select \"Фамилия мастера\" || ' ' || \"Имя мастера\" ||' '||\"Отчество мастера\" from \"Мастер\" where \"Мастер и часы\".\"ID_мастера\"=\"Мастер\".\"ID_мастера\") as \"Мастер\",\n" +
                "(select \"Название типа\" || ' ' || \"Портативность\" from \"Тип часов\" \n" +
                " where \"Мастер и часы\".\"ID_типа\"=\"Тип часов\".\"ID_типа\" and \"Название типа\"='" + jComboBox1.getSelectedItem() + "') as Часы\n" +
                "	FROM \"Мастер и часы\"");
        }
        else if(!jComboBox1.getSelectedItem().equals("Все") && !jComboBox2.getSelectedItem().equals("Все")){
            res = conn.createStatement().executeQuery("SELECT \"ID_мастера\", " +
                "(select \"Фамилия мастера\" || ' ' || \"Имя мастера\" ||' '||\"Отчество мастера\" from \"Мастер\" where \"Мастер и часы\".\"ID_мастера\"=\"Мастер\".\"ID_мастера\") as \"Мастер\",\n" +
                "(select \"Название типа\" || ' ' || \"Портативность\" from \"Тип часов\" \n" +
                " where \"Мастер и часы\".\"ID_типа\"=\"Тип часов\".\"ID_типа\" and \"Название типа\"='" + jComboBox1.getSelectedItem() + "'"
                        + " and \"Портативность\"='" + jComboBox2.getSelectedItem() + "') as Часы\n" +
                "	FROM \"Мастер и часы\"");
        }

        if(res!=null){
            while(res.next()){
                Object[] row = {res.getString(1), res.getString(2), res.getString(3)};
                if(res.getString(3)!=null)
                    model.addRow(row);
            }
        }
    }    
    private void DeleteRowInJTable(javax.swing.JTable jTable, String Table){
        try{
            if(jTable.getRowCount()!=0){
                if(jTable.getSelectedRow()==-1)return;
                int variant = JOptionPane.showConfirmDialog(null, "Вы точно хотите удалить?", "Удаление", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                if(variant > 0)return;//no 
                DefaultTableModel model = (DefaultTableModel)jTable.getModel();
                conn.prepareStatement("delete from \"" + Table + "\""
                        + " where " + (Table.equals("Клиент")? "\"ID_клиента\"":"\"ID_мастера\"") + "=" + model.getValueAt(jTable.getSelectedRow(), 0).toString()).executeUpdate();

                model.removeRow(jTable.getSelectedRow());
            }
        }catch(Exception e){e.printStackTrace();};
    }    
    public void Regestration(){
        //проверка логина на символы кириллицы 
        if(!isTrueLogin){System.out.println("Такой логин уже есть");return;}
        if(!equalsPasswords){System.out.println("Пароли разные!!!");return;}
        if(!jTextFieldLogin.getText().matches("[A-Za-z0-9]+")){System.out.println("Нужно использовать только символы латиницы в логине!!!");return;}
        if(!jTextFieldFamily.getText().matches("[^A-Za-z0-9]+")){System.out.println("В фамилии нужно использовать только символы кириллицы!!!");return;}
        if(!jTextFieldName.getText().matches("[^A-Za-z0-9]+")){System.out.println("В имени нужно использовать только символы кириллицы!!!");return;}
        if(jTextFieldEmail.getText().length() != 0 &&
                !jTextFieldEmail.getText().matches("^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$+")){
            System.out.println("Это не почта, а хуй знает что!!!");return;}
        if(jTextFieldPhone.getText().length() != 11 ||
                !jTextFieldPhone.getText().matches("[0-9]+")){System.out.println("В поле номер телефона должны быть цифры!!!");return;}
        
        try {
            conn.prepareStatement("INSERT INTO \"Мастер\"(" +
                "\"ID_мастера\", \"Фамилия мастера\", \"Имя мастера\", \"Отчество мастера\", \"Email мастера\", \"Телефон мастера\")\n" +
                "VALUES ((select MAX(\"ID_мастера\") from \"Мастер\")+1, "
                        + "'" + jTextFieldFamily.getText() + "', "
                        + "'" + jTextFieldName.getText() + "', "
                        + "'" + jTextFieldOtchestvo.getText() + "', "
                        + "'" + (jTextFieldEmail.getText().length() > 0? jTextFieldEmail.getText(): "") + "', "
                        + "'" + jTextFieldPhone.getText() + "')").executeUpdate();
                    } 
        catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            //Добавление нового мастера
            conn.prepareStatement("INSERT INTO \"Users\"(login, hash_passw, \"ID_мастера\", \"Last_LogIn\", \"Last_LogOut\", \"Create\") "
                    + "VALUES ('" + jTextFieldLogin.getText() + "', "
                            + "'" + md5(String.valueOf(jPasswordField2.getPassword())) + "', "
                                    + "(select MAX(\"ID_мастера\") from \"Мастер\"), "
                                    + "NULL, NULL, "
                                    + "(SELECT date_trunc('second', now()::timestamp with time zone)))").executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Регистрация успешна!!!");
        jTextFieldLogin.setText(""); jPasswordField2.setText(""); jPasswordField1.setText("");
        jTextFieldFamily.setText(""); jTextFieldName.setText(""); jTextFieldOtchestvo.setText("");
        jTextFieldEmail.setText(""); jTextFieldPhone.setText("");
    }    
    public String md5(String in) {
            String result = null;
            try{
                MessageDigest digest = MessageDigest.getInstance("MD5");
                digest.reset();
                digest.update(in.getBytes());
                BigInteger bigInt = new BigInteger(1, digest.digest());
                result = bigInt.toString(16);
            } catch (NoSuchAlgorithmException e) {e.printStackTrace();}
            return result;
        }
    public void setParent(Login object){
        parent = object;
    }
    public void setParent(Request object){
        parent = object;
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
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin().setVisible(true);
            }
        });
    }
    private javax.swing.JFrame parent = null;
    private static Connection conn = null;
    private boolean isTrueLogin = true, equalsPasswords = false;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldFamily;
    private javax.swing.JTextField jTextFieldLogin;
    private javax.swing.JTextField jTextFieldName;
    private javax.swing.JTextField jTextFieldOtchestvo;
    private javax.swing.JTextField jTextFieldPhone;
    // End of variables declaration//GEN-END:variables
}
