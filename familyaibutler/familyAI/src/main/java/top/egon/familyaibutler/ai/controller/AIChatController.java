package top.egon.familyaibutler.ai.controller;

import com.alibaba.cloud.ai.advisor.DocumentRetrievalAdvisor;
import com.alibaba.cloud.ai.dashscope.api.DashScopeImageApi;
import com.alibaba.cloud.ai.dashscope.audio.DashScopeSpeechSynthesisModel;
import com.alibaba.cloud.ai.dashscope.audio.DashScopeSpeechSynthesisOptions;
import com.alibaba.cloud.ai.dashscope.audio.synthesis.SpeechSynthesisPrompt;
import com.alibaba.cloud.ai.dashscope.audio.synthesis.SpeechSynthesisResponse;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatOptions;
import com.alibaba.cloud.ai.dashscope.image.DashScopeImageModel;
import com.alibaba.cloud.ai.dashscope.image.DashScopeImageOptions;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.rag.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.rag.retrieval.search.DocumentRetriever;
import org.springframework.ai.rag.retrieval.search.VectorStoreDocumentRetriever;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.ByteBuffer;

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
@RequestMapping("/qwen")
@Tag(name = "AI相关接口")
@Slf4j
@RequiredArgsConstructor
public class AIChatController {

    private static final String DEFAULT_PROMPT = "你是一个博学的智能聊天助手，请根据用户提问回答！";
    private static final String TEXT = "床前明月光， 疑是地上霜。 举头望明月， 低头思故乡。";
    private static final String FILE_PATH = "E:\\project\\java project\\familyaibutler\\familyaibutler\\familyAI\\src\\main\\resources\\tts";

    private final ChatClient.Builder chatClientBuilder;

    private final DashScopeImageModel imageModel;
    private final DashScopeSpeechSynthesisModel speechSynthesisModel;
    private final VectorStore vectorStore;

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


    @GetMapping("/chat/simple")
    public String simpleChat(String query) {
        return dashScopeChatClient.prompt(query).call().content();
    }

    @GetMapping("image/generate")
    public void getImage(@RequestParam(value = "msg", defaultValue = "生成一只小猫") String msg, HttpServletResponse res) throws MalformedURLException {
        ImageResponse response = imageModel.call(
                new ImagePrompt(
                        msg,
                        DashScopeImageOptions.builder()
                                .withModel(DashScopeImageApi.DEFAULT_IMAGE_MODEL)
                                //要生成的图像数。必须介于 1 和 10 之间。
                                .withN(1)
                                //生成的图像的高宽度。
                                .withHeight(1024).withWidth(1024).build())
        );
        //获取生成图像地址
        String imageUrl = response.getResult().getOutput().getUrl();
        URL url = URI.create(imageUrl).toURL();
        //使用输出流在浏览器输出
        try (InputStream in = url.openStream()) {
            res.setHeader("Content-Type", MediaType.IMAGE_PNG_VALUE);
            res.getOutputStream().write(in.readAllBytes());
            res.getOutputStream().flush();
        } catch (Exception e) {
            log.error("生成图片异常", e);
        }
    }


    @GetMapping("/tts")
    public void tts() throws IOException {
        // 使用构建器模式创建 DashScopeSpeechSynthesisOptions 实例并设置参数
        DashScopeSpeechSynthesisOptions options = DashScopeSpeechSynthesisOptions.builder()
                // 设置 语速 音调 音量
                .speed(1.0f).pitch(0.9).volume(60)
                .build();
        SpeechSynthesisResponse response = speechSynthesisModel.call(new SpeechSynthesisPrompt(TEXT, options));
        File file = new File(FILE_PATH + "/output.mp3");
        try (FileOutputStream fos = new FileOutputStream(file)) {
            ByteBuffer byteBuffer = response.getResult().getOutput().getAudio();
            fos.write(byteBuffer.array());
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    @GetMapping(value = "/chat", produces = "text/plain; charset=UTF-8")
    public String generation(String userInput) {
        return dashScopeChatClient.prompt()
                .user(userInput)
                .advisors(RetrievalAugmentationAdvisor.builder()
                        .documentRetriever((DocumentRetriever) new DocumentRetrievalAdvisor(new VectorStoreDocumentRetriever(vectorStore, 0.7, 5, null)))
                        .build())
                .call()
                .content();
    }


}