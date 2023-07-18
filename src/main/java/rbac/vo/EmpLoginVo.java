package rbac.vo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author MiracloW
 */
public class EmpLoginVo {

    @NotEmpty(message = "用户名不能为空")
    @Size(min = 2, max = 12, message = "用户名长度应该在2到12个字符内。")
    String name;

    @NotEmpty(message = "密码不能为空")
//    @Size(min = 6, max = 12, message = "密码长度应该在6到12个字符内。")
    String password;

    public EmpLoginVo() {
    }

    public EmpLoginVo(@NotEmpty(message = "用户名不能为空") @Size(min = 3, max = 12, message = "用户名长度应该在3到12个字符内。") String name, @NotEmpty(message = "密码不能为空") @Size(min = 6, max = 12, message = "密码长度应该在6到12个字符内。") String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "EmpLoginVo{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
