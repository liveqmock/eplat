/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package test.mplat.uijfx.uiviews;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import org.apache.commons.io.FilenameUtils;

import com.atom.core.lang.utils.CfgUtils;
import com.atom.core.uijfx.test.BaseTest;
import com.atom.core.uijfx.utils.WebViewUtils;

/**
 * 
 * @author obullxl@gmail.com
 * @version $Id: WebViewFlashTest.java, V1.0.1 2013-3-31 下午3:23:52 $
 */
public class WebViewFlashTest extends BaseTest {
    
    /**
     * 应用入口
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    /** 
     * @see com.atom.core.uijfx.test.BaseTest#onTest(javafx.stage.Stage)
     */
    public void onTest(Stage stage) {
        BorderPane border = new BorderPane();
        
        String html = "file:///" + FilenameUtils.normalize(CfgUtils.findConfigPath() + "/views/test_web_Flash.html");
        final WebView webView = WebViewUtils.initWebView(html);
        border.setCenter(webView);
        
        stage.setScene(new Scene(border, 600, 400));
        stage.show();
    }

}
