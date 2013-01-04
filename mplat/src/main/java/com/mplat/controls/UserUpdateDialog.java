/**
 * obullxl@gmail.com
 */
package com.mplat.controls;

import com.mplat.context.MplatContextHolder;
import com.mplat.mgt.UserMgt;
import com.mplat.mgt.dto.UserInfoDTO;
import com.mplat.util.UIUtils;
import org.apache.commons.lang.StringUtils;

/**
 * @author obullxl@gmail.com
 */
public class UserUpdateDialog extends javax.swing.JDialog {

    private UserMgt userMgt;
    private String userName;
    private UserInfoDTO user;
    private UserMgtDialog userMgtDialog;

    /**
     * Creates new form UserUpdateDialog
     */
    public UserUpdateDialog(UserMgtDialog parent, boolean modal, String userName) {
        super(parent, modal);

        this.userMgtDialog = parent;
        this.userName = userName;
        this.userMgt = MplatContextHolder.findUserMgt();

        initComponents();
        
        this.initUserUpdate();
    }

    private void initUserUpdate() {
        this.user = this.userMgt.findByName(this.userName);
        this.txtUserID.setText(String.valueOf(this.user.getId()));
        this.txtUsrName.setText(this.user.getUsrName());
        this.txtUsrPasswd.setText(this.user.getUsrPasswd());
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtUserID = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtUsrName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtUsrPasswd = new javax.swing.JPasswordField();
        btnUpdate = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("更新用户信息");

        jLabel1.setText("ID：");

        txtUserID.setText("jLabel2");

        jLabel3.setText("用户名：");

        txtUsrName.setEditable(false);

        jLabel4.setText("用户密码：");

        btnUpdate.setText("更新");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnClear.setText("清空");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtUsrName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                            .addComponent(txtUserID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtUsrPasswd)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnUpdate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnClear)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtUserID))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtUsrName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtUsrPasswd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate)
                    .addComponent(btnClear))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        this.initUserInfo();
        boolean rtn = this.userMgt.update(this.user);
        if (rtn) {
            UIUtils.info(this, "成功提示", "用户更新成功！");
            this.userMgtDialog.initUserTable();
            this.dispose();
        } else {
            UIUtils.alert(this, "失败提示", "用户更新失败，请重新输入！");
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        this.txtUsrPasswd.setText(StringUtils.EMPTY);
    }//GEN-LAST:event_btnClearActionPerformed

    private void initUserInfo() {
        this.user.setUsrPasswd(this.txtUsrPasswd.getText());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel txtUserID;
    private javax.swing.JTextField txtUsrName;
    private javax.swing.JPasswordField txtUsrPasswd;
    // End of variables declaration//GEN-END:variables
}
