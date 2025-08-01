package top.egon.familyaibutler.family;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
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
//@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = {"top.egon.familyaibutler.family","top.egon.familyaibutler.common"})
public class FamilyApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(FamilyApplication.class);
        ConfigurableApplicationContext application = app.run(args);
        Environment env = application.getEnvironment();
//        try {
//            log.info("""
//                            ----------------------------------------------------------\n\t
//                            Application '{}' is running! Access URLs:\n\t
//                            Local: \t\thttp://localhost:{}\n\t
//                            External: \thttp://{}:{}\n\t
//                            Doc: \thttp://{}:{}/doc.html\n
//                            ----------------------------------------------------------
//                            """,
//                    env.getProperty("spring.application.name"),
//                    env.getProperty("server.port"),
//                    InetAddress.getLocalHost().getHostAddress(),
//                    env.getProperty("server.port"),
//                    InetAddress.getLocalHost().getHostAddress(),
//                    env.getProperty("server.port"));
//        } catch (Exception exception) {
//            log.error("启动输出格式化内容报错", exception);
//        }
    }
}
