package rbac.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rbac.common.enums.ExceptionEnum;
import rbac.common.exception.CustomException;
import rbac.utils.RandomTokenUtils;
import rbac.entity.Emp;
import rbac.entity.Role;
import rbac.mapper.RoleMapper;
import rbac.service.IEmpService;
import rbac.service.IRoleService;
import rbac.vo.PageParams;

/**
 * @author MiracloW
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private IEmpService empService;

    @Override
    public IPage<Role> getRoleListWithPage(PageParams pageParams, Role role) {
        Page<Role> pageinfo = new Page<>(pageParams.getPageNum(), pageParams.getPageSize());
        return baseMapper.rolePage(pageinfo, role);
    }

    @Override
    public IPage getRoleListByEmpId(Long empId, PageParams pageParams) {
        Emp emp = empService.getById(empId);
        if(emp == null)
            throw new CustomException(ExceptionEnum.USER_NOT_EXIST);

        Page<Role> pageinfo = new Page<>(pageParams.getPageNum(), pageParams.getPageSize());

        return baseMapper.getRoleListByEmpId(pageinfo, empId);
    }

    @Override
    public IPage getNotAddedRoleByEmpId(Long empId, PageParams pageParams) {
        Emp emp = empService.getById(empId);
        if(emp == null)
            throw new CustomException(ExceptionEnum.USER_NOT_EXIST);

        Page<Role> pageinfo = new Page<>(pageParams.getPageNum(), pageParams.getPageSize());

        return baseMapper.getNotAddedRoleByEmpId(pageinfo, empId);
    }

    @Transactional
    @Override
    public Boolean removeRoleById(Long roleId) {
        Role role = this.getById(roleId);
        if(role == null)
            throw new CustomException(ExceptionEnum.ROLE_NOT_EXIST);

        this.removeById(roleId);
        baseMapper.removeEmpRoleRelationshipByRoleId(roleId);
        baseMapper.removeRoleMenuRelationshipByRoleId(roleId);
        return true;
    }

    @Transactional
    @Override
    public Boolean saveRole(Role role) {
        Role currole = baseMapper.getRoleByRoleName(role.getRoleName());
        if(currole != null)
            throw new CustomException(ExceptionEnum.ROLE_EXIST);

        Role r = new Role();
        r.setRoleName(role.getRoleName());
        r.setRoleDesc(role.getRoleDesc());
        r.setRecToken(RandomTokenUtils.createRandomRecToken());
        return this.save(r);
    }

    @Transactional
    @Override
    public Boolean updateRoleByRoleId(Role role) {
        Role currole = baseMapper.getRoleById(role.getId());
        if(!currole.getRoleName().equals(role.getRoleName())){
            Role tmp = baseMapper.getRoleByRoleName(role.getRoleName());
            if(tmp != null)
                throw new CustomException(ExceptionEnum.ROLE_EXIST);
        }
        if(!currole.getRecToken().equals(role.getRecToken()))
            throw new CustomException(ExceptionEnum.ROLE_UPDATE_CONCURRENT_MODIFY_ERROR);

        Role r = new Role();
        r.setId(role.getId());
        r.setRoleName(role.getRoleName());
        r.setRoleDesc(role.getRoleDesc());
        r.setRecToken(RandomTokenUtils.updateRandomRecToken(role.getRecToken()));
        boolean isupdated =  this.updateById(r);
        currole = this.getById(r.getId());
        if(currole == null)
            throw new CustomException(ExceptionEnum.UPDATE_ERROR);
        return isupdated;
    }

    @Override
    public Role getRoleById(Long roleId) {
        Role role = this.getById(roleId);
        if(role == null)
            throw new CustomException(ExceptionEnum.ROLE_NOT_EXIST);
        return role;
    }
}
