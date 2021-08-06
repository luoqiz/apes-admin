package top.luoqiz.system.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * <p>
 * 软件版本
 * </p>
 *
 * @author luoqiz
 * @since 2021-05-21
 */
@TableName("sys_software_version")
@Schema(name = "SysSoftwareVersion对象", description = "软件版本")
public class SysSoftwareVersionEntity extends Model<SysSoftwareVersionEntity> {

    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    @TableId("id")
    private Long id;

    @Schema(description = "软件类型 1app 2网站")
    private String type;

    @Schema(description = "版本名称")
    private String versionName;

    @Schema(description = "版本号")
    private String version;

    @Schema(description = "当前版本构建次数")
    private String build;

    @Schema(description = "版本更新内容")
    private String mark;

    @Schema(description = "强制升级")
    private Boolean forceUpdate;

    @Schema(description = "下载地址")
    private String downlaodUrl;

    @Schema(description = "下载次数")
    private String downloadNum;

    @Schema(description = "创建日期")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;


    public Boolean getForceUpdate() {
        return forceUpdate;
    }

    public void setForceUpdate(Boolean forceUpdate) {
        this.forceUpdate = forceUpdate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getDownlaodUrl() {
        return downlaodUrl;
    }

    public void setDownlaodUrl(String downlaodUrl) {
        this.downlaodUrl = downlaodUrl;
    }

    public String getDownloadNum() {
        return downloadNum;
    }

    public void setDownloadNum(String downloadNum) {
        this.downloadNum = downloadNum;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }


    @Override
    public String toString() {
        return "SysSoftwareVersion{" +
                "id=" + id +
                ", type=" + type +
                ", versionName=" + versionName +
                ", version=" + version +
                ", build=" + build +
                ", mark=" + mark +
                ", forceUpdate=" + forceUpdate +
                ", downlaodUrl=" + downlaodUrl +
                ", downloadNum=" + downloadNum +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                "}";
    }

}
