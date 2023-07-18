package rbac.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import rbac.RBACApplication;
import rbac.common.enums.ExceptionEnum;
import rbac.common.exception.CustomException;
import rbac.entity.Emp;
import rbac.entity.Role;
import rbac.service.IAuthService;
import rbac.vo.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author MiracloW
 */
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthServiceImplTest {

    @Autowired
    private IAuthService authService;

    @Test
    public void testSaveRoleMenu_ReturnsSuccess(){
        RoleMenuVo roleMenuVo = new RoleMenuVo();
        roleMenuVo.setRoleId(1l);
        roleMenuVo.setMenuIdList(new ArrayList<>());
        roleMenuVo.getMenuIdList().add(1l);
        roleMenuVo.getMenuIdList().add(2l);
        roleMenuVo.getMenuIdList().add(3l);
        roleMenuVo.getMenuIdList().add(4l);

        assertTrue(authService.saveRoleMenu(roleMenuVo));
    }

    @Test
    public void testSaveRoleMenu_RoleNotFound_ThrowsException(){
        RoleMenuVo roleMenuVo = new RoleMenuVo();
        roleMenuVo.setRoleId(100l);
        roleMenuVo.setMenuIdList(new ArrayList<>());
        roleMenuVo.getMenuIdList().add(1l);
        roleMenuVo.getMenuIdList().add(2l);
        roleMenuVo.getMenuIdList().add(3l);
        roleMenuVo.getMenuIdList().add(4l);

        CustomException exception = assertThrows(CustomException.class, () -> {
            authService.saveRoleMenu(roleMenuVo);
        });
        assertEquals(ExceptionEnum.ROLE_NOT_EXIST.getCode(), exception.getCode());
    }

    @Test
    public void testSaveRoleMenu_MenuNotFound_ThrowsException(){
        RoleMenuVo roleMenuVo = new RoleMenuVo();
        roleMenuVo.setRoleId(1L);
        roleMenuVo.setMenuIdList(new ArrayList<>());
        roleMenuVo.getMenuIdList().add(1L);
        roleMenuVo.getMenuIdList().add(2L);
        roleMenuVo.getMenuIdList().add(3L);
        roleMenuVo.getMenuIdList().add(41111L);

        CustomException exception = assertThrows(CustomException.class, () -> {
            authService.saveRoleMenu(roleMenuVo);
        });
        assertEquals(ExceptionEnum.MENU_NOT_FOUND.getCode(), exception.getCode());
    }

    @Test
    public void testSaveRoleEmp_ReturnsSuccess(){
        RoleEmpVo roleEmpVo = new RoleEmpVo();
        roleEmpVo.setRoleId(1L);
        roleEmpVo.setEmpIdList(new ArrayList<>());
        roleEmpVo.getEmpIdList().add(120L);
        roleEmpVo.getEmpIdList().add(121L);
        roleEmpVo.getEmpIdList().add(122L);
        roleEmpVo.getEmpIdList().add(123L);

        assertTrue(authService.saveRoleEmp(roleEmpVo));
    }

    @Test
    public void testSaveRoleEmp_RoleNotExist_ThrowsException(){
        RoleEmpVo roleEmpVo = new RoleEmpVo();
        roleEmpVo.setRoleId(112121L);
        roleEmpVo.setEmpIdList(new ArrayList<>());
        roleEmpVo.getEmpIdList().add(120L);
        roleEmpVo.getEmpIdList().add(121L);
        roleEmpVo.getEmpIdList().add(122L);
        roleEmpVo.getEmpIdList().add(123L);

        CustomException exception = assertThrows(CustomException.class, () -> {
            authService.saveRoleEmp(roleEmpVo);
        });
        assertEquals(ExceptionEnum.ROLE_NOT_EXIST.getCode(), exception.getCode());
    }

    @Test
    public void testSaveRoleEmp_EmpNotExist_ThrowsException(){
        RoleEmpVo roleEmpVo = new RoleEmpVo();
        roleEmpVo.setRoleId(1L);
        roleEmpVo.setEmpIdList(new ArrayList<>());
        roleEmpVo.getEmpIdList().add(120L);
        roleEmpVo.getEmpIdList().add(121L);
        roleEmpVo.getEmpIdList().add(122L);
        roleEmpVo.getEmpIdList().add(123121L);

        CustomException exception = assertThrows(CustomException.class, () -> {
            authService.saveRoleEmp(roleEmpVo);
        });
        assertEquals(ExceptionEnum.USER_NOT_EXIST.getCode(), exception.getCode());
    }

    @Test
    public void testSaveEmpRole_ReturnsSuccess(){
        EmpRoleVo empRoleVo = new EmpRoleVo();
        empRoleVo.setEmpId(108L);
        empRoleVo.setRoleIdList(new ArrayList<>());
        empRoleVo.getRoleIdList().add(48L);
        empRoleVo.getRoleIdList().add(49L);
        empRoleVo.getRoleIdList().add(50L);
        empRoleVo.getRoleIdList().add(51L);

        assertTrue(authService.saveEmpRole(empRoleVo));
    }

    @Test
    public void testSaveEmpRole_EmpNotExist_ThrowsException(){
        EmpRoleVo empRoleVo = new EmpRoleVo();
        empRoleVo.setEmpId(10800L);
        empRoleVo.setRoleIdList(new ArrayList<>());
        empRoleVo.getRoleIdList().add(48L);
        empRoleVo.getRoleIdList().add(49L);
        empRoleVo.getRoleIdList().add(50L);
        empRoleVo.getRoleIdList().add(51L);

        CustomException exception = assertThrows(CustomException.class, () -> {
            authService.saveEmpRole(empRoleVo);
        });
        assertEquals(ExceptionEnum.USER_NOT_EXIST.getCode(), exception.getCode());
    }

    @Test
    public void testSaveEmpRole_RoleNotExist_ThrowsException(){
        EmpRoleVo empRoleVo = new EmpRoleVo();
        empRoleVo.setEmpId(108L);
        empRoleVo.setRoleIdList(new ArrayList<>());
        empRoleVo.getRoleIdList().add(48L);
        empRoleVo.getRoleIdList().add(49L);
        empRoleVo.getRoleIdList().add(50L);
        empRoleVo.getRoleIdList().add(51111L);

        CustomException exception = assertThrows(CustomException.class, () -> {
            authService.saveEmpRole(empRoleVo);
        });
        assertEquals(ExceptionEnum.ROLE_NOT_EXIST.getCode(), exception.getCode());
    }

    @Test
    public void testDeleteRoleEmp_ReturnsSuccess(){
        RoleRemoveEmpVo roleRemoveEmpVo = new RoleRemoveEmpVo();
        roleRemoveEmpVo.setRoleId(1L);
        roleRemoveEmpVo.setEmpId(108L);

        assertTrue(authService.deleteRoleEmp(roleRemoveEmpVo));
    }

    @Test
    public void testDeleteRoleEmp_RoleNotExist_ThrowsException(){
        RoleRemoveEmpVo roleRemoveEmpVo = new RoleRemoveEmpVo();
        roleRemoveEmpVo.setRoleId(1121121L);
        roleRemoveEmpVo.setEmpId(108L);

        CustomException exception = assertThrows(CustomException.class, () -> {
            authService.deleteRoleEmp(roleRemoveEmpVo);
        });
        assertEquals(ExceptionEnum.ROLE_NOT_EXIST.getCode(), exception.getCode());
    }

    @Test
    public void testDeleteRoleEmp_EmpNotExist_ThrowsException(){
        RoleRemoveEmpVo roleRemoveEmpVo = new RoleRemoveEmpVo();
        roleRemoveEmpVo.setRoleId(1L);
        roleRemoveEmpVo.setEmpId(10121221L);

        CustomException exception = assertThrows(CustomException.class, () -> {
            authService.deleteRoleEmp(roleRemoveEmpVo);
        });
        assertEquals(ExceptionEnum.USER_NOT_EXIST.getCode(), exception.getCode());
    }

    @Test
    public void testGetMenuListByRoleId_ReturnsSuccess(){
        Long roleId = 1L;
        List<Long> result = authService.getMenuListByRoleId(roleId);
        assertNotNull(result);
    }

    @Test
    public void testGetMenuListByRoleId_RoleNotExist_ThrowsException(){
        Long roleId = 112121L;

        CustomException exception = assertThrows(CustomException.class, () -> {
            authService.getMenuListByRoleId(roleId);
        });
        assertEquals(ExceptionEnum.ROLE_NOT_EXIST.getCode(), exception.getCode());
    }

    @Test
    public void testgetEmpListByRoleId_ReturnsSuccess(){
        Long roleId = 1L;
        PageParams pageParams = new PageParams();
        pageParams.setPageNum(1);
        pageParams.setPageSize(5);

        IPage<Emp> result = authService.getEmpListByRoleId(roleId, pageParams);
        assertNotNull(result);
    }

    @Test
    public void testgetEmpListByRoleId_RoleNotExist_ThrowsException(){
        Long roleId = 1123123L;
        PageParams pageParams = new PageParams();
        pageParams.setPageNum(1);
        pageParams.setPageSize(5);

        CustomException exception = assertThrows(CustomException.class, () -> {
            authService.getEmpListByRoleId(roleId, pageParams);
        });
        assertEquals(ExceptionEnum.ROLE_NOT_EXIST.getCode(), exception.getCode());
    }

    @Test
    public void testgetNotAddedEmpListByRoleId_ReturnsSuccess(){
        Long roleId = 1L;
        PageParams pageParams = new PageParams();
        pageParams.setPageNum(1);
        pageParams.setPageSize(5);

        IPage<Emp> result = authService.getNotAddedEmpByRoleId(roleId, pageParams);
        assertNotNull(result);
    }

    @Test
    public void testgetNotAddedEmpListByRoleId_RoleNotExist_ThrowsException(){
        Long roleId = 1123123L;
        PageParams pageParams = new PageParams();
        pageParams.setPageNum(1);
        pageParams.setPageSize(5);

        CustomException exception = assertThrows(CustomException.class, () -> {
            authService.getNotAddedEmpByRoleId(roleId, pageParams);
        });
        assertEquals(ExceptionEnum.ROLE_NOT_EXIST.getCode(), exception.getCode());
    }

    @Test
    public void testgetRoleListByEmpId_ReturnsSuccess(){
        Long empId = 108L;
        PageParams pageParams = new PageParams();
        pageParams.setPageNum(1);
        pageParams.setPageSize(5);

        IPage<Role> result = authService.getRoleListByEmpId(empId, pageParams);
        assertNotNull(result);
    }

    @Test
    public void testgetRoleListByEmpId_EmpNotExist_ThrowsException(){
        Long empId = 10118L;
        PageParams pageParams = new PageParams();
        pageParams.setPageNum(1);
        pageParams.setPageSize(5);

        CustomException exception = assertThrows(CustomException.class, () -> {
            authService.getRoleListByEmpId(empId, pageParams);
        });
        assertEquals(ExceptionEnum.USER_NOT_EXIST.getCode(), exception.getCode());
    }

    @Test
    public void testgetNotAddedRoleByEmpId_ReturnsSuccess(){
        Long empId = 108L;
        PageParams pageParams = new PageParams();
        pageParams.setPageNum(1);
        pageParams.setPageSize(5);

        IPage<Role> result = authService.getNotAddedRoleByEmpId(empId, pageParams);
        assertNotNull(result);
    }

    @Test
    public void testgetNotAddedRoleByEmpId_EmpNotExist_ThrowsException(){
        Long empId = 108111L;
        PageParams pageParams = new PageParams();
        pageParams.setPageNum(1);
        pageParams.setPageSize(5);

        CustomException exception = assertThrows(CustomException.class, () -> {
            authService.getNotAddedRoleByEmpId(empId, pageParams);
        });
        assertEquals(ExceptionEnum.USER_NOT_EXIST.getCode(), exception.getCode());
    }

    @Test
    public void testgetPermissionsByEmpId_ReturnsSuccess(){
        Long empId = 108L;

        List<String> result = authService.getPermissionsByEmpId(empId);
        assertNotNull(result);
    }

    @Test
    public void testgetPermissionsByEmpId_EmpNotExist_ThrowsException(){
        Long empId = 108121L;
        CustomException exception = assertThrows(CustomException.class, () -> {
            authService.getPermissionsByEmpId(empId);
        });
        assertEquals(ExceptionEnum.USER_NOT_EXIST.getCode(), exception.getCode());
    }
}
