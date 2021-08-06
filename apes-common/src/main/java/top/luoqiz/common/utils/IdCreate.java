package top.luoqiz.common.utils;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

/**
 * <p>Title: IdCreate</p>
 *
 * @author luoqiz
 * @version 1.0
 * @description TODO
 * @date 2020/12/3 15:15
 * @since 1.0
 */
public class IdCreate {

    static Snowflake snowflake;

    public IdCreate(long workerId, long datacenterId) {
        snowflake = IdUtil.createSnowflake(workerId, datacenterId);
    }

    public static long longId() {
        return snowflake.nextId();
    }
}
