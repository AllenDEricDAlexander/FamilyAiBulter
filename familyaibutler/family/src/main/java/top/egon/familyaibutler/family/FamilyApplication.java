package top.egon.familyaibutler.family;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

/**
 * @BelongsProject: familyaibutler
 * @BelongsPackage: top.familyaibutler.family
 * @ClassName: FamilyApplication
 * @Author: atluofu
 * @CreateTime: 2025Year-07Month-31Day-22:00
 * @Description: 启动类
 * @Version: 1.0
 */
@SpringBootApplication
public class FamilyApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(FamilyApplication.class);
        ConfigurableApplicationContext application = app.run(args);
        Environment env = application.getEnvironment();
    }
}
