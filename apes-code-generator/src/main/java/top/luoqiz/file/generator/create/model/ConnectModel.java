package top.luoqiz.file.generator.create.model;

/**
 * @author luoqiz
 */
public class ConnectModel {

    // 数据库类型
    private String dbType;
    // 数据库连接地址
    private String dbAddr;
    // 数据库连接端口
    private String dbPort;
    // 数据库连接库名
    private String dbName;

    // 数据库连接全地址
    private String dbConnectAddr;

    // 数据库连接用户名
    private String dbUserName;

    // 数据库连接密码
    private String dbUserPWD;

    // 数据库连接驱动
    private String dbDriverName;

    // 数据库表名查询语句
    private String tableQuerySql;

    // 数据库选择的表名
    private String tableName;

    // 数据库列名查询语句
    private String columnQuerySql;

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        if (dbType.equalsIgnoreCase("mssql")) {
            this.dbDriverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        } else if (dbType.equalsIgnoreCase("mysql")) {
            this.dbDriverName = "com.mysql.cj.jdbc.Driver";
        } else if (dbType.equalsIgnoreCase("oracle")) {
            this.dbDriverName = "oracle.jdbc.driver.OracleDriver";
        } else {
            System.err.println("数据库选型出错");
        }
        this.dbType = dbType;
    }

    public String getDbAddr() {
        return dbAddr;
    }

    public void setDbAddr(String dbAddr) {
        this.dbAddr = dbAddr;
    }

    public String getDbPort() {
        return dbPort;
    }

    public void setDbPort(String dbPort) {
        this.dbPort = dbPort;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDbUserName() {
        return dbUserName;
    }

    public void setDbUserName(String dbUserName) {
        this.dbUserName = dbUserName;
    }

    public String getDbUserPWD() {
        return dbUserPWD;
    }

    public void setDbUserPWD(String dbUserPWD) {
        this.dbUserPWD = dbUserPWD;
    }

    public String getDbDriverName() {
        return dbDriverName;
    }

    public void setDbDriverName(String dbDriverName) {
        this.dbDriverName = dbDriverName;
    }

    public String getTableQuerySql() {
        if (this.dbType.equalsIgnoreCase("mssql")) {
            this.tableQuerySql = "select name from sysobjects where xtype='u'";
        } else if (this.dbType.equalsIgnoreCase("mysql")) {
            this.tableQuerySql = "select TABLE_NAME, TABLE_COMMENT from information_schema.tables where table_schema='" + dbName + "'";
        } else if (this.dbType.equalsIgnoreCase("oracle")) {
            this.tableQuerySql = "";
        } else {
            System.err.println("数据库选型出错");
        }
        return tableQuerySql;
    }

    public void setTableQuerySql(String tableQuerySql) {
        this.tableQuerySql = tableQuerySql;
    }

    public String getDbConnetAddr() {
        if (this.getDbType().equalsIgnoreCase("mssql")) {
            this.dbConnectAddr = "jdbc:sqlserver://" + this.getDbAddr() + ":" + this.getDbPort() + ";DatabaseName="
                    + this.getDbName();
            // +"?" +
            // "user="+this.getDbUserName()+"&password="+this.getDbUserPWD();
        } else if (this.getDbType().equalsIgnoreCase("mysql")) {
            this.dbConnectAddr = "jdbc:mysql://" + this.getDbAddr() + ":" + this.getDbPort() + "/" + this.getDbName()
                    + "?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT";
        } else {
            this.dbConnectAddr = "jdbc:oracle:thin:@" + this.getDbAddr() + ":" + this.getDbPort() + ":"
                    + this.getDbName();// XE是精简版Oracle的默认数据库名
            // +"?" +
            // "user="+this.getDataUserName()+"&password="+this.getDataUserPWD()+"&useUnicode=true&characterEncoding=UTF8";
        }
        return dbConnectAddr;
    }

    public void setDbConnetAddr(String dbConnectAddr) {
        this.dbConnectAddr = dbConnectAddr;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        if (this.dbType.equalsIgnoreCase("mssql")) {
            this.columnQuerySql = "SELECT fieldNum = a.colorder,fieldName  = a.name,fieldType = b.name,fieldIndex = case when COLUMNPROPERTY( a.id,a.name,'IsIdentity')=1 then 'Yes' else '' end,"
                    + " fieldKey = case when exists(SELECT 1 FROM sysobjects where xtype='PK' and parent_obj=a.id and name in (SELECT name FROM sysindexes WHERE indid in( SELECT indid FROM sysindexkeys WHERE id = a.id AND colid=a.colid))) then 'Yes' else '' end,"
                    + " fieldLen = COLUMNPROPERTY(a.id,a.name,'PRECISION'),fieldIsNull= case when a.isnullable=1 then 'Yes' else '' end,"
                    + " fieldDefault= isnull(e.text,''),fieldMark = isnull(cast(g.value as varchar(500)) ,'')"
                    + " FROM syscolumns a left join systypes b on a.xusertype=b.xusertype inner join sysobjects d on a.id=d.id  and d.xtype='U' and  d.name<>'dtproperties' left join syscomments e on a.cdefault=e.id left join sys.extended_properties g on a.id=G.major_id and a.colid=g.minor_id  left join sys.extended_properties f"
                    + " on d.id=f.major_id and f.minor_id=0 where d.name='" + tableName + "' order by  a.id,a.colorder";
            ;
        } else if (this.dbType.equalsIgnoreCase("mysql")) {
            this.columnQuerySql = "SELECT\tordinal_position AS fieldNum,\tcolumn_name AS fieldName,\tdata_type AS fieldType,CASE\t\tcolumn_key \t\tWHEN 'PRI' THEN\t\t'YES' \t\tWHEN 'MUL' THEN\t\t'YES' ELSE 'NO' \tEND AS fieldIndex,CASE\t\tcolumn_key \t\tWHEN 'PRI' THEN\t\t'YES' ELSE 'NO' \tEND AS fieldKey,\tcharacter_maximum_length AS fieldLen,\tis_nullAble AS fieldIsNull,\tcolumn_default AS fieldDefault,\tcolumn_comment AS fieldMark "
                    + "from information_schema.columns where table_name='" + tableName + "' AND TABLE_SCHEMA='" + dbName
                    + "'";
        } else if (this.dbType.equalsIgnoreCase("oracle")) {
            this.columnQuerySql = "";
        } else {
            System.err.println("数据库选型出错");
        }
        this.tableName = tableName;
    }

    public String getColumnQuerySql() {
        return columnQuerySql;
    }

    public void setColumnQuerySql(String columnQuerySql) {
        this.columnQuerySql = columnQuerySql;
    }

}
