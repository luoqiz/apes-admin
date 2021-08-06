package top.luoqiz.common.web.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author luoqiz
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi AllApi() {
        String[] paths = {"/**"};
        return GroupedOpenApi.builder().group("项目接口")
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("spring boot 单体项目脚手架")
                        .version("1.0")
                        .description("快速开发")
                        .termsOfService("http://www.google.com")
                        .license(new License().name("Apache 2.0")
                                .url("http://www.google.com")));
    }

}