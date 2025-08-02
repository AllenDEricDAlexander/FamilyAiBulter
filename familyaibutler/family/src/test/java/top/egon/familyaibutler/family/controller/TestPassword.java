package top.egon.familyaibutler.family.controller;

import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import top.egon.familyaibutler.common.pojo.Result;
import top.egon.familyaibutler.family.FamilyApplication;

import java.util.Random;
import java.util.regex.Pattern;

/**
 * @BelongsProject: familyaibutler
 * @BelongsPackage: top.egon.familyaibutler.family.controller
 * @ClassName: TestPassword
 * @Author: atluofu
 * @CreateTime: 2025Year-07Month-31Day-22:20
 * @Description: 密码管理器相关单元测试
 * @Version: 1.0
 */
@SpringBootTest(classes = FamilyApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TestPassword {

    private static final String PASSWORD_TRUE_REGEX = "^[a-zA-Z0-9!@#$%^&*()-_=+<>?]+$";
    private static final String PASSWORD_FALSE_REGEX = "^[a-zA-Z0-9]+$";

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("单个密码随机生成单元测试")
    void testPassword() throws Exception {
        int length = new Random().nextInt(16, 24);
        mockMvc.perform(MockMvcRequestBuilders.get("/password/generate/" + length)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(10000))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("success"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
                .andExpect(result -> {
                    String data = new Gson().fromJson(result.getResponse().getContentAsString(), Result.class).getData().toString();
                    Assertions.assertEquals(length, data.length());
                    Assertions.assertTrue(Pattern.matches(PASSWORD_TRUE_REGEX, data));
                })
                .andReturn();
        mockMvc.perform(MockMvcRequestBuilders.get("/password/generate/" + length + "/false")
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(10000))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("success"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
                .andExpect(result -> {
                    String data = new Gson().fromJson(result.getResponse().getContentAsString(), Result.class).getData().toString();
                    Assertions.assertEquals(length, data.length());
                    Assertions.assertTrue(Pattern.matches(PASSWORD_FALSE_REGEX, data));
                })
                .andReturn();

    }


}