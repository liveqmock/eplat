/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.uijfx.images;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

/**
 * IMGS点位符
 * 
 * @author obullxl@gmail.com
 * @version $Id: IMGS.java, V1.0.1 2013-1-31 下午1:21:41 $
 */
public class IMGS {

    /**
     * Icon信息
     */
    public static final List<Image> findIconImages() {
        List<Image> imgs = new ArrayList<Image>();
        imgs.add(new Image(IMGS.class.getResourceAsStream("tab-welcome.jpg"), 16, 16, false, false));

        return imgs;
    }

    /**
     * Icon成功
     */
    public static final Image findSuccessIcon(double fit) {
        if (fit <= 0.0) {
            return new Image(IMGS.class.getResourceAsStream("icon-success.gif"));
        } else {
            return new Image(IMGS.class.getResourceAsStream("icon-success.gif"), fit, 0, true, false);
        }
    }

    /**
     * Icon失败
     */
    public static final Image findFailureIcon() {
        return new Image(IMGS.class.getResourceAsStream("icon-failure.gif"));
    }

}
