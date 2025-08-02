package top.egon.familyaibutler.common.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import top.egon.familyaibutler.common.enums.ResultCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * @BelongsProject: familyaibutler
 * @BelongsPackage: top.egon.familyaibutler.common.pojo
 * @ClassName: Result
 * @Author: atluofu
 * @CreateTime: 2025Year-08Month-01Day-18:38
 * @Description: 接口统一返回对象
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@SuperBuilder
@Schema(title = "全局接口统一返回对象", name = "Result")
public class Result<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = -4633117999621557173L;

    @Schema(title = "状态码", name = "code", defaultValue = "10000", type = "int")
    private Integer code;
    @Schema(title = "描述", name = "message", defaultValue = "10000", type = "String")
    private String message;
    @Schema(title = "响应状态", name = "success", defaultValue = "true", type = "bool")
    private Boolean success;
    @Schema(title = "返回结果", name = "data", defaultValue = "test", type = "T")
    private T data;

    public static <T> Result<T> success(T res) {
        return new Result<>(ResultCode.SUCCESS.getCode(), "success", true, res);
    }

    public static <T> Result<T> fail(Integer errorCode, String msg, T res) {
        return new Result<>(errorCode, msg, true, res);
    }

    public static <T> Result<T> result(Integer code, String message, Boolean success, T res) {
        return new Result<>(code, message, success, res);
    }

}