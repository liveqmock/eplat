/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eplat.unicom;

import com.atom.core.lang.utils.CfgUtils;
import com.eplat.unicom.control.ChargeFrame;
import com.atom.core.lang.utils.JARUtils;
import com.atom.core.lang.utils.SwingUtils;

/**
 *
 * @author shizihu
 */
public class Main {
    
    public static void main(String[] args) {
        SwingUtils.initSystemUI();
        JARUtils.loadJarPath(CfgUtils.findRootPath() + "/libs");
        
        ChargeFrame frame = new ChargeFrame();
        SwingUtils.center(frame);
        frame.setVisible(true);
    }
    
}
