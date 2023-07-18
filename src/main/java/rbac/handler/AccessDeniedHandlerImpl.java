package rbac.handler;

import com.alibaba.fastjson.JSON;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import rbac.common.Result;
import rbac.common.enums.ExceptionEnum;
import rbac.utils.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 授权失败
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException){
        Result<String> r = new Result<>(ExceptionEnum.ACCESS_ERROR.getCode(), ExceptionEnum.ACCESS_ERROR.getMsg());
        String json = JSON.toJSONString(r);
        // 将字符串渲染到客户端
        WebUtils.renderString(response, json);
    }
}