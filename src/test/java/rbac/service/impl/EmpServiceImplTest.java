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
import rbac.entity.Emp;
import rbac.service.IEmpService;
import rbac.vo.EmpLoginVo;
import rbac.vo.PageParams;
import rbac.vo.UserPasswordVo;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author MiracloW
 */
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmpServiceImplTest {

//    @Autowired直接注入的方法会真实操作数据库，如果在单元测试中不想改变数据数据库中的值，不能使用直接注入的方法
    @Autowired
    private IEmpService empService;

//    @Test
//    public void testLogin_ValidCredentials_ReturnsEmp(){
//        EmpLoginVo empLoginVo = new EmpLoginVo();
//        empLoginVo.setName("admin");
//        empLoginVo.setPassword("uV3+7jwEtEE+6/FwoiYIPUTcT99VDEZyM29kHtDTk7UwkQuH0JHZK2SzjUILqN4p2ZktEyFwrfcV6I4QECiWFbUWX2+5duJGlGsZ/x/+U538j1ZrTL7fXa9c3F00QGYhvZb8dLj9AzYSvNuCWGDZLhLVJLo/pBzUHm7VKWE6bMw=");
//
//        Emp target = new Emp();
//        target.setEmpName("admin");
//        Emp result = empService.login(empLoginVo);
//        // 进行断言来验证结果
//        assertNotNull(result);
//        assertEquals(result.getEmpName(), target.getEmpName());
//        assertNull(result.getPassword());
//    }

//    @Test
//    public void testLogin_InvalidPassword_ThrowsException(){
//        EmpLoginVo empLoginVo = new EmpLoginVo();
//        empLoginVo.setName("admin");
//        empLoginVo.setPassword("c0j/eKDoRzTm4DbKdWGgY7CQ6jqitrn54fQBdhtCME8PatPuQPeLXI/yDXa5TYPtzliItvun43slQbR4/ElPZElTnZDP8idIvlLdjehTrSLUCcdKa9jk34Im9YqFN4l0n/G6ozklwAqqBA+rv1n7QzIn2mzDhuy0Ik7fdaTYVAk=");
//
//        CustomException exception = assertThrows(CustomException.class, () -> {
//            empService.login(empLoginVo);
//        });
//        assertEquals(ExceptionEnum.USER_NAME_OR_PASSWORD_WRONG.getCode(), exception.getCode());
//
//    }

//    @Test
//    public void testLogin_InvalidUsername_ThrowsException(){
//        EmpLoginVo empLoginVo = new EmpLoginVo();
//        empLoginVo.setName("admin123");
//        empLoginVo.setPassword("c0j/eKDoRzTm4DbKdWGgY7CQ6jqitrn54fQBdhtCME8PatPuQPeLXI/yDXa5TYPtzliItvun43slQbR4/ElPZElTnZDP8idIvlLdjehTrSLUCcdKa9jk34Im9YqFN4l0n/G6ozklwAqqBA+rv1n7QzIn2mzDhuy0Ik7fdaTYVAk=");
//
//        CustomException exception = assertThrows(CustomException.class, () -> {
//            empService.login(empLoginVo);
//        });
//        assertEquals(ExceptionEnum.USER_NOT_EXIST.getCode(), exception.getCode());
//
//    }

    @Test
    public void testUpdatepassword_ReturnsSuccess(){
        UserPasswordVo userPasswordVo = new UserPasswordVo();
        userPasswordVo.setOripassword("Ti75q70x7K2p0+OP6+LvDmOjX6ONmaJFDk/535rV1mCp61co053eUK/B5FD91S4O8e0ib5Q/Bol9Hfb2eo+q8tXXgZ+EgsPFkkfpsbtGmNBveHwzAShyArqlm7bSRUsidbN5Ar99+lIXDAi4k2fcU04yN+rOcJgw9vnO46Ozqqo=");
        userPasswordVo.setNewpassword("ZSqwpPWM8D5MYKiBvcaxUf8mFyMBfLSftYEoGlS52QHPTsgNr02n1l5+/rzFKGmgqUFltxTTQkuXK5X3UsQynTd0EJJjV2JX+32s+RWHvOc56cz18zVr3Sdb4KXoyV6i0wpcFpYC0EL3AfCISnG9FrWItMzDFdRNQwof8vRXpgY=");
        Long empId = 108l;

        assertTrue(empService.updatepassword(userPasswordVo, empId));
    }

    @Test
    public void testUpdatepassword_PasswordSameError_ThrowsException(){
        UserPasswordVo userPasswordVo = new UserPasswordVo();
        userPasswordVo.setOripassword("Ti75q70x7K2p0+OP6+LvDmOjX6ONmaJFDk/535rV1mCp61co053eUK/B5FD91S4O8e0ib5Q/Bol9Hfb2eo+q8tXXgZ+EgsPFkkfpsbtGmNBveHwzAShyArqlm7bSRUsidbN5Ar99+lIXDAi4k2fcU04yN+rOcJgw9vnO46Ozqqo=");
        userPasswordVo.setNewpassword("x5B6sLlOa6w+IAJkaFb1jZ3q/MGuhQrMbi8BZsGgKNvscEWUW/3RbwVakpyjH+T8OJ6R47jrbMxGE87Bok6gDveWsyy18p6qA7NLZTUYcXKWKRYkwUi6qDdPC9c6mBLnz1EsmGDCKkhZde+6jL13LfSj1P8nJW5CWDRmG9s5EN0=");
        Long empId = 108l;

        CustomException exception = assertThrows(CustomException.class, () -> {
            empService.updatepassword(userPasswordVo, empId);
        });
        assertEquals(ExceptionEnum.USER_PASSWORD_SAME.getCode(), exception.getCode());
    }

    @Test
    public void testUpdatepassword_PasswordWrongError_ThrowsException(){
        UserPasswordVo userPasswordVo = new UserPasswordVo();
        userPasswordVo.setOripassword("ZSqwpPWM8D5MYKiBvcaxUf8mFyMBfLSftYEoGlS52QHPTsgNr02n1l5+/rzFKGmgqUFltxTTQkuXK5X3UsQynTd0EJJjV2JX+32s+RWHvOc56cz18zVr3Sdb4KXoyV6i0wpcFpYC0EL3AfCISnG9FrWItMzDFdRNQwof8vRXpgY=");
        userPasswordVo.setNewpassword("x5B6sLlOa6w+IAJkaFb1jZ3q/MGuhQrMbi8BZsGgKNvscEWUW/3RbwVakpyjH+T8OJ6R47jrbMxGE87Bok6gDveWsyy18p6qA7NLZTUYcXKWKRYkwUi6qDdPC9c6mBLnz1EsmGDCKkhZde+6jL13LfSj1P8nJW5CWDRmG9s5EN0=");
        Long empId = 108l;

        CustomException exception = assertThrows(CustomException.class, () -> {
            empService.updatepassword(userPasswordVo, empId);
        });
        assertEquals(ExceptionEnum.USER_PASSWORD_WRONG.getCode(), exception.getCode());
    }

    @Test
    public void testUpdatepassword_UserNotExist_ThrowsException(){
        UserPasswordVo userPasswordVo = new UserPasswordVo();
        userPasswordVo.setOripassword("ZSqwpPWM8D5MYKiBvcaxUf8mFyMBfLSftYEoGlS52QHPTsgNr02n1l5+/rzFKGmgqUFltxTTQkuXK5X3UsQynTd0EJJjV2JX+32s+RWHvOc56cz18zVr3Sdb4KXoyV6i0wpcFpYC0EL3AfCISnG9FrWItMzDFdRNQwof8vRXpgY=");
        userPasswordVo.setNewpassword("x5B6sLlOa6w+IAJkaFb1jZ3q/MGuhQrMbi8BZsGgKNvscEWUW/3RbwVakpyjH+T8OJ6R47jrbMxGE87Bok6gDveWsyy18p6qA7NLZTUYcXKWKRYkwUi6qDdPC9c6mBLnz1EsmGDCKkhZde+6jL13LfSj1P8nJW5CWDRmG9s5EN0=");
        Long empId = 10811l;

        CustomException exception = assertThrows(CustomException.class, () -> {
            empService.updatepassword(userPasswordVo, empId);
        });
        assertEquals(ExceptionEnum.USER_NOT_EXIST.getCode(), exception.getCode());
    }

    @Test
    public void testSaveEmp_ReturnSuccess(){
        Emp emp = new Emp();
        emp.setEmpName("admin123");
        emp.setPassword("uV3+7jwEtEE+6/FwoiYIPUTcT99VDEZyM29kHtDTk7UwkQuH0JHZK2SzjUILqN4p2ZktEyFwrfcV6I4QECiWFbUWX2+5duJGlGsZ/x/+U538j1ZrTL7fXa9c3F00QGYhvZb8dLj9AzYSvNuCWGDZLhLVJLo/pBzUHm7VKWE6bMw=");
        emp.setDeptNo(1);
        emp.setJob("员工");
        emp.setPhone("09876543210");
        emp.setRecToken(RandomTokenUtils.createRandomRecToken());

        assertTrue(empService.saveEmp(emp));
    }

    @Test
    public void testSaveEmp_UserExist_ThrowsException(){
        Emp emp = new Emp();
        emp.setEmpName("admin");
        emp.setPassword("uV3+7jwEtEE+6/FwoiYIPUTcT99VDEZyM29kHtDTk7UwkQuH0JHZK2SzjUILqN4p2ZktEyFwrfcV6I4QECiWFbUWX2+5duJGlGsZ/x/+U538j1ZrTL7fXa9c3F00QGYhvZb8dLj9AzYSvNuCWGDZLhLVJLo/pBzUHm7VKWE6bMw=");
        emp.setDeptNo(1);
        emp.setJob("员工");
        emp.setPhone("09876543210");
        emp.setRecToken(RandomTokenUtils.createRandomRecToken());

        CustomException exception = assertThrows(CustomException.class, () -> {
            empService.saveEmp(emp);
        });
        assertEquals(ExceptionEnum.USER_EXIST.getCode(), exception.getCode());
    }

    @Test
    public void testUpdateEmp_ReturnSuccess(){
        Emp emp = new Emp();
        emp.setEmpNo(108l);
        emp.setEmpName("admin");
        emp.setPassword("uV3+7jwEtEE+6/FwoiYIPUTcT99VDEZyM29kHtDTk7UwkQuH0JHZK2SzjUILqN4p2ZktEyFwrfcV6I4QECiWFbUWX2+5duJGlGsZ/x/+U538j1ZrTL7fXa9c3F00QGYhvZb8dLj9AzYSvNuCWGDZLhLVJLo/pBzUHm7VKWE6bMw=");
        emp.setDeptNo(1);
        emp.setJob("员工");
        emp.setPhone("09876543210");
        emp.setRecToken(38598);

        assertTrue(empService.updateEmpById(emp));
    }

    @Test
    public void testUpdateEmp_EmpnameExist_ThrowsException(){
        Emp emp = new Emp();
        emp.setEmpNo(108l);
        emp.setEmpName("user");
        emp.setPassword("uV3+7jwEtEE+6/FwoiYIPUTcT99VDEZyM29kHtDTk7UwkQuH0JHZK2SzjUILqN4p2ZktEyFwrfcV6I4QECiWFbUWX2+5duJGlGsZ/x/+U538j1ZrTL7fXa9c3F00QGYhvZb8dLj9AzYSvNuCWGDZLhLVJLo/pBzUHm7VKWE6bMw=");
        emp.setDeptNo(1);
        emp.setJob("员工");
        emp.setPhone("09876543210");
        emp.setRecToken(38598);

        CustomException exception = assertThrows(CustomException.class, () -> {
            empService.updateEmpById(emp);
        });
        assertEquals(ExceptionEnum.USER_EXIST.getCode(), exception.getCode());
    }

    @Test
    public void testUpdateEmp_InvalidRectoken_ThrowsException(){
        Emp emp = new Emp();
        emp.setEmpNo(108l);
        emp.setEmpName("admin");
        emp.setPassword("uV3+7jwEtEE+6/FwoiYIPUTcT99VDEZyM29kHtDTk7UwkQuH0JHZK2SzjUILqN4p2ZktEyFwrfcV6I4QECiWFbUWX2+5duJGlGsZ/x/+U538j1ZrTL7fXa9c3F00QGYhvZb8dLj9AzYSvNuCWGDZLhLVJLo/pBzUHm7VKWE6bMw=");
        emp.setDeptNo(1);
        emp.setJob("员工");
        emp.setPhone("09876543210");
        emp.setRecToken(1);

        CustomException exception = assertThrows(CustomException.class, () -> {
            empService.updateEmpById(emp);
        });
        assertEquals(ExceptionEnum.USER_UPDATE_CONCURRENT_MODIFY_ERROR.getCode(), exception.getCode());
    }


    @Test
    public void testRemoveEmpById_ReturnsSuccess(){
        Long empId = 108L;

        assertTrue(empService.removeEmpbyid(empId));
    }

    @Test
    public void testRemoveEmpById_EmpNotFound_ThrowsException(){
        Long empId = 10800L;

        CustomException exception = assertThrows(CustomException.class, () -> {
            empService.removeEmpbyid(empId);
        });
        assertEquals(ExceptionEnum.USER_NOT_EXIST.getCode(), exception.getCode());
    }

    @Test
    public void testGetById_ReturnsSuccess(){
        Long empId = 108L;
        Emp exceptResult = new Emp();
        exceptResult.setEmpNo(empId);
        exceptResult.setPassword(null);

        Emp result = empService.getEmpById(empId);

        assertEquals(result.getEmpNo(), exceptResult.getEmpNo());
    }

    @Test
    public void testGetById_EmpNotFound_ThrowsException(){
        Long empId = 10800L;

        CustomException exception = assertThrows(CustomException.class, () -> {
            empService.getEmpById(empId);
        });
        assertEquals(ExceptionEnum.USER_NOT_EXIST.getCode(), exception.getCode());
    }



    @Test
    public void testGetEmpListWithPage(){
        PageParams pageParams = new PageParams();
        Emp emp = new Emp();
        pageParams.setPageNum(1);
        pageParams.setPageSize(5);

        IPage<Emp> result = empService.getEmpListWithPage(pageParams, emp);
        assertNotNull(result);
    }

    @Test
    public void testGetEmpByRoleId_ReturnsSuccess(){
        Long roleId = 1l;
        PageParams pageParams = new PageParams();
        pageParams.setPageNum(1);
        pageParams.setPageSize(5);

        IPage<Emp> result = empService.getEmpByRoleId(roleId, pageParams);
        assertNotNull(result);
    }

    @Test
    public void testGetEmpByRoleId_RoleNotFound_ThrowsException(){
        Long roleId = 10000l;
        PageParams pageParams = new PageParams();
        pageParams.setPageNum(1);
        pageParams.setPageSize(5);

        CustomException exception = assertThrows(CustomException.class, () -> {
            empService.getEmpByRoleId(roleId, pageParams);
        });
        assertEquals(ExceptionEnum.ROLE_NOT_EXIST.getCode(), exception.getCode());
    }

    @Test
    public void testGetNotAddedEmpByRoleId_ReturnsSuccess(){
        Long roleId = 1l;
        PageParams pageParams = new PageParams();
        pageParams.setPageNum(1);
        pageParams.setPageSize(5);

        IPage<Emp> result = empService.getNotAddedEmpByRoleId(roleId, pageParams);
        assertNotNull(result);
    }

    @Test
    public void testGetNotAddedEmpByRoleId_RoleNotFound_ThrowsException(){
        Long roleId = 10000l;
        PageParams pageParams = new PageParams();
        pageParams.setPageNum(1);
        pageParams.setPageSize(5);

        CustomException exception = assertThrows(CustomException.class, () -> {
            empService.getNotAddedEmpByRoleId(roleId, pageParams);
        });
        assertEquals(ExceptionEnum.ROLE_NOT_EXIST.getCode(), exception.getCode());
    }
}

//@RunWith(MockitoJUnitRunner.class)
//public class EmpServiceImplTest {
//
//    @Mock
//    private IEmpService empService;
//
//    @Test
//    public void testLogin(){
//        // 创建模拟对象
//        EmpLoginVo empLoginVoMock = mock(EmpLoginVo.class);
//        Emp expectedEmp = new Emp();
//
//        // 设置模拟对象的行为和预期结果
//        when(empLoginVoMock.getName()).thenReturn("admin");
//        when(empLoginVoMock.getPassword()).thenReturn("uV3+7jwEtEE+6/FwoiYIPUTcT99VDEZyM29kHtDTk7UwkQuH0JHZK2SzjUILqN4p2ZktEyFwrfcV6I4QECiWFbUWX2+5duJGlGsZ/x/+U538j1ZrTL7fXa9c3F00QGYhvZb8dLj9AzYSvNuCWGDZLhLVJLo/pBzUHm7VKWE6bMw=");
//        when(empService.login(empLoginVoMock)).thenReturn(expectedEmp);
//
//        // 调用要测试的方法
//        Emp result = empService.login(empLoginVoMock);
//
//        // 进行断言来验证结果
//        assertEquals(expectedEmp.getEmpName(), result.getEmpName());
//    }
//
//}