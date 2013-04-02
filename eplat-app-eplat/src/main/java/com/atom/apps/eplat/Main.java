/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.apps.eplat;

import com.atom.apps.eplat.views.HomeMainView;

/**
 * 应用入口
 * 
 * @author obullxl@gmail.com
 * @version $Id: Main.java, V1.0.1 2013-4-1 下午1:52:08 $
 */
public final class Main {

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            new HomeMainView().open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
