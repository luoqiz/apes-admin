package top.luoqiz.system.modules.system.queryCriteria;

import io.swagger.v3.oas.annotations.media.Schema;
import top.luoqiz.common.base.BasePageCriteria;
import java.time.LocalDateTime;

/**
 * <p>
 * 软件版本 查询条件构建类
 * </p>
 *
 * @author luoqiz
 * @since 2021-05-21
 */
public class SysSoftwareVersionQueryCriteria extends BasePageCriteria {

    @Schema(description = "软件类型 1app 2网站")
    private String type;

    @Schema(description = "版本名称")
    private String versionName;

    @Schema(description = "创建日期")
    private LocalDateTime createTime;



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


    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }



}