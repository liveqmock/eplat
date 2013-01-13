/**
 * obullxl@gmail.com
 */
package mplat.mgt;

/**
 * @author obullxl@gmail.com
 */
public class MgtFactory {

    private static final MgtFactory factory = new MgtFactory();
    private final ExamMgt examMgt;
    private final UserMgt userMgt;

    public static final MgtFactory get() {
        return factory;
    }

    private MgtFactory() {
        this.examMgt = new ExamMgt();
        this.userMgt = new UserMgt();
    }

    public void initMgt() {
    }

    public void stopMgt() {
    }

    public ExamMgt getExamMgt() {
        return examMgt;
    }

    public UserMgt getUserMgt() {
        return userMgt;
    }
}
