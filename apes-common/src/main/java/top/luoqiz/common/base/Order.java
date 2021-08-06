package top.luoqiz.common.base;

import cn.hutool.core.util.StrUtil;

/**
 * <p>Title: 排序方式（升序或者降序）</p>
 *
 * @author luoqiz
 * @version 1.0
 * @description 排序方式（升序或者降序）
 * @date 2021/1/25 18:17
 * @since 1.0
 */
public enum Order {

    /**
     * 升序
     */
    ASC,
    /**
     * 降序
     */
    DESC;

    /**
     * 根据字符串值返回对应{@link Order}值
     *
     * @param value 排序方式字符串，只能是 ASC或DESC
     * @return {@link Order}
     * @throws IllegalArgumentException in case the given value cannot be parsed into an enum value.
     */
    public static Order fromString(String value) throws IllegalArgumentException {

        try {
            return Order.valueOf(value.toUpperCase());
        } catch (Exception e) {
            throw new IllegalArgumentException(StrUtil.format(
                    "Invalid value [{}] for orders given! Has to be either 'desc' or 'asc' (case insensitive).", value), e);
        }
    }
}
