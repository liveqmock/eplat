/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.uijfx.uiviews.views;

/**
 * 基本视图组件
 * 
 * @author obullxl@gmail.com
 * @version $Id: BaseView.java, V1.0.1 2013-2-2 上午10:17:15 $
 */
public abstract class BaseView<T, V> {

    /** 根组件 */
    private final T rootView;

    /**
     * CTOR
     */
    public BaseView(T rootView) {
        this.rootView = rootView;
    }

    /**
     * 获取当前视图组件
     */
    public abstract V findView();

    /**
     * 获取根视图组件
     */
    public T getRootView() {
        return this.rootView;
    }

}
