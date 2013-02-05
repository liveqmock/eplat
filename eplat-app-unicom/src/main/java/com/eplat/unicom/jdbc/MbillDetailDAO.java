/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eplat.unicom.jdbc;

import com.atom.core.lang.Money;
import com.atom.core.lang.utils.LogUtils;
import com.eplat.unicom.dto.MbillDetail;
import com.eplat.unicom.utils.MbillWriter;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author shizihu
 */
public class MbillDetailDAO {

    private static final MbillDetailDAO DAO = initMbillDetailDAO();

    private static final MbillDetailDAO initMbillDetailDAO() {
        try {
            Class.forName("org.sqlite.JDBC");

            File root = new File(".");
            String path = FilenameUtils.normalizeNoEndSeparator(root.getAbsolutePath());
            String dbfile = FilenameUtils.normalizeNoEndSeparator(path + "/cfgs/Mbill.db");

            return new MbillDetailDAO(DriverManager.getConnection("jdbc:sqlite:" + dbfile));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static final MbillDetailDAO get() {
        return DAO;
    }
    private Connection conn;

    private MbillDetailDAO() {
    }

    private MbillDetailDAO(Connection conn) {
        this();
        this.conn = conn;
    }

    public void close() {
        try {
            this.conn.close();
        } catch (Exception e) {
            LogUtils.error("关闭数据连接异常", e);
        }
    }

    public void clear() {
        Statement stmt = null;
        try {
            stmt = this.conn.createStatement();
            stmt.execute(this.findDeleteSQL());
        } catch (Exception e) {
            throw new RuntimeException("清除所有数据明细异常！", e);
        } finally {
            this.close(stmt);
        }
    }

    public void insert(MbillDetail detail) {
        PreparedStatement pstmt = null;
        try {
            pstmt = this.conn.prepareStatement(this.findInsertSQL());
            pstmt.setString(1, detail.getTradeNo());
            pstmt.setString(2, detail.getOutTradeNo());
            pstmt.setLong(3, detail.getCharge().getCent());
            pstmt.setLong(4, detail.getOtherCharge().getCent());
            pstmt.setString(5, (detail.isCheck() ? "1" : "0"));

            pstmt.execute();
        } catch (Exception e) {
            throw new RuntimeException("插入数据[" + detail + "]异常！", e);
        } finally {
            this.close(pstmt);
        }
    }

    private MbillDetail findByKey(String sql, String key) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = this.conn.prepareStatement(sql);
            pstmt.setString(1, key);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                MbillDetail detail = new MbillDetail();

                detail.setTradeNo(rs.getString("trade_no"));
                detail.setOutTradeNo(rs.getString("out_trade_no"));

                Money charge = new Money();
                charge.setCent(rs.getLong("charge"));
                detail.setCharge(charge);

                Money otherCharge = new Money();
                otherCharge.setCent(rs.getLong("other_charge"));
                detail.setOtherCharge(otherCharge);

                String check = rs.getString("checked");
                if (StringUtils.equals(check, "1")) {
                    detail.setCheck(true);
                }

                return detail;
            }

            return null;
        } finally {
            this.close(rs);
            this.close(pstmt);
        }
    }

    public MbillDetail findByTradeNo(String tradeNo) {
        try {
            return this.findByKey(this.findSelectByTradeNoSQL(), tradeNo);
        } catch (Exception e) {
            throw new RuntimeException("根据交易号[" + tradeNo + "]查询数据异常！", e);
        }
    }

    public MbillDetail findByOutTradeNo(String outTradeNo) {
        try {
            return this.findByKey(this.findSelectByOutTradeNoSQL(), outTradeNo);
        } catch (Exception e) {
            throw new RuntimeException("根据外部交易号[" + outTradeNo + "]查询数据异常！", e);
        }
    }

    public void updateChecked(String tradeNo) {
        PreparedStatement pstmt = null;
        try {
            pstmt = this.conn.prepareStatement(this.findUpdateCheckedSQL());
            pstmt.setString(1, tradeNo);
            pstmt.execute();
        } catch (Exception e) {
            throw new RuntimeException("根据交易号[" + tradeNo + "]更新数据异常！", e);
        } finally {
            this.close(pstmt);
        }
    }

    public void printUnckedDetail(MbillWriter writer) {
        Statement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = this.conn.createStatement();
            rs = pstmt.executeQuery(this.findSelectUncheckedSQL());
            while (rs.next()) {
                MbillDetail detail = new MbillDetail();

                detail.setTradeNo(rs.getString("trade_no"));
                detail.setOutTradeNo(rs.getString("out_trade_no"));

                Money charge = new Money();
                charge.setCent(rs.getLong("charge"));
                detail.setCharge(charge);

                detail.setMemo("对方缺少");

                // 输出
                writer.writeDifferent(detail.toBill());
            }
        } catch (Exception e) {
            throw new RuntimeException("打印我方未核对数据异常！", e);
        } finally {
            this.close(rs);
            this.close(pstmt);
        }
    }

    private void close(Statement stmt) {
        try {
            stmt.close();
        } catch (Exception e) {
            //
        }
    }

    private void close(ResultSet rs) {
        try {
            rs.close();
        } catch (Exception e) {
            //
        }
    }

    private String findDeleteSQL() {
        return "DELETE FROM mbill_detail";
    }

    private String findInsertSQL() {
        return "INSERT INTO mbill_detail(trade_no, out_trade_no, charge, other_charge, checked) VALUES(?, ?, ?, ?, ?)";
    }

    private String findUpdateCheckedSQL() {
        return "UPDATE mbill_detail SET checked='1' WHERE trade_no=?";
    }

    private String findSelectUncheckedSQL() {
        return "SELECT trade_no, out_trade_no, charge, other_charge, checked FROM mbill_detail WHERE checked='0'";
    }

    private String findSelectByTradeNoSQL() {
        return "SELECT trade_no, out_trade_no, charge, other_charge, checked FROM mbill_detail WHERE trade_no=?";
    }

    private String findSelectByOutTradeNoSQL() {
        return "SELECT trade_no, out_trade_no, charge, other_charge, checked FROM mbill_detail WHERE out_trade_no=?";
    }
}
