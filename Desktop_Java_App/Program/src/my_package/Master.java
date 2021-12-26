package my_package;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author aleks
 */
public class Master extends javax.swing.JFrame {

    public Master(String _id, String _fullName) {//Connection _conn) {
        initComponents();
        //conn = _conn;
        this.setTitle(_fullName);
        id = _id;
        fullName = _fullName;
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID_Заказа", "Гарантийный ремонт", "Клиент", "Проблема клиента", "Предыстория поломоки", "Дата принятия заказ", "Тип часов", "Название пола", "Название производителя", "Название модели"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(jTable1);

        jButton1.setText("Взять заказ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 925, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(0, 201, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Взять заказ", jPanel1);

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Все заказы", "Выполняется", "Выполнено", "Завершено" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jTable2.setAutoCreateRowSorter(true);
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID_Заказа", "Гарантийный ремонт", "Клиент", "Проблема клиента", "Предыстория поломоки", "Дата принятия заказ", "Дата окончания заказа", "Гарантия ремонта", "Общая стоимость заказа", "Статус выполнения", "Тип часов", "Название пола", "Название производителя", "Название модели"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, true, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable2);

        jScrollPane1.setViewportView(jList1);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Виды работ");

        jComboBox2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBox2.setMaximumRowCount(100);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Добавить работу");

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setText("Добавить");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton3.setText("Удалить");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Завершить заказ");
        jButton4.setEnabled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(290, 290, 290)
                        .addComponent(jLabel2)
                        .addGap(290, 290, 290))
                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2)
                            .addComponent(jButton3)))
                    .addComponent(jScrollPane1))
                .addContainerGap(113, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Мои заказы", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if(parent != null)parent.setVisible(true);
        try {
            conn.prepareStatement("UPDATE \"Users\" SET \"Last_LogOut\"=(SELECT date_trunc('second', now()::timestamp with time zone))"
                    + " WHERE \"ID_мастера\"=" + id).executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowClosing

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        if(jTable1.getHeight()!=0)return;
        if(jComboBox2.getHeight()==0)return;
        try {
            ResultSet res = conn.createStatement().executeQuery("SELECT \"ID_заказа\", \"Гарантийный ремонт\", \n" +
                        "(select \"Фамилия клиента\"||' '|| SUBSTR(\"Имя клиента\",1,1)|| '.' ||SUBSTR(\"Отчество клиента\",1,1) || '.' as Клиент\n" +
                        " From \"Клиент\" where \"Заказ\".\"ID_клиента\"=\"Клиент\".\"ID_клиента\"), \n" +
                        "\"Проблема клиента\", \"Предыстория поломки\", \"Дата принятия заказа\",\n" +
                        "(select \"Название типа\" || ' ' || \"Портативность\" from \"Тип часов\" where \"ID_типа\"=\"Заказ\".\"Тип часов\"), \n" +
                        "(select \"Название пола\" from \"Пол часов\" where \"ID_пола\"=\"Заказ\".\"Пол часов\"), \n" +
                        "(select \"Название производителя\" from \"Производитель часов\" where \"ID_производителя\"=\"Заказ\".\"Производитель часов\"), \n" +
                        "(select \"Название модели\" from \"Модель часов\" where \"ID_модели\"=\"Заказ\".\"Модель часов\")\n" +
                        "	FROM \"Заказ\" where \"Статус выполнения\"='В обработке'");
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            while(res.next()){
                Object[] row = {res.getInt(1), res.getBoolean(2), res.getString(3), res.getString(4), res.getString(5),
                                res.getString(6), res.getString(7), res.getString(8), res.getString(9), res.getString(10)};
                model.addRow(row);
            }
            res = conn.createStatement().executeQuery("select \"Название работы\" from \"Виды работ\"");
            while(res.next()){
                jComboBox2.addItem(res.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Master.class.getName()).log(Level.SEVERE, null, ex);
        }
        jComboBox1ActionPerformed(null);
    }//GEN-LAST:event_formWindowOpened

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        ResultSet res = null;
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0);
        if(evt != null){
            DefaultListModel list = new DefaultListModel();
            list.clear();
            jList1.setModel(list);
        }
        try {
            if(jComboBox1.getSelectedItem().toString().equals("Все заказы")){
                    res = conn.createStatement().executeQuery("SELECT \"ID_заказа\", \"Гарантийный ремонт\", \n" +
                            "(select \"Фамилия клиента\"||' '|| SUBSTR(\"Имя клиента\",1,1)|| '.' ||SUBSTR(\"Отчество клиента\",1,1) || '.' as Клиент\n" +
                            " From \"Клиент\" where \"Заказ\".\"ID_клиента\"=\"Клиент\".\"ID_клиента\"), \n" +
                            "\"Проблема клиента\", \"Предыстория поломки\", \"Дата принятия заказа\", \"Дата окончания заказа\", \"Гарантия на ремонт\", \"Общая стоимость заказа\", \"Статус выполнения\", \n" +
                            "(select \"Название типа\" from \"Тип часов\" where \"ID_типа\"=\"Заказ\".\"Тип часов\"), \n" +
                            "(select \"Название пола\" from \"Пол часов\" where \"ID_пола\"=\"Заказ\".\"Пол часов\"), \n" +
                            "(select \"Название производителя\" from \"Производитель часов\" where \"ID_производителя\"=\"Заказ\".\"Производитель часов\"), \n" +
                            "(select \"Название модели\" from \"Модель часов\" where \"ID_модели\"=\"Заказ\".\"Модель часов\")\n" +
                            "	FROM \"Заказ\" where \"ID_мастера\"=" + id);
            }
            else{
                res = conn.createStatement().executeQuery("SELECT \"ID_заказа\", \"Гарантийный ремонт\", \n" +
                        "(select \"Фамилия клиента\"||' '|| SUBSTR(\"Имя клиента\",1,1)|| '.' ||SUBSTR(\"Отчество клиента\",1,1) || '.' as Клиент\n" +
                        " From \"Клиент\" where \"Заказ\".\"ID_клиента\"=\"Клиент\".\"ID_клиента\"), \n" +
                        "\"Проблема клиента\", \"Предыстория поломки\", \"Дата принятия заказа\", \"Дата окончания заказа\", \"Гарантия на ремонт\", \"Общая стоимость заказа\", \"Статус выполнения\", \n" +
                        "(select \"Название типа\" from \"Тип часов\" where \"ID_типа\"=\"Заказ\".\"Тип часов\"), \n" +
                        "(select \"Название пола\" from \"Пол часов\" where \"ID_пола\"=\"Заказ\".\"Пол часов\"), \n" +
                        "(select \"Название производителя\" from \"Производитель часов\" where \"ID_производителя\"=\"Заказ\".\"Производитель часов\"), \n" +
                        "(select \"Название модели\" from \"Модель часов\" where \"ID_модели\"=\"Заказ\".\"Модель часов\")\n" +
                        "	FROM \"Заказ\" where \"Статус выполнения\"='"+ jComboBox1.getSelectedItem().toString() +"' and \"ID_мастера\"=" + id);
                }
            while(res.next()){
                Object[] row = {res.getInt(1), res.getBoolean(2), res.getString(3),res.getString(4),res.getString(5),res.getString(6),res.getString(7),
                    res.getString(8), res.getString(9), res.getString(10), res.getString(11), res.getString(12), res.getString(13), res.getString(14)};
                model.addRow(row);
            }
        }catch (SQLException ex) {
            Logger.getLogger(Master.class.getName()).log(Level.SEVERE, null, ex);
        } 
        if(jComboBox1.getSelectedItem().toString().equals("Выполняется"))jButton4.setEnabled(true);
        else jButton4.setEnabled(false);
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            if(jTable1.getSelectedRow()==-1)return;
            String type = (String) jTable1.getValueAt(jTable1.getSelectedRow(), 6);
            String[] arr = type.split(" ");
            ResultSet res = conn.createStatement().executeQuery("SELECT * FROM \"Мастер и часы\" \n" +
                                                            "where \"ID_мастера\"=" + id + " and \"ID_типа\"= (SELECT \"ID_типа\" FROM \"Тип часов\" \n" +
                                                            "  where \"Название типа\"='" + arr[0] + "' and \"Портативность\"='" + arr[1] + "')");
            if(!res.next())
            {
                JOptionPane.showConfirmDialog(null,"Вы не можете ремонтировать этот вид часов", "", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                return;
            }
            conn.prepareStatement("UPDATE \"Заказ\"\n" +
                    "	SET \"ID_мастера\"=" + id + ", \"Статус выполнения\"='Выполняется'\n" +
                    "	WHERE \"ID_заказа\"=" + jTable1.getValueAt(jTable1.getSelectedRow(), 0)).executeUpdate();
            DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
            model.removeRow(jTable1.getSelectedRow());
        } catch (SQLException ex) {
            Logger.getLogger(Master.class.getName()).log(Level.SEVERE, null, ex);
        } 
        formWindowOpened(null);//обновляем таблицы
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        if(jTable2.getSelectedRow()==-1||
                !jTable2.getValueAt(jTable2.getSelectedRow(), 9).equals("Выполняется"))return;
        try {
            ResultSet res = conn.createStatement().executeQuery("SELECT (select \"Название работы\" from \"Виды работ\" where \"ID_работы\"=\"Работа\") "
                    + "FROM \"Заказ и работы\" where \"Заказ\"=" + jTable2.getValueAt(jTable2.getSelectedRow(), 0));
            DefaultListModel list = new DefaultListModel();
            for(int i = 0;res.next();i++)
                list.addElement(res.getString(1));

            jList1.setModel(list);
        } catch (SQLException ex) {
            Logger.getLogger(Master.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTable2MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try { 
            ResultSet res = conn.createStatement().executeQuery("select \"Название работы\" from \"Виды работ\", \"Заказ и работы\" "
                    + "where \"ID_работы\"=\"Работа\" and \"Название работы\"='" + jComboBox2.getSelectedItem().toString() + "' and \"Заказ\"=" + jTable2.getValueAt(jTable2.getSelectedRow(), 0));
            if(res.next()){
                JOptionPane.showConfirmDialog(null,"Такой вид работы уже существует", "", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                return;
            }
            conn.prepareStatement("INSERT INTO public.\"Заказ и работы\"(\n" +
                    "	\"ID\", \"Заказ\", \"Работа\")\n" +
                    "	VALUES ((select max(\"ID\")+1 from \"Заказ и работы\"),"+ jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString() 
                    +", "+ (jComboBox2.getSelectedIndex()+1)+")").executeUpdate();
            conn.prepareStatement("UPDATE \"Заказ\" SET \"Общая стоимость заказа\" = (select sum(\"Стоимость работы\") from \"Заказ и работы\", \"Виды работ\"\n" +
                    "where \"Работа\"=\"ID_работы\" and \"Заказ и работы\".\"Заказ\"=" + jTable2.getValueAt(jTable2.getSelectedRow(), 0) + ") "
                    + "where \"ID_заказа\"=" + jTable2.getValueAt(jTable2.getSelectedRow(), 0)).execute();
            jTable2MouseClicked(null); 
            jComboBox1ActionPerformed(null);      
        } catch (SQLException ex) {
            Logger.getLogger(Master.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            conn.prepareStatement("DELETE FROM \"Заказ и работы\"\n"
                    + " WHERE \"Заказ\"=" + jTable2.getValueAt(jTable2.getSelectedRow(), 0) + " and \"Работа\"=(select \"ID_работы\" from \"Виды работ\" "
                    + "where \"Название работы\"='"+ jList1.getSelectedValue() +"')").executeUpdate();
            conn.prepareStatement("UPDATE \"Заказ\" SET \"Общая стоимость заказа\" = (select sum(\"Стоимость работы\") from \"Заказ и работы\", \"Виды работ\"\n" +
                    "where \"Работа\"=\"ID_работы\" and \"Заказ и работы\".\"Заказ\"=" + jTable2.getValueAt(jTable2.getSelectedRow(), 0) + ") "
                    + "where \"ID_заказа\"=" + jTable2.getValueAt(jTable2.getSelectedRow(), 0)).execute();
            jTable2MouseClicked(null);  
            jComboBox1ActionPerformed(null);
        } catch (SQLException ex) {
            Logger.getLogger(Master.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            //Меняем статус заказа на завершено
            if(JOptionPane.showConfirmDialog(null, "Вы точно хотите закрыть?", "", JOptionPane.YES_NO_OPTION)==1)return;
            conn.prepareStatement("UPDATE \"Заказ\"\n" +
                    "	SET \"Статус выполнения\"='Завершено'\n" +
                    "	WHERE \"ID_заказа\"=" + jTable2.getValueAt(jTable2.getSelectedRow(), 0)).execute();
            formWindowOpened(null);
        } catch (SQLException ex) {
            Logger.getLogger(Master.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

        public void setParent(Login login){
        parent = login;
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
            java.util.logging.Logger.getLogger(Master.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Master.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Master.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Master.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Master(id, fullName).setVisible(true);
            }
        });
    }
    private static Connection conn = null;
    private static String fullName = null;
    private static String id = null;
    private Login parent = null;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
