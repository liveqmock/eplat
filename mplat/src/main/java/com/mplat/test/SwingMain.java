/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.mplat.test;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 * Swing����
 * 
 * @author shizihu
 * @version $Id: SwingMain.java, v 0.1 2012-12-24 ����11:11:16 shizihu Exp $
 */
public class SwingMain {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); // ��ǰϵͳĬ��
        //UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName()); // ��ƽ̨ 
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); // Windows L & F
        setFont();

        JOptionPane.showMessageDialog(null, "Text", "Title", JOptionPane.WARNING_MESSAGE);

        Object[] options = { "OK", "CANCEL" };
        JOptionPane.showOptionDialog(null, "Click OK to continue", "Warning",
            JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
    }

    private static void setFont() {
        Font font = new Font("Default", Font.PLAIN, 13);

        String[] names = new String[] { "ToolTip.font", "Table.font", "TableHeader.font",
                "ComboBox.font", "TextField.font", "PasswordField.font", "TextArea.font",
                "TextPane.font", "EditorPane.font", "FormattedTextField.font", "Button.font",
                "CheckBox.font", "RadioButton.font", "ToggleButton.font", "ProgressBar.font",
                "DesktopIcon.font", "TitledBorder.font", "Label.font", "List.font",
                "TabbedPane.font", "MenuBar.font", "Menu.font", "MenuItem.font", "PopupMenu.font",
                "CheckBoxMenuItem.font", "RadioButtonMenuItem.font", "Spinner.font", "Tree.font",
                "ToolBar.font", "OptionPane.messageFont", "OptionPane.buttonFont" };

        for (String name : names) {
            UIManager.put(name, font);
        }
    }

}
