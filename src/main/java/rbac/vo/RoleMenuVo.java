package rbac.vo;


import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author MiracloW
 */
public class RoleMenuVo {

    @NotNull(message = "角色id不能为空")
    private Long roleId;

    private List<Long> menuIdList;

    public RoleMenuVo(@NotNull(message = "角色id不能为空") Long roleId, List<Long> menuIdList) {
        this.roleId = roleId;
        this.menuIdList = menuIdList;
    }

    public RoleMenuVo() {
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public List<Long> getMenuIdList() {
        return menuIdList;
    }

    public void setMenuIdList(List<Long> menuIdList) {
        this.menuIdList = menuIdList;
    }
}
