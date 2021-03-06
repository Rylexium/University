package my_package;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Request extends javax.swing.JFrame {
    public Request(Connection _conn) {
        initComponents();
        conn = _conn;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Family = new javax.swing.JTextField();
        Name = new javax.swing.JTextField();
        Othechstvo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Phone = new javax.swing.JTextField();
        Email = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jRadioButton1 = new javax.swing.JRadioButton();
        jTextField8 = new javax.swing.JTextField();
        jRadioButton2 = new javax.swing.JRadioButton();
        jTextField9 = new javax.swing.JTextField();
        jComboBox3 = new javax.swing.JComboBox<>();
        jTextField4 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("?????????????????? ???? ???????????? ??????????");
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setMinimumSize(new java.awt.Dimension(670, 480));
        setSize(new java.awt.Dimension(670, 480));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("??????????????");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("??????");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("????????????????");

        Family.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Family.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                FamilyKeyReleased(evt);
            }
        });

        Name.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                NameKeyReleased(evt);
            }
        });

        Othechstvo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Othechstvo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                OthechstvoKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("?????????? ????????????????");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("?????????????????????? ??????????");

        Phone.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Phone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                PhoneKeyReleased(evt);
            }
        });

        Email.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("\"??????\" ??????????");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("?????????????????????? ??????????????");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("???????????????? ??????????????");

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("?????? ??????????");

        jButton1.setText("????");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("????????????");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jComboBox2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { }));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("?????????????????????????? ??????????");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("???????????? ??????????");

        jTextField6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jTextField7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jRadioButton1.setText("?????????????????????? ????????????");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jTextField8.setText("ID_????????????");
        jTextField8.setEnabled(false);
        jTextField8.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField8FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField8FocusLost(evt);
            }
        });
        jTextField8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField8KeyPressed(evt);
            }
        });

        jRadioButton2.setText("???????????? ?????? ?????? ??????????");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jTextField9.setText("ID_????????????");
        jTextField9.setEnabled(false);
        jTextField9.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField9FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField9FocusLost(evt);
            }
        });
        jTextField9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField9KeyPressed(evt);
            }
        });

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID_????????????", "??????????" }));
        jComboBox3.setEnabled(false);
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        jTextField4.setText("?????????? ????????????????");
        jTextField4.setEnabled(false);
        jTextField4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField4FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField4FocusLost(evt);
            }
        });
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField4KeyPressed(evt);
            }
        });

        jButton4.setText("??????????");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jComboBox4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("??????????????????????????");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField6)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jRadioButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jRadioButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField8)
                            .addComponent(jTextField9)
                            .addComponent(jTextField4)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox2, 0, 279, Short.MAX_VALUE)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Name)
                            .addComponent(Othechstvo)
                            .addComponent(Phone)
                            .addComponent(Email)
                            .addComponent(Family))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(3, 3, 3)
                        .addComponent(jScrollPane1)
                        .addGap(5, 5, 5)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel1))
                            .addComponent(Family))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(Othechstvo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Phone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Email)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButton1)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButton2)
                            .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addGap(168, 168, 168))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private boolean isNumber(char symbol){
        return '0'<=symbol && symbol<='9';
    }
    private void deleteInvalidSymbol(javax.swing.JTextField jTextField){//?????????????? ?????????????????? ????????????, ???????? ???? ??????????
        char elem = jTextField.getText().charAt(jTextField.getText().length()-1);
        if(isNumber(elem))
            jTextField.setText(jTextField.getText().length()>1? jTextField.getText().substring(0, jTextField.getText().length()-1): "");
        
    }
    private void PhoneKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PhoneKeyReleased
        if( evt.getKeyChar()== '\b' && Phone.getText().length()==0 ){ return; } //???????? ?????????? ???????????? ?? ???????????? backspace
        if( Phone.getText().length() > 11 ){ 
            Phone.setText(Phone.getText().substring(0, Phone.getText().length()-1));
            return; 
        }
        char elem = Phone.getText().charAt(Phone.getText().length()-1);//???????????????? ?????????????????? ???????????? ????????????
        if(!isNumber(elem)) //?????? ???? ??????????
            Phone.setText(Phone.getText().length()>1? Phone.getText().substring(0, Phone.getText().length()-1): "");
    }//GEN-LAST:event_PhoneKeyReleased

    private void FamilyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FamilyKeyReleased
        deleteInvalidSymbol(Family);      
        //???????????????? ???????????? ???? ?????????????? ???????? ?? ????????.????????????????!!!! ?????????????? ???? ???????? ?????????? ??????????!!!
    }//GEN-LAST:event_FamilyKeyReleased

    private void NameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NameKeyReleased
        deleteInvalidSymbol(Name);
    }//GEN-LAST:event_NameKeyReleased

    private void OthechstvoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_OthechstvoKeyReleased
        deleteInvalidSymbol(Othechstvo);
    }//GEN-LAST:event_OthechstvoKeyReleased
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
           jTextArea1.setText("");
           jTextArea2.setText("");
           Family.setText("");
           Name.setText("");
           Othechstvo.setText("");
           Phone.setText("");
           Email.setText("");
           jTextField6.setText("");
           jTextField7.setText("");
           jComboBox1.setSelectedIndex(0);
           jComboBox2.setSelectedIndex(0);        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        close = 0;
        if(jTextArea1.getText().length() > 0 ||
           jTextArea2.getText().length() > 0 ||
           Family.getText().length() > 0 ||
           Name.getText().length() > 0 ||
           Othechstvo.getText().length() > 0 ||
           Phone.getText().length() > 0 ||
           Email.getText().length() > 0 ||
           jTextField6.getText().length() > 0 ||
           jTextField7.getText().length() > 0||
           (jTextField8.getText().length() > 0 && !jTextField8.getText().equals("ID_????????????"))|| 
           (jTextField9.getText().length() > 0 && !jTextField9.getText().equals("ID_????????????"))||
           (jTextField4.getText().length() > 0 && !jTextField4.getText().equals("?????????? ????????????????")))
        {
            close = JOptionPane.showConfirmDialog(null, "???? ?????????? ???????????? ???????????????", "", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_formWindowClosing

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        if(close == 0)parent.setVisible(true);
        else this.setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        try {
            ResultSet res = conn.createStatement().executeQuery("SELECT \"???????????????? ????????\" FROM \"?????? ??????????\"");
            if(jComboBox1.getItemCount()==0){
                while(res.next()){
                    jComboBox1.addItem(res.getString(1));
                }
            }
            res = conn.createStatement().executeQuery("SELECT DISTINCT \"???????????????? ????????\" FROM \"?????? ??????????\"");
            if(jComboBox2.getItemCount()==0){
                while(res.next()){
                    jComboBox2.addItem(res.getString(1));
                }
            }
            res = conn.createStatement().executeQuery("SELECT DISTINCT \"??????????????????????????\" FROM \"?????? ??????????\"");
            if(jComboBox4.getItemCount()==0){
                while(res.next()){
                    jComboBox4.addItem(res.getString(1));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Request.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_formWindowOpened
    private int TypeOfClock(){
        int first = 1, second = 1;
        switch(jComboBox2.getSelectedItem().toString()){
            case "????????????????????????":first = 1;break;
            case "??????????????????":first = 2;break;
            case "??????????????????????":first = 3; break;
        }
        
        switch(jComboBox4.getSelectedItem().toString()){
            case "????????????????????":second=1;break;
            case "????????????????":second=2;break;
            case "??????????????????":second=3;break;
            case "??????????????????":second=4;break;
        }
        return first*10+second;
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(jTextArea1.getText().length() == 0 ||
           Family.getText().length() == 0 ||
           Name.getText().length() == 0 ||
           Phone.getText().length() == 0 ||
           jTextField6.getText().length() == 0 ||
           jTextField7.getText().length() == 0)return;
        
        
        try{
            if(!jRadioButton1.isSelected()){
            ResultSet id_zakaza = conn.createStatement().executeQuery("select max(\"ID_????????????\")+1 from \"??????????\"");id_zakaza.next();
            PreparedStatement pst = conn.prepareStatement("INSERT INTO \"??????????\"(" +
                "\"ID_????????????\", \"?????????????????????? ????????????\", \"ID_??????????????\", "
                + "\"ID_??????????????\", \"???????????????? ??????????????\", \"?????????????????????? ??????????????\", "
                + "\"???????? ???????????????? ????????????\", \"???????? ?????????????????? ????????????\", \"???????????????? ???? ????????????\", "
                + "\"?????????? ?????????????????? ????????????\", \"???????????? ????????????????????\", \"?????? ??????????\", \"?????? ??????????\", "
                + "\"?????????????????????????? ??????????\", \"???????????? ??????????\") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            // ID_????????????
            pst.setInt(1, Integer.parseInt(id_zakaza.getString(1)));
                //???? ?????????????????????? ????????????                
                pst.setBoolean(2, false); 
                if(!jRadioButton2.isSelected()){//?????? ?????????????? ?????????????? ????????????????????????
                    //ID_??????????????. 
                    ResultSet id_klient = conn.createStatement().executeQuery("(Select Max(\"ID_??????????????\")+1 from \"????????????\")");id_klient.next();  
                    //?????????????????????? ??????????????
                    PreparedStatement regestration = conn.prepareStatement("INSERT INTO \"????????????\"(" +
                          " \"ID_??????????????\", \"?????????????? ??????????????\", \"?????? ??????????????\", \"???????????????? ??????????????\", \"Email ??????????????\", \"?????????????? ??????????????\") " +
                          " VALUES (?, ?, ?, ?, ?, ?)");

                    regestration.setInt(1, Integer.parseInt(id_klient.getString(1)));
                    regestration.setString(2, Family.getText());
                    regestration.setString(3, Name.getText());
                    regestration.setString(4, (Othechstvo.getText().length() > 0 ? Othechstvo.getText():""));
                    regestration.setString(5, (Email.getText().length() > 0 ? Email.getText():""));
                    regestration.setString(6, Phone.getText());
                    regestration.executeUpdate();
                    //?????????? ?????????????????????? ?????? ID ?? ?????????????????? ?? ??????????
                    pst.setInt(3, Integer.parseInt(id_klient.getString(1)));
                }
                else{//?????????? ???????????? ????????, ?????????????? ???????????? ???????????????? ?????? ID
                    pst.setInt(3, Integer.parseInt(id_client));
                }
                
                pst.setNull(4, 0);
                pst.setString(5, jTextArea1.getText());
                pst.setString(6, jTextArea2.getText());

                ResultSet date = conn.createStatement().executeQuery("select now()::date"); date.next();
                pst.setDate(7, date.getDate(1));
                pst.setNull(8, 0);
                pst.setNull(9, 0);
                pst.setNull(10, 0);
                pst.setString(11, "?? ??????????????????");
                pst.setInt(12, TypeOfClock());
                pst.setInt(13, jComboBox1.getSelectedIndex()+1);

                ResultSet check = conn.createStatement().executeQuery("select \"ID_??????????????????????????\" from \"?????????????????????????? ??????????\" "
                                                        + "where \"???????????????? ??????????????????????????\"='" + jTextField6.getText() + "'");
                if(!check.next()){
                //???????????????? ?????????????????????????? ???????? ?????? ??????
                    ResultSet id_proizvod = conn.createStatement().executeQuery("select max(\"ID_??????????????????????????\")+1 from \"?????????????????????????? ??????????\"");
                    id_proizvod.next();
                    conn.prepareStatement("INSERT INTO \"?????????????????????????? ??????????\"(\n" +
                            "	\"ID_??????????????????????????\", \"???????????????? ??????????????????????????\")\n" +
                            "	VALUES ((select max(\"ID_??????????????????????????\")+1 from \"?????????????????????????? ??????????\"), '" + jTextField6.getText() + "');").executeUpdate();

                    pst.setInt(14, id_proizvod.getInt(1));
                }
                else{
                    pst.setInt(14, Integer.parseInt(check.getString(1)));
                }

                check = conn.createStatement().executeQuery("select \"ID_????????????\" from \"???????????? ??????????\" "
                                                        + "where \"???????????????? ????????????\"='" + jTextField7.getText() + "'");
                if(!check.next()){
                    //???????????????? ???????????? ???????? ?????? ??????
                    ResultSet id_modeli = conn.createStatement().executeQuery("select Max(\"ID_????????????\")+1 from \"???????????? ??????????\"");
                    id_modeli.next();
                    conn.prepareStatement("INSERT INTO \"???????????? ??????????\"(\n" +
                                    "	\"ID_????????????\", \"???????????????? ????????????\")\n" +
                                    "	VALUES ((select Max(\"ID_????????????\")+1 from \"???????????? ??????????\"), '"+ jTextField7.getText() +"');").executeUpdate();
                    pst.setInt(15, id_modeli.getInt(1));
                }
                else{
                    pst.setInt(15, Integer.parseInt(check.getString(1)));
                }
                pst.executeUpdate();
            }
            else{
                //?????????????????????? ????????????
                conn.prepareStatement("INSERT INTO \"??????????\"(" +
                    "	\"ID_????????????\", \"?????????????????????? ????????????\", \"ID_??????????????\", \"ID_??????????????\", \"???????????????? ??????????????\", \"?????????????????????? ??????????????\", \n" +
                    "	\"???????? ???????????????? ????????????\", \"???????? ?????????????????? ????????????\", \"???????????????? ???? ????????????\", \"?????????? ?????????????????? ????????????\", \n" +
                    "	\"???????????? ????????????????????\", \"?????? ??????????\", \"?????? ??????????\", \"?????????????????????????? ??????????\", \"???????????? ??????????\")\n" +
                    "	VALUES ((select max(\"ID_????????????\")+1 from \"??????????\"), " +
                    "	true, (select \"ID_??????????????\" from \"??????????\" where \"ID_????????????\"=" + jTextField8.getText() +"), \n" +
                    "	null, '"+ jTextArea1.getText() +"', '"+ jTextArea2.getText() +"', (select now()::date), null, null, null, '?? ??????????????????', " +
                    "	(select \"?????? ??????????\" from \"??????????\" where \"ID_????????????\"=" + jTextField8.getText() +"), \n" +
                    "	(select \"?????? ??????????\" from \"??????????\" where \"ID_????????????\"=" + jTextField8.getText() +"), \n" +
                    "	(select \"?????????????????????????? ??????????\" from \"??????????\" where \"ID_????????????\"=" + jTextField8.getText() +"), \n" +
                    "	(select \"???????????? ??????????\" from \"??????????\" where \"ID_????????????\"=" + jTextField8.getText() +"))").executeUpdate();
            }
            
           JOptionPane.showConfirmDialog(null, "?????????? ????????????????", "", JOptionPane.DEFAULT_OPTION);
           jTextArea1.setText("");
           jTextArea2.setText("");
           Family.setText("");
           Name.setText("");
           Othechstvo.setText("");
           Email.setText("");
           Phone.setText("");
           jTextField6.setText("");
           jTextField7.setText("");
        } catch (SQLException ex) {
            Logger.getLogger(Request.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    private void EnableClientComponents(boolean flag){
        Family.setEnabled(flag);
        Name.setEnabled(flag);
        Othechstvo.setEnabled(flag);
        Phone.setEnabled(flag);
        Email.setEnabled(flag);
    }
    private void EnableAllComponents(boolean flag){
        jTextArea1.setEnabled(flag);
        jTextArea2.setEnabled(flag);
        EnableClientComponents(!jRadioButton1.isSelected() && !jRadioButton2.isSelected());
        jTextField6.setEnabled(flag);
        jTextField7.setEnabled(flag);
        jComboBox1.setEnabled(flag);
        jComboBox2.setEnabled(flag);
        jComboBox4.setEnabled(flag);
        jRadioButton2.setEnabled(flag);
        jComboBox3.setEnabled(jRadioButton2.isSelected() && jRadioButton2.isEnabled());
        if(jComboBox3.getSelectedItem().equals("ID_????????????"))jTextField9.setEnabled(jRadioButton2.isSelected() && jRadioButton2.isEnabled());
        else jTextField4.setEnabled(jRadioButton2.isSelected() && jRadioButton2.isEnabled());
        
        jTextField8.setEnabled(!flag);
    }
    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        if(jRadioButton1.isSelected()) EnableAllComponents(false);
        else EnableAllComponents(true);
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jTextField8FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField8FocusGained
        if(jTextField8.getText().equals("ID_????????????")){jTextField8.setText("");}
    }//GEN-LAST:event_jTextField8FocusGained

    private void jTextField8FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField8FocusLost
        if(jTextField8.getText().equals("")){jTextField8.setText("ID_????????????");}
    }//GEN-LAST:event_jTextField8FocusLost

    private void jTextField9FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField9FocusGained
        if(jTextField9.getText().equals("ID_????????????")){jTextField9.setText("");}
    }//GEN-LAST:event_jTextField9FocusGained

    private void jTextField9FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField9FocusLost
        if(jTextField9.getText().equals("")){jTextField9.setText("ID_????????????");}
    }//GEN-LAST:event_jTextField9FocusLost

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        if(jRadioButton2.isSelected()){
           jTextField9.setEnabled(true);
           jComboBox3.setEnabled(true);
           jTextField4.setEnabled(true);
           EnableClientComponents(false);
           jComboBox3ActionPerformed(null);
       }
       else{ 
           jTextField9.setEnabled(false);
           jComboBox3.setEnabled(false);
           jTextField4.setEnabled(false);
           EnableClientComponents(true);
       }
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jTextField4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField4FocusGained
        if(jTextField4.getText().equals("?????????? ????????????????")){jTextField4.setText("");}
    }//GEN-LAST:event_jTextField4FocusGained

    private void jTextField4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField4FocusLost
        if(jTextField4.getText().equals("")){jTextField4.setText("?????????? ????????????????");}
    }//GEN-LAST:event_jTextField4FocusLost

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        if(jComboBox3.getSelectedItem().equals("ID_????????????")){
            jTextField9.setEnabled(true);
            jTextField4.setEnabled(false);
        }
        else{
            jTextField9.setEnabled(false);
            jTextField4.setEnabled(true);
        }
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        Family.setText("");
        Name.setText("");
        Othechstvo.setText("");
        Email.setText("");
        Phone.setText("");
        if(jRadioButton1.isSelected()){
            jComboBox1.setSelectedIndex(0);
            jComboBox2.setSelectedIndex(0);
            jComboBox4.setSelectedIndex(0);
            jTextField6.setText("");
            jTextField7.setText("");
            jTextArea1.setText("");
            jTextArea2.setText("");
        }
        try {
            ResultSet res = null;
            //?????????? ID. ?????????????????? ?????? ?????? ?? TextField ?? ???????????????????????? ?????? ??????.
            if(jComboBox3.getSelectedItem().toString().equals("ID_????????????") && jTextField9.isEnabled()){
                res = conn.createStatement().executeQuery("select \"????????????\".\"ID_??????????????\", \"?????????????? ??????????????\", \"?????? ??????????????\", \"???????????????? ??????????????\", "
                        + "\"Email ??????????????\", \"?????????????? ??????????????\" from \"????????????\", \"??????????\"\n" 
                        + " where \"??????????\".\"ID_??????????????\"=\"????????????\".\"ID_??????????????\" and \"ID_????????????\"=" + jTextField9.getText());               
            }        
            else if(jComboBox3.getSelectedItem().toString().equals("??????????") && jTextField4.isEnabled()){
                //?????????? ???? ???????????????? ??????????????
                res = conn.createStatement().executeQuery("SELECT * FROM \"????????????\" where \"?????????????? ??????????????\"='" + jTextField4.getText() + "'");
            }
            else{
                res = conn.createStatement().executeQuery("Select "
                        + "\"????????????\".\"ID_??????????????\",\"?????????????? ??????????????\", \"?????? ??????????????\", \"???????????????? ??????????????\", " +
                    "	\"Email ??????????????\", \"?????????????? ??????????????\", " +
                    "	(select \"???????????????? ????????\" from \"?????? ??????????\" where \"??????????\".\"?????? ??????????\"=\"?????? ??????????\".\"ID_????????\"),\n" +
                    "	(Select \"???????????????? ????????\" from \"?????? ??????????\" where \"??????????\".\"?????? ??????????\"=\"?????? ??????????\".\"ID_????????\"),\n" +
                    "	(Select \"??????????????????????????\" from \"?????? ??????????\" where \"?????? ??????????\".\"ID_????????\"=\"??????????\".\"?????? ??????????\"),\n" +
                    "	(Select \"???????????????? ??????????????????????????\" from \"?????????????????????????? ??????????\" where \"?????????????????????????? ??????????\".\"ID_??????????????????????????\"=\"??????????\".\"?????????????????????????? ??????????\"),\n" +
                    "	(Select \"???????????????? ????????????\" from \"???????????? ??????????\" where \"???????????? ??????????\".\"ID_????????????\"=\"??????????\".\"???????????? ??????????\")," +
                    "   (Select \"???????????????? ??????????????\" from \"??????????\" where \"ID_????????????\"=" + jTextField8.getText() + "),"+
                    "   (Select \"?????????????????????? ??????????????\" from \"??????????\" where \"ID_????????????\"=" + jTextField8.getText() + ")"+
                    "	from \"??????????\", \"????????????\" where \"????????????\".\"ID_??????????????\"=\"??????????\".\"ID_??????????????\" and \"ID_????????????\"=" + jTextField8.getText());
            }
            res.next();
            id_client = res.getString(1);
            Family.setText(res.getString(2));
            Name.setText(res.getString(3));
            Othechstvo.setText(res.getString(4));
            Email.setText(res.getString(5));
            Phone.setText(res.getString(6));
            if(res.getMetaData().getColumnCount() > 6){
                jComboBox1.setSelectedItem(res.getString(7));
                jComboBox2.setSelectedItem(res.getString(8));
                jComboBox4.setSelectedItem(res.getString(9));
                jTextField6.setText(res.getString(10));
                jTextField7.setText(res.getString(11));
                jTextArea1.setText(res.getString(12));
                jTextArea2.setText(res.getString(13));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Request.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField9KeyPressed
        if(evt.getKeyChar()=='\n')jButton4ActionPerformed(null);
    }//GEN-LAST:event_jTextField9KeyPressed

    private void jTextField4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyPressed
        if(evt.getKeyChar()=='\n')jButton4ActionPerformed(null);
    }//GEN-LAST:event_jTextField4KeyPressed

    private void jTextField8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField8KeyPressed
        if(evt.getKeyChar()=='\n')jButton4ActionPerformed(null);
    }//GEN-LAST:event_jTextField8KeyPressed
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Request.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Request.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Request.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Request.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Request(conn).setVisible(true);
            }
        });
    }
    
    void setParent(Login object){
        parent = object;
    }
    void setParent(Admin object){
        parent = object;
    }
    
    private javax.swing.JFrame parent = null;
    private static Connection conn = null;
    private int close = 0;
    private String id_client = null;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Email;
    private javax.swing.JTextField Family;
    private javax.swing.JTextField Name;
    private javax.swing.JTextField Othechstvo;
    private javax.swing.JTextField Phone;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables


}
