dataSource_mysql {
    dbCreate = ""
    pooled = true
    driverClassName = "com.mysql.jdbc.Driver"
    username = "root"
    password = "admingzzn"
    // url = 'jdbc:mysql://192.168.10.50:3306/gztz?useUnicode=true&characterEncoding=utf8'
    // url = 'jdbc:mysql://192.168.10.50:3306/gztz'
    // url = 'jdbc:mysql://192.168.10.50:3306/gztz?characterEncoding=utf8'
    url = 'jdbc:mysql://192.168.10.50:3306/gztz?characterEncoding=gbk'

}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory' // Hibernate 3
//    cache.region.factory_class = 'org.hibernate.cache.ehcache.EhCacheRegionFactory' // Hibernate 4
}

// environment specific settings
environments {
    development {
        dataSource {
            //dbCreate = "create-drop" // one of 'create', 'create-drop', 'update', 'validate', ''
            //url = "jdbc:h2:mem:devDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE"
            dbCreate = ""
            url = "jdbc:jtds:sqlserver://192.168.10.79:1433;databaseName=ProjectApplyDB"
            // url = "jdbc:jtds:sqlserver://192.168.1.139:1433;databaseName=ProjectApplyDB"
            // url = "jdbc:jtds:sqlserver://172.16.9.65;databaseName=ProjectApplyDB"
            // username = "PrjApply"
            // password = "12#$abCD"
            // url = "jdbc:jtds:sqlserver://localhost:1433;databaseName=ProjectApplyDB"
            username = "sa"
            password = "1"
            driverClassName = "net.sourceforge.jtds.jdbc.Driver"
            dialect = "org.hibernate.dialect.SQLServerDialect"
            readOnly=true
        }
    }
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE"
        }
    }
    production {
        dataSource {
            dbCreate = ""
            //url = "jdbc:h2:prodDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE"
            // url = "jdbc:jtds:sqlserver://130.120.2.111:1433;databaseName=ProjectApplyDB"
            // username = "sa"
            // password = "1"
            // url = "jdbc:jtds:sqlserver://172.16.9.65;databaseName=ProjectApplyDB"
            url = "jdbc:sqlserver://130.120.2.79:1433;databaseName=ProjectApplyDB"
            driverClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
            // username = "PrjApply"
            // password = "12#$abCD"
            // url = "jdbc:jtds:sqlserver://localhost;databaseName=ProjectApplyDB"
            username = "sa"
            password = "1"
            // driverClassName = "net.sourceforge.jtds.jdbc.Driver"
            dialect = "org.hibernate.dialect.SQLServerDialect"
            readOnly=true
            properties {
               maxActive = -1
               minEvictableIdleTimeMillis=1800000
               timeBetweenEvictionRunsMillis=1800000
               numTestsPerEvictionRun=3
               testOnBorrow=true
               testWhileIdle=true
               testOnReturn=false
               validationQuery="SELECT 1"
               jdbcInterceptors="ConnectionState"
            }
        }
    }
}
