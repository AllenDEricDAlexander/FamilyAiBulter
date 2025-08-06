package top.egon.familyaibutler.family.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.zhipuai.ZhiPuAiChatModel;
import org.springframework.ai.zhipuai.ZhiPuAiChatOptions;
import org.springframework.ai.zhipuai.api.ZhiPuAiApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    private final ChatClient.Builder chatClientBuilder;

    private ChatClient chatClient;

    @PostConstruct
    private void init() {
        chatClient = chatClientBuilder.build();
    }

    @GetMapping("/prompt")
    public String prompt(@RequestParam("name") String name,
                         @RequestParam("like") String like) {
        String userText = """
                给我推荐三个热门语言，能挣大钱的。
                """;
        UserMessage userMessage = new UserMessage(userText);
        String systemText = """
                你是一个编程咨询助手，可以帮助人们查询计算机编程信息。
                你的名字是{name},
                你应该用你的名字和{like}的用户偏好回复用户的请求。
                """;
        SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate(systemText);
        Message systemMessage = systemPromptTemplate.createMessage(Map.of("name", name, "like", like));
        Prompt prompt = new Prompt(List.of(userMessage, systemMessage));
        List<Generation> results = chatModel.call(prompt).getResults();
        return results.stream().map(x -> x.getOutput().toString()).collect(Collectors.joining(""));
    }

    @GetMapping("/chatNoSysPrompt")
    public String chatNoPrompt(@RequestParam(value = "message", defaultValue = "你叫什么名字") String message) {
        return this.chatClient.prompt().user(message).call().content();
    }

    @GetMapping("/chat")
    public String chat(@RequestParam(value = "message", defaultValue = "你叫什么名字") String message) {
        return this.chatClient.prompt("你是一个计算机编程助手，提供编程相关的帮助。").user(message).call().content();
    }

    @GetMapping("/generate")
    public String generate(@RequestParam(value = "message", defaultValue = "讲一个笑话") String message) {
        AssistantMessage output = this.chatModel.call(new Prompt(message, ZhiPuAiChatOptions.builder().model(ZhiPuAiApi.ChatModel.GLM_4_Flash.getValue()).temperature(0.2).build())).getResult().getOutput();
        return output.getText();
    }

    @GetMapping(value = "/generateStream", produces = "text/html;charset=UTF-8")
    public Flux<ChatResponse> generateStream(@RequestParam(value = "message", defaultValue = "讲一个笑话") String message) {
        return this.chatModel.stream(new Prompt(new UserMessage(message)));
    }

}