package top.luoqiz.file.generator.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import top.luoqiz.file.event.ApplicationEventManager;
import top.luoqiz.file.generator.event.FilePathChangeEventHandler;

import java.net.URL;

/**
 * <p>Title: AutoGeneratorWindow</p>
 *
 * @author luoqiz
 * @version 1.0
 * @description 生成文件启动类
 * @date 2020/12/21 17:13
 * @since 1.0
 */
public class AutoGeneratorWindow extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        ApplicationEventManager.addListener(new FilePathChangeEventHandler());

        URL resource = ClassLoader.getSystemResource("sample.fxml");
        Parent root = FXMLLoader.load(resource);
        primaryStage.setTitle("代码自动生成工具");
        primaryStage.setScene(new Scene(root, 1200, 850));
        primaryStage.show();
    }
}
