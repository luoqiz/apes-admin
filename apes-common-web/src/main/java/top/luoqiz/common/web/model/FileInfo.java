package top.luoqiz.common.web.model;

/**
 * 前后端交互的文件信息
 *
 * @author luoqiz
 */
public class FileInfo {
    /**
     * 唯一id
     */
    private String uid;
    /**
     * 文件名称
     */
    private String name;
    /**
     * 文件保存状态
     */
    private String status;
    /**
     * 文件地址
     */
    private String url;
    /**
     * 文件类型
     */
    private String type;
    /**
     * 文件大小
     */
    private Integer size;

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUid() {
        return this.uid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getSize() {
        return this.size;
    }


}