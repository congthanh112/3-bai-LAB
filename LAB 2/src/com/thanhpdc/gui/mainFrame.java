package com.thanhpdc.gui;

import com.thanhpdc.server.ArmorInterface;
import com.thanhpdc.dto.ArmorDTO;
import com.thanhpdc.util.checkValue;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class mainFrame extends javax.swing.JFrame {

    ArmorInterface armorInterface;
    List<ArmorDTO> listArmor = new ArrayList<>();
    String serviceName = "rmi://127.0.0.1:8899/now";
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
    int indexTable = -1;
    boolean createNew = false;
    boolean findByID = false;

    public mainFrame() {
        initComponents();
        setLocationRelativeTo(null);
        checkSeverConnect();
        loadData();
    }

    public void checkSeverConnect() {
        try {
            armorInterface = (ArmorInterface) Naming.lookup(serviceName);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Please run server before using");
            System.exit(0);
        }
    }

    public boolean checkUpdateStatus() {
        boolean result = true;
        try {
            listArmor = armorInterface.findAllArmor();
        } catch (Exception e) {
        }
        if (indexTable >= 0) {
            String armorIDCheck = txtArmorID.getText();
            String classificationCheck = txtClassification.getText();
            String descriptionCheck = txtDescription.getText();
            String statusCheck = txtStatus.getText();
            boolean checkDef = txtDefense.getText().matches("\\d+");
            if (!checkDef) {
                result = false;
            } else if (!txtDefense.getText().isEmpty()) {
                int defenseCheck = Integer.parseInt(txtDefense.getText());
                for (ArmorDTO armorDTO : listArmor) {
                    if (armorDTO.getArmorId().equals(armorIDCheck)) {
                        if (!classificationCheck.equals(armorDTO.getClassification())
                                || !descriptionCheck.equals(armorDTO.getDescription())
                                || !(defenseCheck == armorDTO.getDefense())
                                || !statusCheck.equals(armorDTO.getStatus())) {
                            result = false;
                        }
                    }
                }
            } else {
                result = false;
            }
        } else {
            result = true;
        }
        return result;
    }

    public void loadData() {
        indexTable = -1;
        tblArmor.clearSelection();
        try {
            DefaultTableModel model = (DefaultTableModel) tblArmor.getModel();
            model.getDataVector().removeAllElements();
            for (ArmorDTO armorDTO : listArmor) {
                Vector row = new Vector();
                row.add(armorDTO.getArmorId());
                row.add(armorDTO.getClassification());
                row.add(sdf.format(armorDTO.getTimeOfCreate()));
                row.add(armorDTO.getDefense());
                model.addRow(row);
                tblArmor.updateUI();
            }
        } catch (Exception e) {
        }
    }

    public void whenAddNew() {
        txtArmorID.setText("");
        txtClassification.setText("");
        txtTimeOfCreate.setText("");
        txtDefense.setText("");
        txtDescription.setText("");
        txtStatus.setText("");

        txtArmorID.setEnabled(true);
        txtClassification.setEnabled(true);
        txtDefense.setEnabled(true);
        txtDescription.setEnabled(true);
        txtStatus.setEnabled(true);

        tblArmor.setEnabled(false);
        tblArmor.clearSelection();
        btnFindByArmorID.setEnabled(false);
        btnGetAll.setEnabled(false);
        btnUpdateArmor.setEnabled(false);
        btnRemoveArmor.setEnabled(false);
    }

    public void closeCreate() {
        txtArmorID.setText("");
        txtClassification.setText("");
        txtTimeOfCreate.setText("");
        txtDefense.setText("");
        txtDescription.setText("");
        txtStatus.setText("");
        btnCreateArmor.setText("Create");
        createNew = false;
        tblArmor.setEnabled(true);
        tblArmor.clearSelection();
        btnFindByArmorID.setEnabled(true);
        btnGetAll.setEnabled(true);
        btnUpdateArmor.setEnabled(true);
        btnRemoveArmor.setEnabled(true);

        txtClassification.setEnabled(false);
        txtDefense.setEnabled(false);
        txtDescription.setEnabled(false);
        txtStatus.setEnabled(false);
        indexTable = -1;
    }

    public void checkClick() {
        int checkClickRow = tblArmor.getSelectedRow();
        if (checkClickRow >= 0) {
            ArmorDTO armor;
            try {
                System.out.println("");
                String id = (String) tblArmor.getValueAt(checkClickRow, 0);
                armor = armorInterface.findByArmorID(id);
                DefaultTableModel model = (DefaultTableModel) tblArmor.getModel();
                if (armor == null) {
                    model.removeRow(checkClickRow);
                } else {
                    model.setValueAt(armor.getClassification(), checkClickRow, 1);
                    model.setValueAt(sdf.format(armor.getTimeOfCreate()), checkClickRow, 2);
                    model.setValueAt(armor.getDefense(), checkClickRow, 3);
                }

            } catch (RemoteException ex) {
            }

        }       
    }
    
    public void clearNotification() {
       lblArmorIDWarnning.setText("");
       lblClassificationWarnning.setText("");
       lblDefenseWarnning.setText("");
       lblDescriptionWarnning.setText("");
       lblStatusWarnning.setText("");
       lblTimeOfCreateWarnning.setText("");
       lblNotification.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblArmor = new javax.swing.JTable();
        lblTitle = new javax.swing.JLabel();
        btnGetAll = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txtArmorID = new javax.swing.JTextField();
        btnFindByArmorID = new javax.swing.JButton();
        lblArmorID = new javax.swing.JLabel();
        txtClassification = new javax.swing.JTextField();
        lblClassification = new javax.swing.JLabel();
        txtTimeOfCreate = new javax.swing.JTextField();
        lblTimeOfCreate = new javax.swing.JLabel();
        txtDefense = new javax.swing.JTextField();
        lblDefense = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        lblDescription = new javax.swing.JLabel();
        txtStatus = new javax.swing.JTextField();
        lblStatus = new javax.swing.JLabel();
        btnRemoveArmor = new javax.swing.JButton();
        btnUpdateArmor = new javax.swing.JButton();
        btnCreateArmor = new javax.swing.JButton();
        lblArmorIDWarnning = new javax.swing.JLabel();
        lblClassificationWarnning = new javax.swing.JLabel();
        lblTimeOfCreateWarnning = new javax.swing.JLabel();
        lblDefenseWarnning = new javax.swing.JLabel();
        lblDescriptionWarnning = new javax.swing.JLabel();
        lblStatusWarnning = new javax.swing.JLabel();
        lblNotification = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        tblArmor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblArmor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Classification", "TimeOfCreate", "Defense"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblArmor.getTableHeader().setReorderingAllowed(false);
        tblArmor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblArmorMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblArmorMouseReleased(evt);
            }
        });
        tblArmor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblArmorKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblArmor);

        lblTitle.setFont(new java.awt.Font("Snap ITC", 0, 36)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(51, 0, 204));
        lblTitle.setText("Armor Client");

        btnGetAll.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnGetAll.setText("Get All");
        btnGetAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetAllActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Armor's Detail:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(255, 0, 0))); // NOI18N

        txtArmorID.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        btnFindByArmorID.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnFindByArmorID.setText("Find By ArmorID");
        btnFindByArmorID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindByArmorIDActionPerformed(evt);
            }
        });

        lblArmorID.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblArmorID.setText("ArmorID:");

        txtClassification.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtClassification.setEnabled(false);

        lblClassification.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblClassification.setText("Classification:");

        txtTimeOfCreate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtTimeOfCreate.setEnabled(false);

        lblTimeOfCreate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTimeOfCreate.setText("Time Of Create:");

        txtDefense.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtDefense.setEnabled(false);

        lblDefense.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblDefense.setText("Defense:");

        txtDescription.setColumns(20);
        txtDescription.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        txtDescription.setRows(5);
        txtDescription.setEnabled(false);
        jScrollPane2.setViewportView(txtDescription);

        lblDescription.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblDescription.setText("Description:");

        txtStatus.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtStatus.setEnabled(false);

        lblStatus.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblStatus.setText("Status:");

        btnRemoveArmor.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnRemoveArmor.setText("Remove");
        btnRemoveArmor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveArmorActionPerformed(evt);
            }
        });

        btnUpdateArmor.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnUpdateArmor.setText("Update");
        btnUpdateArmor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateArmorActionPerformed(evt);
            }
        });

        btnCreateArmor.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnCreateArmor.setText("Create");
        btnCreateArmor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateArmorActionPerformed(evt);
            }
        });

        lblArmorIDWarnning.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        lblArmorIDWarnning.setForeground(new java.awt.Color(255, 0, 0));

        lblClassificationWarnning.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        lblClassificationWarnning.setForeground(new java.awt.Color(255, 0, 0));

        lblTimeOfCreateWarnning.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        lblTimeOfCreateWarnning.setForeground(new java.awt.Color(255, 0, 0));

        lblDefenseWarnning.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        lblDefenseWarnning.setForeground(new java.awt.Color(255, 0, 0));

        lblDescriptionWarnning.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        lblDescriptionWarnning.setForeground(new java.awt.Color(255, 0, 0));

        lblStatusWarnning.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        lblStatusWarnning.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblStatus)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnCreateArmor, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(btnUpdateArmor)
                                .addGap(42, 42, 42)
                                .addComponent(btnRemoveArmor))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(lblArmorID)
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblArmorIDWarnning, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtArmorID, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(btnFindByArmorID))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblClassification)
                            .addComponent(lblTimeOfCreate)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(lblDefense))
                            .addComponent(lblDescription))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(txtClassification, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDefense, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTimeOfCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblTimeOfCreateWarnning, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblDefenseWarnning, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblDescriptionWarnning, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblStatusWarnning, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(lblClassificationWarnning, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblArmorID)
                    .addComponent(txtArmorID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFindByArmorID))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblArmorIDWarnning, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtClassification, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblClassification))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblClassificationWarnning, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimeOfCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTimeOfCreate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTimeOfCreateWarnning, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDefense, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDefense))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(lblDescription))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDefenseWarnning, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDescriptionWarnning, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStatus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblStatusWarnning, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreateArmor)
                    .addComponent(btnUpdateArmor)
                    .addComponent(btnRemoveArmor))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        lblNotification.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        lblNotification.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(167, 167, 167)
                        .addComponent(btnGetAll))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(lblNotification, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(320, 320, 320))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(20, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(btnGetAll)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblNotification, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGetAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetAllActionPerformed
        // TODO add your handling code here:
        checkSeverConnect();
        if (!checkUpdateStatus()) {
            JOptionPane.showMessageDialog(null, "Please finish UPDATE feature");
            return;
        } else {
            indexTable = -1;
            tblArmor.clearSelection();
            try {
                listArmor = armorInterface.findAllArmor();
                if (listArmor == null || listArmor.isEmpty()) {
                    DefaultTableModel model = (DefaultTableModel) tblArmor.getModel();
                    model.getDataVector().removeAllElements();
                    txtArmorID.setText("");
                    txtClassification.setText("");
                    txtTimeOfCreate.setText("");
                    txtDefense.setText("");
                    txtDescription.setText("");
                    txtStatus.setText("");
                    txtArmorID.setEnabled(true);
                    txtClassification.setEnabled(false);
                    txtDefense.setEnabled(false);
                    txtDescription.setEnabled(false);
                    txtStatus.setEnabled(false);
                    txtTimeOfCreate.setEnabled(false);

                    tblArmor.updateUI();
                    //JOptionPane.showMessageDialog(null, "FILE has no data");                 

                } else {
                    DefaultTableModel model = (DefaultTableModel) tblArmor.getModel();
                    model.getDataVector().removeAllElements();
                    for (ArmorDTO armorDTO : listArmor) {
                        Vector row = new Vector();
                        row.add(armorDTO.getArmorId());
                        row.add(armorDTO.getClassification());
                        row.add(sdf.format(armorDTO.getTimeOfCreate()));
                        row.add(armorDTO.getDefense());
                        model.addRow(row);

                        findByID = false;
                        txtArmorID.setEnabled(true);
                        btnFindByArmorID.setText("Find By ArmorID");
                        txtArmorID.setText("");
                        txtClassification.setText("");
                        txtTimeOfCreate.setText("");
                        txtDefense.setText("");
                        txtDescription.setText("");
                        txtStatus.setText("");
                        txtArmorID.setEnabled(true);
                        txtClassification.setEnabled(false);
                        txtDefense.setEnabled(false);
                        txtDescription.setEnabled(false);
                        txtStatus.setEnabled(false);
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error Getting data");
            }
        }
    }//GEN-LAST:event_btnGetAllActionPerformed

    private void tblArmorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblArmorMouseClicked
        // TODO add your handling code here:
        checkSeverConnect();
        checkClick();
        if (!checkUpdateStatus() && !createNew) {
            JOptionPane.showMessageDialog(null, "Please finish UPDATE feature");
            return;
        } else {
            indexTable = tblArmor.getSelectedRow();
            if (indexTable >= 0) {
                try {
                    String idGet = (String) tblArmor.getValueAt(indexTable, 0);
                    ArmorDTO armorGet = armorInterface.findByArmorID(idGet);
                    txtArmorID.setText(armorGet.getArmorId());
                    txtClassification.setText(armorGet.getClassification());
                    txtTimeOfCreate.setText(sdf.format(armorGet.getTimeOfCreate()));
                    txtDefense.setText(armorGet.getDefense() + "");
                    txtDescription.setText(armorGet.getDescription());
                    txtStatus.setText(armorGet.getStatus());

                    txtClassification.setEnabled(true);
                    txtDefense.setEnabled(true);
                    txtDescription.setEnabled(true);
                    txtStatus.setEnabled(true);
                    txtArmorID.setEnabled(false);

                    findByID = false;
                    btnFindByArmorID.setText("Find By ArmorID");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }//GEN-LAST:event_tblArmorMouseClicked

    private void btnCreateArmorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateArmorActionPerformed
        // TODO add your handling code here: 
        checkSeverConnect();
        if (!checkUpdateStatus() && createNew == false) {
            JOptionPane.showMessageDialog(null, "Please finish UPDATE feature");
            indexTable = tblArmor.getSelectedRow();
            return;
        } else {
            //neu dang chon 
            if (createNew == false) {
                createNew = true;
                whenAddNew();
                btnCreateArmor.setText("Save");

                findByID = false;
                txtArmorID.setEnabled(true);
                btnFindByArmorID.setText("Find By ArmorID");
                txtArmorID.setText("");
                txtClassification.setText("");
                txtTimeOfCreate.setText("");
                txtDefense.setText("");
                txtDescription.setText("");
                txtStatus.setText("");
            } else if (createNew) {
                if (JOptionPane.showConfirmDialog(null, "Do you want to SAVE?", "SAVE ARMOR", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    try {
                        String armorID = txtArmorID.getText().trim();
                        String classification = txtClassification.getText().trim();
                        String defense = txtDefense.getText().trim();
                        String description = txtDescription.getText().trim();
                        Date time = Calendar.getInstance().getTime();
                        String status = txtStatus.getText().trim();
                        boolean check = checkValue.checkArmorId(armorID) && checkValue.checkClassification(classification)
                                && checkValue.checkDescription(description) && checkValue.checkDefense(defense)
                                && description.matches(".{0,30}");
                        if (armorID.isEmpty() || classification.isEmpty() || defense.isEmpty() || description.isEmpty() || status.isEmpty()) {
                            lblNotification.setText("Please input enough information  ");
                            return;
                        } else if (!check) {
                            if (!checkValue.checkArmorId(armorID)) {
                                lblArmorIDWarnning.setText("Max length is 10 and not contains special characters (@, #, $) ");
                            } else if (!checkValue.checkClassification(classification)) {
                                lblClassificationWarnning.setText("Max length is 30  ");
                            } else if (!checkValue.checkDescription(description)) {
                                lblDescriptionWarnning.setText("Max Desciption'S length is 30 character  ");
                            } else if (!checkValue.checkDefense(defense)) {
                                lblDefenseWarnning.setText("Defense must be integer number greater than 0  ");
                            } else if (!description.matches(".{0,30}")) {
                                lblDescriptionWarnning.setText("Please don't user Enter  ");
                            }
                            return;
                        } else if (armorInterface.findByArmorID(armorID) != null) {
                            lblArmorIDWarnning.setText("ID is already exists  ");
                            return;
                        } else {
                            ArmorDTO armorGet = new ArmorDTO(armorID, classification, description, status,
                                    time, Integer.parseInt(defense));
                            armorInterface.createArmor(armorGet);
                            JOptionPane.showMessageDialog(null, "Created successfully");

                            DefaultTableModel model = (DefaultTableModel) tblArmor.getModel();

                            Vector row = new Vector();
                            row.add(armorID);
                            row.add(classification);
                            row.add(sdf.format(time));
                            row.add(defense);
                            model.addRow(row);

                            txtArmorID.setEnabled(true);
                            findByID = false;
                            btnFindByArmorID.setText("Find By ArmorID");
                            txtArmorID.setText("");
                            txtClassification.setText("");
                            txtTimeOfCreate.setText("");
                            txtDefense.setText("");
                            txtDescription.setText("");
                            txtStatus.setText("");

                            // btnGetAllActionPerformed(null);
                        }
                        closeCreate();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (JOptionPane.showConfirmDialog(null, "Do you want to SAVE?", "SAVE ARMOR", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
                    closeCreate();
                }
            }
        }

    }//GEN-LAST:event_btnCreateArmorActionPerformed

    private void btnUpdateArmorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateArmorActionPerformed
        // TODO add your handling code here:
        checkSeverConnect();
        indexTable = tblArmor.getSelectedRow();
        if (indexTable < 0) {
            JOptionPane.showMessageDialog(null, "Please choose any row to UPDATE");
        } else {
            try {
                String armorID = txtArmorID.getText().trim();
                String classification = txtClassification.getText().trim();
                String defense = txtDefense.getText().trim();
                String description = txtDescription.getText().trim();
                Date time = Calendar.getInstance().getTime();
                String status = txtStatus.getText().trim();
                boolean check = checkValue.checkClassification(classification)
                        && checkValue.checkDescription(description) && checkValue.checkDefense(defense)
                        && description.matches(".{0,30}");
                if (classification.isEmpty() || defense.isEmpty() || description.isEmpty() || status.isEmpty()) {
                    lblNotification.setText("Please input enough information  ");
                    return;
                } else if (!check) {
                    if (!checkValue.checkClassification(classification)) {
                        lblClassificationWarnning.setText("Max length is 30");
                    } else if (!checkValue.checkDescription(description)) {
                        lblDescriptionWarnning.setText("Max Desciption'S length is 30 character   ");
                    } else if (!checkValue.checkDefense(defense)) {
                        lblDefenseWarnning.setText("Defense must be integer number greater than 0  ");
                    } else if (!description.matches(".{0,30}")) {
                        lblDescriptionWarnning.setText("Please don't use Enter");
                    }
                } else {
                    ArmorDTO armorGet = new ArmorDTO(armorID, classification, description, status,
                            time, Integer.parseInt(defense));
                    armorInterface.updateArmor(armorGet);
                    DefaultTableModel model = (DefaultTableModel) tblArmor.getModel();
                    model.setValueAt(classification, indexTable, 1);
                    model.setValueAt(sdf.format(time), indexTable, 2);
                    model.setValueAt(Integer.parseInt(defense), indexTable, 3);

                    indexTable = -1;
                    JOptionPane.showMessageDialog(null, "Update successfully");
                    tblArmor.clearSelection();
                    findByID = false;
                    txtArmorID.setEnabled(true);
                    btnFindByArmorID.setText("Find By ArmorID");
                    txtArmorID.setText("");
                    txtClassification.setText("");
                    txtTimeOfCreate.setText("");
                    txtDefense.setText("");
                    txtDescription.setText("");
                    txtStatus.setText("");
                    txtClassification.setEnabled(false);
                    txtDefense.setEnabled(false);
                    txtDescription.setEnabled(false);
                    txtStatus.setEnabled(false);
                    txtTimeOfCreate.setEnabled(false);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }//GEN-LAST:event_btnUpdateArmorActionPerformed

    private void btnRemoveArmorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveArmorActionPerformed
        // TODO add your handling code here:
        checkSeverConnect();
        int indexTBL = tblArmor.getSelectedRow();
        if (indexTBL < 0) {
            lblNotification.setText("Please choose any row to Delete");
        } else if (!checkUpdateStatus()) {
            lblNotification.setText("Please finish UPDATE feature");
            return;
        } else {
            try {
                String idGet = txtArmorID.getText();
                if (JOptionPane.showConfirmDialog(null, "Do you want to REMOVE?", "REMOVE ARMOR", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    boolean check = armorInterface.removeArmor(idGet);
                    JOptionPane.showMessageDialog(null, "Delete successfully");
                    clearNotification();
                    DefaultTableModel model = (DefaultTableModel) tblArmor.getModel();
                    model.removeRow(indexTBL);
                    indexTBL = -1;
                    tblArmor.clearSelection();
                    tblArmor.updateUI();
                    closeCreate();
                    //btnGetAllActionPerformed(null);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnRemoveArmorActionPerformed

    private void btnFindByArmorIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindByArmorIDActionPerformed
        // TODO add your handling code here:
        checkSeverConnect();
        if (tblArmor.getSelectedRow() >= 0) {
            return;
        } else if (findByID == false) {
            try {
                String idArmor = txtArmorID.getText();
                if (checkValue.checkArmorId(idArmor)) {
                    ArmorDTO armor1Click = armorInterface.findByArmorID(idArmor);
                    if (idArmor.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Have not entered id");
                    } else if (armor1Click == null) {
                        lblArmorIDWarnning.setText("ID does not exist");
                        txtArmorID.setText("");
                        txtArmorID.requestFocus();
                    } else {
                        txtArmorID.setText(armor1Click.getArmorId());
                        txtClassification.setText(armor1Click.getClassification());
                        txtTimeOfCreate.setText(sdf.format(armor1Click.getTimeOfCreate()));
                        txtDefense.setText(armor1Click.getDefense() + "");
                        txtDescription.setText(armor1Click.getDescription());
                        txtStatus.setText(armor1Click.getStatus());
                        findByID = true;
                        txtArmorID.setEnabled(false);
                        btnFindByArmorID.setText("Clean detail");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Max length is 10 and not contains special characters (@, #, $)");
                }
            } catch (Exception ex) {
            }
        } else if (findByID) {
            findByID = false;
            txtArmorID.setEnabled(true);
            btnFindByArmorID.setText("Find By ArmorID");
            txtArmorID.setText("");
            txtClassification.setText("");
            // txtTimeOfCreateChoose.setText("");
            txtDefense.setText("");
            txtDescription.setText("");
            txtStatus.setText("");
        }
    }//GEN-LAST:event_btnFindByArmorIDActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        if (!checkUpdateStatus() || createNew) {
            JOptionPane.showMessageDialog(null, "Please finish the feature before closing the program");
            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        } else {
            System.exit(0);
        }

    }//GEN-LAST:event_formWindowClosing

    private void tblArmorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblArmorKeyReleased
        // TODO add your handling code here:
        int iindexTBL = tblArmor.getSelectedRow();
        if (iindexTBL >= 0) {
            tblArmor.setRowSelectionInterval(iindexTBL, iindexTBL);
            tblArmorMouseClicked(null);
        }
    }//GEN-LAST:event_tblArmorKeyReleased

    private void tblArmorMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblArmorMouseReleased
        // TODO add your handling code here:
        int iindexTBL = tblArmor.getSelectedRow();
        if (iindexTBL >= 0) {
            tblArmor.setRowSelectionInterval(iindexTBL, iindexTBL);
            tblArmorMouseClicked(null);
        }
    }//GEN-LAST:event_tblArmorMouseReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreateArmor;
    private javax.swing.JButton btnFindByArmorID;
    private javax.swing.JButton btnGetAll;
    private javax.swing.JButton btnRemoveArmor;
    private javax.swing.JButton btnUpdateArmor;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblArmorID;
    private javax.swing.JLabel lblArmorIDWarnning;
    private javax.swing.JLabel lblClassification;
    private javax.swing.JLabel lblClassificationWarnning;
    private javax.swing.JLabel lblDefense;
    private javax.swing.JLabel lblDefenseWarnning;
    private javax.swing.JLabel lblDescription;
    private javax.swing.JLabel lblDescriptionWarnning;
    private javax.swing.JLabel lblNotification;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblStatusWarnning;
    private javax.swing.JLabel lblTimeOfCreate;
    private javax.swing.JLabel lblTimeOfCreateWarnning;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTable tblArmor;
    private javax.swing.JTextField txtArmorID;
    private javax.swing.JTextField txtClassification;
    private javax.swing.JTextField txtDefense;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JTextField txtStatus;
    private javax.swing.JTextField txtTimeOfCreate;
    // End of variables declaration//GEN-END:variables
}
