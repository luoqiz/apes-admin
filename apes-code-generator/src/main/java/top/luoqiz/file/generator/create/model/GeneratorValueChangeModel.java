package top.luoqiz.file.generator.create.model;

import java.io.File;

/**
 * @author luoqiz
 */
public class GeneratorValueChangeModel {
    /**
     * 模板地址
     */
    private File template;
    /**
     * 模块地址
     */
    private File module;

    public File getTemplate() {
        return template;
    }

    public void setTemplate(File template) {
        this.template = template;
    }

    public File getModule() {
        return module;
    }

    public void setModule(File module) {
        this.module = module;
    }


}
