package rbac.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import rbac.common.Result;
import rbac.common.enums.ExceptionEnum;

import java.util.List;

/**
 * @author MiracloW
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // 系统异常
    @ExceptionHandler(Exception.class)
    public Result<String> systemExceptionHandler(Exception e){
        log.error("系统异常：",e);
        return Result.error("系统繁忙，请稍后再试");
    }

    // 业务异常
    @ExceptionHandler(CustomException.class)
    public Result<String> customExceptionHandler(CustomException e){
        log.info("业务异常，异常代码：{}， 信息：{}", e.getCode(), e.getMsg());
        return Result.write(e.getCode(), e.getMsg());
    }

    // 参数校验异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<String> doMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        // 由于用户输入的内容可能存在多处错误，所以我们要将所有错误信息都提示给用户
        BindingResult bindingResult = exception.getBindingResult();
        // 获取错误集合
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        // 拼接字符串
        StringBuilder sb = new StringBuilder();
        fieldErrors.forEach(fieldError -> sb.append(fieldError.getDefaultMessage()).append(","));
        // 记录日志
        log.info("参数校验异常 异常信息 {}", sb);
        // 响应给用户
        return Result.write(ExceptionEnum.METHOD_ARUGMENT_NOT_VALID.getCode(), sb.toString());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public Result<String> handleBadCredentialsException(BadCredentialsException e) {
        return Result.write(ExceptionEnum.USER_PASSWORD_WRONG.getCode(), ExceptionEnum.USER_PASSWORD_WRONG.getMsg());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public Result<String> handleAccessDeniedException(AccessDeniedException e) {
        return Result.write(ExceptionEnum.AUTHORITY_NOT_ACCESS.getCode(), ExceptionEnum.AUTHORITY_NOT_ACCESS.getMsg());
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public Result<String> handleInternalAuthenticationServiceException(InternalAuthenticationServiceException e) {
        return Result.write(ExceptionEnum.AUTHENTICATION_ERROR.getCode(), ExceptionEnum.AUTHENTICATION_ERROR.getMsg());
    }
}
