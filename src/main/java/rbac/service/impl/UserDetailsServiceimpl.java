package rbac.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rbac.common.enums.ExceptionEnum;
import rbac.common.exception.CustomException;
import rbac.entity.Emp;
import rbac.mapper.EmpMapper;
import rbac.service.IAuthService;
import rbac.token.LoginUser;

import java.util.List;
import java.util.Objects;

/**
 * @author MiracloW
 */
@Service
public class UserDetailsServiceimpl implements UserDetailsService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private IAuthService authService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 查询用户信息
        LambdaQueryWrapper<Emp> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Emp::getEmpName, username);
        Emp emp = empMapper.selectOne(queryWrapper);
        if(Objects.isNull(emp))
            throw new CustomException(ExceptionEnum.USER_NOT_EXIST);

        // 查询对应的权限信息
        List<String> list = authService.getPermissionsByEmpId(emp.getEmpNo());

        // 把数据封装成UserDetails对象返回
        return new LoginUser(emp, list);
    }
}
