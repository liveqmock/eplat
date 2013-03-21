/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eplat.unicom.control;

import au.com.bytecode.opencsv.CSVReader;
import com.atom.core.lang.Money;
import com.atom.core.lang.prefs.PrefUtils;
import com.atom.core.lang.utils.LogUtils;
import com.atom.core.lang.utils.SwingUtils;
import com.eplat.unicom.Main;
import com.eplat.unicom.dto.MbillDetail;
import com.eplat.unicom.jdbc.MbillDetailDAO;
import com.eplat.unicom.utils.ChargeRateUtils;
import com.eplat.unicom.utils.MbillWriter;
import java.awt.Dialog;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

/**
 *
 * @author shizihu
 */
public class ChargeFrame extends javax.swing.JFrame {

    private static final String PREF_CATG = "ChargeFrame";
    private final List<String> fileTypeExts;
    private MbillWriter writer;
    private MbillDetailDAO dao = MbillDetailDAO.get();
    // private final Map<String, MbillDetail> tradeNoMap = new ConcurrentHashMap<String, MbillDetail>();
    // private final Map<String, MbillDetail> outTradeNoMap = new ConcurrentHashMap<String, MbillDetail>();

    /**
     * Creates new form ChargeFrame
     */
    public ChargeFrame() {
        initComponents();

        // 账单文件格式
        this.fileTypeExts = new ArrayList<String>();
        this.fileTypeExts.add("普通文件");
        // this.fileTypeExts.add("Excel文件");

        this.cboxFileExt.setModel(new DefaultComboBoxModel(this.fileTypeExts.toArray()));
        this.cboxOtherFileExt.setModel(new DefaultComboBoxModel(this.fileTypeExts.toArray()));

        // 列选择器
        PrefUtils.setCategory(PREF_CATG);
        int tradeNoValue = PrefUtils.getInt("tradeNoIdx");
        this.spinTradeNo.setModel(new SpinnerNumberModel(tradeNoValue, 0, Integer.MAX_VALUE, 1));

        int otherOradeNoValue = PrefUtils.getInt("otherTradeNoIdx");
        this.spinOtherTradeNo.setModel(new SpinnerNumberModel(otherOradeNoValue, -1, Integer.MAX_VALUE, 1));

        int outTradeNoValue = PrefUtils.getInt("outTradeNoIdx");
        this.spinOutTradeNo.setModel(new SpinnerNumberModel(outTradeNoValue, 0, Integer.MAX_VALUE, 1));

        int otherOutTradeNoValue = PrefUtils.getInt("otherOutTradeNoIdx");
        this.spinOtherOutTradeNo.setModel(new SpinnerNumberModel(otherOutTradeNoValue, 0, Integer.MAX_VALUE, 1));

        int amountValue = PrefUtils.getInt("amountIdx");
        this.spinAmount.setModel(new SpinnerNumberModel(amountValue, 0, Integer.MAX_VALUE, 1));

        int rateValue = PrefUtils.getInt("rateIdx");
        this.spinRate.setModel(new SpinnerNumberModel(rateValue, 0, Integer.MAX_VALUE, 1));

        int otherAmountValue = PrefUtils.getInt("otherAmountIdx");
        this.spinOtherAmount.setModel(new SpinnerNumberModel(otherAmountValue, 0, Integer.MAX_VALUE, 1));

        int prodCodeValue = PrefUtils.getInt("prodCodeIdx");
        this.spinProdCode.setModel(new SpinnerNumberModel(prodCodeValue, 0, Integer.MAX_VALUE, 1));

        PrefUtils.removeCategory();

        // 提示消息
        this.lblTipMsg.setText(" ");

        this.btnSelectFile.requestFocus();
    }

