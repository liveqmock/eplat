dataSource {
    pooled = true
	logSql = true
    driverClassName = "com.mysql.jdbc.Driver"
	url = "jdbc:mysql://127.0.01:3306/misc?useUnicode=true&amp;characterEncoding=UTF8"
    username = "misc"
    password = "misc"
}

hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}

// environment specific settings
environments {
    development {
        dataSource {
            // dbCreate = "create-drop" // one of 'create', 'create-drop', 'update', 'validate', ''
            logSql = true
        }
    }
    test {
        dataSource {
            logSql = true
        }
    }
    production {
        dataSource {
            // dbCreate = "update"
            // url = "jdbc:h2:prodDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
            pooled = true
			logSql = true
            properties {
               maxActive = -1
               minEvictableIdleTimeMillis=1800000
               timeBetweenEvictionRunsMillis=1800000
               numTestsPerEvictionRun=3
               testOnBorrow=true
               testWhileIdle=true
               testOnReturn=true
               validationQuery="SELECT 1"
            }
        }
    }
}
