/**
 * aBoy.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.atom.core.lang.utils;

import org.apache.commons.lang.StringUtils;

/**
 * 模糊搜索字段构造的工具类，用于构造模糊搜索条件。
 * 
 * @author obullxl@gmail.com
 * @version $Id: QueryLikeUtils.java, 2012-1-12 下午9:16:00 Exp $
 */
public final class QueryLikeUtils {
    /** 搜索匹配字符串% */
    private static final String SEARCH_MATCH_STR        = "%";

    /** 单个字符匹配字符串_ */
    private static final String SINGLE_SEARCH_MATCH_STR = "_";

    /** 单个字符匹配字符串\\ */
    private static final String SQL_ESCAPE_STR          = "\\";

    // 不允许实例化 
    /**
     * 构造函数
     */
    private QueryLikeUtils() {
    }

    /**
     * 规范化搜索关键字
     * <p>1.去掉搜索关键字中的空格</p>
     * <p>2.escape特殊字符,包括:\,_和'三个</p>
     * <p>3.拼接为数据库匹配的字符串%keyword%</p>
     * 
     * @param keyword 搜索关键字
     * @return 如果keyword去掉空格和%之后为空，则返回<code>NULL</code>数据库匹配的字符串
     */
    public static String normalizeKeyword(String keyword) {
        //去掉关键字头尾空格
        keyword = StringUtils.trim(keyword);
        //转义%
        keyword = StringUtils.replace(keyword, SEARCH_MATCH_STR, SQL_ESCAPE_STR + SEARCH_MATCH_STR);
        //转义_
        keyword = StringUtils.replace(keyword, SINGLE_SEARCH_MATCH_STR, SQL_ESCAPE_STR + SINGLE_SEARCH_MATCH_STR);
        //转义'
        keyword = StringUtils.replace(keyword, "'", "''");

        if (StringUtils.isBlank(keyword)) {
            return null;
        }

        //返回数据库匹配的字符串%keyword%
        return SEARCH_MATCH_STR + keyword + SEARCH_MATCH_STR;
    }

    /**
     * 转义不可直接查询的字符<p>
     * 
     * <ul>
     * <li>去掉前后空格</li>
     * <li>%->\%</li>
     * <li>_->\_</li>
     * <li>'->\'</li>
     * </ul>
     * 
     * <p>
     * for example:
     * 
     * <ul>
     * <li> quickpoint  ->quickpoint</li>
     * <li>quick%point->quick\%point</li>
     * <li>quick_point->quick\_point</li>
     * <li>quickpoint's->quickpoint\'s</li>
     * </ul>
     * @param plainQuery 普通搜索字段输入
     * @return 转义后字段，如果输入为空白或null，返回null
     */
    public static String escape(String plainQuery) {
        // 为空白或null，返回null 
        if (StringUtils.isBlank(plainQuery)) {
            return null;
        }
        //trim 输入
        String escapedQuery = StringUtils.trim(plainQuery);
        //转义%
        escapedQuery = StringUtils.replace(escapedQuery, "%", "\\%");
        //转义\_
        escapedQuery = StringUtils.replace(escapedQuery, "_", "\\_");
        //转义单引号
        escapedQuery = StringUtils.replace(escapedQuery, "'", "\\'");

        return escapedQuery;
    }

    /**
     * 构造模糊搜索字段，一般情况是前后加%<br/>
     * 
     * <strong>注意</strong>：对于含有转义字符的模糊搜索，在不同数据库系统上采用了不同的语法
     * 
     * 据目前所知，在mysql数据库上的模糊搜索，可以使用 like '%field\_test%'，而在
     * oracle数据库上模糊搜索，相应的应该写成 like '%field\_test%' escape '\'。
     * 
     * @param plainQuery 普通搜索字段输入
     * @return 模糊搜索字段，如果输入为空白或null，返回null，如果转义后为null，也返回null
     */
    public static String format(String plainQuery) {
        // 无输入则返回null
        if (StringUtils.isBlank(plainQuery)) {
            return null;
        }

        // 转义掉一些不允许输入的字符
        String escapedQuery = escape(plainQuery);

        // 如果转义后为空白，返回null
        if (StringUtils.isBlank(escapedQuery)) {
            return null;
        }

        return "%" + escapedQuery + "%";
    }
}
