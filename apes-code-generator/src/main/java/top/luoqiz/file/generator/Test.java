package top.luoqiz.file.generator;


import top.luoqiz.file.generator.ui.AutoGeneratorWindow;

import java.io.IOException;
import java.util.regex.Pattern;

/**
 * <p>Title: Test</p>
 *
 * @author luoqiz
 * @version 1.0
 * @description TODO
 * @date 2020/12/21 17:10
 * @since 1.0
 */
public class Test {
    private  static Pattern pattern = Pattern.compile("^api" );

    private void test(){
        System.out.println(this.getClass().getClassLoader().getName());;
    }

    public static void main(String[] args) throws IOException {
        // 获取工程路径
        String sp1 = System.getProperty("user.dir");
        System.out.println("sp1 = " + sp1);
//        System.out.println(AutoGeneratorWindow.class.getClassLoader().getResource("").getPath());
        AutoGeneratorWindow.launch(AutoGeneratorWindow.class, args);

//        String tablename = "api_test.ftl";
//        Matcher res = pattern.matcher(tablename);
//        System.out.println(res.groupCount());
//        for (int i = 0; i < res.groupCount(); i++) {
//            System.out.println(res.group(i));
//        }
//
//        String r = tablename.replaceAll("^api", "");
//        System.out.println(r);
//
//        String r1 = tablename.replaceAll("\\.ftl$", "");
//        System.out.println(r1);

//        String value = "2021-03-04T05:58:48.668Z";
//        LocalDateTime locate = LocalDateTime.parse(value, DateTimeFormatter.ofPattern(ISO8601DateFormat));
//        System.out.println(locate);

    }
}
