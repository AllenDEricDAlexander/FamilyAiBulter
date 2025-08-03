package top.egon.familyaibutler.family.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.egon.familyaibutler.family.mapper.PasswordViewMapper;
import top.egon.familyaibutler.family.po.PasswordViewPO;
import top.egon.familyaibutler.family.service.PasswordViewService;

/**
 * @BelongsProject: familyaibutler
 * @BelongsPackage: top.egon.familyaibutler.family
 * @Author: atluofu
 * @CreateTime: 2025-08-03 09:40:09
 * @Description: (PasswordView)表服务实现类
 * @Version: 1.0
 */
@Service("passwordViewService")
public class PasswordViewServiceImpl extends ServiceImpl<PasswordViewMapper, PasswordViewPO> implements PasswordViewService {

}

