package top.egon.familyaibutler.gateway.predicate;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * @BelongsProject: familyaibutler
 * @BelongsPackage: top.egon.familyaibutler.gateway.predicate
 * @ClassName: AutoFamilyRoutePredicateFactory
 * @Author: atluofu
 * @CreateTime: 2025Year-08Month-16Day-12:27
 * @Description: 自定义断言
 * @Version: 1.0
 */
//@Component
public class AutoFamilyRoutePredicateFactory extends AbstractRoutePredicateFactory<AutoFamilyRoutePredicateFactory.Config> {

    public AutoFamilyRoutePredicateFactory() {
        super(AutoFamilyRoutePredicateFactory.Config.class);
    }

    /**
     * @description: 全匹配
     * @author: atluofu
     * @date: 2025/8/16 12:39
     * @param:
     * @return:
     **/
    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return serverWebExchange -> {
            boolean flag = false;
            String first = serverWebExchange.getRequest().getHeaders().getFirst("Authorization");
            if (first != null && !first.isBlank() && first.equals(config.getUserType())) {
                flag = true;
            }
            return flag;
        };
    }

    /**
     * @description: 短匹配支持
     * @author: atluofu
     * @date: 2025/8/16 12:37
     * @param:
     * @return:
     **/
    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("userType");
    }

    @Validated
    @Setter
    @Getter
    public static class Config {
        @NotEmpty
        private String userType;
    }
}