/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mplat.controls;

import com.mplat.context.MplatContextHolder;
import com.mplat.mgt.UserMgt;
import com.mplat.mgt.dto.UserInfoDTO;
import com.mplat.util.UIUtils;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kitty
 */
public class UserMgtDialog extends javax.swing.JDialog {

    private UserMgt userMgt;

    /**
     * Creates new form UserMgtDialog
     */
    public UserMgtDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);

        this.userMgt = MplatContextHolder.findUserMgt();

        initComponents();

        this.initUserTable();
    }

    public void initUserTable() {
        DefaultTableModel model = (DefaultTableModel) this.tableUserInfos.getModel();
        int rowCnt = model.getRowCount();
        for (int i = 0; i < rowCnt; i++) {
            model.removeRow(0);
        }

        List<UserInfoDTO> users = this.userMgt.findAll();
        model.setRowCount(users.size());
        // model = new DefaultTableModel(new String[]{"ID", "用户名"}, users.size());
        for (int i = 0; i < users.size(); i++) {
            UserInfoDTO user = users.get(i);
            this.tableUserInfos.setValueAt(user.getId(), i, 0);
            this.tableUserInfos.setValueAt(user.getUsrName(), i, 1);
        }
    }
    
    private String findSelectedUserName(ActionEvent evt) {
        int row = this.tableUserInfos.getSelectedRow();
        if(row < 0) {
            return null;
        }
        
        Object value = this.tableUserInfos.getValueAt(row, 1);
        if (value == null) {
            return null;
        }

        return (String) value;
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableUserInfos = new javax.swing.JTable();
        btnCreate = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("用户信息管理");
        setResizable(false);

        tableUserInfos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "用户名"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableUserInfos.setRowHeight(20);
        tableUserInfos.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tableUserInfos);
        tableUserInfos.getColumnModel().getColumn(0).setMaxWidth(120);

        btnCreate.setText("新增用户");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        btnUpdate.setText("更新用户");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnRemove.setText("删除用户");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCreate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUpdate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRemove)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRemove)
                    .addComponent(btnUpdate)
                    .addComponent(btnCreate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        Dialog dialog = new UserCreateDialog(this, true);
        UIUtils.center(dialog);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        String usrName = this.findSelectedUserName(evt);
        if (usrName == null) {
            UIUtils.alert(this, "错误", "用户为空，请选择用户！");
            return;
        }

        Dialog dialog = new UserUpdateDialog(this, true, usrName);
        UIUtils.center(dialog);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        String usrName = this.findSelectedUserName(evt);
        if (usrName == null) {
            UIUtils.alert(this, "错误", "用户为空，请选择用户！");
            return;
        }

        boolean rtn = this.userMgt.remove(usrName);
        if (rtn) {
            UIUtils.info(this, "成功提示", "删除用户成功！");
            this.initUserTable();
        } else {
            UIUtils.alert(this, "失败提示", "删除用户失败，请重新输入！");
        }
    }//GEN-LAST:event_btnRemoveActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableUserInfos;
    // End of variables declaration//GEN-END:variables
}
