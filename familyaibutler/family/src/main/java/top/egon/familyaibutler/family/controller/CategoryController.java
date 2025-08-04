package top.egon.familyaibutler.family.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.egon.familyaibutler.common.pojo.PageResult;
import top.egon.familyaibutler.common.pojo.Result;
import top.egon.familyaibutler.family.po.CategoryPo;
import top.egon.familyaibutler.family.service.CategoryService;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * @BelongsProject: familyaibutler
 * @BelongsPackage: top.egon.familyaibutler.family.controller
 * @ClassName: CategoryController
 * @Author: atluofu
 * @CreateTime: 2025Year-08Month-04Day-11:55
 * @Description: CategoryController
 * @Version: 1.0
 */
@RestController
@RequestMapping("/category")
@Validated
@Tag(name = "类型管理相关接口")
@Slf4j
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;


    @GetMapping("/list")
    @Operation(summary = "获取所有类型", description = "获取所有类型")
    public PageResult<List<CategoryPo>> getUser() {
        Page<CategoryPo> all = categoryService.findAll(PageRequest.of(0, 10));
        List<CategoryPo> records = all.getContent();
        return PageResult.success(Collections.singletonList(records), all.getTotalElements(), (long) all.getNumber(), (long) all.getSize());
    }

    @GetMapping("/category/{id}")
    @Operation(summary = "获取指定类型", description = "获取指定类型")
    public Result<CategoryPo> getUser(@PathVariable(value = "id") Long id) {
        return Result.success(categoryService.findById(id).orElse(null));
    }

    @PostMapping(value = "/category")
    @Operation(summary = "添加类型", description = "添加类型")
    public Result<CategoryPo> saveUser(@RequestBody CategoryPo category) {
        return Result.success(categoryService.save(category));
    }

    @PutMapping(value = "/category")
    @Operation(summary = "更新类型", description = "更新类型")
    public Result<CategoryPo> updateUser(@RequestBody CategoryPo category) {
        return Result.success(categoryService.update(category));
    }

    @DeleteMapping(value = "/user/{id}")
    @Operation(summary = "删除类型", description = "删除类型")
    public Result<Boolean> deleteUser(@PathVariable(value = "id") Long id) {
        return Result.success(categoryService.delete(id));
    }

}