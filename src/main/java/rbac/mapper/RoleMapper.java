package rbac.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import rbac.entity.Role;


/**
 * @author MiracloW
 */
public interface RoleMapper extends BaseMapper<Role> {
    IPage<Role> rolePage(@Param("page") Page<Role> page, @Param("param") Role role);

    IPage<Role> getRoleListByEmpId(@Param("page") Page<Role> page, @Param("empId") Long empId);

    IPage<Role> getNotAddedRoleByEmpId(@Param("page") Page<Role> page, @Param("empId") Long empId);

    Integer removeEmpRoleRelationshipByRoleId(Long roleid);

    Integer removeRoleMenuRelationshipByRoleId(Long roleid);

    Role getRoleByRoleName(String roleName);

    Role getRoleById(Long id);
}
