/**
 * obullxl@gmail.com
 */
package com.mplat.context;

import com.mplat.das.daointerface.UserInfoDAO;
import com.mplat.das.dataobject.UserInfoDO;
import com.mplat.util.JdbcUtils;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author obullxl@gmail.com
 */
public class MplatDataHelper implements InitializingBean {

    private static final String JDBC_CATG_SQLITE = "SQLite";
    private static final String USER_ADMIN_NAME = "admin";
    private static final String USER_ADMIN_PASSWD = "888888";
    private String jdbcCatg;
    private Map<String, String> tables;
    private List<String> updates;
    private DataSource dataSource;
    private UserInfoDAO userInfoDAO;

    public void afterPropertiesSet() throws Exception {
        // this.initTables();
        // this.initUpdates();
        this.initUserInfo();
    }

    private void initTables() throws Exception {
        if (StringUtils.equalsIgnoreCase(this.jdbcCatg, JDBC_CATG_SQLITE)) {
            Connection conn = this.dataSource.getConnection();
            try {
                DatabaseMetaData meta = conn.getMetaData();
                for (Map.Entry<String, String> entry : this.tables.entrySet()) {
                    ResultSet rs = meta.getTables(null, null, entry.getKey(), new String[]{"TABLE"});
                    try {
                        if (rs.next()) {
                            continue;
                        }

                        Statement stmt = conn.createStatement();
                        try {
                            stmt.execute(entry.getValue());
                        } finally {
                            JdbcUtils.close(stmt);
                        }

                    } finally {
                        JdbcUtils.close(rs);
                    }
                }
            } finally {
                JdbcUtils.close(conn);
            }
        }
    }

    private void initUpdates() throws Exception {
        Connection conn = this.dataSource.getConnection();
        try {
            for (String update : this.updates) {
                String[] sqls = StringUtils.split(StringUtils.trimToEmpty(update), ";");
                if (sqls == null) {
                    continue;
                }

                for (String sql : sqls) {
                    Statement stmt = null;
                    try {
                        stmt = conn.createStatement();
                        stmt.execute(sql);
                    } finally {
                        JdbcUtils.close(stmt);
                    }
                }
            }
        } finally {
            JdbcUtils.close(conn);
        }
    }

    private void initUserInfo() throws Exception {
        UserInfoDO admin = this.userInfoDAO.findByName(USER_ADMIN_NAME);
        if (admin == null) {
            admin = new UserInfoDO();
            admin.setUsrName(USER_ADMIN_NAME);
            admin.setUsrPasswd(USER_ADMIN_PASSWD);

            this.userInfoDAO.insert(admin);
        }
    }

    // ~~~~~~~~~~~~ 依赖注入 ~~~~~~~~~~~~ //
    public void setJdbcCatg(String jdbcCatg) {
        this.jdbcCatg = jdbcCatg;
    }

    public void setTables(Map<String, String> tables) {
        this.tables = tables;
    }

    public void setUpdates(List<String> updates) {
        this.updates = updates;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setUserInfoDAO(UserInfoDAO userInfoDAO) {
        this.userInfoDAO = userInfoDAO;
    }
}
