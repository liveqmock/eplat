/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.uijfx.uiviews.views;

import mplat.uijfx.uiviews.MainViewController;

/**
 * 基本视图组件
 * 
 * @author obullxl@gmail.com
 * @version $Id: BaseView.java, V1.0.1 2013-2-2 上午10:17:15 $
 */
public abstract class BaseView {

    /** 根组件 */
    private final MainViewController rootView;

    /**
     * CTOR
     */
    public BaseView(MainViewController rootView) {
        this.rootView = rootView;
    }

    /**
     * 获取当前视图组件
     */
    public abstract <T> T findView();

    /**
     * 获取根视图组件
     */
    public MainViewController getRootView() {
        return this.rootView;
    }

}
