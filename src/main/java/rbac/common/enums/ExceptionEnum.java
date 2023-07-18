package rbac.common.enums;

/**
 * 用枚举定义异常的 code 和 message
 * @author MiracloW
 */
public enum ExceptionEnum {

    SUCCESS(200,"成功"),
    UNKOWN_ERROR(500,"未知错误"),

    USER_TOKEN_ISNULL(1001, "token不能为空。"),
    USER_TOKEN_EXPIRE(1002, "token已过期，请重新登陆。"),
    USER_TOKEN_ILLEGAL(1003, "token校验失败，请重新登陆。"),
    AUTHENTICATION_ERROR(1004, "认证失败"),
    ACCESS_ERROR(1005, "授权失败"),


    USER_DATA_EMPTY(2001, "用户名或密码为空。"),
    USER_NOT_EXIST(2002, "该用户不存在。"),
    USER_NAME_OR_PASSWORD_WRONG(2003, "用户名或密码错误。"),
    USER_PASSWORD_SAME(2004, "原密码和新密码不能相同。"),
    USER_PASSWORD_WRONG(2005, "用户密码输入错误。"),
    USER_EXIST(2006, "该用户已存在，请更改用户名。"),
    USER_UPDATE_CONCURRENT_MODIFY_ERROR(2007, "该用户已被修改，请重新刷新界面。"),

    ROLE_EXIST(3001, "该角色已存在，请更改角色名。"),
    ROLE_UPDATE_CONCURRENT_MODIFY_ERROR(3002, "该角色已被修改，请重新刷新界面。"),
    ROLE_NOT_EXIST(3003, "该角色不存在。"),

    MENU_NOT_FOUND(4001, "插入的权限不存在，请检查。"),

    AUTHORITY_NOT_ACCESS(5001, "您没有权限访问。"),

    UPDATE_ERROR(6001, "更新失败，请重试。"),


    METHOD_ARUGMENT_NOT_VALID(8001, "参数校验失败。"),

    ENCODE_ERROR(9001, "加密失败。"),
    DECODE_ERROR(9002, "解密失败。"),
    ;

    private Integer code;

    private String msg;

    ExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
