/**
 * obullxl@gmail.com
 */
package com.mplat.controls;

import com.mplat.context.MplatContextHolder;
import com.mplat.mgt.ExamMgt;
import com.mplat.mgt.dto.ExamInfoDTO;
import com.mplat.mgt.dto.ExamItemDTO;
import com.mplat.util.UIUtils;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 * @author obullxl@gmail.com
 */
public class ExamUpdateDialog extends javax.swing.JDialog {

    private final long id;
    private final ExamMgt examMgt;
    private final ExamMgtDialog examMgtDialog;

    /**
     * Creates new form ExamUpdateDialog
     */
    public ExamUpdateDialog(ExamMgtDialog parent, boolean modal, long id) {
        super(parent, modal);

        this.id = id;
        this.examMgt = MplatContextHolder.findExamMgt();
        this.examMgtDialog = parent;

        initComponents();

        this.initUpdateForm();
    }

    private void initUpdateForm() {
        ExamInfoDTO exam = this.findExamInfo();
        if (exam == null) {
            return;
        }

        this.txtId.setText(String.valueOf(exam.getId()));
        this.txtTitle.setText(exam.getTitle());
        this.cboxRgtNo.setSelectedItem(exam.getRgtNo());

        this.txtItemA.setText(this.findItemText(exam.getItems(), "A"));
        this.txtItemB.setText(this.findItemText(exam.getItems(), "B"));
        this.txtItemC.setText(this.findItemText(exam.getItems(), "C"));
        this.txtItemD.setText(this.findItemText(exam.getItems(), "D"));
    }

    private String findItemText(List<ExamItemDTO> items, String no) {
        ExamItemDTO item = this.findItem(items, no);
        if (item == null) {
            return StringUtils.EMPTY;
        }

        return item.getText();
    }

    private ExamItemDTO findItem(List<ExamItemDTO> items, String no) {
        for (ExamItemDTO item : items) {
            if (StringUtils.equalsIgnoreCase(item.getNo(), no)) {
                return item;
            }
        }

        return null;
    }

    private ExamInfoDTO findExamInfo() {
        ExamInfoDTO exam = null;

        try {
            exam = this.examMgt.findExamInfo(this.id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (exam == null) {
            UIUtils.alert(this, "错误提示", "试题[" + this.id + "]标题不存在！");
        }

        return exam;
    }

    private ExamInfoDTO initExamInfo() {
        ExamInfoDTO exam = this.findExamInfo();
        if (exam == null) {
            return null;
        }

        String title = StringUtils.trimToEmpty(this.txtTitle.getText());
        String rgtNo = StringUtils.trimToEmpty((String) this.cboxRgtNo.getSelectedItem());
        String itemA = StringUtils.trimToEmpty(this.txtItemA.getText());
        String itemB = StringUtils.trimToEmpty(this.txtItemB.getText());
        String itemC = StringUtils.trimToEmpty(this.txtItemC.getText());
        String itemD = StringUtils.trimToEmpty(this.txtItemD.getText());

        if (StringUtils.isEmpty(title)) {
            UIUtils.alert(this, "输入错误提示", "试题标题不能为空，请重新输入！");
            return null;
        }
        if (StringUtils.isEmpty(rgtNo)) {
            UIUtils.alert(this, "输入错误提示", "请选择试题正确选项！");
            return null;
        }

        exam.setTitle(title);
        exam.setRgtNo(rgtNo);
        exam.getItems().add(new ExamItemDTO("A", itemA));
        exam.getItems().add(new ExamItemDTO("B", itemB));
        exam.getItems().add(new ExamItemDTO("C", itemC));
        exam.getItems().add(new ExamItemDTO("D", itemD));

        return exam;
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtId = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtTitle = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtItemA = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtItemB = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtItemC = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtItemD = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        cboxRgtNo = new javax.swing.JComboBox();
        btnUpdate = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("更新试题");
        setResizable(false);

        jLabel1.setText("ID：");

        txtId.setFont(new java.awt.Font("宋体", 1, 12)); // NOI18N
        txtId.setText("123");

        jLabel3.setText("标题：");

        txtTitle.setColumns(20);
        txtTitle.setRows(5);
        jScrollPane1.setViewportView(txtTitle);

        jLabel4.setText("选项A：");

        txtItemA.setColumns(20);
        txtItemA.setRows(5);
        jScrollPane2.setViewportView(txtItemA);

        jLabel5.setText("选项B：");

        txtItemB.setColumns(20);
        txtItemB.setRows(5);
        jScrollPane3.setViewportView(txtItemB);

        jLabel6.setText("选项C：");

        txtItemC.setColumns(20);
        txtItemC.setRows(5);
        jScrollPane4.setViewportView(txtItemC);

        jLabel7.setText("选项D：");

        txtItemD.setColumns(20);
        txtItemD.setRows(5);
        jScrollPane5.setViewportView(txtItemD);

        jLabel8.setText("正确选项：");

        cboxRgtNo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "A", "B", "C", "D" }));

        btnUpdate.setText("更新");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnClear.setText("清除");
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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboxRgtNo, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtId))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtId))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(17, 17, 17)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel5)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel6)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel7)))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cboxRgtNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClear)
                    .addComponent(btnUpdate))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        String text = StringUtils.EMPTY;
        this.txtTitle.setText(text);
        this.txtItemA.setText(text);
        this.txtItemB.setText(text);
        this.txtItemC.setText(text);
        this.txtItemD.setText(text);
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        ExamInfoDTO exam = this.initExamInfo();
        if (exam == null) {
            return;
        }

        boolean rtn = this.examMgt.updateExamInfo(exam);
        if (rtn) {
            UIUtils.info(this, "成功提示", "更新试题成功！");
            this.examMgtDialog.initExamTable();
        } else {
            UIUtils.alert(this, "失败提示", "更新试题失败！");
        }
    }//GEN-LAST:event_btnUpdateActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox cboxRgtNo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel txtId;
    private javax.swing.JTextArea txtItemA;
    private javax.swing.JTextArea txtItemB;
    private javax.swing.JTextArea txtItemC;
    private javax.swing.JTextArea txtItemD;
    private javax.swing.JTextArea txtTitle;
    // End of variables declaration//GEN-END:variables
}
