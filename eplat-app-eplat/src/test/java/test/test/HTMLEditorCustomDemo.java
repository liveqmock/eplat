/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package test.test;

import java.util.List;
import java.util.regex.Pattern;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

/**
 * HTML在线编辑器简介：
HTML online editor
在Web程序应用中，最常见的一种是信息和言论的发布和交流。而在信息发布的同时，往往需要对发布的数据进行格式的转换，才能使信息以用户需要的格式显示在Web页面上。而为了实现Web应用中在线信息发布的正确显示和用户对信息发布的格式、类型和功能上的需求，HTML在线编辑器的概念就应运而生了。
顾名思义，HTML在线编辑器就是用于在线编辑的工具，编辑的内容是基于HTML的文档。因为它可用于在线编辑基于HTML的文档，所以，它经常被应用于留言板留言、论坛发贴、Blog编写日志或等需要用户输入普通HTML的地方，是Web应用的常用模块之一。

编辑本段HTML在线编辑器特点：
一个完整意义上的HTML在线编辑器，为了能够真正满足用户的需求，具备以下特点：
1、 所见即所得：通过编辑器，编辑的文字和图片等HTML标记输出到页面的效果和编辑时显示的效果一致，无需经过其他步骤的转换和编码，能让用户方便地对编辑的内容进行修改和排版；
2、 自动转换HTML代码：在编辑状态编辑的文字、图片等内容都在后台状态下自动转换为可被浏览器识别的HTML标记语言；而且用户可以在代码状态下对代码标记进行修改和排版。
3、 简单易用：编辑器的编辑使用方法应该和尽量和FrontPage、Dreamweaver等主流网页制作软件或代码编辑器类似，风格保持一致，以达到无需任何HTML语法知识，机械式的操作也能让没有网页制作经验的用户快速掌握的目的。
4、 方便快捷:使用所见即所得的编辑器能快捷、方便地编辑出效果一流的图文内容，与纯手工编写代码进行编辑的方式比较，可以节省大量的时间和精力。
5、 与系统现有内容兼容、吻合：利用编辑器提供的功能，可以方便地把系统已上传的图片动画影音文件加载到网页内容中，也可以通过插入信息组件与其他网页内容进行整合。

javafx自定义html编辑器功能介绍：
1、隐藏HTMLEditor复制、粘贴、剪切功能。
2、删除编辑器内部分字体。
2、新增HTMLEditor一个自定义按钮功能。
 * 
 * @author obullxl@gmail.com
 * @version $Id: HTMLEditorCustomDemo.java, V1.0.1 2013-3-31 下午1:37:01 $
 */
public class HTMLEditorCustomDemo extends Application {
    // limits the fonts a user can select from in the html editor.
    private static final List<String> limitedFonts = FXCollections.observableArrayList("Arial", "Times New Roman", "Courier New", "Comic Sans MS");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        // create a new html editor and show it before we start modifying it.
        final HTMLEditor htmlEditor = new HTMLEditor();
        htmlEditor.setPrefHeight(400);
        stage.setScene(new Scene(htmlEditor));
        stage.setTitle("个性化Html编辑器");
        stage.show();
        // hide controls we don't need.
        hideImageNodesMatching(htmlEditor, Pattern.compile(".*(Cut|Copy|Paste).*"), 0);
        Node seperator = htmlEditor.lookup(".separator");
        seperator.setVisible(false);
        seperator.setManaged(false);
        // modify font selections.
        int i = 0;
        for (Node candidate : (htmlEditor.lookupAll("MenuButton"))) {
            // fonts are selected by the second menu in the htmlEditor.
            if (candidate instanceof MenuButton && i == 1) {
                // limit the font selections to our predefined list.
                MenuButton menuButton = (MenuButton) candidate;
                List<MenuItem> removalList = FXCollections.observableArrayList();
                final List<MenuItem> fontSelections = menuButton.getItems();
                for (MenuItem item : fontSelections) {
                    if (!limitedFonts.contains(item.getText())) {
                        removalList.add(item);
                    }
                }
                fontSelections.removeAll(removalList);
                // Select a font from out limited font selection.
                // Selection done in Platform.runLater because if you try to do
                // the selection immediately, it won't take place.
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        boolean fontSelected = false;
                        for (final MenuItem item : fontSelections) {
                            if ("Comic Sans MS".equals(item.getText())) {
                                if (item instanceof RadioMenuItem) {
                                    ((RadioMenuItem) item).setSelected(true);
                                    fontSelected = true;
                                }
                            }
                        }
                        if (!fontSelected && fontSelections.size() > 0 && fontSelections.get(0) instanceof RadioMenuItem) {
                            ((RadioMenuItem) fontSelections.get(0)).setSelected(true);
                        }
                    }
                });
            }
            i++;
        }
        // add a custom button to the top toolbar.
        Node node = htmlEditor.lookup(".top-toolbar");
        if (node instanceof ToolBar) {
            ToolBar bar = (ToolBar) node;
            Button smurfButton = new Button("自己添加的按钮");
            bar.getItems().add(smurfButton);
            smurfButton.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent arg0) {
                    htmlEditor.setHtmlText("自己添加的按钮功能^_^");
                }
            });

            Button btnHtmlText = new Button("源代码");
            bar.getItems().add(btnHtmlText);
            btnHtmlText.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent arg0) {
                    System.out.println(htmlEditor.getHtmlText());
                }
            });

        }
    }

    // hide buttons containing nodes whose image url matches a given name
    // pattern.
    public void hideImageNodesMatching(Node node, Pattern imageNamePattern, int depth) {
        if (node instanceof ImageView) {
            ImageView imageView = (ImageView) node;
            String url = imageView.getImage().impl_getUrl();
            if (url != null && imageNamePattern.matcher(url).matches()) {
                Node button = imageView.getParent().getParent();
                button.setVisible(false);
                button.setManaged(false);
            }
        }
        if (node instanceof Parent)
            for (Node child : ((Parent) node).getChildrenUnmodifiable())
                hideImageNodesMatching(child, imageNamePattern, depth + 1);
    }
}
