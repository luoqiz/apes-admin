package top.luoqiz.file.generator.create.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import top.luoqiz.file.generator.create.model.GenerateFileModel;
import top.luoqiz.file.generator.create.model.GeneratorConfigModel;

import java.io.*;

/**
 * <p>Title: GeneratorUtil</p>
 *
 * @author luoqiz
 * @version 1.0
 * @description 文件生成类
 * @date 2020/12/23 10:45
 * @since 1.0
 */
public class GeneratorUtil {

//    public static void generateInfo(GeneratorConfigModel config) {
//        config.getFileList().forEach(fileInfo -> {
//            String path = getFlePath(config, fileInfo.getPackagePath());
//            System.out.println(fileInfo.getFileName() + " 文件所在路径：" + path);
//        });
//    }

    public static void generate(GeneratorConfigModel config) {
        // 创建Freemarker配置实例
        Configuration cfg = new Configuration(Configuration.getVersion());
        cfg.setDefaultEncoding("utf-8");
        try {
            cfg.setDirectoryForTemplateLoading(
                    new File(config.getTemplatePath()));
            config.getFileList().forEach(file -> {
                file.setCurrent(true);
                String path = createFile(cfg, config, file);
                System.out.println(file.getFileName() + " 文件所在路径：" + path);
                file.setCurrent(false);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String createFile(Configuration configuration, GeneratorConfigModel config, GenerateFileModel model) {
        // 加载模板文件
        Template t = null;
        BufferedWriter rd = null;
        OutputStream out = null;
        try {
            t = configuration.getTemplate(model.getFileName());
            // 实体类路径
            String[] fileSplit = model.getFinalPath().split("\\.");
            File temfile;
            if (fileSplit.length > 1) {
                temfile = new File(model.getFinalPath());
                if (!temfile.getParentFile().exists()) {
                    boolean result = temfile.getParentFile().mkdirs();
                    if (!result) {
                        System.out.println("创建失败");
                    }
                }
            } else {
                File pojoFile = new File(model.getFinalPath());
                if (!pojoFile.getParentFile().exists()) {
                    pojoFile.mkdirs();
                }
//                String filepath = "";
//                if ("java".equalsIgnoreCase(model.getSuffix())) {
//                    filepath = pojoFile.getAbsolutePath() + "/" + config.getTableInfo().getClassName() + model.getSummary() + "." + model.getSuffix();
//                } else {
//                    filepath = pojoFile.getAbsolutePath() + "/" + config.getTableInfo().getClassName() + model.getSummary() + "." + model.getSuffix();
//                }

                temfile = pojoFile;
            }
            out = new FileOutputStream(temfile);
            rd = new BufferedWriter(new OutputStreamWriter(out, "utf-8"));
            t.process(config, rd);
            return temfile.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rd.close();
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

//    private static String getFlePath(GeneratorConfigModel config, String filePath) {
//        // 实体类路径
//        File pojoFile = new File(config.getRootPath() + filePath);
//        return pojoFile.getAbsolutePath();
//    }
}
