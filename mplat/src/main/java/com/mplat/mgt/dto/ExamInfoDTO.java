/**
 * obullxl@gmail.com
 */
package com.mplat.mgt.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author obullxl@gmail.com
 */
public class ExamInfoDTO extends BaseDTO {

    /**
     * column:id
     */
    private long id;
    /**
     * column:title
     */
    private String title;
    /**
     * column:rgt_no
     */
    private String rgtNo;
    
    private List<ExamItemDTO> items;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
            this.items = new ArrayList<ExamItemDTO>();
        }
        return items;
    }

    public void setItems(List<ExamItemDTO> items) {
        this.items = items;
    }
    
}
