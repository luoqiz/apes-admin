package top.luoqiz.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author luoqiz
 * @date 2020/12/10
 */
@EnableAspectJAutoProxy
@EnableAsync
@MapperScan(basePackages = {"top.luoqiz.**.mapper"})
@SpringBootApplication(scanBasePackages = {"top.luoqiz"})
public class SystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args);
    }

}
