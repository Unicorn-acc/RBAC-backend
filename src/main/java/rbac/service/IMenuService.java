package rbac.service;

import com.baomidou.mybatisplus.extension.service.IService;
import rbac.entity.Menu;

import java.util.List;

/**
 * @author MiracloW
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> getTreelist();

}
