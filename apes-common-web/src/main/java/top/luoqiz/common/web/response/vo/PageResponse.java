package top.luoqiz.common.web.response.vo;

import java.io.Serializable;

/**
 * @author luoqiz
 */
//@NoArgsConstructor
//@AllArgsConstructor
//@Data
public class PageResponse<T> implements Serializable {


    private T data;
    private Integer total;
    private Integer pageIndex;
    private Integer pageSize;
    private Integer nextPageIndex;

    public static <T> PageResponse<T> build(T data, Number total, int pageIndex, int pageSize) {
        int nextPageIndex = 0;
        if (total.longValue() > ((pageIndex - 1) * pageSize)) {
            nextPageIndex = pageIndex + 1;
        }
        return new PageResponse(data, total.intValue(), pageIndex, pageSize, nextPageIndex);
    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getNextPageIndex() {
        return nextPageIndex;
    }

    public void setNextPageIndex(Integer nextPageIndex) {
        this.nextPageIndex = nextPageIndex;
    }

    public PageResponse(T data, Integer total, Integer pageIndex, Integer pageSize, Integer nextPageIndex) {
        this.data = data;
        this.total = total;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.nextPageIndex = nextPageIndex;
    }
}
