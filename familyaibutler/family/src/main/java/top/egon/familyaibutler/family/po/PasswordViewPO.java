package top.egon.familyaibutler.family.po;


import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.With;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @BelongsProject: top.egon.familyaibutler.family
 * @BelongsPackage: top.egon.familyaibutler.family
 * @Author: atluofu
 * @CreateTime: 2025-08-03 09:40:11
 * @Description: (PasswordView)表实体类
 * @Version: 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@With
@Accessors(chain = true)
@Builder
@TableName("password_view")
public class PasswordViewPO extends Model<PasswordViewPO> implements Serializable {
    /**
     * key
     */

    private Long id;
    /**
     * name
     */

    private String name;
    /**
     * password
     */

    private String password;
    /**
     * description,len500
     */

    private String description;
    /**
     * account
     */

    private String accountNumber;
    /**
     * websit
     */

    private String websit;
    /**
     * is like
     */

    private Boolean likeStatus;
    /**
     * category
     */

    private String category;
    /**
     * latested view time
     */

    private LocalDateTime lastViewTime;
    /**
     * logic delete
     */

    private Boolean deleted;

    private Date createTime;

    private Date updateTime;


    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    public Serializable pkVal() {
        return this.id;
    }
}

