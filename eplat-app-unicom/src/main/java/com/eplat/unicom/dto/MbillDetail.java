/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eplat.unicom.dto;

import com.atom.core.lang.Money;
import com.atom.core.lang.ToString;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author shizihu
 */
public class MbillDetail extends ToString {

    private String tradeNo;
    private String outTradeNo;
    private String otherOutTradeNo;
    private Money charge = new Money(0, 0);
    private Money otherCharge = new Money(0, 0);
    private String memo;

    public boolean isSame() {
        return this.getCharge().equals(this.getOtherCharge());
    }

    public String toBill() {
        StringBuilder txt = new StringBuilder();

        txt.append("\t").append(StringUtils.trimToEmpty(this.tradeNo));
        txt.append(",\t").append(StringUtils.trimToEmpty(this.outTradeNo));
        txt.append(",\t").append(this.getCharge());
        txt.append(",\t").append(StringUtils.trimToEmpty(this.otherOutTradeNo));
        txt.append(",\t").append(this.getOtherCharge());
        txt.append(",\t").append(StringUtils.trimToEmpty(this.memo));
        txt.append("\r\n");

        return txt.toString();
    }
    
    public static String title() {
        return "\t交易号,\t外部交易号,\t应收款,\t对方订单号,\t手续费,\t备注\r\n";
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getOtherOutTradeNo() {
        return otherOutTradeNo;
    }

    public void setOtherOutTradeNo(String otherOutTradeNo) {
        this.otherOutTradeNo = otherOutTradeNo;
    }

    public Money getCharge() {
        if (charge == null) {
            charge = new Money(0, 0);
        }

        return charge;
    }

    public void setCharge(Money charge) {
        this.charge = charge;
    }

    public Money getOtherCharge() {
        if (otherCharge == null) {
            otherCharge = new Money(0, 0);
        }

        return otherCharge;
    }

    public void setOtherCharge(Money otherCharge) {
        this.otherCharge = otherCharge;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
