package top.egon.familyaibutler.ai.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.egon.familyaibutler.ai.advisor.ReReadingAdvisor;

import java.util.Map;

/**
 * @BelongsProject: familyaibutler
 * @BelongsPackage: top.egon.familyaibutler.ai.controller
 * @ClassName: PromptController
 * @Author: atluofu
 * @CreateTime: 2025Year-08Month-11Day-10:21
 * @Description: PromptController
 * @Version: 1.0
 */
@RestController
@RequestMapping("/prompt")
@Tag(name = "prompt-AI相关接口")
@Slf4j
@RequiredArgsConstructor
public class PromptController {

    private final Map<String, ChatClient> chatClientMap;

    @GetMapping("/chat1")
    String generation1(@RequestParam String message,
                       @RequestParam String model) {
        ChatClient chatClient = chatClientMap.get(model);
        return chatClient.prompt().user(u ->
                        u.text("告诉我5部{composer}的电影.").param("composer", message)
                )
                .advisors(new ReReadingAdvisor())
                .advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID, "userid"))
                .call().content();
    }

}