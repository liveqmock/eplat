/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eplat.unicom;

import com.atom.core.lang.Money;
import java.math.BigDecimal;

/**
 *
 * @author shizihu
 */
public class ChargeRateTest {

    public static void main(String[] args) {
        Money amount = new Money(200, 0);
        BigDecimal rate = new BigDecimal("0.002");
        System.out.println(amount.multiply(rate));
    }
}
