package top.luoqiz.common.base;

import java.io.Serializable;

/**
 * <p>Title: Sort</p>
 *
 * @author luoqiz
 * @version 1.0
 * @description 排序实体类
 * @date 2021/1/25 18:13
 * @since 1.0
 */
public class Sort implements Serializable {
    private String field;
    private Order order;

    //---------------------------------------------------------- Constructor start

    public Sort() {
    }

    /**
     * 构造
     *
     * @param field 排序字段
     */
    public Sort(String field) {
        this.field = field;
        this.order = Order.ASC;
    }

    /**
     * 构造
     *
     * @param field 排序字段
     * @param order 排序方式
     */
    public Sort(String field, Order order) {
        this(field);
        this.order = order;
    }

    public Sort(String field, String order) {
        this(field);
        this.order = Order.fromString(order);
    }

    //---------------------------------------------------------- Constructor end

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
