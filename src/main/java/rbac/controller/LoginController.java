package rbac.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import rbac.common.Result;
import rbac.token.LoginUser;
import rbac.token.UserToken;
import rbac.service.IEmpService;
import rbac.vo.EmpLoginVo;
import rbac.vo.UserPasswordVo;

/**
 * https://cloud.tencent.com/developer/article/2258309
 * @author MiracloW
 */
@Api(value = "用户登录管理", tags = "用户登录管理")
@RestController
public class LoginController {

    @Autowired
    private IEmpService empService;

    @ApiOperation(value = "用户登录接口")
    @PostMapping("/login")
    public Result<String> login(@RequestBody @Validated EmpLoginVo empLoginVo){
        return Result.success(empService.login(empLoginVo));
    }

    @ApiOperation(value = "查询用户信息接口")
    @GetMapping("/userinfo")
    public Result<UserToken> userinfo(){
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserToken userToken = new UserToken(loginUser.getEmp().getEmpNo(), loginUser.getEmp().getEmpName());
        return Result.success(userToken);
    }

    @ApiOperation(value = "修改用户密码接口")
    @PostMapping("/updatepassword")
    public Result<String> updatepassword(@RequestBody @Validated UserPasswordVo userPasswordVo){
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = loginUser.getEmp().getEmpNo();
        empService.updatepassword(userPasswordVo, userId);
        return Result.success("更新成功");
    }

    @ApiOperation(value = "用户退出接口")
    @PostMapping("/out")
    public Result<String> logout(){
        empService.logout();
        return Result.success("退出成功");
    }
}
