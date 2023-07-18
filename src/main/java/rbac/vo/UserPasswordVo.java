package rbac.vo;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author MiracloW
 */
public class UserPasswordVo {

    @NotNull(message = "原密码不能为空")
//    @Size(min = 6, max = 12, message = "密码长度应该在6到12个字符内。")
    private String oripassword;

    @NotNull(message = "旧密码不能为空")
//    @Size(min = 6, max = 12, message = "密码长度应该在6到12个字符内。")
    private String newpassword;

    public UserPasswordVo(@NotNull(message = "原密码不能为空") @Size(min = 6, max = 12, message = "密码长度应该在6到12个字符内。") String oripassword, @NotNull(message = "旧密码不能为空") @Size(min = 6, max = 12, message = "密码长度应该在6到12个字符内。") String newpassword) {
        this.oripassword = oripassword;
        this.newpassword = newpassword;
    }

    public UserPasswordVo() {
    }

    public String getOripassword() {
        return oripassword;
    }

    public void setOripassword(String oripassword) {
        this.oripassword = oripassword;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }
}
