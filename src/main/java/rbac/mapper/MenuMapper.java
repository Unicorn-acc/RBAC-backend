package rbac.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import rbac.entity.Menu;

import java.util.List;

/**
 * @author MiracloW
 */
public interface MenuMapper extends BaseMapper<Menu> {

    @Select("select * from menu order by order_num")
    List<Menu> treeList();
}
