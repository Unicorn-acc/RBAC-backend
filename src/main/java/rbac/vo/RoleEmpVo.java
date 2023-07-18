package rbac.vo;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author MiracloW
 */
public class RoleEmpVo {

    @NotNull(message = "角色id不能为空")
    private Long roleId;

    private List<Long> empIdList;

    public RoleEmpVo(@NotNull(message = "角色id不能为空") Long roleId, List<Long> empIdList) {
        this.roleId = roleId;
        this.empIdList = empIdList;
    }

    public RoleEmpVo() {
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public List<Long> getEmpIdList() {
        return empIdList;
    }

    public void setEmpIdList(List<Long> empIdList) {
        this.empIdList = empIdList;
    }
}
