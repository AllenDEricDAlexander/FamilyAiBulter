package top.egon.familyaibutler.gateway.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

/**
 * @BelongsProject: familyaibutler
 * @BelongsPackage: top.egon.familyaibutler.gateway.filter
 * @ClassName: GatewayProperties
 * @Author: atluofu
 * @CreateTime: 2025Year-08Month-15Day-21:09
 * @Description: 配置文件
 * @Version: 1.0
 */
@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "family-gateway")
public class FamilyButlerGateWayProperties {

    private Jwt jwt = new Jwt();

    @Getter
    @Setter
    public static class Jwt {

        private Set<String> ignoreurlset = Set.of(
                "/api/user/login",
                "/api/user/register",
                "/api/user/refresh"
        );

        private String authorization = "Authorization";

        private long accessTokenExpireTime = 60 * 1000L;

        private long refreshTokenExpireTime = 30 * 24 * 3600 * 1000L;

        private String accessKey;

        private String refreshKey;
    }

}