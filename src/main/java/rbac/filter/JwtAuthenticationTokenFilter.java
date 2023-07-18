package rbac.filter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import rbac.common.enums.ExceptionEnum;
import rbac.common.exception.CustomException;
import rbac.token.LoginUser;
import rbac.token.UserToken;
import rbac.utils.JWTUtils;
import rbac.utils.RedisCache;
import rbac.utils.RedisConstants;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * token认证过滤器
 *
 * 作用：解析请求头中的token。并验证合法性
 * 继承 OncePerRequestFilter 保证请求经过过滤器一次
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Resource
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");

        // 没有token
        if (!StringUtils.hasText(token)) {
            filterChain.doFilter(request, response);//放行,因为后面的会抛出相应的异常
            return;
        }

        try {
            JWTUtils.checkToken(token);
        } catch (TokenExpiredException e){
            throw new CustomException(ExceptionEnum.USER_TOKEN_EXPIRE);
        } catch (JWTVerificationException e){
            throw new CustomException(ExceptionEnum.USER_TOKEN_ILLEGAL);
        }
        // 验证通过，获取token
        UserToken userToken = JWTUtils.getUserToken(token);

        String redisKey = RedisConstants.LOGIN_USER_KEY + userToken.getUserId();

        LoginUser loginUser = redisCache.getCacheObject(redisKey);// 从redis中获取用户信息

        // redis中用户不存在
        if (Objects.isNull(loginUser)) {
            throw new CustomException(ExceptionEnum.USER_NOT_EXIST);
        }

        // 将Authentication对象（用户信息、已认证状态、权限信息）存入 SecurityContextHolder
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //放行
        filterChain.doFilter(request, response);
    }
}