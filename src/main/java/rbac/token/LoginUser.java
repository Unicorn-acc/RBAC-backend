package rbac.token;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import rbac.entity.Emp;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 重写UserDetails返回的用户信息
 * SpringSecurity返回的用户信息实体类
 */
public class LoginUser implements UserDetails {

    private Emp emp;//用户信息

    private List<String> permissions;//权限信息

    public LoginUser() {
    }

    public LoginUser(Emp emp, List<String> permissions) {
        this.emp = emp;
        this.permissions = permissions;
    }

    @JSONField(serialize = false) //fastjson注解,表示此属性不会被序列化，因为SimpleGrantedAuthority这个类型不能在redis中序列化
    private List<SimpleGrantedAuthority> authorities;

    /**
     * 获取权限信息
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 权限为空的时候才往遍历，不为空直接返回
        if (authorities != null) {
            return authorities;
        }
        //把permissions中String类型的权限信息封装成SimpleGrantedAuthority对象
        authorities = permissions.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return authorities;
    }

    /**
     * 获取密码
     */
    @Override
    public String getPassword() {
        return emp.getPassword();
    }

    /**
     * 获取用户名
     */
    @Override
    public String getUsername() {
        return emp.getEmpName();
    }

    /**
     * 判断是否过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 是否锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 是否没有超时
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否可用
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    public Emp getEmp() {
        return emp;
    }

    public void setEmp(Emp emp) {
        this.emp = emp;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }
}
