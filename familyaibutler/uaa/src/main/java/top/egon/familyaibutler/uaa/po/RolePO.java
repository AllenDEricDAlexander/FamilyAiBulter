package top.egon.familyaibutler.uaa.po;



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

/**
 * @BelongsProject: familyaibutler
 * @BelongsPackage: top.egon.familyaibutler.uaa
 * @Author: atluofu
 * @CreateTime: 2025-08-13 00:48:17
 * @Description: (Role)表实体类
 * @Version: 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@With
@Accessors(chain = true)
@Builder
@TableName("role")
public class RolePO extends Model<RolePO> implements Serializable {
    private Integer id;
    
    private String name;
    
    private String description;
    


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

