package com.atom.apps.eplat.views;

import java.util.List;

import mplat.mgt.MgtFactory;
import mplat.mgt.SpeakMgt;
import mplat.mgt.dto.SpeakDTO;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import com.atom.apps.eplat.SWTUtils;
import com.atom.core.lang.ToString;
import com.atom.core.lang.utils.LogUtils;

public class EmgeBodyCmpt extends Composite implements PaintListener, SelectionListener {
    /** 菜单数据 */
    private static final String MENU_BUTTON = "__menu_button_data_";
    private static final String MNTM_DATA   = "__mntm_speak_data_";

    /** 主窗体 */
    private final Composite     mainView;

    /** 背景图片 */
    private Image               imgBack;

    /** 数据 */
    private List<SpeakDTO>      hearts;
    private List<SpeakDTO>      breaths;
    private List<SpeakDTO>      palpates;

    /** 组件 */
    private Button              btnBreath1;
    private Button              btnBreath2;
    private Button              btnHeart;
    private Button              btnSpeak;
    private Button              btnPalpate;

    /** 弹出菜单 */
    private Menu                menuBreath1;
    private Menu                menuBreath2;
    private Menu                menuHeart;
    private Menu                menuPalpate;

    /**
     * Create the composite.
     */
    public EmgeBodyCmpt(Composite parent) {
        super(parent, SWT.NONE);

        this.mainView = parent;

        this.imgBack = SWTUtils.findImage(super.getDisplay(), "emerge_body.bmp");
        this.setBackgroundImage(this.imgBack);

        this.addPaintListener(this);

        this.btnBreath1 = new Button(this, SWT.NONE);
        this.btnBreath1.addSelectionListener(this);
        this.btnBreath1.setToolTipText("肺泡呼吸音");
        this.btnBreath1.setText("肺泡呼吸音");
        this.btnBreath1.setBounds(10, 77, 94, 24);

        this.btnBreath2 = new Button(this, SWT.NONE);
        this.btnBreath2.addSelectionListener(this);
        this.btnBreath2.setToolTipText("肺泡呼吸音");
        this.btnBreath2.setText("肺泡呼吸音");
        this.btnBreath2.setBounds(230, 77, 94, 24);

        this.btnHeart = new Button(this, SWT.NONE);
        this.btnHeart.addSelectionListener(this);
        this.btnHeart.setToolTipText("第一心音");
        this.btnHeart.setText("第一心音");
        this.btnHeart.setBounds(10, 197, 94, 24);

        this.btnSpeak = new Button(this, SWT.NONE);
        this.btnSpeak.addSelectionListener(this);
        this.btnSpeak.setToolTipText("语音");
        this.btnSpeak.setText("语音");
        this.btnSpeak.setBounds(203, 7, 94, 24);

        this.btnPalpate = new Button(this, SWT.NONE);
        this.btnPalpate.addSelectionListener(this);
        this.btnPalpate.setToolTipText("正常肠鸣音");
        this.btnPalpate.setText("正常肠鸣音");
        this.btnPalpate.setBounds(230, 166, 94, 24);

        this.menuBreath1 = new Menu(this);
        this.menuBreath1.setData(MENU_BUTTON, this.btnBreath1);
        super.setMenu(this.menuBreath1);

        this.menuBreath2 = new Menu(this);
        this.menuBreath2.setData(MENU_BUTTON, this.btnBreath2);
        super.setMenu(this.menuBreath2);

        this.menuHeart = new Menu(this);
        this.menuHeart.setData(MENU_BUTTON, this.btnHeart);
        super.setMenu(this.menuHeart);

        this.menuPalpate = new Menu(this);
        this.menuPalpate.setData(MENU_BUTTON, this.btnPalpate);
        super.setMenu(this.menuPalpate);
    }

