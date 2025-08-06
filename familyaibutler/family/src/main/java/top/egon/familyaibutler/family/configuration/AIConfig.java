package top.egon.familyaibutler.family.configuration;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @BelongsProject: familyaibutler
 * @BelongsPackage: top.egon.familyaibutler.family.configuration
 * @ClassName: AIConfig
 * @Author: atluofu
 * @CreateTime: 2025Year-08Month-06Day-16:59
 * @Description: AIConfig
 * @Version: 1.0
 */
@Configuration
public class AIConfig {

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder) {
        return builder.defaultSystem("你是一个计算机编程助手，提供编程相关的帮助。你的名字叫Egon-Assistant。").build();
    }

}