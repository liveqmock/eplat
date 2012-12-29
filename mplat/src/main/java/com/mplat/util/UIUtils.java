/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mplat.util;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.UIManager;

/**
 *
 * @author Kitty
 */
public class UIUtils {

    /**
     * 初始化系统UI
     */
    public static final void initSystemUI() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            LogUtils.error("设置系统UI异常!", e);
        }
        
        Font font = new Font("宋体", Font.PLAIN, 13);
        
        String[] names = new String[]{"ToolTip.font", "Table.font", "TableHeader.font",
            "ComboBox.font", "TextField.font", "PasswordField.font", "TextArea.font",
            "TextPane.font", "EditorPane.font", "FormattedTextField.font", "Button.font",
            "CheckBox.font", "RadioButton.font", "ToggleButton.font", "ProgressBar.font",
            "DesktopIcon.font", "TitledBorder.font", "Label.font", "List.font",
            "TabbedPane.font", "MenuBar.font", "Menu.font", "MenuItem.font", "PopupMenu.font",
            "CheckBoxMenuItem.font", "RadioButtonMenuItem.font", "Spinner.font", "Tree.font",
            "ToolBar.font", "OptionPane.messageFont", "OptionPane.buttonFont"};
        
        for (String name : names) {
            UIManager.put(name, font);
        }
    }

    /**
     * 设置组件位置为显示器中间
     */
    public static final void center(Component component) {
        Dimension size = component.getSize();
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        
        int x = (int) (screen.getWidth() - size.getWidth()) / 2;
        int y = (int) (screen.getHeight() - size.getHeight()) / 2;
        
        component.setLocation(x, y);
    }
}
