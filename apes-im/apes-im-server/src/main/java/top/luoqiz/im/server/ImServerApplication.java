package top.luoqiz.im.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author luoqiz
 * @date 2020/12/10
 */
@EnableAsync
@SpringBootApplication(scanBasePackages = {"top.luoqiz"})
public class ImServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImServerApplication.class, args);
    }

}
