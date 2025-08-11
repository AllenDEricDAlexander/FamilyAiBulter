package top.egon.familyaibutler.ai.configuration;

import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.ChatMemoryRepository;
import org.springframework.ai.chat.memory.InMemoryChatMemoryRepository;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * @BelongsProject: familyaibutler
 * @BelongsPackage: top.egon.familyaibutler.ai.configuration
 * @ClassName: ChatMemoryAutoConfiguration
 * @Author: atluofu
 * @CreateTime: 2025Year-08Month-11Day-10:34
 * @Description: ChatMemoryAutoConfiguration
 * @Version: 1.0
 */
@AutoConfiguration
@ConditionalOnClass({ChatMemory.class, ChatMemoryRepository.class})
public class ChatMemoryAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    ChatMemoryRepository chatMemoryRepository() {
        return new InMemoryChatMemoryRepository();
    }

    @Bean
    @ConditionalOnMissingBean
    ChatMemory chatMemory(ChatMemoryRepository chatMemoryRepository) {
        return MessageWindowChatMemory.builder()
                .chatMemoryRepository(chatMemoryRepository)
                .maxMessages(10)
                .build();
    }

}