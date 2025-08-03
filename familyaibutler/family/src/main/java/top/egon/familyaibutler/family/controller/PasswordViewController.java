package top.egon.familyaibutler.family.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.egon.familyaibutler.common.pojo.PageResult;
import top.egon.familyaibutler.common.pojo.Result;
import top.egon.familyaibutler.family.domain.dto.PasswordViewDTO;
import top.egon.familyaibutler.family.po.PasswordViewPO;
import top.egon.familyaibutler.family.service.PasswordViewService;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;

/**
 * @BelongsProject: familyaibutler
 * @BelongsPackage: top.egon.familyaibutler.family.domain.pojo
 * @ClassName: PasswordView
 * @Author: atluofu
 * @CreateTime: 2025Year-08Month-02Day-21:08
 * @Description: password view pojo
 * @Version: 1.0
 */
@RestController
@RequestMapping("/password")
@Validated
@Tag(name = "密码管理相关接口")
@Slf4j
@RequiredArgsConstructor
public class PasswordViewController {
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_=+<>?";

    private static final int PASSWORD_LENGTH = 12;

    private final PasswordViewService passwordViewService;

    @Operation(summary = "获取账号密码列表", description = "获取账号密码列表",
            responses = {@ApiResponse(description = "返回一个字符串", responseCode = "10000",
                    content = @Content(schema = @Schema(implementation = Result.class, description = "账号密码列表", name = "账号密码列表", title = "账号密码列表", example = "List<PasswordView>")))})
    @GetMapping(value = {"/password/list/{pageNum}/{pageSize}", "/password/list"})
    public PageResult<List<PasswordViewPO>> selectAll(@PathVariable(value = "pageNum", required = false) @Range(min = 1) Integer pageNum,
                                                      @PathVariable(value = "pageSize", required = false) @Range(min = 1) Integer pageSize
            , @RequestBody PasswordViewPO passwordView) {
        int realPageNum = 1;
        int realPageSize = 10;
        if (ObjectUtils.isNotEmpty(pageNum)) {
            realPageNum = pageNum;
        }
        if (ObjectUtils.isNotEmpty(pageSize)) {
            realPageSize = pageSize;
        }
        Page<PasswordViewPO> page = this.passwordViewService.page(new Page<>(realPageNum, realPageSize), new QueryWrapper<>(passwordView));
        return PageResult.success(Collections.singletonList(page.getRecords()), page.getTotal(), page.getCurrent(), page.getSize());
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result<PasswordViewPO> selectOne(@PathVariable Long id) {
        return Result.success(this.passwordViewService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param passwordView 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Result<Boolean> insert(@RequestBody PasswordViewPO passwordView) {
        return Result.success(this.passwordViewService.save(passwordView));
    }

    /**
     * 修改数据
     *
     * @param passwordView 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result<Boolean> update(@RequestBody PasswordViewPO passwordView) {
        return Result.success(this.passwordViewService.updateById(passwordView));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public Result<Boolean> delete(@RequestParam("idList") List<Long> idList) {
        return Result.success(this.passwordViewService.removeByIds(idList));
    }

    @Operation(summary = "添加一个账号密码", description = "添加一个账号密码", parameters = {
            @Parameter(name = "passwordView", description = "passwordView", in = ParameterIn.PATH, required = true, example = "PasswordView")
    }, responses = {@ApiResponse(description = "返回是否添加成功", responseCode = "10000",
            content = @Content(schema = @Schema(implementation = Result.class, description = "添加结果", name = "添加结果", title = "添加结果", example = "添加成功"))),})
    @PostMapping("/password/add")
    public Result<String> add(@RequestBody PasswordViewDTO passwordViewDTO) {
        PasswordViewPO passwordView = PasswordViewPO.builder()
                .name(passwordViewDTO.getName())
                .password(passwordViewDTO.getPassword())
                .description(passwordViewDTO.getDescription())
                .accountNumber(passwordViewDTO.getAccountNumber())
                .websit(passwordViewDTO.getWebsit())
                .likeStatus(passwordViewDTO.isLikeStatus())
                .category(passwordViewDTO.getCategory())
                .build();
        boolean save = passwordViewService.save(passwordView);
        return save ? Result.success("添加成功") : Result.fail(10001, "添加失败", null);
    }


    @Operation(summary = "生成一个随机密码", description = "生成一个随机密码", parameters = {
            @Parameter(name = "passwordLength", description = "passwordLength", in = ParameterIn.PATH, required = true, example = "16"),
            @Parameter(name = "needSpecialCharacters", description = "needSpecialCharacters", in = ParameterIn.PATH, example = "true"),
            @Parameter(name = "specialCharacters", description = "specialCharacters", in = ParameterIn.PATH, example = "!@#$%^&*()-_=+<>?")
    }, responses = {@ApiResponse(description = "返回一个字符串", responseCode = "10000",
            content = @Content(schema = @Schema(implementation = Result.class, description = "随机密码", name = "随机密码", title = "随机密码", example = "W7%@pQJt16ZeN&2u"))),})
    @GetMapping(value = {"/generate/{passwordLength}", "/generate/{passwordLength}/{needSpecialCharacters}", "/generate/{passwordLength}/{needSpecialCharacters}/{specialCharacters}"})
    public Result<String> generatePassword(@PathVariable(value = "passwordLength") @Range(min = 12, max = 24, message = "密码生成长度在12-24之间") Integer passwordLength,
                                           @PathVariable(value = "needSpecialCharacters", required = false) Boolean needSpecialCharacters,
                                           @PathVariable(value = "specialCharacters", required = false) String specialCharacters) {
        int realLength = PASSWORD_LENGTH;
        if (ObjectUtils.isNotEmpty(passwordLength)) {
            realLength = passwordLength;
        }
        boolean isRealNeed = true;
        if (ObjectUtils.isNotEmpty(needSpecialCharacters)) {
            isRealNeed = needSpecialCharacters;
        }
        String realSpecialCharacters = SPECIAL_CHARACTERS;
        if (ObjectUtils.isNotEmpty(specialCharacters)) {
            realSpecialCharacters = specialCharacters;
        }
        return Result.success(generatePassword(realLength, isRealNeed, realSpecialCharacters));
    }

    /**
     * @description: 密码生成
     * @author: atluofu
     * @date: 2025/7/31 22:09
     * @param:
     * @return:
     **/
    public static String generatePassword(int length, boolean needSpecialCharacters, String realSpecialCharacters) {
        StringBuilder password = new StringBuilder(length);
        SecureRandom random = new SecureRandom();
        // 确保密码包含至少一个大写字母、小写字母、数字和特殊字符
        password.append(UPPERCASE.charAt(random.nextInt(UPPERCASE.length())));
        password.append(LOWERCASE.charAt(random.nextInt(LOWERCASE.length())));
        password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        String allCharacters = null;
        if (needSpecialCharacters) {
            password.append(realSpecialCharacters.charAt(random.nextInt(realSpecialCharacters.length())));
            allCharacters = UPPERCASE + LOWERCASE + DIGITS + realSpecialCharacters;
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

