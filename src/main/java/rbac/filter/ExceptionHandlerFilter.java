package rbac.filter;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import rbac.common.Result;
import rbac.common.exception.CustomException;
import rbac.utils.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  Filter链 异常捕获 处理
 */
@Component
public class ExceptionHandlerFilter extends OncePerRequestFilter {

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (CustomException e) {
            Result<String> r = new Result<>(e.getCode(), e.getMsg());
            String json = JSON.toJSONString(r);
            // 将字符串渲染到客户端
            WebUtils.renderString(response, json);
        }
    }
}