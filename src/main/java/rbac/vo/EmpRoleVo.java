package rbac.vo;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author MiracloW
 */
public class EmpRoleVo {

    @NotNull(message = "用户id不能为空")
    private Long empId;

    private List<Long> roleIdList;

    public EmpRoleVo() {
    }

    public EmpRoleVo(@NotNull(message = "用户id不能为空") Long empId, List<Long> roleIdList) {
        this.empId = empId;
        this.roleIdList = roleIdList;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public List<Long> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<Long> roleIdList) {
        this.roleIdList = roleIdList;
    }
}
