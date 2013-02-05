/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eplat.unicom.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author shizihu
 */
public class ChargeRateUtils {

    private static final Map<String, String> rates = initChargeRates();

    private static Map<String, String> initChargeRates() {
        Map<String, String> rates = new ConcurrentHashMap<String, String>();

        rates.put("CZ", "0.0006");
        rates.put("ABC-B2B", "0");
        rates.put("CCB-B2B", "0");
        rates.put("BOC-B2B", "0");
        rates.put("CMBCHINA-B2B", "0");
        rates.put("ICBC-B2B", "0");
        rates.put("SPDB-B2B", "0");
        rates.put("BCCB", "0.0006");
        rates.put("BJRCB", "0.0006");
        rates.put("DEBITCARD_ALIPAY", "0.002");
        rates.put("CREDITCARD_ALIPAY", "0.005");
        rates.put("CREDITCARD_QHXBANK", "0.005");
        rates.put("CREDITCARD_QCEB", "0.005");
        rates.put("CREDITCARD_QCMBC", "0.005");
        rates.put("CREDITCARD_QGDB", "0.005");
        rates.put("CREDITCARD_QCIB", "0.005");
        rates.put("CREDITCARD_QSDB", "0.005");
        rates.put("CREDITCARD_QSPABANK", "0.005");
        rates.put("CREDITCARD_QNBBANK", "0.005");
        rates.put("CREDITCARD_QSHRCB", "0.005");
        rates.put("CREDITCARD_QJSBANK", "0.005");
        rates.put("CREDITCARD_QHZCB", "0.005");
        rates.put("CREDITCARD_QNXBANK", "0.005");
        rates.put("CREDITCARD_QDLB", "0.005");
        rates.put("CREDITCARD_QBOD", "0.005");
        rates.put("CREDITCARD_QNJCB", "0.005");
        rates.put("CREDITCARD_QHSBANK", "0.005");
        rates.put("CREDITCARD_QTCCB", "0.005");
        rates.put("CREDITCARD_QWJRCB", "0.005");
        rates.put("CREDITCARD_QSRBANK", "0.005");
        rates.put("CREDITCARD_QICBC", "0.005");
        rates.put("CREDITCARD_QABC", "0.005");
        rates.put("CREDITCARD_QCCB", "0.005");
        rates.put("CREDITCARD_QCMB", "0.005");
        rates.put("alipaywap_debitcard", "0.01");
        rates.put("DEBITCARD_QICBC", "0.002");
        rates.put("DEBITCARD_QABC", "0.002");
        rates.put("DEBITCARD_QBOC", "0.002");
        rates.put("DEBITCARD_QSDB", "0.002");
        rates.put("SDB", "0.0006");
        rates.put("HZB", "0.0006");
        rates.put("alipaywap", "0.01");
        rates.put("SHB", "0.0006");
        rates.put("NBCB", "0.0006");
        rates.put("BOC", "0.0006");
        rates.put("CMBC", "0.0006");
        rates.put("ABC", "0.0006");
        rates.put("ICBC", "0.0006");
        rates.put("CEB", "0.0006");
        rates.put("AliPay04", "0.002");
        rates.put("POST", "0.0006");
        rates.put("GDB", "0.0006");
        rates.put("CZB", "0.0006");
        rates.put("CMBCHINA", "0.0006");
        rates.put("CIB", "0.0006");
        rates.put("HXB", "0.0006");
        rates.put("HKBEA", "0.0006");
        rates.put("alipaywap_CREDITCARD", "0.005");
        rates.put("alipaywap_DEBITCARD", "0.002");
        rates.put("CREDITCARD_ABC", "0.005");
        rates.put("CREDITCARD_BHB", "0.005");
        rates.put("CREDITCARD_BJBANK", "0.005");
        rates.put("CREDITCARD_BOC", "0.005");
        rates.put("CREDITCARD_CCB", "0.005");
        rates.put("CREDITCARD_CIB", "0.005");
        rates.put("CREDITCARD_CMBC", "0.005");
        rates.put("CREDITCARD_GDB", "0.005");
        rates.put("CREDITCARD_HXBANK", "0.005");
        rates.put("CREDITCARD_ICBC", "0.005");
        rates.put("CREDITCARD_JSBANK", "0.005");
        rates.put("CREDITCARD_SDB", "0.005");
        rates.put("CREDITCARD_SPABANK", "0.005");
        rates.put("CREDITCARD_TCCB", "0.005");
        rates.put("DEBITCARD_ABC", "0.002");
        rates.put("DEBITCARD_BHB", "0.002");
        rates.put("DEBITCARD_CEB", "0.002");
        rates.put("DEBITCARD_CITIC", "0.002");
        rates.put("DEBITCARD_ICBC", "0.002");
        rates.put("DEBITCARD_JJBANK", "0.002");
        rates.put("DEBITCARD_JLRCU", "0.002");
        rates.put("DEBITCARD_JSB", "0.002");
        rates.put("DEBITCARD_SDB", "0.002");
        rates.put("DEBITCARD_TCRCB", "0.002");
        rates.put("alipaywap_DEBITCARD ", "0.002");
        rates.put("CREDITCARD_QBHB", "0.005");
        rates.put("CREDITCARD_QCRCBANK", "0.005");
        rates.put("CREDITCARD_QCZCB", "0.005");
        rates.put("CREDITCARD_QBOC", "0.005");
        rates.put("CREDITCARD_QBJBANK", "0.005");
        rates.put("DEBITCARD_QCCB", "0.002");
        rates.put("DEBITCARD_QCEB", "0.002");
        rates.put("AliPay02", "0.002");
        rates.put("AliPay01", "0.002");
        rates.put("BOCO", "0.0006");
        rates.put("CCB", "0.0006");
        rates.put("ECITIC", "0.0006");
        rates.put("SPDB", "0.0006");
        rates.put("CEB-B2B", "0");
        rates.put("SDB-B2B", "0");
        rates.put("alipaywap_creditcard", "0.005");

        return rates;
    }
    
    public static final String getRate(String code) {
        return rates.get(code);
    }
    
}
