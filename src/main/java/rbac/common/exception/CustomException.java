package rbac.common.exception;

import rbac.common.enums.ExceptionEnum;

/**
 * 自定义业务异常类
 * @author MiracloW
 */
public class CustomException extends RuntimeException{

    private Integer code;

    private String msg;

    public CustomException() {
    }

    public Integer getCode() {
        return code;
    }
    
    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public CustomException(ExceptionEnum exceptionEnum) {
        this.code = exceptionEnum.getCode();
        this.msg = exceptionEnum.getMsg();
    }
}
