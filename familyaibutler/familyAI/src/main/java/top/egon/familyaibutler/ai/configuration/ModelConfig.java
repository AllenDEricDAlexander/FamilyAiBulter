package top.egon.familyaibutler.ai.configuration;

import com.alibaba.cloud.ai.dashscope.api.DashScopeApi;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatOptions;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.deepseek.DeepSeekChatModel;
import org.springframework.ai.deepseek.DeepSeekChatOptions;
import org.springframework.ai.deepseek.api.DeepSeekApi;
import org.springframework.ai.model.deepseek.autoconfigure.DeepSeekChatProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @BelongsProject: familyaibutler
 * @BelongsPackage: top.egon.familyaibutler.ai.configuration
 * @ClassName: ModelConfig
 * @Author: atluofu
 * @CreateTime: 2025Year-08Month-09Day-11:44
 * @Description: ModelConfig
 * @Version: 1.0
 */
@Configuration
public class ModelConfig {

    @Bean("deepSeek")
    public ChatClient deepseekR1(DeepSeekChatProperties chatProperties) {

        DeepSeekApi deepSeekApi = DeepSeekApi.builder()
                .apiKey(System.getenv("AI_DEEP_SEEK_KEY"))
                .build();


        DeepSeekChatModel deepSeekChatModel = DeepSeekChatModel.builder()
                .deepSeekApi(deepSeekApi)
                .defaultOptions(DeepSeekChatOptions.builder().model(DeepSeekApi.ChatModel.DEEPSEEK_REASONER).build())
                .build();

        return ChatClient.builder(deepSeekChatModel).build();
    }

    @Bean("qwen")
    public ChatClient qwen(DeepSeekChatProperties chatProperties) {

        DashScopeApi qwenApi = DashScopeApi.builder()
                .apiKey(System.getenv("AI_QWEN_KEY"))
                .build();


        DashScopeChatModel deepSeekChatModel = DashScopeChatModel.builder()
                .dashScopeApi(qwenApi)
                .defaultOptions(DashScopeChatOptions.builder().withModel(DashScopeApi.ChatModel.QWEN_PLUS.getModel()).build())
                .build();

        return ChatClient.builder(deepSeekChatModel).build();
    }


}