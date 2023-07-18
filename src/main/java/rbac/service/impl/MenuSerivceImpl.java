package rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import rbac.entity.Menu;
import rbac.mapper.MenuMapper;
import rbac.service.IMenuService;

import java.util.*;

/**
 * @author MiracloW
 */
@Service
public class MenuSerivceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {


    @Override
    public List<Menu> getTreelist() {
        List<Menu> menuList = baseMapper.treeList();

        // 转为标准json树格式
        // 记录id ->  menu 索引
        Map<Long, Menu> idToMenu = new HashMap<>();
        for(Menu menu : menuList){
            idToMenu.put(menu.getId(), menu);
        }

        // 将 menu 挂载到 pid对应的menu 下
        idToMenu.put(-1l, new Menu());
        for(Menu menu : menuList){
            if(menu.getPid() == -1l)
                continue;
            Menu pmenu = idToMenu.get(menu.getPid());
            if(pmenu.getChildren() == null) pmenu.setChildren(new ArrayList<>());
            idToMenu.get(menu.getPid()).getChildren().add(menu);
        }

        // 找到所有一级父节点 然后返回
        List<Menu> treelist = new ArrayList<>();
        for(Menu menu : menuList){
            if(menu.getPid() != -1l)
                continue;
            treelist.add(menu);
        }
        return treelist;
    }
}
