package top.luoqiz.file.generator.create.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;


/**
 * @author luoqiz
 */
public class TableInfoModel implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private List<TableColumnInfoModel> colInfoList;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 表注释
     */
    private String comment;

    /**
     * 列名数组
     */
    private String[] colNames;
    /**
     * 数据库列名数组
     */
    private String[] jdbcColNames;
    /**
     * java列名类型数组
     */
    private String[] javaColTypes;
    /**
     * 列名类型数组
     */
    private String[] jdbcColTypes;
    /**
     * 列的个数
     */
    private int colSize;

    /**
     * 表的主键（在数据库中）
     */
    private String sqlTablePK;
    /**
     * 表的主键（在java中对应）
     */
    private String javaTablePK;
    /**
     * 表的主键类型（数据库中对应）
     */
    private String sqlTablePKType;
    /**
     * 表的主键类型（java中对应）
     */
    private String javaTablePKType;
    /**
     * 类名
     */
    private String className;

    /**
     * 首字母小写的类名
     */
    private String classNameFirstLower;

    public List<TableColumnInfoModel> getColInfoList() {
        return colInfoList;
    }

    public void setColInfoList(List<TableColumnInfoModel> colInfoList) {
        this.colInfoList = colInfoList;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String[] getColNames() {
        return colNames;
    }

    public void setColNames(String[] colNames) {
        this.colNames = colNames;
    }

    public String[] getJdbcColNames() {
        return jdbcColNames;
    }

    public void setJdbcColNames(String[] jdbcColNames) {
        this.jdbcColNames = jdbcColNames;
    }

    public String[] getJavaColTypes() {
        return javaColTypes;
    }

    public void setJavaColTypes(String[] javaColTypes) {
        this.javaColTypes = javaColTypes;
    }

    public String[] getJdbcColTypes() {
        return jdbcColTypes;
    }

    public void setJdbcColTypes(String[] jdbcColTypes) {
        this.jdbcColTypes = jdbcColTypes;
    }

    public int getColSize() {
        return colSize;
    }

    public void setColSize(int colSize) {
        this.colSize = colSize;
    }

    public String getSqlTablePK() {
        return sqlTablePK;
    }

    public void setSqlTablePK(String sqlTablePK) {
        this.sqlTablePK = sqlTablePK;
    }

    public String getJavaTablePK() {
        return javaTablePK;
    }

    public void setJavaTablePK(String javaTablePK) {
        this.javaTablePK = javaTablePK;
    }

    public String getSqlTablePKType() {
        return sqlTablePKType;
    }

    public void setSqlTablePKType(String sqlTablePKType) {
        this.sqlTablePKType = sqlTablePKType;
    }

    public String getJavaTablePKType() {
        return javaTablePKType;
    }

    public void setJavaTablePKType(String javaTablePKType) {
        this.javaTablePKType = javaTablePKType;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassNameFirstLower() {
        return classNameFirstLower;
    }

    public void setClassNameFirstLower(String classNameFirstLower) {
        this.classNameFirstLower = classNameFirstLower;
    }

    @Override
    public String toString() {
        return "TableInfoModel{" +
                "colInfoList=" + colInfoList +
                ", tableName='" + tableName + '\'' +
                ", colNames=" + Arrays.toString(colNames) +
                ", jdbcColNames=" + Arrays.toString(jdbcColNames) +
                ", javaColTypes=" + Arrays.toString(javaColTypes) +
                ", jdbcColTypes=" + Arrays.toString(jdbcColTypes) +
                ", colSize=" + colSize +
                ", sqlTablePK='" + sqlTablePK + '\'' +
                ", javaTablePK='" + javaTablePK + '\'' +
                ", sqlTablePKType='" + sqlTablePKType + '\'' +
                ", javaTablePKType='" + javaTablePKType + '\'' +
                ", className='" + className + '\'' +
                ", classNameFirstLower='" + classNameFirstLower + '\'' +
                '}';
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
