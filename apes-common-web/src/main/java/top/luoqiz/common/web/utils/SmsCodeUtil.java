package top.luoqiz.common.web.utils;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>Title: CodeUtil</p>
 *
 * @author luoqiz
 * @version 1.0
 * @description 短信验证码 处理工具类
 * @date 2021/3/2 19:20
 * @since 1.0
 */
public class SmsCodeUtil {

    final static Pattern pattern = Pattern.compile("\\{([^}]*)\\}");

    /**
     * 生成指定长度的数字字符串
     *
     * @param length 需要的字符串长度
     * @return String
     */
    public static String randomStr(int length) {
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(9));
        }
        return sb.toString();
    }

    public static boolean randomStr1(List<String> ss, String findStr) {
        Iterator<String> iter = ss.iterator();
        while (iter.hasNext()) {
            String model = iter.next();
            System.out.println(model);
            if (model.equals(findStr)) {
                iter.remove();
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        List<String> list = new LinkedList();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        boolean res = randomStr1(list, "2");
        System.out.println(res);
    }

    /**
     * 正则匹配提取对应的字符串
     * 例如 first{phone}and{code}，将会提取 phone和code以及它们的索引
     *
     * @param param 需要提取的字符串
     * @return
     */
    public static LinkedList<Param> matcher(String param) {
        LinkedList<Param> paramList = new LinkedList<>();
        Matcher matcher = pattern.matcher(param);
        while (matcher.find()) {
            Param param1 = new Param();
            param1.name = matcher.group(1);
            param1.start = matcher.start();
            param1.end = matcher.end();
            paramList.add(param1);
        }
        return paramList;
    }

    public static class Param {
        public String name;
        public String value;
        public int start;
        public int end;
    }
}
