package rbac.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import rbac.common.Result;
import rbac.entity.Emp;
import rbac.entity.Role;
import rbac.service.IAuthService;
import rbac.token.LoginUser;
import rbac.vo.*;

import java.util.List;

/**
 * @author MiracloW
 */
@Api(value = "权限管理", tags = "权限管理")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IAuthService authService;


    @ApiOperation("根据角色ID获取分配权限接口")
    @GetMapping("/getMenuListByRoleId/{id}")
    @PreAuthorize("hasAuthority('auth/getMenuListByRoleId/*')")
    public Result<List<Long>> getMenuListByRoleId(@PathVariable("id") Long id){
        return Result.success(authService.getMenuListByRoleId(id));
    }

    @ApiOperation("更新角色ID分配权限接口")
    @PostMapping("/saveRoleMenu")
    @PreAuthorize("hasAuthority('auth/saveRoleMenu')")
    public Result<String> saveRoleMenu(@RequestBody @Validated RoleMenuVo roleMenuVo){
       authService.saveRoleMenu(roleMenuVo);
       return Result.success("保存成功");
    }

    @ApiOperation("根据角色ID获取已分配用户接口")
    @PostMapping("/getempByRoleId")
    @PreAuthorize("hasAuthority('auth/getempByRoleId')")
    public Result<IPage<Emp>> getEmpListByRoleId(Long id, @RequestBody @Validated PageParams pageParams){
        return Result.success(authService.getEmpListByRoleId(id, pageParams));
    }

    @ApiOperation("根据角色ID获取未分配用户接口")
    @PostMapping("/getNotAddedEmpByRoleId")
    @PreAuthorize("hasAuthority('auth/getNotAddedEmpByRoleId')")
    public Result<IPage<Emp>> getNotAddedEmpByRoleId(Long id, @RequestBody @Validated PageParams pageParams){
        return Result.success(authService.getNotAddedEmpByRoleId(id, pageParams));
    }

    @ApiOperation("保存角色分配的新用户接口")
    @PostMapping("/saveRoleEmp")
    @PreAuthorize("hasAuthority('auth/saveRoleEmp')")
    public Result<String> saveRogetMenuListByRoleIdleEmp(@RequestBody @Validated RoleEmpVo roleEmpVo){
        authService.saveRoleEmp(roleEmpVo);
        return Result.success("保存成功");
    }

    @ApiOperation("删除角色已分配的用户接口")
    @PostMapping("/deleteRoleEmp")
    @PreAuthorize("hasAuthority('auth/deleteRoleEmp')")
    public Result<String> deleteRoleEmp(@RequestBody @Validated RoleRemoveEmpVo roleRemoveEmpVo){
        authService.deleteRoleEmp(roleRemoveEmpVo);
        return Result.success("删除成功");
    }

    @ApiOperation("删除用户已分配的角色接口")
    @PostMapping("/deleteEmpRole")
    @PreAuthorize("hasAuthority('auth/deleteEmpRole')")
    public Result<String> deleteEmpRole(@RequestBody @Validated RoleRemoveEmpVo roleRemoveEmpVo){
        authService.deleteRoleEmp(roleRemoveEmpVo);
        return Result.success("删除成功");
    }

    @ApiOperation("根据用户ID获取已分配角色接口")
    @PostMapping("/getRoleByEmpId")
    @PreAuthorize("hasAuthority('auth/getRoleByEmpId')")
    public Result<IPage<Role>> getRoleByEmpId(Long id, @RequestBody @Validated PageParams pageParams){
        return Result.success(authService.getRoleListByEmpId(id, pageParams));
    }

    @ApiOperation("根据用户ID获取未分配角色接口")
    @PostMapping("/getNotAddedRoleByEmpId")
    @PreAuthorize("hasAuthority('auth/getNotAddedRoleByEmpId')")
    public Result<IPage<Role>> getNotAddedRoleByEmpId(Long id, @RequestBody @Validated PageParams pageParams){
        return Result.success(authService.getNotAddedRoleByEmpId(id, pageParams));
    }

    @ApiOperation("保存用户分配的新角色接口")
    @PostMapping("/saveEmpRole")
    @PreAuthorize("hasAuthority('auth/saveEmpRole')")
    public Result<String> saveEmpRole(@RequestBody @Validated EmpRoleVo empRoleVo){
        authService.saveEmpRole(empRoleVo);
        return Result.success("保存成功");
    }

    @ApiOperation("获取用户的所有的权限接口")
    @GetMapping("/getAuth")
    public Result<List<String>> getAuth(){
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = loginUser.getEmp().getEmpNo();
        List<String> permissions = authService.getPermissionsByEmpId(userId);
        return Result.success(permissions);
    }

}
