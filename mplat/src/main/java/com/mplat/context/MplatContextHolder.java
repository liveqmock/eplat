/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mplat.context;

import com.mplat.mgt.UserMgt;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;

/**
 * @author Kitty
 */
public class MplatContextHolder implements ApplicationContextAware {
    // 上下文
    private static ApplicationContext _context = null;
    
    /**
     * 设置上下文
     */
    public void setApplicationContext(ApplicationContext ac) {
        _context = ac;
    }
    
    /**
     * 获取上下文
     */
    public static ApplicationContext findContext() {
        return _context;
    }
    
    public static void testMessage() {
        Assert.notNull(_context, "应用上下文对象为NULL.");
        
        //String msg = _context.getMessage("SYSTEM-NAME", null, Locale.CHINESE);
        //System.out.println("++++++++++++++++++++++++++" + msg);
    }
    
    /**
     * 用户管理器
     */
    public static final UserMgt findUserMgt() {
        Assert.notNull(_context, "应用上下文对象为NULL.");
        
        return _context.getBean(UserMgt.class);
    }
    
}
