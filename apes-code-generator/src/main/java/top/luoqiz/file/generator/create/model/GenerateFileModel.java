package top.luoqiz.file.generator.create.model;

import top.luoqiz.file.generator.create.utils.DataDeal;

import java.util.Arrays;

/**
 * <p>Title: GenerateFileModel</p>
 *
 * @author luoqiz
 * @version 1.0
 * @description TODO
 * @date 2020/12/23 13:27
 * @since 1.0
 */
public class GenerateFileModel {

    /**
     * 是否需要创建
     */
    private Boolean created;
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 包名称
     */
    private String packagePath;
    /**
     * 文件最终生成路径
     */
    private String finalPath;
    /**
     * 是否操作当前的文件
     */
    private boolean current;
    /**
     * 文件名称简称（如文件名称为 mapper.xml.ftl ,则summary的值为 mapper）
     */
    private String summary;

    /**
     * 文件后缀
     */
    private String suffix;

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }

    public String getFinalPath() {
        return finalPath;
    }

    public void setFinalPath(String finalPath) {
        this.finalPath = finalPath;
    }

    public Boolean getCreated() {
        return created;
    }

    public void setCreated(Boolean created) {
        this.created = created;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
        String[] fileSplit = fileName.trim().split("\\.");
        setSummary(DataDeal.capital(String.join(".", Arrays.copyOfRange(fileSplit, 0, fileSplit.length - 2))));
        setSuffix(fileSplit[fileSplit.length - 2]);
    }

    public String getPackagePath() {
        return packagePath;
    }

    public void setPackagePath(String packagePath) {
        this.packagePath = packagePath;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    @Override
    public String toString() {
        return "GenerateFileModel{" +
                "created=" + created +
                ", fileName='" + fileName + '\'' +
                ", packagePath='" + packagePath + '\'' +
                ", finalPath='" + finalPath + '\'' +
                ", current=" + current +
                ", summary='" + summary + '\'' +
                ", suffix='" + suffix + '\'' +
                '}';
    }
}
