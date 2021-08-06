package top.luoqiz.file.generator.create.model;

import java.util.List;
import java.util.Map;

/**
 * <p>Title: GeneratorConfigModel</p>
 *
 * @author luoqiz
 * @version 1.0
 * @description 配置文件
 * @date 2020/12/23 10:15
 * @since 1.0
 */
public class GeneratorConfigModel {


    /**
     * 生成的文件列表
     */
    private List<GenerateFileModel> fileList;

    /**
     * 多表联查对应关系
     */
    private List<Map<String, String>> joinRelation;

    /**
     * 表信息
     */
    private volatile TableInfoModel tableInfo = new TableInfoModel();

    /**
     * 设置作者
     */
    private String author;
    /**
     * 版本号
     */
    private String version;
    /**
     * 基础包路径
     */
    private String basePackage;

    /**
     * 模板路径
     */
    private String templatePath;

    /**
     * 去除表前缀
     */
    private String tablePrefix;

    /**
     * 生成包结构是否添加表名
     */
    private Boolean tableExists;

    /**
     * 获取项目路径
     */
    private String projectPath = System.getProperty("user.dir");

    /**
     * 页面路径
     */
    private String pagePath;

    /**
     * 是否开启swagger3
     */
    private boolean swagger3;

    /**
     * 实体类父类
     */
    private String superEntityClass;

    /**
     * 是否是链式调用
     */
    private boolean chainModel;


    public boolean isSwagger3() {
        return swagger3;
    }

    public void setSwagger3(boolean swagger3) {
        this.swagger3 = swagger3;
    }

    public String getSuperEntityClass() {
        return superEntityClass;
    }

    public void setSuperEntityClass(String superEntityClass) {
        this.superEntityClass = superEntityClass;
    }

    public boolean isChainModel() {
        return chainModel;
    }

    public void setChainModel(boolean chainModel) {
        this.chainModel = chainModel;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    public String getTablePrefix() {
        return tablePrefix;
    }

    public void setTablePrefix(String tablePrefix) {
        this.tablePrefix = tablePrefix;
    }

    public Boolean getTableExists() {
        return tableExists;
    }

    public void setTableExists(Boolean tableExists) {
        this.tableExists = tableExists;
    }

    public String getProjectPath() {
        if (projectPath == null) {
            return System.getProperty("user.dir");
        }
        return projectPath;
    }

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }

    public String getPagePath() {
        return pagePath;
    }

    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }

    public TableInfoModel getTableInfo() {
        return tableInfo;
    }

    public void setTableInfo(TableInfoModel tableInfo) {
        this.tableInfo = tableInfo;
    }

    public List<GenerateFileModel> getFileList() {
        return fileList;
    }

    public void setFileList(List<GenerateFileModel> fileList) {
        this.fileList = fileList;
    }


    public List<Map<String, String>> getJoinRelation() {
        return joinRelation;
    }

    public void setJoinRelation(List<Map<String, String>> joinRelation) {
        this.joinRelation = joinRelation;
    }


    @Override
    public String toString() {
        return "GeneratorConfigModel{" +
                ", fileList=" + fileList +
                ", joinRelation=" + joinRelation +
                ", tableInfo=" + tableInfo +
                ", author='" + author + '\'' +
                ", version='" + version + '\'' +
                ", basePackage='" + basePackage + '\'' +
                ", templatePath='" + templatePath + '\'' +
                ", tablePrefix='" + tablePrefix + '\'' +
                ", tableExists=" + tableExists +
                ", projectPath='" + projectPath + '\'' +
                ", pagePath='" + pagePath + '\'' +
                ", swagger3=" + swagger3 +
                ", superEntityClass='" + superEntityClass + '\'' +
                ", chainModel=" + chainModel +
                '}';
    }
}
