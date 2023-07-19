package rbac.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rbac.common.enums.ExceptionEnum;
import rbac.common.exception.CustomException;
import rbac.utils.*;
import rbac.entity.Emp;
import rbac.entity.Role;
import rbac.mapper.EmpMapper;
import rbac.service.IEmpService;
import rbac.service.IRoleService;
import rbac.token.LoginUser;
import rbac.token.UserToken;
import rbac.vo.EmpLoginVo;
import rbac.vo.PageParams;
import rbac.vo.UserPasswordVo;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author MiracloW
 */
@Service
public class EmpServiceImpl extends ServiceImpl<EmpMapper, Emp> implements IEmpService {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public String login(EmpLoginVo empLoginVo) {
        String username = empLoginVo.getName();
        String password = RsaEncryptUtils.decrypt(empLoginVo.getPassword());

        // 认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        // 认证没通过
        if (Objects.isNull(authenticate)) {
            throw new CustomException(ExceptionEnum.USER_NOT_EXIST);
        }
        // 认证通过 生成jwt
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        Long userId = loginUser.getEmp().getEmpNo();
        String userName = loginUser.getEmp().getEmpName();
        UserToken userToken = new UserToken(userId, userName);
        String jwttoken = JWTUtils.createToken(userToken);
 
        // 认证通过 存入redis
        redisCache.setCacheObject(RedisConstants.LOGIN_USER_KEY + userId, loginUser);
        redisCache.expire(RedisConstants.LOGIN_USER_KEY + userId, RedisConstants.LOGIN_USER_TTL);
        return jwttoken;
    }

    @Transactional
    @Override
    public Boolean saveEmp(Emp emp) {
        Emp tmp = baseMapper.getUserByUsername(emp.getEmpName());
        if(tmp != null)
            throw new CustomException(ExceptionEnum.USER_EXIST);

        String rsapassword = RsaEncryptUtils.decrypt(emp.getPassword());
        String password = BCryptUtils.getInstance().encode(rsapassword);

        Emp e = new Emp();
        e.setEmpName(emp.getEmpName());
        e.setPassword(password);
        e.setDeptNo(emp.getDeptNo());
        e.setJob(emp.getJob());
        e.setPhone(emp.getPhone());
        e.setRecToken(RandomTokenUtils.createRandomRecToken());
        return this.save(e);
    }

    @Transactional
    @Override
    public Boolean updateEmpById(Emp emp) {
        Emp user = baseMapper.getEmpById(emp.getEmpNo());
        if(!user.getEmpName().equals(emp.getEmpName())){
            Emp tmp = baseMapper.getUserByUsername(emp.getEmpName());
            if(tmp != null)
                throw new CustomException(ExceptionEnum.USER_EXIST);
        }
        if(!emp.getRecToken().equals(user.getRecToken()))
            throw new CustomException(ExceptionEnum.USER_UPDATE_CONCURRENT_MODIFY_ERROR);

        String rsapassword = RsaEncryptUtils.decrypt(emp.getPassword());
        String password = BCryptUtils.getInstance().encode(rsapassword);

        Emp e = new Emp();
        e.setEmpNo(emp.getEmpNo());
        e.setEmpName(emp.getEmpName());
        e.setPassword(password);
        e.setDeptNo(emp.getDeptNo());
        e.setJob(emp.getJob());
        e.setPhone(emp.getPhone());
        e.setRecToken(RandomTokenUtils.updateRandomRecToken(emp.getRecToken()));
        boolean isupdated = this.updateById(e);

        user = this.getById(e.getEmpNo());
        if(user == null)
            throw new CustomException(ExceptionEnum.UPDATE_ERROR);
        return isupdated;
    }

    @Override
    public IPage<Emp> getEmpListWithPage(PageParams pageParams, Emp emp) {
        Page<Emp> page = new Page<>(pageParams.getPageNum(), pageParams.getPageSize());

        IPage<Emp> empIPage = baseMapper.empPage(page, emp);

        empIPage.setRecords(empIPage.getRecords().stream().map(item ->{
            item.setPassword(null);
            return item;
        }).collect(Collectors.toList()));

        return empIPage;
    }

    @Override
    public IPage<Emp> getEmpByRoleId(Long roleId, PageParams pageParams) {
        Role role = roleService.getById(roleId);
        if(role == null)
            throw new CustomException(ExceptionEnum.ROLE_NOT_EXIST);

        Page<Emp> page = new Page<>(pageParams.getPageNum(), pageParams.getPageSize());

        IPage<Emp> empIPage = baseMapper.getEmpByRoleId(page, roleId);

        empIPage.setRecords(empIPage.getRecords().stream().map(item ->{
            item.setPassword(null);
            return item;
        }).collect(Collectors.toList()));

        return empIPage;
    }

    @Override
    public IPage<Emp> getNotAddedEmpByRoleId(Long roleId, PageParams pageParams) {
        Role role = roleService.getById(roleId);
        if(role == null)
            throw new CustomException(ExceptionEnum.ROLE_NOT_EXIST);

        Page<Emp> page = new Page<>(pageParams.getPageNum(), pageParams.getPageSize());

        IPage<Emp> empIPage = baseMapper.getNotAddedEmpByRoleId(page, roleId);

        empIPage.setRecords(empIPage.getRecords().stream().map(item ->{
            item.setPassword(null);
            return item;
        }).collect(Collectors.toList()));

        return empIPage;
    }

    @Transactional
    @Override
    public Boolean removeEmpbyid(Long empId) {
        Emp emp = this.getById(empId);
        if(emp == null)
            throw new CustomException(ExceptionEnum.USER_NOT_EXIST);

        this.removeById(empId);
        baseMapper.removeRelationshipByEmpid(empId);
        return true;
    }

    @Override
    public Boolean updatepassword(UserPasswordVo userPasswordVo, Long empId) {
        String newPassword = RsaEncryptUtils.decrypt(userPasswordVo.getNewpassword());
        String oldPassword = RsaEncryptUtils.decrypt(userPasswordVo.getOripassword());

        if(newPassword.equals(oldPassword))
            throw new CustomException(ExceptionEnum.USER_PASSWORD_SAME);

        Emp emp = this.getById(empId);
        if(emp == null)
            throw new CustomException(ExceptionEnum.USER_NOT_EXIST);

        if(!BCryptUtils.getInstance().matches(oldPassword, emp.getPassword()))
            throw new CustomException(ExceptionEnum.USER_PASSWORD_WRONG);

        String password = BCryptUtils.getInstance().encode(newPassword);

        Emp e = new Emp();
        e.setEmpNo(emp.getEmpNo());
        e.setPassword(password);
        this.updateById(e);

        return true;
    }

    @Override
    public Emp getEmpById(Long id) {
        Emp emp = this.getById(id);
        if(emp == null)
            throw new CustomException(ExceptionEnum.USER_NOT_EXIST);

        emp.setPassword("123456");
        return emp;
    }

    @Override
    public Boolean logout() {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = loginUser.getEmp().getEmpNo();
        // 清空redis
        redisCache.deleteObject("login:" + userId);
        return true;
    }
}
