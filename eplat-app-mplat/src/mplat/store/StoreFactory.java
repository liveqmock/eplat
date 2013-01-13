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
    private final Store examStore;
    private final Store userStore;

    public static final StoreFactory get() {
        return factory;
    }

    public StoreFactory() {
        this.examStore = new ExamStore();
        this.userStore = new UserStore();
    }

    public void initStores() {
        this.examStore.initStore();
        this.userStore.initStore();

    }

    public void saveStores() {
        this.examStore.saveStore();
        this.userStore.saveStore();
    }

    public Store getExamStore() {
        return examStore;
    }

    public Store getUserStore() {
        return userStore;
    }
}
