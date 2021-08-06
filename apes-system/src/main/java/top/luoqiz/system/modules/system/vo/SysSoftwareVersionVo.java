package top.luoqiz.system.modules.system.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.BeanUtils;
import top.luoqiz.system.modules.system.entity.SysSoftwareVersionEntity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

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
public class SysSoftwareVersionVo implements Serializable {

    private static final long serialVersionUID = 1L;


    @Schema(description = "ID")
    @ExcelProperty(value = "ID", index = 0)
    private Long id;


    @Schema(description = "软件类型 1app 2网站")
    @ExcelProperty(value = "软件类型 1app 2网站", index = 1)
    private String type;


    @Schema(description = "版本名称")
    @ExcelProperty(value = "版本名称", index = 2)
    private String versionName;


    @Schema(description = "版本号")
    @ExcelProperty(value = "版本号", index = 3)
    private String version;


    @Schema(description = "当前版本构建次数")
    @ExcelProperty(value = "当前版本构建次数", index = 4)
    private String build;


    @Schema(description = "版本更新内容")
    @ExcelProperty(value = "版本更新内容", index = 5)
    private String mark;

    @Schema(description = "强制升级")
    @ExcelProperty(value = "强制升级", index = 5)
    private Boolean forceUpdate;

    @Schema(description = "下载地址")
    @ExcelProperty(value = "下载地址", index = 6)
    private String downlaodUrl;


    @Schema(description = "下载次数")
    @ExcelProperty(value = "下载次数", index = 7)
    private String downloadNum;


    @Schema(description = "创建日期")
    @ExcelProperty(value = "创建日期", index = 8)
    private LocalDateTime createTime;


    @Schema(description = "更新时间")
    @ExcelProperty(value = "更新时间", index = 9)
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


    public static SysSoftwareVersionVo fromEntity(SysSoftwareVersionEntity entity) {
        if (entity == null) {
            return null;
        }
        SysSoftwareVersionVo vo = new SysSoftwareVersionVo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }

    public static LinkedList<SysSoftwareVersionVo> fromEntity(List<SysSoftwareVersionEntity> entities) {
        LinkedList<SysSoftwareVersionVo> list = new LinkedList<>();
        if (entities == null || entities.isEmpty()) {
            return list;
        }
        for (SysSoftwareVersionEntity entity : entities) {
            list.add(fromEntity(entity));
        }
        return list;
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
