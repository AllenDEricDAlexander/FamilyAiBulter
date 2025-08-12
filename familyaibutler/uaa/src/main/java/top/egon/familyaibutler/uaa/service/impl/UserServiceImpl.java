package top.egon.familyaibutler.uaa.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.egon.familyaibutler.uaa.mapper.UserMapper;
import top.egon.familyaibutler.uaa.po.UserPO;
import top.egon.familyaibutler.uaa.service.UserService;
import org.springframework.stereotype.Service;

 /**
 * @BelongsProject: familyaibutler
 * @BelongsPackage: top.egon.familyaibutler.uaa
 * @Author: atluofu
 * @CreateTime: 2025-08-13 00:48:18
 * @Description: user table(User)表服务实现类
 * @Version: 1.0
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, UserPO> implements UserService {

}

