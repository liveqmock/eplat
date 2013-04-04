/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat;

import mplat.mgt.msgs.DataMSG;

import org.eclipse.swt.widgets.Display;

import com.atom.apps.eplat.SWTUtils;
import com.atom.apps.eplat.views.UserLoginView;
import com.atom.core.lang.utils.CfgUtils;
import com.atom.core.lang.utils.TemplateUtils;
import com.atom.core.xstream.store.StoreFactory;

/**
 * 应用入口
 * 
 * @author obullxl@gmail.com
 * @version $Id: SWTMain.java, V1.0.1 2013-4-4 下午1:47:46 $
 */
public class SWTMain {

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            // 初始化
            initSystem();

            // 显示窗口
            UserLoginView window = new UserLoginView();
            window.setBlockOnOpen(true);
            window.open();
            Display.getCurrent().dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 系统初始化
     */
    private static void initSystem() {
        CfgUtils.findRootPath();
        CfgUtils.findConfigPath();
        StoreFactory.get().init();

        TemplateUtils.setTplPath(CfgUtils.findConfigPath() + "/cfgs/tpls");
    }

    /**
     * 退出系统
     */
    public static void exitSystem() {
        SWTUtils.dispose();

        StoreFactory.get().stop();
        DataMSG.get().closePort();

        // 退出
        Display.getCurrent().dispose();
        System.exit(0);
    }
    
}
