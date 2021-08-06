package top.luoqiz.system.modules.system.queryCriteria;

import top.luoqiz.common.base.BasePageCriteria;

/**
 * <p>
 * 查询条件构建类
 * </p>
 *
 * @author luoqiz
 * @since 2021-03-17
 */
public class SysDictDetailQueryCriteria extends BasePageCriteria {

    private Long dictId;

    private String dictKey;

    public String getDictKey() {
        return dictKey;
    }

    public void setDictKey(String dictKey) {
        this.dictKey = dictKey;
    }

    public Long getDictId() {
        return dictId;
    }

    public void setDictId(Long dictId) {
        this.dictId = dictId;
    }
}