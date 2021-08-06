package top.luoqiz.common.base;

import java.io.Serializable;

/**
 * <p>Title: BasePageCriteria</p>
 *
 * @author luoqiz
 * @version 1.0
 * @description 查询基础类
 * @date 2021/1/25 18:27
 * @since 1.0
 */
public class BasePageCriteria implements Serializable {
    private Integer page = 1;
    private Integer size = 10;

    public Integer getPage() {
        if (page < 1) {
            page = 1;
        }
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        if (size < 1) {
            size = 10;
        }
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
