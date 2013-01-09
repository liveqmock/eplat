/**
 * obullxl@gmail.com
 */
package com.mplat.mgt.impl;

import com.mplat.das.daointerface.ExamInfoDAO;
import com.mplat.das.daointerface.ExamItemDAO;
import com.mplat.das.daointerface.UserInfoDAO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author obullxl@gmail.com
 */
public abstract class BaseMgtImpl {

    protected static final Logger logger = Logger.getLogger(BaseMgtImpl.class);
    @Autowired
    protected TransactionTemplate transactionTemplate;
    @Autowired
    protected ExamInfoDAO examInfoDAO;
    @Autowired
    protected ExamItemDAO examItemDAO;
    @Autowired
    protected UserInfoDAO userInfoDAO;
}
