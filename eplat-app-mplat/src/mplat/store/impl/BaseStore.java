/**
 * obullxl@gmail.com
 */
package mplat.store.impl;

import com.thoughtworks.xstream.XStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import mplat.mgt.dto.ID;
import mplat.store.Store;
import mplat.utils.CfgUtils;
import mplat.utils.LogUtils;
import org.apache.commons.io.IOUtils;

/**
 * @author obullxl@gmail.com
 */
public abstract class BaseStore<T extends ID> implements Store<T> {

    private static final XStream xstream = new XStream();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Map<Long, T> store = new ConcurrentHashMap<>();
    private AtomicLong id = new AtomicLong();
    private String storePathName = "store";

    public void setStorePathName(String pathName) {
        this.storePathName = pathName;
    }

    public static XStream getXStream() {
        return xstream;
    }

    /**
     * 初始化工作
     */
    public abstract void initExt();

    /**
     * 获取数据文件名称，需要子类实现
     *
     * @return 数据文件名称
     */
    public abstract String findDataName();

    public boolean initStore() {
        boolean rtn = true;

        this.lock.writeLock().lock();
        try {
            this.initExt();

            xstream.alias("ID", ID.class);

            String cfg = CfgUtils.findConfigPath();
            File root = new File(cfg + "/" + this.storePathName);
            if (!root.exists()) {
                root.mkdirs();
            }

            File file = new File(root, this.findDataName());
            if (file.exists()) {
                this.store.putAll((Map<Long, T>) xstream.fromXML(file));
                this.id.set(this.findMaxID());
            }
        } catch (Exception e) {
            rtn = false;
            LogUtils.error(this.clazz() + "实始化数据存储异常.", e);
        } finally {
            this.lock.writeLock().unlock();
        }

        return rtn;
    }

    public boolean create(T value) {
        boolean rtn = true;

        this.lock.writeLock().lock();
        try {
            value.setId(this.id.incrementAndGet());
            this.update(value);
        } finally {
            this.lock.writeLock().unlock();
        }

        return rtn;
    }

    public boolean saveStore() {
        boolean rtn = true;

        this.lock.writeLock().lock();
        try {
            String cfg = CfgUtils.findConfigPath();
            File root = new File(cfg + "/" + this.storePathName);
            if (!root.exists()) {
                root.mkdirs();
            }

            File file = new File(root, this.findDataName());
            OutputStream out = new FileOutputStream(file);
            try {
                xstream.toXML(this.store, out);
            } finally {
                IOUtils.closeQuietly(out);
            }
        } catch (Exception e) {
            rtn = false;
            LogUtils.error(this.clazz() + "保存异常.", e);
        } finally {
            this.lock.writeLock().unlock();
        }

        return rtn;
    }

    public T find(long id) {
        this.lock.readLock().lock();
        try {
            return this.store.get(id);
        } finally {
            this.lock.readLock().unlock();
        }
    }

    public List<T> findAll() {
        this.lock.readLock().lock();
        try {
            return new ArrayList<>(this.store.values());
        } finally {
            this.lock.readLock().unlock();
        }
    }

    public boolean remove(long id) {
        boolean rtn = true;

        this.lock.writeLock().lock();
        try {
            this.store.remove(id);
            rtn = this.saveStore();
        } catch (Exception e) {
            rtn = false;
            LogUtils.error(this.clazz() + "删除异常, ID[" + id + "].", e);
        } finally {
            this.lock.writeLock().unlock();
        }

        return rtn;
    }

    public boolean update(T value) {
        boolean rtn = true;

        this.lock.writeLock().lock();
        try {
            this.store.put(value.getId(), value);
            rtn = this.saveStore();
        } catch (Exception e) {
            rtn = false;
            LogUtils.error(this.clazz() + "更新异常, Value[" + value + "].", e);
        } finally {
            this.lock.writeLock().unlock();
        }

        return rtn;
    }

    public String clazz() {
        return this.getClass().getSimpleName();
    }

    private long findMaxID() {
        long max = 0L;

        for (long tid : this.store.keySet()) {
            max = Math.max(max, tid);
        }

        return max;
    }
}
