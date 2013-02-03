/**
 * obullxl@gmail.com
 */
package mplat.mgt.dto;

import java.util.ArrayList;
import java.util.List;

import com.atom.core.lang.MapExt;
import com.atom.core.lang.ToString;
import com.atom.core.lang.ids.ID;

/**
 * @author obullxl@gmail.com
 */
public class ExamInfoDTO extends ToString implements ID<Long> {
    private static final long serialVersionUID = 4749376888539259294L;

    private long              id;
    private String            title;
    private String            rgtNo;
    private MapExt            ext;

    private List<ExamItemDTO> items;

    // ~~~~~~~~~~~ getters and setters ~~~~~~~~~~~ //

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRgtNo() {
        return rgtNo;
    }

    public void setRgtNo(String rgtNo) {
        this.rgtNo = rgtNo;
    }

    public List<ExamItemDTO> getItems() {
        if (this.items == null) {
            this.items = new ArrayList<>();
        }
        return items;
    }

    public void setItems(List<ExamItemDTO> items) {
        this.items = items;
    }

    public MapExt getExt() {
        if (this.ext == null) {
            this.ext = new MapExt();
        }

        return ext;
    }

    public void setExt(MapExt ext) {
        this.ext = ext;
    }

}
