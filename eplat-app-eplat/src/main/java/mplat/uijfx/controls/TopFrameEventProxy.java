/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.uijfx.controls;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * TopFrame事件代理
 * 
 * @author obullxl@gmail.com
 * @version $Id: TopFrameEventProxy.java, V1.0.1 2013-1-29 下午6:42:03 $
 */
public class TopFrameEventProxy {

    public static final TopFrameEventProxy get() {
        return new TopFrameEventProxy();
    }

    public void onCourseWareInit(ImageView image) {
        image.setOpacity(1);
    }

    public void onCourseWareMouseOn(MouseEvent evt, ImageView image) {
        image.setOpacity(1);
    }

    public void onCourseWareMouseOut(MouseEvent evt, ImageView image) {
        image.setOpacity(1);
    }

    public void onCourseWareMouseClick(MouseEvent evt, ImageView image) {
        image.setOpacity(1);
    }

}
