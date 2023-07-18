package rbac.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.*;
import rbac.interceptor.AuthorityInterceptor;
import rbac.interceptor.LoginInterceptor;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author MiracloW
 */
//@Configuration
public class WebMVCConfig extends WebMvcConfigurationSupport{

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Autowired
    private AuthorityInterceptor authorityInterceptor;

    // 放行的请求
    private String[] excludepaths = new String[]{
            "/login",

            "/doc.html",
            "/swagger-resources",
            "/v2/api-docs"
    };

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor) // 注册自己的拦截器
                .addPathPatterns("/**")
                .excludePathPatterns(excludepaths)
                .order(1);

        registry.addInterceptor(authorityInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login",
                        "/userinfo",
                        "/updatepassword",
                        "/auth/getAuth",
                        "/menu/tree",

                        "/doc.html",
                        "/webjars/**",
                        "/swagger-resources",
                        "/v2/api-docs")
                .order(2);

    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public Docket createRestApi() {
        //文档类型
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("rbac.controller"))
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("RBAC")
                .version("1.0")
                .description("RBAC接口文档")
                .build();
    }
}
