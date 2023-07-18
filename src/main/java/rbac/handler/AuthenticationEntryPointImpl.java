package rbac.handler;

import com.alibaba.fastjson.JSON;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import rbac.common.Result;
import rbac.common.enums.ExceptionEnum;
import rbac.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Result<String> r = new Result<>(ExceptionEnum.AUTHENTICATION_ERROR.getCode(), ExceptionEnum.AUTHENTICATION_ERROR.getMsg());
        String json = JSON.toJSONString(r);
        // 将字符串渲染到客户端
        WebUtils.renderString(response, json);
    }
}