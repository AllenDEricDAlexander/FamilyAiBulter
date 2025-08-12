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
import java.util.Date;

 /**
 * @BelongsProject: familyaibutler
 * @BelongsPackage: top.egon.familyaibutler.uaa
 * @Author: atluofu
 * @CreateTime: 2025-08-13 00:48:18
 * @Description: user table(User)表实体类
 * @Version: 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@With
@Accessors(chain = true)
@Builder
@TableName("user")
public class UserPO extends Model<UserPO> implements Serializable {
    /**
      * id
      */  
    private Integer id;
    
    private String userName;
    
    private String account;
    
    private String password;
    
    private Boolean enable;
    
    private Boolean locked;
    
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

