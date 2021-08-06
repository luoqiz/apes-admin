package top.luoqiz.system.modules;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
 */
public class CodeGenerator {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static GlobalConfig globalConfig(String modulePath) {
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
//        String modulePath = projectPath + "/tancms-common/tancms-common-db";
        modulePath = projectPath + modulePath;
        gc.setOutputDir(modulePath + "/src/main/java");
        gc.setAuthor("luoqiz");
        gc.setOpen(false);
        //实体属性 Swagger2 注解
        gc.setSwagger2(true);
        gc.setFileOverride(true);
        gc.setActiveRecord(true);
        // XML 二级缓存
        gc.setEnableCache(false);
        // XML ResultMap
        gc.setBaseResultMap(true);
        // XML columList
        gc.setBaseColumnList(false);
        return gc;
    }

    public static DataSourceConfig dataSourceSet(String dbName) {
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://tx.luoqiz.top:3396/" + dbName + "?serverTimezone=Asia/Shanghai&characterEncoding=utf8&useSSL=false&allowPublicKeyRetrieval=true");
//        dsc.setUrl("jdbc:mysql://180.76.190.100:33066/tancms-sys?useUnicode=true&useSSL=false&characterEncoding=utf8");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("!qaz2wsX");
        return dsc;
    }

    public static InjectionConfig customConfig(String modulePath, String moduleName) {
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(
                new FileOutConfig(templatePath) {
                    @Override
                    public String outputFile(com.baomidou.mybatisplus.generator.config.po.TableInfo tableInfo) {
                        // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                        return System.getProperty("user.dir") + modulePath + "/src/main/resources/mapper/" + moduleName
                                + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                    }
                }
        );

        cfg.setFileOutConfigList(focList);
        return cfg;
    }

    public static StrategyConfig strategyConfig(String tablePrefix) {
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityClass("com.baomidou.ant.common.BaseEntity");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 公共父类
//        strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
        // 写于父类中的公共字段
//        strategy.setSuperEntityColumns("id");
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(tablePrefix + "_");
        return strategy;
    }

    public static void main(String[] args) {
        //模块子项目
        String modulePath = "/apes-system";

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 子项目路径
        GlobalConfig gc = globalConfig(modulePath);
        mpg.setGlobalConfig(gc);
        DataSourceConfig dsc = dataSourceSet("apes-admin");
        mpg.setDataSource(dsc);
        // 包配置
        PackageConfig pc = new PackageConfig();
//        pc.setModuleName(scanner("模块名"));
        // 模块名
        pc.setModuleName("");
        pc.setParent("top.luoqiz.system.modules.user");
        mpg.setPackageInfo(pc);

        InjectionConfig customConfig = customConfig(modulePath, pc.getModuleName());
        mpg.setCfg(customConfig);
        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // 关闭默认 xml 生成，调整生成 至 根目录
//        templateConfig.setController("/templates/controller.java.vm");
//        templateConfig.setEntity("templates/entity.java");
//        templateConfig.setMapper("/templates/mapper.java.vm");
//        templateConfig.setXml("/templates/mapper.xml.vm");
        templateConfig.setXml(null);
//        templateConfig.setService("/templates/service.java.vm");
//        templateConfig.setServiceImpl("/templates/serviceImpl.java.vm");
        mpg.setTemplate(templateConfig);

        StrategyConfig strategy = strategyConfig("api");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}