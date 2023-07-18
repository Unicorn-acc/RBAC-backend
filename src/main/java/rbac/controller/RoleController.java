package rbac.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import rbac.common.Result;
import rbac.common.exception.ValidationGroups;
import rbac.entity.Role;
import rbac.service.IRoleService;
import rbac.vo.PageParams;

/**
 * @author MiracloW
 */
@RestController
@Api(value = "角色管理", tags = "角色管理")
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;


    @ApiOperation(value = "角色分页查询接口")
    @PostMapping("/page")
    @PreAuthorize("hasAuthority('role/page')")
    public Result<IPage<Role>> page(PageParams pageParams, @RequestBody Role role){
        return Result.success(roleService.getRoleListWithPage(pageParams, role));
    }


    @ApiOperation(value = "根据id查询角色接口")
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('role/info/*')")
    public Result<Role> getByid(@PathVariable("id") Long id){
        Role role = roleService.getRoleById(id);
        return Result.success(role);
    }

    @ApiOperation(value = "新增角色接口")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('role/save')")
    public Result<String> save(@RequestBody @Validated(ValidationGroups.Insert.class) Role role){
        roleService.saveRole(role);
        return Result.success("添加角色成功");
    }

    @ApiOperation(value = "更新角色接口")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('role/update')")
    public Result<String> update(@RequestBody @Validated(ValidationGroups.Update.class) Role role){
        roleService.updateRoleByRoleId(role);
        return Result.success("更新角色成功");
    }

    @ApiOperation(value = "删除角色接口")
    @DeleteMapping("/remove/{id}")
    @PreAuthorize("hasAuthority('role/remove/*')")
    public Result<String> delete(@PathVariable("id") Long id){
        roleService.removeRoleById(id);
        return Result.success("删除角色成功");
    }
}
