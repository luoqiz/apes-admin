package top.luoqiz.file.generator.event;

import top.luoqiz.file.event.ApplicationListener;
import top.luoqiz.file.generator.create.model.GeneratorConfigModel;

/**
 * @author luoqiz
 */
public class FilePathChangeEventHandler implements ApplicationListener<FilePathChangeEvent> {

    public static GeneratorConfigModel config = new GeneratorConfigModel();

    @Override
    public void onApplicationEvent(FilePathChangeEvent event) {
        System.out.println(event);
    }
}
