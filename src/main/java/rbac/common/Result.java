package rbac.common;

import java.util.HashMap;
import java.util.Map;

/**
 * @author MiracloW
 */
public class Result<T> {

    private Integer code; //编码

    private T data; //数据

    private String msg; //错误信息

    private Map<Object, Object> map = new HashMap<>(); //动态数据

    public static <T> Result<T> success() {
        Result<T> r = new Result<>();
        r.code = 200;
        return r;
    }

    public static <T> Result<T> success(T object) {
        Result<T> r = new Result<>();
        r.data = object;
        r.code = 200;
        return r;
    }

    public static <T> Result<T> error() {
        Result<T> r = new Result<>();
        r.code = 500;
        return r;
    }

    public static <T> Result<T> error(String msg) {
        Result<T> r = new Result<>();
        r.code = 500;
        r.msg = msg;
        return r;
    }

    public Result<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }


    public static <T> Result<T> write(Integer code, String msg) {
        Result<T> r = new Result<>();
        r.code = code;
        r.msg = msg;
        return r;
    }

    public Result() {
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Map<Object, Object> getMap() {
        return map;
    }

    public void setMap(Map<Object, Object> map) {
        this.map = map;
    }
}
