/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.apps.eplat.views.dtos;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.time.DateUtils;

import com.atom.core.lang.ToString;

/**
 * ACLS理论知识考核
 * 
 * @author obullxl@gmail.com
 * @version $Id: ExamEvaluateDTO.java, V1.0.1 2013-4-12 下午10:05:25 $
 */
public class ExamEvaluateDTO extends ToString {
    private static final long serialVersionUID = 8743894155392350976L;

    private String            userName;

    private Date              begin            = new Date();
    private Date              finish           = new Date();

    private int               totalCount;

    private int               rightCount;
    private final Set<String> rightExams       = new HashSet<String>();

    /**
     * 时间秒
     */
    public long findMillis() {
        return (this.finish.getTime() - this.begin.getTime());
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getFinish() {
        return finish;
    }

    public void setFinish(Date finish) {
        this.finish = finish;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getRightCount() {
        return rightCount;
    }

    public void setRightCount(int rightCount) {
        this.rightCount = rightCount;
    }

    public Set<String> getRightExams() {
        return rightExams;
    }

}
