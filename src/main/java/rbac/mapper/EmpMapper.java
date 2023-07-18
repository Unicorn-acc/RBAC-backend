package rbac.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import rbac.entity.Emp;

/**
 * @author MiracloW
 */
public interface EmpMapper extends BaseMapper<Emp> {

    IPage<Emp> empPage(@Param("page") Page<Emp> page, @Param("param") Emp emp);

    IPage<Emp> getEmpByRoleId(@Param("page") Page<Emp> page, @Param("roleId") Long roleId);

    IPage<Emp> getNotAddedEmpByRoleId(@Param("page") Page<Emp> page, @Param("roleId") Long roleId);

    Emp getUserByUsername(String username);

    Integer removeRelationshipByEmpid(Long id);

    Emp getEmpById(Long id);
} 