    /**
     * 初始化数据
     */
    public void initComposites() {
        SpeakMgt mgt = MgtFactory.get().findSpeakMgt();

        this.breaths = mgt.findBreaths();
        for (SpeakDTO speak : this.breaths) {
            MenuItem mntm1 = new MenuItem(this.menuBreath1, SWT.RADIO);
            mntm1.setData(MNTM_DATA, speak);
            mntm1.addSelectionListener(this);
            mntm1.setText(speak.getText());

            MenuItem mntm2 = new MenuItem(this.menuBreath2, SWT.RADIO);
            mntm2.setData(MNTM_DATA, speak);
            mntm2.addSelectionListener(this);
            mntm2.setText(speak.getText());
        }

        this.hearts = mgt.findHearts();
        for (SpeakDTO speak : this.hearts) {
            MenuItem mntm = new MenuItem(this.menuHeart, SWT.RADIO);
            mntm.setData(MNTM_DATA, speak);
            mntm.addSelectionListener(this);
            mntm.setText(speak.getText());
        }

        this.palpates = mgt.findPalpates();
        for (SpeakDTO speak : this.palpates) {
            MenuItem mntm = new MenuItem(this.menuPalpate, SWT.RADIO);
            mntm.setData(MNTM_DATA, speak);
            mntm.addSelectionListener(this);
            mntm.setText(speak.getText());
        }
    }

    /**
     * @see org.eclipse.swt.widgets.Control#getBackgroundImage()
     */
    public Image getBackgroundImage() {
        return this.imgBack;
    }

    /** 
     * @see org.eclipse.swt.widgets.Control#setBackgroundImage(org.eclipse.swt.graphics.Image)
     */
    public void setBackgroundImage(Image image) {
        this.imgBack = image;
        this.redraw();
    }

    /** 
     * @see org.eclipse.swt.widgets.Composite#checkSubclass()
     */
    protected void checkSubclass() {
        // Disable the check that prevents subclassing of SWT components
    }

    /** 
     * @see org.eclipse.swt.events.PaintListener#paintControl(org.eclipse.swt.events.PaintEvent)
     */
    public void paintControl(PaintEvent evt) {
        if (this.imgBack == null) {
            return;
        }

        //        int width = super.getSize().x;
        //        int height = super.getSize().y;
        //        int imgWidth = this.imgBack.getImageData().width;
        //        int imgHeight = this.imgBack.getImageData().height;
        //
        //        int x = (width - imgWidth) / 2;
        //        int y = (height - imgHeight) / 2;

        evt.gc.drawImage(this.imgBack, 0, 0);
    }

    /** 
     * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
     */
    public void widgetSelected(SelectionEvent evt) {
        Object evtObj = evt.getSource();
        if (evtObj == null) {
            return;
        }

        // 按钮
        if (Button.class.isAssignableFrom(evtObj.getClass())) {
            this.onButtonSelected((Button) evtObj);
        }

        // 菜单
        else if (MenuItem.class.isAssignableFrom(evtObj.getClass())) {
            this.onMenuItemSelected((MenuItem) evtObj);
        }
    }

    /** 
     * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
     */
    public void widgetDefaultSelected(SelectionEvent evt) {
        // ignore
    }

    /**
     * 按钮点击事件
     */
    private final void onButtonSelected(Button button) {
        // 模拟人发声
        if (button == this.btnSpeak) {
            // TODO:
            EmgeSpeakDlg dialog = new EmgeSpeakDlg(this.getShell());
            dialog.open();
        }

        // 肺泡音1
        else if (button == this.btnBreath1) {
            this.menuBreath1.setVisible(true);
        }

        // 肺泡音2
        else if (button == this.btnBreath2) {
            this.menuBreath2.setVisible(true);
        }

        // 心音
        else if (button == this.btnHeart) {
            this.menuHeart.setVisible(true);
        }

        // 肠鸣音
        else if (button == this.btnPalpate) {
            this.menuPalpate.setVisible(true);
        }
    }

    /**
     * 菜单选择事件
     */
    private final void onMenuItemSelected(MenuItem mntm) {
        // TODO:
        SpeakDTO speak = (SpeakDTO) mntm.getData(MNTM_DATA);
        LogUtils.info(ToString.toString(speak));

        Menu menu = mntm.getParent();
        Button button = (Button) menu.getData(MENU_BUTTON);
        if (button != null) {
            button.setText(speak.getText());
        }
    }

}
