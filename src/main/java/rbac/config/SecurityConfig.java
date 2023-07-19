package rbac.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;
import rbac.filter.ExceptionHandlerFilter;
import rbac.filter.JwtAuthenticationTokenFilter;
import rbac.handler.AccessDeniedHandlerImpl;
import rbac.handler.AuthenticationEntryPointImpl;

/**
 * @author MiracloW
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true) // 开启@PreAuthorize()注解权限功能
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //认证过滤器
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    // 异常处理器
    @Autowired
    private ExceptionHandlerFilter exceptionHandlerFilter;

    // 认证失败处理器
    @Autowired
    private AuthenticationEntryPointImpl authenticationEntryPoint;

    // 授权失败处理器
    @Autowired
    private AccessDeniedHandlerImpl accessDeniedHandler;

    /**
     * 认证配置
     * anonymous()：匿名访问：未登录可以访问，已登录不能访问
     * permitAll()：有没有认证都能访问：登录或未登录都能访问
     * denyAll(): 拒绝
     * authenticated()：认证之后才能访问
     * hasAuthority（）：包含权限
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 关闭csrf(前后端分离项目要关闭此功能）
                .csrf().disable()
                // 禁用session (前后端分离项目，不通过Session获取SecurityContext)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // 请求认证配置
                .authorizeRequests()
                // 允许匿名访问：未登录可以访问，已登录不能访问
                .antMatchers("/login").permitAll()
                // .antMatchers("/login").permitAll()// 登录或未登录都能访问
                // .antMatchers("/textMybatis").hasAuthority("system:dept:list22")
                // 任意用户，认证之后才可以访问（除上面外的）
                .anyRequest().authenticated();

        // 添加token过滤器 这里其实没有用到Username这个系统提供的过滤器，这里相当于用我们的jwt过滤器替换了usernamePassword这个过滤器
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        http.addFilterBefore(exceptionHandlerFilter, CorsFilter.class);

        // 配置异常处理器
        http.exceptionHandling()
                // 认证失败
                .authenticationEntryPoint(authenticationEntryPoint)
                // 授权失败
                .accessDeniedHandler(accessDeniedHandler);

        // spring security 允许跨域
        http.cors();
    }



    /**
     * 密码机密处理器
     * <p>
     * 将BCryptPasswordEncoder对象注入到spring容器中,更换掉原来的 PasswordEncoder加密方式
     * 原PasswordEncoder密码格式为：{id}password。它会根据id去判断密码的加密方式。
     * 如果没替换原来的加密方式，数据库中想用明文密码做测试，将密码字段改为{noop}123456这样的格式
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * 注入AuthenticationManager 进行用户认证
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
