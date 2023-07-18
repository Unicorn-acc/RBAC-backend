package rbac.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author MiracloW
 */

@Component
public class MyCorsFilter {


    @Bean
    public CorsFilter corsFilter(){
        // 设置注册URL 配置类
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig());
        return new CorsFilter(source);
    }

    private CorsConfiguration corsConfig(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedHeader("*"); // 允许请求携带任意请求头信息
        corsConfiguration.addAllowedMethod("*"); // 允许任意类型的请求方法
        corsConfiguration.addAllowedOriginPattern("*"); // 允许任意域进行访问
        corsConfiguration.setMaxAge(3600L); // 设置预检请求的最大的时长
        corsConfiguration.setAllowCredentials(true); // 设置是否在响应中生成凭证信息
        return corsConfiguration;
    }
}
