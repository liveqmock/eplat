/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package test.mplat.store;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import mplat.mgt.MgtFactory;
import mplat.mgt.dto.EcgtInfoDTO;
import mplat.store.EcgtStore;

import org.apache.commons.lang.StringUtils;

import com.atom.core.lang.utils.DBUtils;
import com.atom.core.xstream.store.StoreFactory;

/**
 * EcgtStore测试和初始化
 * 
 * @author obullxl@gmail.com
 * @version $Id: EcgtStoreTest.java, V1.0.1 2013-3-22 下午8:50:53 $
 */
public class EcgtStoreTest {

    private static Map<String, String> ecgtRhythms = new ConcurrentHashMap<String, String>();
    
    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        ecgtRhythms.put("Sinus", "窦性");
        ecgtRhythms.put("Sinus  Unifocal PVC", "窦性单源性室性早搏");
        ecgtRhythms.put("Sinus  Coupled PVC", "窦性双发型室性早搏");
        ecgtRhythms.put("Sinus  PAC", "窦性房性早搏");
        ecgtRhythms.put("Sinus  PJC", "窦性交界性早搏");
        ecgtRhythms.put("A.tach.", "房性心动过速");
        ecgtRhythms.put("A.flutt.", "房扑");
        ecgtRhythms.put("A.fib.", "房颤");
        ecgtRhythms.put("Junct.", "交界性心律");
        ecgtRhythms.put("Idiov.", "心室律");
        ecgtRhythms.put("V.tach", "室性心动过速");
        ecgtRhythms.put("V.tach Q220", "室速220");
        ecgtRhythms.put("Torsade.de Pointes", "尖端扭转型室速");
        ecgtRhythms.put("V.fibr.Very coarse", "十分粗室颤");
        ecgtRhythms.put("V.fibr.coarse", "粗室颤");
        ecgtRhythms.put("V.fibr", "室颤");
        ecgtRhythms.put("V.fibr.fine", "细室颤");
        ecgtRhythms.put("V.fibr.very fine", "十分细室颤");
        ecgtRhythms.put("Asystole", "心搏停止");
        ecgtRhythms.put("Agonal rhythm", "临终心律");
        ecgtRhythms.put("Ventr.standstill", "心室停顿(搏)");
        ecgtRhythms.put("Die", "死亡心律");
        ecgtRhythms.put("1 degree AVB", "一度房室传导阻滞");
        ecgtRhythms.put("2 degree AVB Type-1", "二度Ⅰ型房室传导阻滞");
        ecgtRhythms.put("2 degree AVB Type-2", "二度Ⅱ型房室传导阻滞");
        ecgtRhythms.put("3 degree AVB", "三度房室传导阻滞");
        ecgtRhythms.put("Pacemaker Atr", "起搏器心律");
        ecgtRhythms.put("Pacem.AV-seq.", "房室顺序起搏");
        ecgtRhythms.put("Pacemaker A.fib", "房颤起搏");
        ecgtRhythms.put("Pacemaker LOC", "Pacemarker LOC");
        ecgtRhythms.put("Pacemaker No Ps", "Pacemaker No Ps");
        ecgtRhythms.put("Pacemaker P=90 Q=80", "Pacemaker p=90 Q=80");
        ecgtRhythms.put("Pacemaker p=90 Q=100", "Pacemaker p=90 Q=100");
        ecgtRhythms.put("Unifocal PVC", "单形性室性早搏");
        ecgtRhythms.put("PVC RonT", "室性早搏RonT");
        ecgtRhythms.put("Coupled PVC", "室性早搏二联律");
        ecgtRhythms.put("Multifocal PVC", "多形性室性早搏");
        ecgtRhythms.put("PAC", "房性早搏");
        ecgtRhythms.put("PJC", "交界性早搏");
        
        // 初始化
        initData();
    }

    /**
     * 初始化
     */
    protected static void initData() throws Exception {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        String file = "e:/CodeSVN/eplat/eplat-app-eplat/cfgs/TrainManage.mdb";
        String url = "jdbc:odbc:Driver={MicroSoft Access Driver (*.mdb)};DBQ=" + file;
        String sql = "SELECT * FROM ECGGlobalThemes";

        EcgtStore store = StoreFactory.get().findStore(EcgtStore.class);

        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
            conn = DriverManager.getConnection(url, "", "");
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                EcgtInfoDTO dto = new EcgtInfoDTO();

                String xml = findValue(rs, "ecgAnswer");
                dto.setEcgtQrs(findValue(xml, "QRS"));
                dto.setEcgtRhythm(MgtFactory.get().findEcgtKVMgt().findValue(findValue(xml, "Basic_Rhythm")));
                dto.setEcgtSyst(MgtFactory.get().findEcgtKVMgt().findValue(findValue(xml, "ExtraSyst")));
                dto.setEcgtRate(findValue(xml, "Rate"));

                dto.setTipQrs(findValue(rs, "qrs"));
                dto.setTipRate(findValue(rs, "Rate_Hint"));
                dto.setTipRegular(findValue(rs, "Regularity"));
                dto.setTipWave(findValue(rs, "P_wave"));
                dto.setTipInterval(findValue(rs, "PR_Interval"));

                System.out.println(dto);
                store.create(dto);
            }
        } finally {
            DBUtils.closeQuietly(rs);
            DBUtils.closeQuietly(stmt);
            DBUtils.closeQuietly(conn);
        }
    }

    /**
     * 获取字符串
     */
    private static String findValue(ResultSet rs, String name) throws Exception {
        byte[] data = rs.getBytes(name);
        if (data != null && data.length > 0) {
            return StringUtils.trimToEmpty(new String(data, "GBK"));
        }

        return StringUtils.EMPTY;
    }

    /**
     * 从XML中获取节点内容
     */
    private static String findValue(String xml, String node) {
        String open = "<" + node + ">";
        String close = "</" + node + ">";
        return StringUtils.substringBetween(xml, open, close);
    }

}
