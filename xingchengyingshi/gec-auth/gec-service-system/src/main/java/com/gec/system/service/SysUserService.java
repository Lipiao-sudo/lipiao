package com.gec.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gec.model.system.SysUser;
import com.gec.model.vo.SysUserQueryVo;

import java.util.Map;

/**
* @author l
* @description 针对表【sys_user(用户表)】的数据库操作Service
* @createDate 2024-09-24 16:26:30
*/
public interface SysUserService extends IService<SysUser> {

    IPage<SysUser> queryUserPage(IPage<SysUser> page1, SysUserQueryVo sysUserQueryVo);

    void updateStatus(Long id, Integer status);

    SysUser getUserInfoUserName(String username);

    Map<String, Object> getUserInfo(String username);


}





