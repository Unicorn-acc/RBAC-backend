package rbac.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;
import rbac.common.enums.ExceptionEnum;
import rbac.common.exception.CustomException;
import rbac.context.UserContextHolder;
import rbac.service.IAuthService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * @author MiracloW
 */
@Component
@Configuration
public class AuthorityInterceptor implements HandlerInterceptor {

    @Autowired
    private IAuthService authService;

    @Value("${server.port}")
    private String port;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

//    antPathMatcher
//        ?	匹配一个字符
//        *	匹配多个字符
//        **	匹配多层路径
//     antPathMatcher.match("/root/*",  "/root/aaa"); // true
//      // true，都以 / 结束
//     antPathMatcher.match("/root/*/", "/root/aaa/");// true
//      // false，结束符不一致
//     antPathMatcher.match("/root/*", "/root/aaa/"); // false


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        // 获取该用户拥有的所有权限，一一对比
        List<String> perSet = authService.getPermissionsByEmpId(UserContextHolder.get().getUserId());
        String url = request.getRequestURL().toString();
        // 截取访问资源路径
        String[] parts = url.split("/");
        String result = "";

        if (parts.length >= 4) {
            result = String.join("/", Arrays.copyOfRange(parts, 3, parts.length));
        }
        if(result.equals("")) return false;

        boolean flag = false;

        for(String per : perSet){
            if(antPathMatcher.match(per, result)){
                flag = true;
                break;
            }
        }

        if(flag) return true;
        else
            throw new CustomException(ExceptionEnum.AUTHORITY_NOT_ACCESS);
    }
}

/**

 Spring Security一般流程为：
 1. 当用户登录时，前端将用户输入的用户名、密码信息传输到后台，后台用一个类对象将其封装起来，
        通常使用的是UsernamePasswordAuthenticationToken这个类。
 2. 程序负责验证这个类对象。验证方法是调用Service根据username从数据库中取用户信息到实体类的实例中，
        比较两者的密码，如果密码正确就成功登陆，
        同时把包含着用户的用户名、密码、所具有的权限等信息的类对象放到SecurityContextHolder（安全上下文容器，类似Session）中去。
 3. 用户访问一个资源的时候，首先判断是否是受限资源。如果是的话还要判断当前是否未登录，没有的话就跳到登录页面。
 4. 如果用户已经登录，访问一个受限资源的时候，程序要根据url去数据库中取出该资源所对应的所有可以访问的角色，
        然后拿着当前用户的所有角色一一对比，判断用户是否可以访问（这里就是和权限相关）。

 */