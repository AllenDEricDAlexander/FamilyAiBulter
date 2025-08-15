package top.egon.familyaibutler.gateway.resolver;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @BelongsProject: familyaibutler
 * @BelongsPackage: top.egon.familyaibutler.gateway.resolver
 * @ClassName: IpKeyResolver
 * @Author: atluofu
 * @CreateTime: 2025Year-08Month-15Day-21:15
 * @Description: 解析IP
 * @Version: 1.0
 */
public class IpKeyResolver implements KeyResolver {

    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        return Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
    }
}