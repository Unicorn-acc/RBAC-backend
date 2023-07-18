package rbac.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rbac.common.Result;
import rbac.entity.Menu;
import rbac.service.IMenuService;

import java.util.List;


/**
 * @author MiracloW
 */
@RestController
@Api(value = "菜单管理", tags = "菜单管理")
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private IMenuService menuService;

    @ApiOperation(value = "获取菜单树接口")
    @GetMapping("/tree")
    public Result<List<Menu>> tree(){
        return Result.success(menuService.getTreelist());
    }



}
