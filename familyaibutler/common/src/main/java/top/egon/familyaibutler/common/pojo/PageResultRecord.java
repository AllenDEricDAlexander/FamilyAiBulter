package top.egon.familyaibutler.common.pojo;

import top.egon.familyaibutler.common.enums.ResultCode;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * @BelongsProject: familyaibutler
 * @BelongsPackage: top.egon.familyaibutler.common.pojo
 * @ClassName: PageResultRecord
 * @Author: atluofu
 * @CreateTime: 2025Year-08Month-01Day-19:44
 * @Description: PageResult Record 类型
 * @Version: 1.0
 */
public record PageResultRecord<T>(Integer code, String message, Boolean success, List<T> data, Long total,
                                  Long pageNum, Long pageSize) implements Serializable {
    @Serial
    private static final long serialVersionUID = 8938942437517754680L;

    public static <T> PageResult<T> success(List<T> data, Long total, Long pageNum, Long pageSize) {
        return new PageResult<>(ResultCode.SUCCESS.getCode(), "success", true, data, pageNum, pageSize, total);
    }

    public static <T> PageResult<T> error(Integer code, String msg, List<T> data, Long total, Long pageNum, Long pageSize) {
        return new PageResult<>(code, msg, true, data, pageNum, pageSize, total);
    }

    public static <T> PageResult<T> pageResult(Integer code, String msg, Boolean success, List<T> data, Long total, Long pageNum, Long pageSize) {
        return new PageResult<>(code, msg, success, data, pageNum, pageSize, total);
    }
}
