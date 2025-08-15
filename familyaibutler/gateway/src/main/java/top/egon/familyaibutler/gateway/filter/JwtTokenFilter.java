package top.egon.familyaibutler.gateway.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import top.egon.familyaibutler.gateway.configuration.FamilyButlerGateWayProperties;
import top.egon.familyaibutler.gateway.exception.BusinessException;
import top.egon.familyaibutler.gateway.util.JwtUtil;

import java.nio.charset.StandardCharsets;

/**
 * @BelongsProject: familyaibutler
 * @BelongsPackage: top.egon.familyaibutler.gateway.filter
 * @ClassName: JwtTokenFilter
 * @Author: atluofu
 * @CreateTime: 2025Year-08Month-15Day-20:45
 * @Description: 认证过滤器
 * @Version: 1.0
 */
@Slf4j
@RequiredArgsConstructor
public class JwtTokenFilter implements GlobalFilter, Ordered {

    private final ObjectMapper objectMapper;
    private final JwtUtil jwtUtil;

    private final FamilyButlerGateWayProperties.Jwt jwt = new FamilyButlerGateWayProperties.Jwt();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String url = exchange.getRequest().getURI().getPath();

        for (String ignoreUrl : jwt.getIgnoreurlset()) {
            if (url.contains(ignoreUrl)) {
                return chain.filter(exchange);
            }
        }

        String token = exchange.getRequest().getHeaders().getFirst(jwt.getAuthorization());
        ServerHttpResponse resp = exchange.getResponse();

        if (StringUtils.isBlank(token)) {
            return authError(resp, "请先认证");
        }

        try {
            if (!jwtUtil.validateAccessToken(token)) {
                throw new BusinessException("token已过期");
            }
            return chain.filter(exchange);
        } catch (Exception e) {
            return authError(resp, e.getMessage());
        }
    }

    private Mono<Void> authError(ServerHttpResponse resp, String msg) {
        resp.setStatusCode(HttpStatus.UNAUTHORIZED);
        resp.getHeaders().add("Content-Type", "application/json;charset=UTF-8");

        String returnStr = "";
        try {
            // todo 输出 result
            returnStr = objectMapper.writeValueAsString("请求失败" + msg);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
        DataBuffer buffer = resp.bufferFactory().wrap(returnStr.getBytes(StandardCharsets.UTF_8));

        return resp.writeWith(Flux.just(buffer));
    }

    @Override
    public int getOrder() {
        return -100;
    }
}