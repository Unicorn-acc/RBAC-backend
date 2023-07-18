package rbac.interceptor;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import rbac.common.enums.ExceptionEnum;
import rbac.common.exception.CustomException;
import rbac.utils.JWTUtils;
import rbac.context.UserContextHolder;
import rbac.token.UserToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户登录拦截器，进行token验证
 * @author MiracloW
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 对token解析并验证合法性和有效期
        String token = request.getHeader("token");
        if(ObjectUtils.isEmpty(token))
            throw new CustomException(ExceptionEnum.USER_TOKEN_ISNULL);

        try {
            JWTUtils.checkToken(token);
        } catch (TokenExpiredException e){
            throw new CustomException(ExceptionEnum.USER_TOKEN_EXPIRE);
        } catch (JWTVerificationException e){
            throw new CustomException(ExceptionEnum.USER_TOKEN_ILLEGAL);
        }

        // 验证通过，放到threadlocal中
        UserToken userToken = JWTUtils.getUserToken(token);
        UserContextHolder.set(userToken);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 删除UserToken，避免内存泄漏
        UserContextHolder.remove();
    }
}
