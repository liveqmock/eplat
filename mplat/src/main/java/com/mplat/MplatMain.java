/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mplat;

import com.mplat.controls.LoginFrame;
import java.awt.Frame;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author shizihu
 */
public class MplatMain {
    
    public static void main(String[] args) {
        System.out.println("系统启动");
        
        ApplicationContext context = new ClassPathXmlApplicationContext("");
        
        Frame loginFrame = new LoginFrame();
        loginFrame.setVisible(true);
    }
    
}
