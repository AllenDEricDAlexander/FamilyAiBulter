package top.egon.familyaibutler.family.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @BelongsProject: familyaibutler
 * @BelongsPackage: top.egon.familyaibutler.family.configuration
 * @ClassName: SwaggerConfig
 * @Author: atluofu
 * @CreateTime: 2025Year-08Month-01Day-11:39
 * @Description: SwaggerConfig
 * @Version: 1.0
 */

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI swaggerOpenApi() {
        return new OpenAPI()
                .info(new Info().title("Swagger学习-比晚风温柔")
                        .description("Swagger简单入门")
                        .version("v1.0"))
                .externalDocs(new ExternalDocumentation()
                        .description("我的博客")
                        .url("https://blog.csdn.net/2202_76007821?spm=1000.2115.3001.5343"));
    }
}