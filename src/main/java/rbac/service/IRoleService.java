package rbac.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import rbac.entity.Role;
import rbac.vo.PageParams;


/**
 * @author MiracloW
 */
public interface IRoleService extends IService<Role> {

    IPage<Role> getRoleListWithPage(PageParams pageParams, Role role);

    IPage<Role> getRoleListByEmpId(Long empId, PageParams pageParams);

    IPage<Role> getNotAddedRoleByEmpId(Long empId, PageParams pageParams);

    Boolean removeRoleById(Long roleId);

    Boolean saveRole(Role role);

    Boolean updateRoleByRoleId(Role role);

    Role getRoleById(Long roleId);
}
