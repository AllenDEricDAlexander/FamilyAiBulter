package top.egon.familyaibutler.common.pojo;

import top.egon.familyaibutler.common.enums.ResultCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * @BelongsProject: familyaibutler
 * @BelongsPackage: top.egon.familyaibutler.common.pojo
 * @ClassName: Results
 * @Author: atluofu
 * @CreateTime: 2025Year-08Month-01Day-18:38
 * @Description: Result Record 类型
 * @Version: 1.0
 */
public record ResultRecord<T>(Integer code, String message, Boolean success, T data) implements Serializable {

    @Serial
    private static final long serialVersionUID = -4633117999621557174L;

    public static <T> ResultRecord<T> success(T res) {
        return new ResultRecord<>(ResultCode.SUCCESS.getCode(), "success", true, res);
    }

    public static <T> ResultRecord<T> error(Integer errorCode, String msg, T res) {
        return new ResultRecord<>(errorCode, msg, true, res);
    }

    public static <T> ResultRecord<T> result(Integer code, String message, Boolean success, T res) {
        return new ResultRecord<>(code, message, success, res);
    }

}
