package top.egon.familyaibutler.ai.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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
@RequestMapping("/mix")
@Tag(name = "mix-AI相关接口")
@Slf4j
@RequiredArgsConstructor
public class AIChatController2 {

    private final Map<String, ChatClient> chatClientMap;

    @GetMapping("/chat")
    String generation(@RequestParam String message,
                      @RequestParam String model) {
        ChatClient chatClient = chatClientMap.get(model);
        return chatClient.prompt().user(message).call().content();
    }

}