package rbac.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rbac.common.enums.ExceptionEnum;
import rbac.common.exception.CustomException;
import rbac.entity.Emp;
import rbac.entity.Menu;
import rbac.entity.Role;
import rbac.mapper.AuthMapper;
import rbac.service.IAuthService;
import rbac.service.IEmpService;
import rbac.service.IMenuService;
import rbac.service.IRoleService;
import rbac.vo.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author MiracloW
 */
@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private AuthMapper authMapper;

    @Autowired
    private IEmpService empService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IMenuService menuService;

    @Override
    public List<Long> getMenuListByRoleId(Long roleId) {
        Role role = roleService.getById(roleId);
        if(role == null)
            throw new CustomException(ExceptionEnum.ROLE_NOT_EXIST);

        return authMapper.getMenuListByRoleId(roleId);
    }

    @Transactional
    @Override
    public Boolean saveRoleMenu(RoleMenuVo roleMenuVo) {
        Role role = roleService.getById(roleMenuVo.getRoleId());
        if(role == null)
            throw new CustomException(ExceptionEnum.ROLE_NOT_EXIST);

        authMapper.deleteRoleMenuByRoleId(roleMenuVo.getRoleId());
        if(!roleMenuVo.getMenuIdList().isEmpty()){
            for(Long menuid : roleMenuVo.getMenuIdList()){
                Menu menu = menuService.getById(menuid);
                if(menu == null)
                    throw new CustomException(ExceptionEnum.MENU_NOT_FOUND);
            }
            authMapper.saveRoleMenuList(roleMenuVo);
        }
        return true;
    }

    @Override
    public IPage<Emp> getEmpListByRoleId(Long roleId, PageParams pageParams) {
        Role role = roleService.getById(roleId);
        if(role == null)
            throw new CustomException(ExceptionEnum.ROLE_NOT_EXIST);

        return empService.getEmpByRoleId(roleId, pageParams);
    }

    @Override
    public IPage<Emp> getNotAddedEmpByRoleId(Long roleId, PageParams pageParams) {
        Role role = roleService.getById(roleId);
        if(role == null)
            throw new CustomException(ExceptionEnum.ROLE_NOT_EXIST);

        return empService.getNotAddedEmpByRoleId(roleId, pageParams);
    }

    @Override
    public Boolean saveRoleEmp(RoleEmpVo roleEmpVo) {
        Role role = roleService.getById(roleEmpVo.getRoleId());
        if(role == null)
            throw new CustomException(ExceptionEnum.ROLE_NOT_EXIST);

        if(!roleEmpVo.getEmpIdList().isEmpty()){
            for(Long empid : roleEmpVo.getEmpIdList()){
                Emp e = empService.getById(empid);
                if(e == null)
                    throw new CustomException(ExceptionEnum.USER_NOT_EXIST);
            }
            authMapper.saveRoleEmpList(roleEmpVo);
        }

        return true;
    }

    @Override
    public Boolean deleteRoleEmp(RoleRemoveEmpVo roleRemoveEmpVo) {
        Role role = roleService.getById(roleRemoveEmpVo.getRoleId());
        if(role == null)
            throw new CustomException(ExceptionEnum.ROLE_NOT_EXIST);
        Emp emp = empService.getById(roleRemoveEmpVo.getEmpId());
        if(emp == null)
            throw new CustomException(ExceptionEnum.USER_NOT_EXIST);

        authMapper.deleteRoleEmp(roleRemoveEmpVo);
        return true;
    }

    @Override
    public IPage<Role> getRoleListByEmpId(Long empId, PageParams pageParams) {
        Emp emp = empService.getById(empId);
        if(emp == null)
            throw new CustomException(ExceptionEnum.USER_NOT_EXIST);

        return roleService.getRoleListByEmpId(empId, pageParams);
    }

    @Override
    public IPage<Role> getNotAddedRoleByEmpId(Long empId, PageParams pageParams) {
        Emp emp = empService.getById(empId);
        if(emp == null)
            throw new CustomException(ExceptionEnum.USER_NOT_EXIST);

        return roleService.getNotAddedRoleByEmpId(empId, pageParams);
    }

    @Override
    public Boolean saveEmpRole(EmpRoleVo empRoleVo) {
        Emp emp = empService.getById(empRoleVo.getEmpId());
        if(emp == null)
            throw new CustomException(ExceptionEnum.USER_NOT_EXIST);

        if(!empRoleVo.getRoleIdList().isEmpty()){
            for(Long roleid : empRoleVo.getRoleIdList()){
                Role r = roleService.getById(roleid);
                if(r == null)
                    throw new CustomException(ExceptionEnum.ROLE_NOT_EXIST);
            }
            authMapper.saveEmpRoleList(empRoleVo);
        }
        return true;
    }

    @Override
    public List<String> getPermissionsByEmpId(Long empId) {
        Emp emp = empService.getById(empId);
        if(emp == null)
            throw new CustomException(ExceptionEnum.USER_NOT_EXIST);

        List<String> permissionList = authMapper.getPermissions(empId);
        Set<String> permissionSet = new HashSet<>();
        for(String p : permissionList){
            if(ObjectUtils.isNotEmpty(p))
                permissionSet.add(p);
        }
        return new ArrayList<>(permissionSet);
    }
}
