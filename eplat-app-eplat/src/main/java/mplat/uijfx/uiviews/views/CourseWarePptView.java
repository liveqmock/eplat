/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.uijfx.uiviews.views;

/**
 * 系统课件PPT
 * 
 * @author obullxl@gmail.com
 * @version $Id: CourseWarePptView.java, V1.0.1 2013-2-2 下午6:39:42 $
 */
public final class CourseWarePptView extends BasePptView<CourseWareWebView> {

    /**
     * CTOR
     */
    public CourseWarePptView(CourseWareWebView rootView, String pptName) {
        this(rootView, pptName, 0);
    }

    /**
     * CTOR
     */
    public CourseWarePptView(CourseWareWebView rootView, String pptName, int pptNo) {
        super(rootView, pptName, pptNo);
    }

}
