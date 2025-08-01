package top.egon.familyaibutler.family.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hibernate.validator.constraints.Range;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.egon.familyaibutler.common.pojo.Result;

import java.security.SecureRandom;

/**
 * @BelongsProject: familyaibutler
 * @BelongsPackage: top.familyaibutler.family.controller
 * @ClassName: PasswordController
 * @Author: atluofu
 * @CreateTime: 2025Year-07Month-31Day-22:03
 * @Description: 密码管理器访问层
 * @Version: 1.0
 */
@RestController
@RequestMapping("/password")
@Validated
@Tag(name = "密码管理相关接口")
public class PasswordController {

    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_=+<>?";

    private static final int PASSWORD_LENGTH = 12;

    @Operation(summary = "生成一个随机密码", description = "生成一个随机密码", parameters = {
            @Parameter(name = "passwordLength", description = "passwordLength", in = ParameterIn.PATH, required = true, example = "16"),
            @Parameter(name = "needSpecialCharacters", description = "needSpecialCharacters", in = ParameterIn.PATH, example = "true")
    }, responses = {@ApiResponse(description = "返回一个字符串", responseCode = "10000",
            content = @Content(schema = @Schema(implementation = Result.class, description = "随机密码", name = "随机密码", title = "随机密码", example = "W7%@pQJt16ZeN&2u"))),})
    @GetMapping(value = {"/generate/{passwordLength}/{needSpecialCharacters}", "/generate/{passwordLength}"})
    public Result<String> generatePassword(@PathVariable(value = "passwordLength") @Range(min = 12, max = 24, message = "密码生成长度在12-24之间") Integer passwordLength,
                                           @PathVariable(value = "needSpecialCharacters", required = false) Boolean needSpecialCharacters) {
        int realLength = PASSWORD_LENGTH;
        if (!ObjectUtils.isEmpty(passwordLength)) {
            realLength = passwordLength;
        }
        boolean isRealNeed = true;
        if (!ObjectUtils.isEmpty(needSpecialCharacters)) {
            isRealNeed = needSpecialCharacters;
        }
        return Result.success(generatePassword(realLength, isRealNeed));
    }

    /**
     * @description: 密码生成
     * @author: atluofu
     * @date: 2025/7/31 22:09
     * @param:
     * @return:
     **/
    public static String generatePassword(int length, boolean needSpecialCharacters) {
        StringBuilder password = new StringBuilder(length);
        SecureRandom random = new SecureRandom();
        // 确保密码包含至少一个大写字母、小写字母、数字和特殊字符
        password.append(UPPERCASE.charAt(random.nextInt(UPPERCASE.length())));
        password.append(LOWERCASE.charAt(random.nextInt(LOWERCASE.length())));
        password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        String allCharacters = null;
        if (needSpecialCharacters) {
            password.append(SPECIAL_CHARACTERS.charAt(random.nextInt(SPECIAL_CHARACTERS.length())));
            allCharacters = UPPERCASE + LOWERCASE + DIGITS + SPECIAL_CHARACTERS;
        } else {
            password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
            allCharacters = UPPERCASE + LOWERCASE + DIGITS;
        }
        // 随机选择剩余的字符
        for (int i = 4; i < length; i++) {
            password.append(allCharacters.charAt(random.nextInt(allCharacters.length())));
        }
        return shuffleString(password.toString());
    }

    /**
     * @description: 打乱密码顺序
     * @author: atluofu
     * @date: 2025/7/31 22:08
     * @parm: a
     * @retrn: a
     **/
    private static String shuffleString(String input) {
        StringBuilder shuffled = new StringBuilder(input);
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < shuffled.length(); i++) {
            int j = random.nextInt(shuffled.length());
            char temp = shuffled.charAt(i);
            shuffled.setCharAt(i, shuffled.charAt(j));
            shuffled.setCharAt(j, temp);
        }
        return shuffled.toString();
    }
}