package com.atom.apps.eplat.views;

import mplat.mgt.MgtFactory;
import mplat.mgt.TreeMgt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.sf.feeling.swt.win32.extension.ole.flash.Flash;

import com.atom.apps.eplat.SWTUtils;
import com.atom.core.lang.Tuple;

public class EmgeMainView extends Composite {
    /** 大小 */
    public static final Tuple<Integer, Integer> SIZE = new Tuple<Integer, Integer>(1000, 800);

    private final String                        caseName;

    private EmgeCaseCmpt                     cmptCase;
    private EmgeBodyCmpt                      cmptBody;
    private EmgeTreeCmpt                      cmptTree;
    private EmgeLogCmpt                       cmptLog;

    /**
     * Create the composite.
     */
    public EmgeMainView(Composite parent, String caseName) {
        super(parent, SWT.NONE);

        this.caseName = caseName;
        
        SWTUtils.setEmgeMainView(this);

        initComponents();
    }

    private void initComponents() {
        GridLayout gridLayout = new GridLayout(2, true);
        gridLayout.marginWidth = 0;
        gridLayout.verticalSpacing = 0;
        gridLayout.marginHeight = 0;
        gridLayout.horizontalSpacing = 0;
        setLayout(gridLayout);

        if (Flash.canCreate()) {
            GridData gd_cmptCase = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
            gd_cmptCase.widthHint = 500;
            gd_cmptCase.heightHint = 375;
            this.cmptCase = new EmgeCaseCmpt(this, this.caseName);
            this.cmptCase.startAnimeCase();
            cmptCase.setVisible(true);
            this.cmptCase.setLayoutData(gd_cmptCase);
        }

        GridData gd_cmptBody = new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1);
        gd_cmptBody.widthHint = 355;
        gd_cmptBody.heightHint = 306;
        this.cmptBody = new EmgeBodyCmpt(this);
        this.cmptBody.setLayoutData(gd_cmptBody);
        this.cmptBody.initComposites();

        if (!Flash.canCreate()) {
            Composite composite_1 = new Composite(this, SWT.NONE);
            composite_1.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
        }

        this.cmptLog = new EmgeLogCmpt(this);
        GridData gd_cmptLog = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
        gd_cmptLog.widthHint = 500;
        gd_cmptLog.heightHint = 150;
        this.cmptLog.setLayoutData(gd_cmptLog);
        cmptLog.setLayoutData(gd_cmptLog);

        this.cmptTree = new EmgeTreeCmpt(this);
        GridData gd_cmptTree = new GridData(SWT.LEFT, SWT.TOP, true, false, 1, 1);
        gd_cmptTree.widthHint = 500 - 20;
        gd_cmptTree.heightHint = 150;
        this.cmptTree.setLayoutData(gd_cmptTree);
        TreeMgt mgt = MgtFactory.get().findTreeMgt();
        this.cmptTree.updateTreeNodes(mgt.findEmergeAbcNodes(), mgt.findEmergeMiscNodes(), mgt.findEmergeMedicNodes());
        new Label(this, SWT.NONE);
    }

    /** 
     * @see org.eclipse.swt.widgets.Composite#checkSubclass()
     */
    protected void checkSubclass() {
    }

    /**
     * 增加事件属性日志
     */
    public void onCreateEventLog(String time, String log) {
        this.cmptLog.onCreateEventLog(time, log);
    }

}
