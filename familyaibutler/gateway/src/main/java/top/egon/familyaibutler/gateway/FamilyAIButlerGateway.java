package top.egon.familyaibutler.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @BelongsProject: familyaibutler
 * @BelongsPackage: top.egon.familyaibutler.gateway
 * @ClassName: FamilyAIButlerGateway
 * @Author: atluofu
 * @CreateTime: 2025Year-08Month-01Day-9:09
 * @Description: 路由组件
 * @Version: 1.0
 */
@Slf4j
@ConfigurationPropertiesScan("top.egon.familyaibutler.gateway")
@SpringBootApplication
public class FamilyAIButlerGateway {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(FamilyAIButlerGateway.class);
        ConfigurableApplicationContext application = app.run(args);
        Environment env = application.getEnvironment();
        try {
            String port = env.getProperty("server.port");
            String hostAddress = InetAddress.getLocalHost().getHostAddress();
            log.info("""
                            ----------------------------------------------------------\n
                            Application '{}' is running! Access URLs:\n
                            Local: \t\thttp://localhost:{}\n
                            External: \thttp://{}:{}\n
                            Doc: \thttp://{}:{}/doc.html\n
                            ----------------------------------------------------------
                            """,
                    env.getProperty("spring.application.name"),
                    port,
                    hostAddress, port,
                    hostAddress, port);
        } catch (UnknownHostException exception) {
            log.error("启动输出格式化内容报错", exception);
        }
    }
}