/**
 * obullxl@gmail.com
 */
package mplat.mgt.dto;

import com.atom.core.lang.MapExt;
import com.atom.core.lang.ToString;
import com.atom.core.lang.ids.ID;

/**
 * @author obullxl@gmail.com
 */
public class ExamItemDTO extends ToString implements ID<Long> {
    private static final long serialVersionUID = -382377572104430128L;

    private long              id;
    private String            no;
    private long              exmId;
    private String            text;
    private MapExt            ext;

    public ExamItemDTO() {
    }

    public ExamItemDTO(String no, String text) {
        this.no = no;
        this.text = text;
    }

    // ~~~~~~~~~~~ getters and setters ~~~~~~~~~~~ //

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public long getExmId() {
        return exmId;
    }

    public void setExmId(long exmId) {
        this.exmId = exmId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
