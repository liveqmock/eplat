/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mplat.orm.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class ExamInfoDTO {

    private long id;
    private String title;
    private List<ExamItemDTO> items;
    private String rgtNo;

    @Override
    public String toString() {
        return this.title;
    }

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

    public List<ExamItemDTO> getItems() {
        if(this.items == null) {
            this.items = new ArrayList<ExamItemDTO>();
        }
        
        return items;
    }

    public void setItems(List<ExamItemDTO> items) {
        this.items = items;
    }

    public String getRgtNo() {
        return rgtNo;
    }

    public void setRgtNo(String rgtNo) {
        this.rgtNo = rgtNo;
    }
}
