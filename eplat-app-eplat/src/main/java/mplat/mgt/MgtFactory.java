/**
 * obullxl@gmail.com
 */
package mplat.mgt;

/**
 * @author obullxl@gmail.com
 */
public final class MgtFactory {
    /** 管理器工厂 */
    private static final MgtFactory factory = new MgtFactory();

    private final EcgtKVMgt         ecgtKVMgt;
    private final EcgtMgt           ecgtMgt;
    private final ExamMgt           examMgt;
    private final CfgMgt            cfgMgt;
    private final PumpMgt           ejectorMgt;
    private final PumpMgt           transferMgt;
    private final TreeMgt           treeMgt;
    private final UserMgt           userMgt;

    public static final MgtFactory get() {
        return factory;
    }

    public MgtFactory() {
        this.ecgtKVMgt = new EcgtKVMgt();
        this.ecgtMgt = new EcgtMgt();
        this.examMgt = new ExamMgt();
        this.cfgMgt = new CfgMgt();
        this.ejectorMgt = new PumpMgt(PumpMgt.EJECTOR);
        this.transferMgt = new PumpMgt(PumpMgt.TRANSFER);
        this.treeMgt = new TreeMgt();
        this.userMgt = new UserMgt();
    }

    public void initMgt() {
    }

    public void stopMgt() {
    }

    public EcgtKVMgt findEcgtKVMgt() {
        return this.ecgtKVMgt;
    }

    public EcgtMgt findEcgtMgt() {
        return this.ecgtMgt;
    }

    public ExamMgt findExamMgt() {
        return this.examMgt;
    }

    public CfgMgt findCfgMgt() {
        return this.cfgMgt;
    }

    public PumpMgt findEjectorMgt() {
        return this.ejectorMgt;
    }

    public PumpMgt findTransferMgt() {
        return transferMgt;
    }

    public TreeMgt findTreeMgt() {
        return treeMgt;
    }

    public UserMgt findUserMgt() {
        return userMgt;
    }
}
