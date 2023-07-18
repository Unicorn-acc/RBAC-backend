package rbac.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * MP自定义元数据对象处理器
 * @author MiracloW
 */
@Component
public class MyMetaObjecthandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        metaObject.setValue("createTime", LocalDateTime.now());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // Do nothing
    }

}
