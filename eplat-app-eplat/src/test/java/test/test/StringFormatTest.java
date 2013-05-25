/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package test.test;

import com.atom.core.lang.utils.DigitUtils;

/**
 * 
 * @author obullxl@gmail.com
 * @version $Id: StringFormatTest.java, V1.0.1 2013-5-25 下午6:32:12 $
 */
public class StringFormatTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String catg = DigitUtils.toHex(4);
        String body = DigitUtils.toHex(new int[] { 34, 56, 16, 4, 8, 9 });

        String text = String.format("类型[%1$s], 长度[%2$d], 内容[%3$s].", catg, 6, body);
        System.out.println(text);
    }

}
