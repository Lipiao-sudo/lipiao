package com.gec.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gec.model.system.SysUser;
import com.gec.model.vo.SysUserQueryVo;
import org.apache.ibatis.annotations.Param;

/**
* @author l
* @description 针对表【sys_user(用户表)】的数据库操作Mapper
* @createDate 2024-09-24 16:26:30
* @Entity com.gec.model.system.SysUser
*/
public interface SysUserMapper extends BaseMapper<SysUser> {

    IPage<SysUser> selectUserByPage(IPage<SysUser> page1,@Param("vo") SysUserQueryVo sysUserQueryVo);
}





