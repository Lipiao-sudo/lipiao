package com.gec.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gec.model.system.SysRole;
import com.gec.model.system.SysUserRole;
import com.gec.model.vo.AssginRoleVo;
import com.gec.model.vo.SysRoleQueryVo;
import com.gec.system.mapper.SysRoleMapper;
import com.gec.system.mapper.SysUserRoleMapper;
import com.gec.system.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public IPage<SysRole> queryRolePage(IPage<SysRole> page, SysRoleQueryVo sysRoleQueryVo) {
        //调用自己定义的方法selectRoleByPage
        return this.baseMapper.selectRoleByPage(page, sysRoleQueryVo);
    }

    @Override
    public Map<String, Object> getRoleByUserId(Long userId) {
        List<SysRole> roles = this.baseMapper.selectList(null);

        //根据用户id查询
        QueryWrapper qw = new QueryWrapper();
        qw.eq("user_id", userId);
        //获取用户已分配的角色
        List<SysUserRole> userRoles = sysUserRoleMapper.selectList(qw);
        //存储当前用户对应的角色id的值，一个用户可能会有多个角色
        List<Long> roleIds = new ArrayList<>();
        for (SysUserRole userRole : userRoles) {
            roleIds.add(userRole.getRoleId());
        }

        //封装成一个map集合
        Map<String, Object> map = new HashMap<>();
        map.put("allRoles", roles);
        map.put("userRoleIds",roleIds);
        return map;
    }

    //给用户分配新角色的方法
    @Override
    public void doAssign(AssginRoleVo assginRoleVo) {
        //根据id删除掉该用户原来的角色信息
        QueryWrapper<SysUserRole> qw = new QueryWrapper<>();
        qw.eq("user_id", assginRoleVo.getUserId());
        sysUserRoleMapper.delete(qw);

        //从客户端表单中获得新的角色数据再插入到数据库当中
        List<Long> roleIdList = assginRoleVo.getRoleIdList();
        //遍历
        for (Long roleId : roleIdList) {
            if(roleId!=null){
                SysUserRole sysUserRole = new SysUserRole();
                //封装要用的数据
                sysUserRole.setUserId(assginRoleVo.getUserId());
                sysUserRole.setRoleId(roleId);
                //把封装好的数据插入到数据库
                sysUserRoleMapper.insert(sysUserRole);
            }
        }
    }
}
