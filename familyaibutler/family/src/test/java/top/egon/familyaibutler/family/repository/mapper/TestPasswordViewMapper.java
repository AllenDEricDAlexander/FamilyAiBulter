package top.egon.familyaibutler.family.repository.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @BelongsProject: familyaibutler
 * @BelongsPackage: top.egon.familyaibutler.family.repository.mapper
 * @ClassName: TestPasswordViewMapper
 * @Author: atluofu
 * @CreateTime: 2025Year-08Month-02Day-21:24
 * @Description: TestPasswordViewMapper
 * @Version: 1.0
 */
@SpringBootTest
public class TestPasswordViewMapper {
    @Autowired
    private PasswordViewMapper passwordViewMapper;

    @Test
    void testSelect() {
        Long l = passwordViewMapper.selectCount(null);
        System.out.println(l);
    }

}