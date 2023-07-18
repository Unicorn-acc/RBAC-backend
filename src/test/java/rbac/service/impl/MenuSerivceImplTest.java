package rbac.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import rbac.entity.Menu;
import rbac.service.IMenuService;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author MiracloW
 */
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class MenuSerivceImplTest {

    @Autowired
    private IMenuService menuService;

    @Test
    public void testGetTreelist(){
        List<Menu> result = menuService.getTreelist();
        for(Menu menu : result){
            System.out.println(menu.getPid());
        }
        assertNotNull(result);
    }

}