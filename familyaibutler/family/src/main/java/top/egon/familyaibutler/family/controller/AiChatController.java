package top.egon.familyaibutler.family.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.zhipuai.ZhiPuAiChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

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
public class AiChatController {

    private final ZhiPuAiChatModel chatModel;

    @GetMapping("/generate")
    public String generate(@RequestParam(value = "message", defaultValue = "hello") String message) {
        return this.chatModel.call(message);
    }

    @GetMapping("/generateStream")
    public Flux<ChatResponse> generateStream(@RequestParam(value = "message", defaultValue = "讲一个笑话") String message) {
        return this.chatModel.stream(new Prompt(new UserMessage(message)));
    }

}