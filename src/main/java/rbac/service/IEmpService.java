package rbac.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import rbac.entity.Emp;
import rbac.vo.EmpLoginVo;
import rbac.vo.PageParams;
import rbac.vo.UserPasswordVo;

/**
 * @author MiracloW
 */
public interface IEmpService extends IService<Emp> {

    String login(EmpLoginVo empLoginVo);

    Boolean saveEmp(Emp emp);

    Boolean updateEmpById(Emp emp);

    IPage<Emp> getEmpListWithPage(PageParams pageParams, Emp emp);

    IPage<Emp> getEmpByRoleId(Long roleId, PageParams pageParams);

    IPage<Emp> getNotAddedEmpByRoleId(Long roleId, PageParams pageParams);

    Boolean removeEmpbyid(Long empid);

    Boolean updatepassword(UserPasswordVo userPasswordVo, Long empId);

    Emp getEmpById(Long id);

    Boolean logout();
}
