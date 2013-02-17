/**
 * obullxl@gmail.com
 */
package mplat.mgt.dto;

import com.atom.core.lang.MapExt;
import com.atom.core.lang.ids.LongID;

/**
 * @author obullxl@gmail.com
 */
public class ExamInfoDTO extends LongID {
    private static final long serialVersionUID = 4749376888539259294L;

    private String            title;
    private String            rgtNo;
    private String            itemA;
    private String            itemB;
    private String            itemC;
    private String            itemD;

    private MapExt            ext;

    // ~~~~~~~~~~~ getters and setters ~~~~~~~~~~~ //

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

    public MapExt getExt() {
        if (this.ext == null) {
            this.ext = new MapExt();
        }

        return ext;
    }

    public void setExt(MapExt ext) {
        this.ext = ext;
    }

    public String getItemA() {
        return itemA;
    }

    public void setItemA(String itemA) {
        this.itemA = itemA;
    }

    public String getItemB() {
        return itemB;
    }

    public void setItemB(String itemB) {
        this.itemB = itemB;
    }

    public String getItemC() {
        return itemC;
    }

    public void setItemC(String itemC) {
        this.itemC = itemC;
    }

    public String getItemD() {
        return itemD;
    }

    public void setItemD(String itemD) {
        this.itemD = itemD;
    }

}
