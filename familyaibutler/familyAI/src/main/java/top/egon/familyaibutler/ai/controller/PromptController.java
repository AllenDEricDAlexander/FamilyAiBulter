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
import top.egon.familyaibutler.ai.po.Address;

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

    @GetMapping("/chat2")
    String generation2(@RequestParam String message,
                       @RequestParam String model) {
        Boolean isComplain = chatClientMap.get(model)
                .prompt()
                .system("""
                        请判断用户信息是否表达了投诉意图?
                        只能用 true 或 false 回答，不要输出多余内容
                        """)
                .user("你们家的快递迟迟不到,我要退货！")
                .call()
                .entity(Boolean.class);

        // 分支逻辑
        if (Boolean.TRUE.equals(isComplain)) {
            return "用户是投诉，转接人工客服！";
        } else {
            return "用户不是投诉，自动流转客服机器人。";
        }
    }

    @GetMapping("/chat3")
    String generation3(@RequestParam String message,
                       @RequestParam String model) {
        return chatClientMap.get(model)
                .prompt()
                .system("""
                        请从下面这条文本中提取收货信息
                        """)
                .user("收货人：张三，电话13588888888，地址：浙江省杭州市西湖区文一西路100号8幢202室")
                .call()
                .entity(Address.class).toString();
    }

}