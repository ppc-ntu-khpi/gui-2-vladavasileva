/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mybank.gui;

import com.formdev.flatlaf.FlatDarkLaf;
import com.mybank.gui.data.DataSource;
import com.mybank.gui.domain.Bank;
import com.mybank.gui.domain.CheckingAccount;
import com.mybank.gui.domain.Customer;

import javax.swing.*;
import java.io.IOException;

/**
 *
 * @author Vlada
 */
public class MatisseDemo extends javax.swing.JFrame {

    /**
     * Creates new form MatisseDemo
     */
    private static final DataSource dataSource = new DataSource("src/main/java/com/mybank/gui/data/test.dat");
    public MatisseDemo() {
        try {
            dataSource.loadData();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Exception while reading [test.dat] file.");
        }

        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        comboBoxUserSelect = new javax.swing.JComboBox<>();
        btnShow = new javax.swing.JButton();
        btnReport = new javax.swing.JButton();
        btnAbout = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaUserInfo = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        String[] customerNames = new String[Bank.getNumberOfCustomers()];
        for(int i = 0; i < Bank.getNumberOfCustomers(); i++) {
            customerNames[i] = Bank.getCustomer(i).getFirstName() + " " + Bank.getCustomer(i).getLastName();
        }
        comboBoxUserSelect.setModel(new javax.swing.DefaultComboBoxModel<>(customerNames));

        btnShow.setText("Show");
        btnShow.addActionListener(this::btnShowActionPerformed);

        btnReport.setText("Report");
        btnReport.addActionListener(this::btnReportActionPerformed);

        btnAbout.setText("About");
        btnAbout.addActionListener(this::btnAboutActionPerformed);

        textAreaUserInfo.setColumns(20);
        textAreaUserInfo.setRows(5);
        jScrollPane1.setViewportView(textAreaUserInfo);

        jLabel1.setText("Choose a client name and press 'Show' button");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(jScrollPane1)
                            .add(comboBoxUserSelect, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(btnAbout, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .add(btnReport, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(btnShow, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jSeparator1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(comboBoxUserSelect, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(btnShow))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(btnReport)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(btnAbout))
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 137, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnShowActionPerformed(java.awt.event.ActionEvent evt) {
        Customer current = Bank.getCustomer(comboBoxUserSelect.getSelectedIndex());

        String customer = "";

        for (int i = 0; i < current.getNumberOfAccounts(); i++) {
            String accType = current.getAccount(i) instanceof CheckingAccount ? "Checking" : "Saving";
            customer += "Acc Type: " + accType + "\nBalance: $" + current.getAccount(i).getBalance() + '\n';
        }

        textAreaUserInfo.setText(customer);
    }

    private void btnReportActionPerformed(java.awt.event.ActionEvent evt) {
        String report = "CUSTOMERS REPORT\n\n";

        for(int i = 0; i < Bank.getNumberOfCustomers(); i++) {
            Customer customer = Bank.getCustomer(i);
            report += customer.getLastName() + " " + customer.getFirstName() + "\n";
            for(int j = 0; j < customer.getNumberOfAccounts(); j++) {
                String accType = customer.getAccount(j) instanceof CheckingAccount ? "Checking" : "Saving";
                report += "Acc Type: " + accType + "\nBalance: "
                        + customer.getAccount(j).getBalance() + "\n";
            }
            report += '\n';
        }

        textAreaUserInfo.setText(report);
    }

    private void btnAboutActionPerformed(java.awt.event.ActionEvent evt) {
        JOptionPane.showMessageDialog(this, "Розробник: Влада Васильєва");
    }

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
            java.util.logging.Logger.getLogger(MatisseDemo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MatisseDemo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MatisseDemo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MatisseDemo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */


        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            System.out.println(e);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MatisseDemo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbout;
    private javax.swing.JButton btnReport;
    private javax.swing.JButton btnShow;
    private javax.swing.JComboBox<String> comboBoxUserSelect;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea textAreaUserInfo;
    // End of variables declaration//GEN-END:variables
}
