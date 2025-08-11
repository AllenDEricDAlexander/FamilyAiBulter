package top.egon.familyaibutler.ai.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.ChatMemoryRepository;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
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
@RequiredArgsConstructor
public class ChatMemoryAutoConfiguration {

    private final JdbcChatMemoryRepository chatMemoryRepository;

    @Bean
    @ConditionalOnMissingBean
    ChatMemory chatMemory() {
        return MessageWindowChatMemory.builder()
                .chatMemoryRepository(chatMemoryRepository)
                .maxMessages(10)
                .build();
    }

}