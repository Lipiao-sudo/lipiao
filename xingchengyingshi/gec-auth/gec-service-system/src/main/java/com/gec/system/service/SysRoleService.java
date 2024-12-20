package com.gec.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gec.model.system.SysRole;
import com.gec.model.vo.AssginRoleVo;
import com.gec.model.vo.SysRoleQueryVo;

import java.util.Map;

public interface SysRoleService extends IService<SysRole> {
    IPage<SysRole> queryRolePage(IPage<SysRole> page, SysRoleQueryVo sysRoleQueryVo);

    Map<String, Object> getRoleByUserId(Long userId);

    void doAssign(AssginRoleVo assginRoleVo);
}

