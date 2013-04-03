/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.apps.eplat;

import org.eclipse.swt.widgets.Display;

import com.atom.apps.eplat.views.UserLoginView;

/**
 * 应用入口
 * 
 * @author obullxl@gmail.com
 * @version $Id: JFaceMain.java, V1.0.1 2013-4-1 下午1:52:08 $
 */
public final class JFaceMain {

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            UserLoginView window = new UserLoginView();
            window.setBlockOnOpen(true);
            window.open();
            Display.getCurrent().dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
