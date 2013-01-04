/**
 * aBoy.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.atom.core.ticket.support;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import com.atom.core.ticket.AtomicTicket;
import com.atom.core.ticket.MutexTicket;
import com.atom.core.ticket.TicketDAO;
import com.atom.core.ticket.TicketException;

/**
 * 票据默认实现
 * 
 * @author obullxl@gmail.com
 * @version $Id: DefaultMutexTicket.java, 2012-10-19 下午9:50:27 Exp $
 */
public class DefaultMutexTicket implements MutexTicket, InitializingBean {
    private final Lock            lock = new ReentrantLock();

    /** 序列名称 */
    private String                name;

    private TicketDAO             ticketDAO;

    private volatile AtomicTicket currentTicket;

    /**
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() {
        Assert.notNull(this.name, "票据名称注入失败！");
        Assert.notNull(this.ticketDAO, "接口[TicketDAO]注入失败！");
    }

    /**
     * @see com.atom.core.ticket.MutexTicket#nextValue()
     */
    public long nextValue() throws TicketException {
        if (this.currentTicket == null) {
            this.lock.lock();
            try {
                if (this.currentTicket == null) {
                    this.currentTicket = ticketDAO.nextRange(name);
                }
            } finally {
                this.lock.unlock();
            }
        }

        long value = this.currentTicket.getAndIncrement();
        if (value == -1) {
            this.lock.lock();
            try {
                for (;;) {
                    this.currentTicket = this.ticketDAO.nextRange(this.name);

                    value = this.currentTicket.getAndIncrement();
                    if (value < 1) {
                        continue;
                    }

                    break;
                }
            } finally {
                this.lock.unlock();
            }
        }

        if (value < 0) {
            throw new TicketException("Sequence value overflow, value = " + value);
        }

        return value;
    }

    // ~~~~~~~~~~ getters and setters ~~~~~~~~~~~ //

    public void setTicketDAO(TicketDAO ticketDAO) {
        this.ticketDAO = ticketDAO;
    }

    public void setName(String name) {
        this.name = name;
    }

}
