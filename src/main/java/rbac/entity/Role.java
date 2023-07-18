package rbac.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import rbac.common.exception.ValidationGroups;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author MiracloW
 */
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    @Null(message = "角色id需为空", groups = ValidationGroups.Insert.class)
    @NotNull(message = "角色id不能为空", groups = ValidationGroups.Update.class)
    @TableId(type = IdType.AUTO)
    private Long id;

    @NotEmpty(message = "角色名不能为空", groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    private String roleName;

    @NotEmpty(message = "角色描述不能为空", groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    private String roleDesc;

    @TableField(fill = FieldFill.INSERT)
    @JSONField(format = "yyyy-MM-dd")
    private LocalDateTime createTime;

    @TableLogic
    @TableField(select = false)
    private Integer isDeleted;

    @Null(message = "随机token需为空", groups = ValidationGroups.Insert.class)
    @NotNull(message = "随机token不能为空", groups = ValidationGroups.Update.class)
    private Integer recToken;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getRecToken() {
        return recToken;
    }

    public void setRecToken(Integer recToken) {
        this.recToken = recToken;
    }
}
