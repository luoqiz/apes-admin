package top.luoqiz.file.generator.create.model;

import top.luoqiz.file.generator.create.utils.DataDeal;

/**
 * @author luoqiz
 * @ClassName: JtableDataModel
 * @Description: 表格数据模型
 * @date 2017年6月30日 下午1:24:10
 */
public class TableColumnInfoModel {

    public TableColumnInfoModel(int num, String dbColumnName, String dbColumnType, boolean indexKey, boolean primaryKey,
                                int columnLength, boolean nullValue, String defaultValue, String comment,
                                String searchWay, String otherOpt, boolean frontShow) {
        this.num = num;
        this.dbColumnName = dbColumnName;
        this.javaColumnType = DataDeal.dbType2JavaType(dbColumnType);
        this.tsColumnType = DataDeal.dbType2TsType(dbColumnType);
        this.javaColumnName = DataDeal.getCamelStr(dbColumnName);
        this.mybatisColumnType = DataDeal.dbType2MybatisType(dbColumnType);
        this.dbColumnType = dbColumnType;
        this.indexKey = indexKey;
        this.primaryKey = primaryKey;
        this.columnLength = columnLength;
        this.nullValue = nullValue;
        this.defaultValue = defaultValue;
        this.comment = comment;
        this.searchWay = searchWay;
        this.otherOpt = otherOpt;
        this.frontShow = frontShow;
    }

    /**
     * 序号
     */
    private int num;
    /**
     * 数据库中的列名
     */
    private String dbColumnName;
    /**
     * java中的列名
     */
    private String javaColumnName;
    /**
     * 数据库中的字段类型
     */
    private String dbColumnType;
    /**
     * java中对应的字段数据类型
     */
    private String javaColumnType;
    /**
     * 前端ts文件对应的字段数据类型
     */
    private String tsColumnType;
    /**
     * mybatis中对应的字段数据类型
     */
    private String mybatisColumnType;
    /**
     * 是否是索引字段
     */
    private boolean indexKey;

    /**
     * 前端是否展示
     */
    private boolean frontShow;
    //是否是主键
    private boolean primaryKey;
    //共有多少列
    private int columnLength;
    //是否允许为空
    private boolean nullValue;
    //默认值
    private String defaultValue;
    //备注
    private String comment;
    //查询模式
    private String searchWay;
    //其他操作
    private String otherOpt;

    public boolean isFrontShow() {
        return frontShow;
    }

    public void setFrontShow(boolean frontShow) {
        this.frontShow = frontShow;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public boolean isIndexKey() {
        return indexKey;
    }

    public void setIndexKey(boolean indexKey) {
        this.indexKey = indexKey;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    public int getColumnLength() {
        return columnLength;
    }

    public void setColumnLength(int columnLength) {
        this.columnLength = columnLength;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getSearchWay() {
        return searchWay;
    }

    public void setSearchWay(String searchWay) {
        this.searchWay = searchWay;
    }

    public String getOtherOpt() {
        return otherOpt;
    }

    public void setOtherOpt(String otherOpt) {
        if (otherOpt == null || "".equals(otherOpt) || "null".equals(otherOpt)) {
            otherOpt = "and";
        }
        this.otherOpt = otherOpt;
    }

    public boolean isNullValue() {
        return nullValue;
    }

    public String getTsColumnType() {
        return tsColumnType;
    }

    public void setTsColumnType(String tsColumnType) {
        this.tsColumnType = tsColumnType;
    }

    public void setNullValue(boolean nullValue) {
        this.nullValue = nullValue;
    }

    public String getJavaColumnType() {
        return javaColumnType;
    }

    public void setJavaColumnType(String javaColumnType) {
        this.javaColumnType = javaColumnType;
    }

    public String getDbColumnName() {
        return dbColumnName;
    }

    public void setDbColumnName(String dbColumnName) {
        this.dbColumnName = dbColumnName;
    }

    public String getJavaColumnName() {
        return javaColumnName;
    }

    public void setJavaColumnName(String javaColumnName) {
        this.javaColumnName = javaColumnName;
    }

    public String getDbColumnType() {
        return dbColumnType;
    }

    public void setDbColumnType(String dbColumnType) {
        this.dbColumnType = dbColumnType;
    }

    @Override
    public String toString() {
        return "TableDataModel{" +
                "num=" + num +
                ", dbColumnName='" + dbColumnName + '\'' +
                ", javaColumnName='" + javaColumnName + '\'' +
                ", dbColumnType='" + dbColumnType + '\'' +
                ", javaColumnType='" + javaColumnType + '\'' +
                ", indexKey=" + indexKey +
                ", primaryKey=" + primaryKey +
                ", columnLength=" + columnLength +
                ", nullValue=" + nullValue +
                ", defaultValue='" + defaultValue + '\'' +
                ", comment='" + comment + '\'' +
                ", searchWay='" + searchWay + '\'' +
                ", otherOpt='" + otherOpt + '\'' +
                '}';
    }

    public String getMybatisColumnType() {
        return mybatisColumnType;
    }

    public void setMybatisColumnType(String mybatisColumnType) {
        this.mybatisColumnType = mybatisColumnType;
    }
}
