package rbac.mapper;

import rbac.vo.EmpRoleVo;
import rbac.vo.RoleEmpVo;
import rbac.vo.RoleMenuVo;
import rbac.vo.RoleRemoveEmpVo;

import java.util.List;

/**
 * @author MiracloW
 */
public interface AuthMapper {

    List<Long> getMenuListByRoleId(Long roleId);

    Integer deleteRoleMenuByRoleId(Long roleId);

    // 批量插入
    Integer saveRoleMenuList(RoleMenuVo roleMenuVo);

    Integer saveRoleEmpList(RoleEmpVo roleEmpVo);

    Integer deleteRoleEmp(RoleRemoveEmpVo roleRemoveEmpVo);

    Integer saveEmpRoleList(EmpRoleVo empRoleVo);

    List<String> getPermissions(Long empId);
}
