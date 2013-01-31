/**
 * obullxl@gmail.com
 */
package mplat.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author obullxl@gmail.com
 */
public class DataMap {

    private static final ThreadLocal<DataMap> _holder = new ThreadLocal<>();

    public static void set(DataMap dmap) {
        _holder.set(dmap);
    }

    public static void remove() {
        _holder.remove();
    }

    public static DataMap find() {
        return _holder.get();
    }

    public static DataMap fetch() {
        DataMap dmap = find();
        if (dmap == null) {
            set(new DataMap());
        }

        return find();
    }
    private Map<String, Object> _data = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        return (T) _data.get(key);
    }

    public void put(String key, Object value) {
        _data.put(key, value);
    }

    @SuppressWarnings("unchecked")
    public <T> T remove(String key) {
        return (T) _data.remove(key);
    }
}
