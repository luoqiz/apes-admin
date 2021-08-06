package top.luoqiz.common.utils;

import cn.hutool.core.util.RandomUtil;

import java.util.Arrays;
import java.util.List;

/**
 * <p>Title: Avatar</p>
 *
 * @author luoqiz
 * @version 1.0
 * @description 头像工具类
 * @date 2021/1/27 15:22
 * @since 1.0
 */
public class AvatarUtil {
    static List<String> avatars = Arrays.asList(
            "http://5b0988e595225.cdn.sohucs.com/images/20190203/7ac870f2e0114a0a96ef5708d0805420.jpeg",
            "http://5b0988e595225.cdn.sohucs.com/images/20190203/a302e4846e4f4722b777e77ecd8933a9.jpeg",
            "https://5b0988e595225.cdn.sohucs.com/images/20190926/8146f606becd4812b0ae13819bd194bd.jpeg",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQUMrWcnotQJBYFVGn1ypbV4c_7F-QFqD3zWg&usqp=CAU",
            "https://pic.yupoo.com/xjrumo/369dbe3e/825c6837.jpg",
            "https://img.yao51.com/jiankangtuku/lnhghdddv.jpeg",
            "https://gaoxiaoa.ymengni.com/uploads/allimg/150223/15130A606-2.jpg"
    );

    public static String getAvatar() {
        return avatars.get(RandomUtil.randomInt(0, avatars.size()));
    }
}
