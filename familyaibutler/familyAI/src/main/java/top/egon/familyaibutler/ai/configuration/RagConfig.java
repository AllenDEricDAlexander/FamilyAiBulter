package top.egon.familyaibutler.ai.configuration;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @BelongsProject: familyaibutler
 * @BelongsPackage: top.egon.familyaibutler.family.configuration
 * @ClassName: RagConfig
 * @Author: atluofu
 * @CreateTime: 2025Year-08Month-06Day-21:00
 * @Description: RAG Config
 * @Version: 1.0
 */
@Configuration
public class RagConfig {

    @Bean
    VectorStore vectorStore(EmbeddingModel embeddingModel) {
        SimpleVectorStore simpleVectorStore = SimpleVectorStore.builder(embeddingModel)
                .build();
        List<Document> documents = List.of(
                new Document("产品说明:名称：Java开发语言\n" +
                             "产品描述：Java是一种面向对象开发语言。\n" +
                             "特性：\n" +
                             "1. 封装\n" +
                             "2. 继承\n" +
                             "3. 多态\n"));
        simpleVectorStore.add(documents);
        return simpleVectorStore;
    }

}
