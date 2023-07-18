package rbac.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.Transient;
import rbac.common.exception.ValidationGroups;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author MiracloW
 */
public class Emp implements Serializable {

    private static final long serialVersionUID = 2405172041950251807L;

    @Null(message = "用户id需为空", groups = ValidationGroups.Insert.class)
    @NotNull(message = "用户id不能为空", groups = ValidationGroups.Update.class)
    @TableId(type = IdType.AUTO)
    private Long empNo;

    @NotEmpty(message = "用户名不能为空", groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    @Size(min = 3, max = 12, message = "用户名长度应该在3到12个字符内。", groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    private String empName;

    @NotEmpty(message = "密码不能为空", groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
//    @Size(min = 6, max = 12, message = "密码长度应该在6到12个字符内。", groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    private String password;

    @NotNull(message = "部门号不能为空", groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    @Range(min=1, max=5, message = "部门号应该在1到5之间", groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    @Digits(integer = 1, fraction = 0, message = "部门号应该为整数")
    private Integer deptNo;

    @NotEmpty(message = "职务不能为空", groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    @Pattern(regexp = "^(员工|经理|主管|领导)$", message = "职务必须是员工、经理、主管或领导", groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    private String job;

    @NotEmpty(message = "手机号不能为空", groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    @Size(min = 11, max = 11, message = "手机号长度应该为11个字符。", groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    private String phone;

    @TableField(fill = FieldFill.INSERT)
    @JSONField(format = "yyyy-MM-dd")
    private LocalDateTime createTime;

    @TableLogic
    @TableField(select = false)
    private Integer isDeleted;

    @Null(message = "随机token需为空", groups = ValidationGroups.Insert.class)
    @NotNull(message = "随机token不能为空", groups = ValidationGroups.Update.class)
    private Integer recToken;

    @TableField(exist = false)
    @JSONField(serialize = false)
    @Transient
    private List<Role> roleList;

    public Long getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Long empNo) {
        this.empNo = empNo;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(Integer deptNo) {
        this.deptNo = deptNo;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
