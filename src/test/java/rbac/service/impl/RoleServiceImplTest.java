package rbac.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import rbac.common.enums.ExceptionEnum;
import rbac.common.exception.CustomException;
import rbac.utils.RandomTokenUtils;
import rbac.entity.Role;
import rbac.service.IRoleService;
import rbac.vo.PageParams;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author MiracloW
 */
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleServiceImplTest {

    @Autowired
    private IRoleService roleService;

    @Test
    public void testgetRoleById_ReturnsSuccess(){
        Long roleId = 1L;
        Role result = roleService.getRoleById(roleId);
        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getRoleName());
        assertNotNull(result.getRoleDesc());
    }

    @Test
    public void testgetRoleById_RoleNotExist_ThrowsException(){
        Long roleId = 1000L;
        CustomException exception = assertThrows(CustomException.class, () -> {
            roleService.getRoleById(roleId);
        });
        assertEquals(ExceptionEnum.ROLE_NOT_EXIST.getCode(), exception.getCode());
    }

    @Test
    public void testsaveRole_ReturnsSuccess(){
        Role role = new Role();
        role.setRoleName("1111111121");
        role.setRoleDesc("1231231231312");
        role.setRecToken(RandomTokenUtils.createRandomRecToken());

        assertTrue(roleService.saveRole(role));
    }

    @Test
    public void testsaveRole_RoleExist_ThrowsException(){
        Role role = new Role();
        role.setRoleName("管理员");
        role.setRoleDesc("1231231231312");
        role.setRecToken(RandomTokenUtils.createRandomRecToken());

        CustomException exception = assertThrows(CustomException.class, () -> {
            roleService.saveRole(role);
        });
        assertEquals(ExceptionEnum.ROLE_EXIST.getCode(), exception.getCode());
    }

    @Test
    public void testupdateRole_ReturnsSuccess(){
        Role role = new Role();
        role.setId(1l);
        role.setRoleName("1111111121");
        role.setRoleDesc("1231231231312");
        role.setRecToken(0);

        assertTrue(roleService.updateRoleByRoleId(role));
    }

    @Test
    public void testupdateRole_RoleExist_ThrowsException(){
        Role role = new Role();
        role.setId(1l);
        role.setRoleName("普通用户");
        role.setRoleDesc("1231231231312");
        role.setRecToken(0);

        CustomException exception = assertThrows(CustomException.class, () -> {
            roleService.updateRoleByRoleId(role);
        });
        assertEquals(ExceptionEnum.ROLE_EXIST.getCode(), exception.getCode());
    }

    @Test
    public void testupdateRole_RoleRecTokenIllegal_ThrowsException(){
        Role role = new Role();
        role.setId(1l);
        role.setRoleName("管理员");
        role.setRoleDesc("1231231231312");
        role.setRecToken(11232);

        CustomException exception = assertThrows(CustomException.class, () -> {
            roleService.updateRoleByRoleId(role);
        });
        assertEquals(ExceptionEnum.ROLE_UPDATE_CONCURRENT_MODIFY_ERROR.getCode(), exception.getCode());
    }

    @Test
    public void testRemoveRole_ReturnsSuccess(){
        Long roleId = 1L;

        assertTrue(roleService.removeRoleById(roleId));
    }

    @Test
    public void testRemoveRole_RoleNotExist_ThrowsException(){
        Long roleId = 1123123L;

        CustomException exception = assertThrows(CustomException.class, () -> {
            roleService.removeRoleById(roleId);
        });
        assertEquals(ExceptionEnum.ROLE_NOT_EXIST.getCode(), exception.getCode());
    }

    @Test
    public void testGetRoleListWithPage(){
        PageParams pageParams = new PageParams();
        pageParams.setPageNum(1);
        pageParams.setPageSize(5);
        Role role = new Role();

        IPage<Role> result = roleService.getRoleListWithPage(pageParams, role);
        assertNotNull(result);
    }

    @Test
    public void testGetRoleListByEmpId(){
        PageParams pageParams = new PageParams();
        pageParams.setPageNum(1);
        pageParams.setPageSize(5);
        Long empId = 108L;

        IPage<Role> result = roleService.getRoleListByEmpId(empId, pageParams);
        assertNotNull(result);
    }

    @Test
    public void testGetRoleListByEmpId_EmpNotFound_ThrowsException(){
        PageParams pageParams = new PageParams();
        pageParams.setPageNum(1);
        pageParams.setPageSize(5);
        Long empId = 10811L;

        CustomException exception = assertThrows(CustomException.class, () -> {
            roleService.getRoleListByEmpId(empId, pageParams);
        });
        assertEquals(ExceptionEnum.USER_NOT_EXIST.getCode(), exception.getCode());
    }

    @Test
    public void testGetNotAddedRoleByEmpId(){
        PageParams pageParams = new PageParams();
        pageParams.setPageNum(1);
        pageParams.setPageSize(5);
        Long empId = 108L;

        IPage<Role> result = roleService.getNotAddedRoleByEmpId(empId, pageParams);
        assertNotNull(result);
    }

    @Test
    public void testGetNotAddedRoleByEmpId_EmpNotFound_ThrowsException(){
        PageParams pageParams = new PageParams();
        pageParams.setPageNum(1);
        pageParams.setPageSize(5);
        Long empId = 10811L;

        CustomException exception = assertThrows(CustomException.class, () -> {
            roleService.getNotAddedRoleByEmpId(empId, pageParams);
        });
        assertEquals(ExceptionEnum.USER_NOT_EXIST.getCode(), exception.getCode());
    }

}