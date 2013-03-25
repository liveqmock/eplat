/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package mplat.uijfx.uiviews.acts;

import java.util.HashMap;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Dimension2D;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import com.atom.core.lang.utils.TemplateUtils;
import com.atom.core.uijfx.utils.StageUtils;
import com.atom.core.uijfx.views.BaseXmlAct;

/**
 * ACLS基础理论知识训练（训练结果）
 * 
 * @author obullxl@gmail.com
 * @version $Id: TopicTrain0103Act.java, V1.0.1 2013-3-25 下午9:56:42 $
 */
public class TopicTrain0103Act extends BaseXmlAct {
    /** 上下文数据 */
    private final Map<Object, Object> data = new HashMap<Object, Object>();

    @FXML
    private BorderPane                rootView;
    @FXML
    private WebView                   webMain;

    /**
     * 构造器
     */
    public TopicTrain0103Act(Stage stage, Map<Object, Object> context) {
        super(stage);

        if (context != null) {
            this.data.putAll(context);
        }

        System.out.println("训练结果：" + this.data);
    }

    /** 
     * @see com.atom.core.uijfx.views.BaseXmlAct#initXmlViews()
     */
    public BaseXmlAct initXmlViews() {
        // 舞台属性
        this.setNewStage(new Stage());
        this.findSizeProperty().set(new Dimension2D(400.0, 300.0));
        this.findTitleProperty().set("ACLS基础理论知识训练[训练结果]");
        this.findGroupViewProperty().set(this.rootView);

        return this;
    }

    /** 
     * @see com.atom.core.uijfx.views.BaseViewAct#initViewShown()
     */
    public void initViewShown() {
        // 结果页面
        String webContent = TemplateUtils.render("ExamResult.html", this.data);
        this.webMain.getEngine().loadContent(webContent);
    }

    /**
     * 事件-确认
     */
    @FXML
    private final void onSure(ActionEvent evt) {
        StageUtils.close(this.findNewStage());
        StageUtils.close(this.findStage());
    }

}
