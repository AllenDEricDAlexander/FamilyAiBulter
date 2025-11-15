package top.egon.familyaibutler.ai.qwen.configuration;

import com.alibaba.cloud.ai.dashscope.api.DashScopeApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @BelongsProject: familyaibutler
 * @BelongsPackage: top.egon.familyaibutler.configuration
 * @ClassName: SaaLLMConfig
 * @Author: atluofu
 * @CreateTime: 2025Year-10Month-29Day-12:57
 * @Description: TODO
 * @Version: 1.0
 */
@Configuration
public class SaaLLMConfig {

    @Bean("dashScopeApi")
    public DashScopeApi dashScopeApi() {
        return DashScopeApi.builder()
                .apiKey(System.getenv("AI_QWEN_KEY"))
                .build();
    }

}