    /**
     * 存储个性化参数
     */
    private void storePrefValues() {
        PrefUtils.setCategory(PREF_CATG);

        PrefUtils.put("tradeNoIdx", this.spinTradeNo.getValue());
        PrefUtils.put("otherTradeNoIdx", this.spinOtherTradeNo.getValue());

        PrefUtils.put("outTradeNoIdx", this.spinOutTradeNo.getValue());
        PrefUtils.put("otherOutTradeNoIdx", this.spinOtherOutTradeNo.getValue());

        PrefUtils.put("amountIdx", this.spinAmount.getValue());
        PrefUtils.put("rateIdx", this.spinRate.getValue());

        PrefUtils.put("otherAmountIdx", this.spinOtherAmount.getValue());
        PrefUtils.put("prodCodeIdx", this.spinProdCode.getValue());

        PrefUtils.removeCategory();
    }

    private void showTipMsg(final String tipMsg) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                lblTipMsg.setText("<html>&nbsp;" + tipMsg + "</html>");
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtFile = new javax.swing.JTextField();
        btnSelectFile = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        cboxFileExt = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        spinTradeNo = new javax.swing.JSpinner();
        jLabel12 = new javax.swing.JLabel();
        spinOutTradeNo = new javax.swing.JSpinner();
        jLabel13 = new javax.swing.JLabel();
        spinAmount = new javax.swing.JSpinner();
        jLabel14 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        spinRate = new javax.swing.JSpinner();
        jLabel19 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtOtherFile = new javax.swing.JTextField();
        btnSelectOtherFile = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        cboxOtherFileExt = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        spinOtherTradeNo = new javax.swing.JSpinner();
        jLabel15 = new javax.swing.JLabel();
        spinOtherOutTradeNo = new javax.swing.JSpinner();
        jLabel16 = new javax.swing.JLabel();
        spinOtherAmount = new javax.swing.JSpinner();
        jLabel17 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        spinProdCode = new javax.swing.JSpinner();
        jLabel21 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtTempPath = new javax.swing.JTextField();
        btnSelectTempPath = new javax.swing.JButton();
        btnAnalyze = new javax.swing.JButton();
        lblTipMsg = new javax.swing.JLabel();
        topMenuBar = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuItemHelp = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        menuItemExit = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("支付宝对账文件明细差异对比筛选系统V1.0.1");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("我方账单"));
        jPanel1.setForeground(new java.awt.Color(255, 0, 0));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("账单文件：");

        txtFile.setEditable(false);

        btnSelectFile.setText("选择文件");
        btnSelectFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectFileActionPerformed(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("文件格式：");

        cboxFileExt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "普通文件", "Excel文件" }));

        jLabel3.setText("交易号第");

        jLabel4.setText("外部交易号第");

        jLabel5.setText("交易量第");

        jLabel12.setText("列");

        jLabel13.setText("列");

        jLabel14.setText("列");

        jLabel18.setText("费率第");

        jLabel19.setText("列");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboxFileExt, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(jLabel3)
                        .addGap(0, 0, 0)
                        .addComponent(spinTradeNo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel12)
                        .addGap(15, 15, 15)
                        .addComponent(jLabel4)
                        .addGap(0, 0, 0)
                        .addComponent(spinOutTradeNo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel13)
                        .addGap(15, 15, 15)
                        .addComponent(jLabel5)
                        .addGap(0, 0, 0)
                        .addComponent(spinAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel14)
                        .addGap(15, 15, 15)
                        .addComponent(jLabel18)
                        .addGap(0, 0, 0)
                        .addComponent(spinRate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel19))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFile)
                        .addGap(5, 5, 5)
                        .addComponent(btnSelectFile, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSelectFile))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cboxFileExt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(spinTradeNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(spinOutTradeNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(spinAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel18)
                    .addComponent(spinRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("对方账单"));

        jLabel6.setText("账单文件：");

        txtOtherFile.setEditable(false);

        btnSelectOtherFile.setText("选择文件");
        btnSelectOtherFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectOtherFileActionPerformed(evt);
            }
        });

        jLabel7.setText("文件格式：");

        cboxOtherFileExt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "普通文件", "Excel文件" }));

        jLabel8.setText("交易号第");

        jLabel9.setText("外部交易号第");

        jLabel10.setText("交易量第");

        jLabel15.setText("列");

        jLabel16.setText("列");

        jLabel17.setText("列");

        jLabel20.setText("产品第");

        jLabel21.setText("列");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboxOtherFileExt, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(jLabel8)
                        .addGap(0, 0, 0)
                        .addComponent(spinOtherTradeNo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel15)
                        .addGap(15, 15, 15)
                        .addComponent(jLabel9)
                        .addGap(0, 0, 0)
                        .addComponent(spinOtherOutTradeNo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel16)
                        .addGap(15, 15, 15)
                        .addComponent(jLabel10)
                        .addGap(0, 0, 0)
                        .addComponent(spinOtherAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel17)
                        .addGap(15, 15, 15)
                        .addComponent(jLabel20)
                        .addGap(0, 0, 0)
                        .addComponent(spinProdCode, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel21))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtOtherFile)
                        .addGap(5, 5, 5)
                        .addComponent(btnSelectOtherFile, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtOtherFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSelectOtherFile))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cboxOtherFileExt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(spinOtherTradeNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(spinOtherOutTradeNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(spinOtherAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel20)
                    .addComponent(spinProdCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("其它设置"));

        jLabel11.setText("临时目录：");

        txtTempPath.setEditable(false);

        btnSelectTempPath.setText("选择目录");
        btnSelectTempPath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectTempPathActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTempPath)
                .addGap(5, 5, 5)
                .addComponent(btnSelectTempPath, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtTempPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSelectTempPath))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnAnalyze.setFont(new java.awt.Font("微软雅黑", 1, 14)); // NOI18N
        btnAnalyze.setText("分析账单文件");
        btnAnalyze.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalyzeActionPerformed(evt);
            }
        });

        lblTipMsg.setBackground(new java.awt.Color(204, 255, 153));
        lblTipMsg.setFont(new java.awt.Font("宋体", 0, 14)); // NOI18N
        lblTipMsg.setForeground(new java.awt.Color(255, 51, 51));
        lblTipMsg.setText("提示信息：");
        lblTipMsg.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        lblTipMsg.setOpaque(true);

        jMenu1.setText("文件");

        menuItemHelp.setText("帮助&说明");
        menuItemHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemHelpActionPerformed(evt);
            }
        });
        jMenu1.add(menuItemHelp);
        jMenu1.add(jSeparator1);

        menuItemExit.setText("退出本系统");
        menuItemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemExitActionPerformed(evt);
            }
        });
        jMenu1.add(menuItemExit);

        topMenuBar.add(jMenu1);

        setJMenuBar(topMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTipMsg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAnalyze, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAnalyze, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTipMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void setCurrentDirectory(JFileChooser fc, String key) {
        try {
            PrefUtils.setCategory(PREF_CATG);

            String path = PrefUtils.get(key);

            PrefUtils.removeCategory();

            if (StringUtils.isNotBlank(path)) {
                fc.setCurrentDirectory(new File(path));
            }
        } catch (Exception e) {
            LogUtils.warn("设置当前目录[" + key + "]异常.", e);
        }
    }

    private void savePrefDirectory(String key, String value) {
        try {
            PrefUtils.setCategory(PREF_CATG);
            
            PrefUtils.put(key, value);
            
            PrefUtils.removeCategory();
        } catch (Exception e) {
            LogUtils.warn("保存当前目录[" + key + "]异常.", e);
        }
    }

    private void btnSelectFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectFileActionPerformed
        // 选择我方对账文件
        JFileChooser fc = new JFileChooser();
        this.setCurrentDirectory(fc, "file");
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int flag = JFileChooser.CANCEL_OPTION;
        try {
            flag = fc.showOpenDialog(this);
        } catch (Exception e) {
            this.showTipMsg("选择我方对账文件异常:" + e.getMessage());
            LogUtils.error("选择我方对账文件异常.", e);
        }

        if (flag == JFileChooser.APPROVE_OPTION) {
            //获得该文件
            File file = fc.getSelectedFile();
            this.txtFile.setText(FilenameUtils.normalizeNoEndSeparator(file.getAbsolutePath()));
            this.savePrefDirectory("file", FilenameUtils.normalizeNoEndSeparator(file.getParent()));
        }
    }//GEN-LAST:event_btnSelectFileActionPerformed

    private void btnSelectOtherFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectOtherFileActionPerformed
        // 选择对方对账文件
        JFileChooser fc = new JFileChooser();
        this.setCurrentDirectory(fc, "other-file");
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int flag = JFileChooser.CANCEL_OPTION;
        try {
            flag = fc.showOpenDialog(this);
        } catch (Exception e) {
            this.showTipMsg("选择对方对账文件异常:" + e.getMessage());
            LogUtils.error("选择对方对账文件异常.", e);
        }

        if (flag == JFileChooser.APPROVE_OPTION) {
            //获得该文件
            File file = fc.getSelectedFile();
            this.txtOtherFile.setText(FilenameUtils.normalizeNoEndSeparator(file.getAbsolutePath()));
            this.savePrefDirectory("other-file", FilenameUtils.normalizeNoEndSeparator(file.getParent()));
        }
    }//GEN-LAST:event_btnSelectOtherFileActionPerformed

    private void btnSelectTempPathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectTempPathActionPerformed
        // 选择临时目录
        JFileChooser fc = new JFileChooser();
        this.setCurrentDirectory(fc, "temp-file");
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int flag = JFileChooser.CANCEL_OPTION;
        try {
            flag = fc.showOpenDialog(this);
        } catch (Exception e) {
            this.showTipMsg("选择临时目录异常:" + e.getMessage());
            LogUtils.error("选择临时目录异常.", e);
        }

        if (flag == JFileChooser.APPROVE_OPTION) {
            //获得该文件
            String path = fc.getSelectedFile().getAbsolutePath();
            this.savePrefDirectory("temp-file", path);
            this.txtTempPath.setText(path);
        }
    }//GEN-LAST:event_btnSelectTempPathActionPerformed

    /**
     * 分析
     */
    private void analyze() {
        // 1. 检查设置参数
        this.showTipMsg("正在检查系统配置参数……");
        boolean rtn = this.checkConfigs();
        if (!rtn) {
            return;
        }

        // 2. 解析并导入我方数据
        /*
        if (!this.loadBillDetails()) {
            return;
        }
        */

        // 3. 解析对方文件数据
        if (!this.processOtherBills()) {
            return;
        }

        // 4. 记录我方明细
        try {
            this.dao.printUnckedDetail(this.writer);
        } catch (Exception e) {
            this.showTipMsg("记录对方缺少明细异常：" + e.getMessage());
            LogUtils.error("记录对方缺少明细异常！", e);
        }

        // 5. 关闭写入流
        this.writer.finish();
        this.dao.close();

        this.showTipMsg("分析完成，请检查临时目录下的差异数据文件！");
    }

    /**
     * 导入我方数据明细
     */
    private boolean loadBillDetails() {
        this.showTipMsg("正在分析我方对账文件内容……");
        String file = this.txtFile.getText();
        int tradeNoIdx = (Integer) this.spinTradeNo.getValue();
        int outTradeNoIdx = (Integer) this.spinOutTradeNo.getValue();
        int amountIdx = (Integer) this.spinAmount.getValue();
        int rateIdx = (Integer) this.spinRate.getValue();

        if (tradeNoIdx < 0 || outTradeNoIdx < 0 || amountIdx < 0 || rateIdx < 0) {
            SwingUtils.alert(this, "解析我方对账文件出错", "请仔细迁移各项数据列号，列号从0开始！");
            return false;
        }

        Set<Integer> idxs = new HashSet<Integer>();
        idxs.add(tradeNoIdx);
        idxs.add(outTradeNoIdx);
        idxs.add(amountIdx);
        idxs.add(rateIdx);
        if (idxs.size() < 4) {
            SwingUtils.alert(this, "解析我方对账文件出错", "请仔细迁移各项数据列号，列号从0开始！");
            return false;
        }

        // 清除数据
        this.dao.clear();

        try {
            int count = 1;
            CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(file), "GBK"));
            String[] values = reader.readNext();
            while (values != null) {
                MbillDetail item = new MbillDetail();

                item.setTradeNo(values[tradeNoIdx]);
                item.setOutTradeNo(values[outTradeNoIdx]);

                Money amount = new Money(StringUtils.trim(values[amountIdx]));
                item.setCharge(amount.multiply(new BigDecimal(StringUtils.trim(values[rateIdx]))));

                // 保存
                // this.tradeNoMap.put(item.getTradeNo(), item);
                // this.outTradeNoMap.put(item.getOutTradeNo(), item);
                if (amount.getCent() > 0) {
                    this.dao.insert(item);
                }

                // 打印日志
                // LogUtils.info("[我方] " + item);
                this.showTipMsg("[我方]-分析第[<b>" + (count++) + "</b>]条数据明细……");

                // 下一行
                values = reader.readNext();
            }

            // 关闭
            reader.close();
        } catch (Exception e) {
            this.showTipMsg("读取我方对账文件异常:" + e.getMessage());
            LogUtils.error("读取我方对账文件异常！", e);
            return false;
        }

        return true;
    }

    /**
     * 处理对方文件
     */
    private boolean processOtherBills() {
        try {
            String tmpPath = this.txtTempPath.getText();
            this.writer = new MbillWriter(tmpPath);
            this.writer.writeTitle(MbillDetail.title());
        } catch (Exception e) {
            this.showTipMsg("创建临时文件异常:" + e.getMessage());
            LogUtils.error("创建临时文件异常！", e);
            return false;
        }

        this.showTipMsg("正在分析对方对账文件内容……");
        String otherFile = this.txtOtherFile.getText();
        int otherTradeNoIdx = (Integer) this.spinOtherTradeNo.getValue();
        int otherOutTradeNoIdx = (Integer) this.spinOtherOutTradeNo.getValue();
        int otherAmountIdx = (Integer) this.spinOtherAmount.getValue();
        int prodCodeIdx = (Integer) this.spinProdCode.getValue();
        try {
            CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(otherFile), "GBK"));
            String[] values = reader.readNext();
            int count = 1;
            while (values != null) {
                this.showTipMsg("[对方]-分析第[<b>" + (count++) + "</b>]条数据明细……");

                String tradeNo = StringUtils.EMPTY;
                if (otherTradeNoIdx >= 0) {
                    tradeNo = values[otherTradeNoIdx];
                }
                String outTradeNo = values[otherOutTradeNoIdx];
                if (StringUtils.isBlank(tradeNo) && StringUtils.isBlank(outTradeNo)) {
                    SwingUtils.alert(this, "错误提醒", "对方对账文件格式错误，交易号或外部交易号不能同时为空！");
                    break;
                }

                MbillDetail item;
                if (StringUtils.isNotBlank(tradeNo)) {
                    // item = this.tradeNoMap.get(tradeNo);
                    item = this.dao.findByTradeNo(tradeNo);
                } else {
                    // item = this.outTradeNoMap.get(outTradeNo);
                    item = this.dao.findByOutTradeNo(outTradeNo);
                }

                BigDecimal prodRate = new BigDecimal(ChargeRateUtils.getRate(values[prodCodeIdx]));
                Money otherAmount = new Money(values[otherAmountIdx]);
                Money otherCharge = otherAmount.multiply(prodRate);

                if (item == null) {
                    item = new MbillDetail();
                    item.setOtherOutTradeNo(outTradeNo);
                    item.setOtherCharge(otherCharge);
                    item.setMemo("我方缺少");

                    this.writer.writeDifferent(item.toBill());

                    // 下一行
                    values = reader.readNext();
                    continue;
                }

                item.setOtherOutTradeNo(outTradeNo);
                item.setOtherCharge(otherCharge);

                // 打印日志
                if (!item.isSame()) {
                    item.setMemo("差异明细");
                    String msg = item.toBill();
                    // LogUtils.info("[差异] " + msg);
                    this.writer.writeDifferent(msg);
                }

                // 标记
                item.setCheck(true);
                this.dao.updateChecked(item.getTradeNo());

                // 下一行
                values = reader.readNext();
            }

            // 关闭
            reader.close();
        } catch (Exception e) {
            this.showTipMsg("读取对方对账文件异常:" + e.getMessage());
            LogUtils.error("读取对方对账文件异常！", e);
            return false;
        }

        return true;
    }

    private void btnAnalyzeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalyzeActionPerformed
        // 存储参数
        this.storePrefValues();

        // 分析账单文件
        Thread analyzer = new Thread() {
            public void run() {
                analyze();
            }
        };

        analyzer.start();
    }//GEN-LAST:event_btnAnalyzeActionPerformed

    private void menuItemHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemHelpActionPerformed
        Dialog dialog = new HelpDialog(this, true);
        SwingUtils.center(dialog);
        dialog.setVisible(true);
    }//GEN-LAST:event_menuItemHelpActionPerformed

    private void menuItemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemExitActionPerformed
        this.writer.finish();
        Main.exit(0);
    }//GEN-LAST:event_menuItemExitActionPerformed

    private boolean checkConfigs() {
        boolean rtn = false;

        // 我方对账文件
        File file = new File(this.txtFile.getText());
        if (file == null || !file.exists()) {
            SwingUtils.alert(this, "参数检查", "我方对账文件不存在，请重新选择");
            this.btnSelectFile.requestFocus();
            return rtn;
        }

        // 对方对账文件
        file = new File(this.txtOtherFile.getText());
        if (file == null || !file.exists()) {
            SwingUtils.alert(this, "参数检查", "对方对账文件不存在，请重新选择");
            this.btnSelectOtherFile.requestFocus();
            return rtn;
        }

        // 临时目录
        String tmpPath = this.txtTempPath.getText();
        if (StringUtils.isBlank(tmpPath)) {
            SwingUtils.alert(this, "参数检查", "临时目录不存在，请重新选择");
            this.btnSelectTempPath.requestFocus();
            return rtn;
        }

        // 正常返回
        return true;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnalyze;
    private javax.swing.JButton btnSelectFile;
    private javax.swing.JButton btnSelectOtherFile;
    private javax.swing.JButton btnSelectTempPath;
    private javax.swing.JComboBox cboxFileExt;
    private javax.swing.JComboBox cboxOtherFileExt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JLabel lblTipMsg;
    private javax.swing.JMenuItem menuItemExit;
    private javax.swing.JMenuItem menuItemHelp;
    private javax.swing.JSpinner spinAmount;
    private javax.swing.JSpinner spinOtherAmount;
    private javax.swing.JSpinner spinOtherOutTradeNo;
    private javax.swing.JSpinner spinOtherTradeNo;
    private javax.swing.JSpinner spinOutTradeNo;
    private javax.swing.JSpinner spinProdCode;
    private javax.swing.JSpinner spinRate;
    private javax.swing.JSpinner spinTradeNo;
    private javax.swing.JMenuBar topMenuBar;
    private javax.swing.JTextField txtFile;
    private javax.swing.JTextField txtOtherFile;
    private javax.swing.JTextField txtTempPath;
    // End of variables declaration//GEN-END:variables
}
