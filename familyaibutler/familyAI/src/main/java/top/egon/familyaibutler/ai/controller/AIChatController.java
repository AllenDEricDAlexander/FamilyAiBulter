package top.egon.familyaibutler.ai.controller;

import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatOptions;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: familyaibutler
 * @BelongsPackage: top.egon.familyaibutler.family.controller
 * @ClassName: AiChatController
 * @Author: atluofu
 * @CreateTime: 2025Year-08Month-06Day-14:05
 * @Description: AiChatController
 * @Version: 1.0
 */
@RestController
@RequestMapping("/ai")
@Tag(name = "AI相关接口")
@Slf4j
@RequiredArgsConstructor
public class AIChatController {

    private static final String DEFAULT_PROMPT = "你是一个博学的智能聊天助手，请根据用户提问回答！";

    private final ChatClient.Builder chatClientBuilder;

    private ChatClient dashScopeChatClient;

    @PostConstruct
    private void init() {
        this.dashScopeChatClient = chatClientBuilder
                .defaultSystem(DEFAULT_PROMPT)
                // 实现 Chat Memory 的 Advisor
                .defaultAdvisors(
                        MessageChatMemoryAdvisor.builder(MessageWindowChatMemory.builder().build()).build()
                )
                // 实现 Logger 的 Advisor
                .defaultAdvisors(
                        new SimpleLoggerAdvisor()
                )
                // 设置 ChatClient 中 ChatModel 的 Options 参数
                .defaultOptions(
                        DashScopeChatOptions.builder()
                                .withTopP(0.7)
                                .build()
                )
                .build();
    }


    @GetMapping("/simple/chat")
    public String simpleChat(String query) {
        return dashScopeChatClient.prompt(query).call().content();
    }

}