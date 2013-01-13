/**
 * obullxl@gmail.com
 */
package mplat.store;

import java.util.List;
import mplat.mgt.dto.ID;

/**
 * @author obullxl@gmail.com
 */
public interface Store<T extends ID> {

    public boolean initStore();

    public boolean saveStore();

    public boolean create(T value);

    public T find(long id);

    public List<T> findAll();

    public boolean remove(long id);

    public boolean update(T value);

}
