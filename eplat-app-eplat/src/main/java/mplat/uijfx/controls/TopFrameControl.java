/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.uijfx.controls;

import javafx.beans.property.ObjectProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import com.atom.core.lang.utils.LogUtils;
import com.atom.core.uijfx.UISize;

/**
 * 顶部导航栏
 * 
 * @author obullxl@gmail.com
 * @version $Id: TopFrameControl.java, V1.0.1 2013-1-29 下午5:50:34 $
 */
public class TopFrameControl extends HBox {
    /** 透明度 */
    public static final double                               OPACITY_ON  = 1.0;
    public static final double                               OPACITY_OUT = 0.5;

    @FXML
    private HBox                                             topFrame;
    @FXML
    private ImageView                                        imgCourseWare;
    @FXML
    private ImageView                                        imgTopicTrain;
    @FXML
    private ImageView                                        imgEmergeTrain;
    @FXML
    private ImageView                                        imgEmergeExam;
    @FXML
    private ImageView                                        imgSystemCfg;
    @FXML
    private ImageView                                        imgGotoMain;

    /** 组件尺寸 */
    private UISize                                           size        = UISize.to(1000, 120);
    /** 事件代理器 */
    private TopFrameEventProxy                               eventProxy  = TopFrameEventProxy.get();

    /**
     * 初始化
     */
    public TopFrameControl() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TopFrameControl.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        
        try {
            loader.load();

            this.initViews();
        } catch (Exception e) {
            LogUtils.error("初始化TopFrame组件异常!", e);
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 返回主界面鼠标点击事件
     */
    public EventHandler<? super MouseEvent> getOnGotoMainClicked() {
        return this.onGotoMainClickedProperty().get();
    }
    
    public void setOnGotoMainClicked(EventHandler<? super MouseEvent> handler) {
        this.onGotoMainClickedProperty().set(handler);
    }
    
    public ObjectProperty<EventHandler<? super MouseEvent>> onGotoMainClickedProperty() {
        return this.imgGotoMain.onMouseClickedProperty();
    }
    

    @FXML
    private void onMouseOn(MouseEvent evt) {
        Node node = (Node) evt.getSource();
        node.setCursor(Cursor.HAND);
        this.chargeOpacity(node, OPACITY_ON);
    }

    @FXML
    private void onMouseOut(MouseEvent evt) {
        Node node = (Node) evt.getSource();
        node.setCursor(Cursor.DEFAULT);
        this.chargeOpacity(node, OPACITY_OUT);
    }

    @FXML
    private void onMouseClick(MouseEvent evt) {
        Node node = (Node) evt.getSource();

        if (node == this.imgCourseWare) {
            // 系统课件
            LogUtils.info("系统课件");
            this.eventProxy.onCourseWareMouseClick(evt, this.imgCourseWare);
        } else if (node == this.imgTopicTrain) {
            // 专项技能训练
            LogUtils.info("专项技能训练");
        } else if (node == this.imgEmergeTrain) {
            // 专项急救案例训练
            LogUtils.info("专项急救案例训练");
        } else if (node == this.imgEmergeExam) {
            // 专项急救案例考核
            LogUtils.info("专项急救案例考核");
        } else if (node == this.imgSystemCfg) {
            // 系统功能
            LogUtils.info("系统功能");
        } else if (node == this.imgGotoMain) {
            // 系统功能
            LogUtils.info("返回主页面");
        } else {
            // 未知
            LogUtils.error("未知的导航操作", new RuntimeException("未知的导航操作"));
        }
    }

    /**
     * 设置尺寸
     */
    public void setSize(UISize size) {
        this.size = size;
        this.resize();
    }

    public void resize() {
        this.topFrame.prefWidthProperty().setValue(this.size.getWidth());
        this.topFrame.prefHeightProperty().setValue(this.size.getHeight());
    }

    /**
     * 设置事件代理
     */
    public void setEventProxy(TopFrameEventProxy eventProxy) {
        this.eventProxy = eventProxy;
        this.initViews();
    }

    private void initViews() {
        this.resize();

        this.eventProxy.onCourseWareInit(this.imgCourseWare);
    }

    /**
     * 设置透明度
     */
    private void chargeOpacity(Node node, double value) {
        node.setOpacity(value);
    }

}
