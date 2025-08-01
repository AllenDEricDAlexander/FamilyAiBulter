package top.egon.familyaibutler.common.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import java.lang.reflect.Modifier;

/**
 * @BelongsProject: familyaibutler
 * @BelongsPackage: top.egon.familyaibutler.common.configuration
 * @ClassName: Gsonconfig
 * @Author: atluofu
 * @CreateTime: 2025Year-08Month-01Day-21:42
 * @Description: Gson 相关配置
 * 忽略protected修饰的字段
 * 日期格式化
 * @Version: 1.0
 */
@Configuration
public class GsonConfig {

    @Bean
    GsonHttpMessageConverter gsonHttpMessageConverter() {
        GsonHttpMessageConverter converter = new GsonHttpMessageConverter();
        GsonBuilder builder = new GsonBuilder();
        //设置日期格式
        builder.setDateFormat("yyyy-MM-dd HH:mm:ss");
        //忽略字段，Gson解析时，修饰符为protected的字段被过滤掉
        builder.excludeFieldsWithModifiers(Modifier.FINAL);
        Gson gson = builder.create();
        converter.setGson(gson);
        return converter;
    }

}