/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.apps.eplat;

import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * SWT主窗口
 * 
 * @author obullxl@gmail.com
 * @version $Id: SWTMainView.java, V1.0.1 2013-4-4 下午3:35:20 $
 */
public interface SWTMainView {

    /** 获取设备 */
    public Display findDisplay();

    /** 获取窗口 */
    public Shell findShell();

    /** 获取标签容器 */
    public CTabFolder findCTabFolder();

    /** 设置状态内容 */
    public void setStatusMessage(String msg);

}
