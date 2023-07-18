package rbac.vo;


import javax.validation.constraints.NotNull;

/**
 * @author MiracloW
 */
public class RoleRemoveEmpVo {

    @NotNull(message = "用户id不能为空")
    private Long empId;

    @NotNull(message = "角色不能为空")
    private Long roleId;

    public RoleRemoveEmpVo(@NotNull(message = "用户id不能为空") Long empId, @NotNull(message = "角色不能为空") Long roleId) {
        this.empId = empId;
        this.roleId = roleId;
    }

    public RoleRemoveEmpVo() {
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
