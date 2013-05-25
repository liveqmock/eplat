/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package test.mplat.mgt.test;

import com.atom.core.lang.utils.DigitUtils;

import mplat.mgt.msgs.DataItem;
import mplat.mgt.msgs.DataUtils;

/**
 * DataUtils测试
 * 
 * @author obullxl@gmail.com
 * @version $Id: DataUtilsTest.java, V1.0.1 2013-5-25 下午7:38:38 $
 */
public class DataUtilsTest {

    /**
     * 测试
     */
    public static void main(String[] args) {
        int[] data = new int[] { 0xFF, 0x00, 0xFF, 0x00, 0xFF, 0x04, 0x79, 0x00, 0x80, 0x80, 0x81, 0x82, 0x83, 0x84, 0x85, 0x86, 0x87, 0x87, 0x88, 0x88, 0x88, 0x88, 0x88, 0x88, 0x87, 0x87, 0x86,
                0x85, 0x84, 0x83, 0x82, 0x81, 0x80, 0x80, 0x80, 0x80, 0x80, 0x80, 0x80, 0x80, 0x81, 0x7F, 0x7D, 0x7F, 0x86, 0x8F, 0x96, 0xA0, 0xAA, 0xAD, 0xA8, 0x9C, 0x8E, 0x83, 0x7B, 0x79, 0x7B,
                0x7E, 0x7F, 0x7F, 0x7F, 0x7F, 0x7F, 0x7F, 0x7F, 0x7F, 0x7F, 0x7F, 0x7F, 0x7F, 0x7F, 0x7F, 0x7F, 0x7F, 0x7F, 0x7F, 0x7F, 0x7F, 0x7F, 0x80, 0x80, 0x81, 0x81, 0x81, 0x82, 0x82, 0x83,
                0x83, 0x84, 0x84, 0x84, 0x85, 0x85, 0x85, 0x86, 0x86, 0x87, 0x87, 0x88, 0x88, 0x87, 0x87, 0x87, 0x86, 0x86, 0x85, 0x85, 0x85, 0x84, 0x84, 0x83, 0x83, 0x83, 0x82, 0x82, 0x81, 0x81,
                0x81, 0x81, 0x80, 0x80, 0x80, 0x80, 0x80, 0x80, 0x80, 0x80, 0x80, 0xFC, 0xFC, 0xFC };
        System.out.println(data.length);

        DataItem item = DataUtils.parse(data);
        System.out.println(item);

        item = new DataItem(0x04, 10, new int[] { 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0A });
        data = DataUtils.compat(item);
        System.out.println(DigitUtils.toHex(data));
    }
}