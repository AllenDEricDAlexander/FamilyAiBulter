package top.egon.familyaibutler.family.domain.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @BelongsProject: familyaibutler
 * @BelongsPackage: top.egon.familyaibutler.family.domain.pojo
 * @ClassName: PasswordView
 * @Author: atluofu
 * @CreateTime: 2025Year-08Month-02Day-21:08
 * @Description: password view pojo
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@With
@Accessors(chain = true)
@Builder
@TableName("password_view")
public class PasswordView implements Serializable {
    @Serial
    private static final long serialVersionUID = 9146111814279753262L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String name;
    private String password;
    private String description;
    private String accountNumber;
    private String websit;
    private Boolean likeStatus;
    private String category;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime lastViewTime;

}