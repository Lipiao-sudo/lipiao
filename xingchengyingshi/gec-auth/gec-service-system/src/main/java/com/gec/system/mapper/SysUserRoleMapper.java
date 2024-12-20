package com.gec.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gec.model.system.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

/**
* @author l
* @description 针对表【sys_user_role(用户角色)】的数据库操作Mapper
* @createDate 2024-09-25 19:47:27
* @Entity com.gec.system.system.SysUserRole
*/

@Repository
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

}




