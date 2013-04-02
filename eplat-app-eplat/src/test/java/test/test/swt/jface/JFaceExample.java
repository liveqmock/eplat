/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package test.test.swt.jface;

/**
 * 
 * @author obullxl@gmail.com
 * @version $Id: JFaceExample.java, V1.0.1 2013-4-2 下午4:59:02 $
 */
public class JFaceExample {

    private static class ExitAction extends Action {
        ApplicationWindow m_Window;

        public ExitAction(ApplicationWindow w) {
            m_Window = w; //得到窗口对象  
            setText("E&xit@Ctrl+W");
            setToolTipText("Exit the application");
        }
    }

}
