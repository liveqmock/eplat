/**
 * obullxl@gmail.com
 */
package mplat.store;

import mplat.store.impl.ExamStore;
import mplat.store.impl.UserStore;

/**
 * @author obullxl@gmail.com
 */
public class StoreFactory {

    private static final StoreFactory factory = new StoreFactory();
    private final ExamStore examStore;
    private final UserStore userStore;

    public static final StoreFactory get() {
        return factory;
    }

    private StoreFactory() {
        this.examStore = new ExamStore();
        this.userStore = new UserStore();
    }

    public void init() {
        this.examStore.initStore();
        this.userStore.initStore();

    }

    public void stop() {
        this.examStore.saveStore();
        this.userStore.saveStore();
    }

    public ExamStore getExamStore() {
        return examStore;
    }

    public UserStore getUserStore() {
        return userStore;
    }
}
