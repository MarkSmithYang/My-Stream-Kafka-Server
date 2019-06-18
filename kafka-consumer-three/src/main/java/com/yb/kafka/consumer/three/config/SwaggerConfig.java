package com.yb.kafka.consumer.three.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 普通版swagger配置,之前的那个和webFlux使用的swagger如果引入SpringCloudStream相关依赖
 * (如spring-cloud-starter-stream/binder-kafka)都会导致swagger渲染失败,因为那边把swagger
 * 注解的那个依赖排除掉了,所以现在直接在这里来处理了,直接使用普通的swagger2来实现接口文档
 * 而不是使用WebFlux整合的那个swagger2,目前还没找到解决上面问题的办法,所以就用这个来实现
 *
 * @author biaoyang
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("springboot利用swagger构建api文档")
                .description("简单优雅的restfun风格，http://www.baidu.com")
                .termsOfServiceUrl("http://www.baidu.com")
                .version("1.0")
                .build();
    }
}
