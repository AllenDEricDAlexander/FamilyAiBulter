package top.egon.familyaibutler.family.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.egon.familyaibutler.family.domain.pojo.PasswordView;
import top.egon.familyaibutler.family.repository.mapper.PasswordViewMapper;
import top.egon.familyaibutler.family.service.PasswordViewService;

/**
 * @BelongsProject: familyaibutler
 * @BelongsPackage: top.egon.familyaibutler.family.service.impl
 * @ClassName: PasswordViewServiceImpl
 * @Author: atluofu
 * @CreateTime: 2025Year-08Month-02Day-21:46
 * @Description: PasswordViewServiceImpl
 * @Version: 1.0
 */
@Service
public class PasswordViewServiceImpl extends ServiceImpl<PasswordViewMapper, PasswordView> implements PasswordViewService {
}