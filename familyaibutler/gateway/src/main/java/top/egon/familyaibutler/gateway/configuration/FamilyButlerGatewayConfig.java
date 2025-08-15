package top.egon.familyaibutler.gateway.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @BelongsProject: familyaibutler
 * @BelongsPackage: top.egon.familyaibutler.gateway.configuration
 * @ClassName: FamilyButlerGatewayConfig
 * @Author: atluofu
 * @CreateTime: 2025Year-08Month-15Day-22:20
 * @Description: 配置类
 * @Version: 1.0
 */
@Configuration
public class FamilyButlerGatewayConfig {
    @Bean
    public FamilyButlerGateWayProperties familyButlerGatewayProperties() {
        return new FamilyButlerGateWayProperties();
    }
}