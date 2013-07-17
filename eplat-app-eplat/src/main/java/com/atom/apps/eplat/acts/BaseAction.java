/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.apps.eplat.acts;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.Composite;

/**
 * 事件基类
 * 
 * @author obullxl@gmail.com
 * @version $Id: BaseAction.java, V1.0.1 2013-7-17 下午7:58:32 $
 */
public class BaseAction extends Action {

    /**
     * 转换为实际视图
     */
    @SuppressWarnings("unchecked")
    public final <T extends Composite> T cast(Object view) {
        return (T) view;
    }

}
