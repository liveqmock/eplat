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

    private final EcgtMgt           ecgtMgt;
    private final ExamMgt           examMgt;
    private final GsetMgt           gsetMgt;
    private final UserMgt           userMgt;

    public static final MgtFactory get() {
        return factory;
    }

    private MgtFactory() {
        this.ecgtMgt = new EcgtMgt();
        this.examMgt = new ExamMgt();
        this.gsetMgt = new GsetMgt();
        this.userMgt = new UserMgt();
    }

    public void initMgt() {
    }

    public void stopMgt() {
    }

    public EcgtMgt findEcgtMgt() {
        return ecgtMgt;
    }

    public ExamMgt findExamMgt() {
        return examMgt;
    }

    public GsetMgt findGsetMgt() {
        return gsetMgt;
    }

    public UserMgt findUserMgt() {
        return userMgt;
    }
}
