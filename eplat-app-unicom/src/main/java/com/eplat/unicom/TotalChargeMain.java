/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eplat.unicom;

import au.com.bytecode.opencsv.CSVReader;
import com.atom.core.lang.Money;
import com.eplat.unicom.utils.ChargeRateUtils;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;

/**
 * 汇总总金额
 *
 * @author shizihu
 */
public class TotalChargeMain {

    public static void main(String[] args) throws Exception {
        String file = "E:/DeskTop/联通12月差异/20130205/联通文件-ZFB_201212.txt";
        int outTradeNoIdx = 0;
        int prodCodeIdx = 4;
        int tradeAmountIdx = 6;

        Money amount = new Money(0, 0);
        
        CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(file), "GBK"));
        String[] values = reader.readNext();
        while (values != null) {
            String outTradeNo = values[outTradeNoIdx];
            String prodCode = values[prodCodeIdx];
            
            BigDecimal prodRate = new BigDecimal(ChargeRateUtils.getRate(prodCode));
            Money tradeAmount = new Money(values[tradeAmountIdx]);
            amount.addTo(tradeAmount.multiply(prodRate));
            
            // 下一行
            values = reader.readNext();
        }
        
        System.out.println("总金额为：" + amount+"元.");
    }
}
