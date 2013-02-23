/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.uijfx.uiviews.fxmls;

import java.net.URL;

import org.apache.commons.lang.StringUtils;

/**
 * FXML文件工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: FXMLUtils.java, V1.0.1 2013-2-23 下午2:05:45 $
 */
public final class FXMLUtils {

    /**
     * 获取FXML文件URL
     */
    public static final URL findURL(String fxml) {
        if(!StringUtils.endsWithIgnoreCase(fxml, ".fxml")) {
            fxml += ".fxml";
        }
        
        return FXMLUtils.class.getResource(fxml);
    }
    
}
