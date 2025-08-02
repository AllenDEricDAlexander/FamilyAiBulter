package top.egon.familyaibutler.common.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import top.egon.familyaibutler.common.enums.ResultCode;

import java.io.Serial;
import java.util.List;

/**
 * @BelongsProject: familyaibutler
 * @BelongsPackage: top.egon.familyaibutler.common.pojo
 * @ClassName: PageResult
 * @Author: atluofu
 * @CreateTime: 2025Year-08Month-01Day-19:14
 * @Description: 接口统一返回分页对象
 * @Version: 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(builderMethodName = "PageResultBuilder")
@Accessors(chain = true)
@Schema(title = "全局接口统一返回分页对象", name = "PageResult")
public class PageResult<T> extends Result<List<T>> {
    @Serial
    private static final long serialVersionUID = 8938942437517754689L;

    @Schema(title = "总量", name = "total", defaultValue = "100", type = "long")
    private Long total;
    @Schema(title = "页号", name = "pageNum", defaultValue = "0", type = "int")
    private Long pageNum;
    @Schema(title = "页大小", name = "pageSize", defaultValue = "10", type = "int")
    private Long pageSize;

    public static <T> PageResult<T> success(List<T> data, Long total, Long pageNum, Long pageSize) {
        return new PageResult<>(ResultCode.SUCCESS.getCode(), "success", true, data, pageNum, pageSize, total);
    }

    public static <T> PageResult<T> fail(Integer code, String msg, List<T> data, Long total, Long pageNum, Long pageSize) {
        return new PageResult<>(code, msg, true, data, pageNum, pageSize, total);
    }

    public static <T> PageResult<T> pageResult(Integer code, String msg, Boolean success, List<T> data, Long total, Long pageNum, Long pageSize) {
        return new PageResult<>(code, msg, success, data, pageNum, pageSize, total);
    }

    public PageResult(Integer code, String message, Boolean success, List<T> data, Long pageNum, Long pageSize, Long total) {
        super(code, message, success, data);
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
    }
}