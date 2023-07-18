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
import rbac.entity.Emp;
import rbac.service.IEmpService;
import rbac.vo.PageParams;

/**
 * @author MiracloW
 */
@RestController
@Api(value = "用户管理", tags = "用户管理")
@RequestMapping("/emp")
public class EmpController {

    @Autowired
    private IEmpService empService;

    @ApiOperation(value = "用户分页查询接口")
    @PostMapping("/page")
    @PreAuthorize("hasAuthority('emp/page')")
    public Result<IPage<Emp>> page(PageParams pageParams, @RequestBody Emp emp){ // page复用分页和条件查询
        return Result.success(empService.getEmpListWithPage(pageParams, emp));
    }

    @ApiOperation(value = "根据Id查询用户接口")
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('emp/info/*')")
    public Result<Emp> getById(@PathVariable("id") Long id){
        Emp emp = empService.getEmpById(id);
        return Result.success(emp);
    }

    @ApiOperation(value = "新增用户接口")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('emp/save')")
    public Result<String> save(@RequestBody @Validated(ValidationGroups.Insert.class) Emp emp){
        empService.saveEmp(emp);
        return Result.success("添加用户成功");
    }

    @ApiOperation(value = "更新用户接口")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('emp/update')")
    public Result<String> update(@RequestBody @Validated(ValidationGroups.Update.class) Emp emp){
        empService.updateEmpById(emp);
        return Result.success("更新用户成功");
    }

    @ApiOperation(value = "删除用户接口")
    @DeleteMapping("/remove/{id}")
    @PreAuthorize("hasAuthority('emp/remove/*')")
    public Result<String> remove(@PathVariable("id") Long id){
        empService.removeEmpbyid(id);
        return Result.success("删除用户成功");
    }
}