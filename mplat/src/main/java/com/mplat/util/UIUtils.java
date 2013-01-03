/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mplat.util;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
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
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = component.getSize();

        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }

        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }

        component.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    }

    /**
     * 设置组件为80%
     */
    public static final void defaultSize(Component component) {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension size = new Dimension((int) (screen.getWidth() * 2 / 3), (int) (screen.getHeight() * 0.8));
        component.setSize(size);
    }

    /**
     * 设置组件为全屏
     */
    public static final void fullScreen(Component component) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = component.getSize();

        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }

        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }

        component.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    }

    /**
     * 设置组件为全屏
     */
    public static final void fullScreen(Frame frame) {
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
    }

    /**
     * 是否确定
     */
    public static final boolean confirm(Component component, String title, String msg) {
        int rtn = JOptionPane.showConfirmDialog(component, msg, title, JOptionPane.OK_CANCEL_OPTION);
        return (rtn == JOptionPane.OK_OPTION);
    }

    /**
     * 弹出提示
     */
    public static final void info(Component component, String title, String msg) {
        JOptionPane.showMessageDialog(component, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * 弹出警告
     */
    public static final void alert(Component component, String title, String msg) {
        JOptionPane.showMessageDialog(component, msg, title, JOptionPane.ERROR_MESSAGE);
    }
}
