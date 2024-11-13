package com.gec.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.gec.model.system.SysUser;
import com.gec.model.vo.RouterVo;
import com.gec.model.vo.SysUserQueryVo;
import com.gec.system.mapper.SysUserMapper;
import com.gec.system.service.SysMenuService;
import com.gec.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author l
* @description 针对表【sys_user(用户表)】的数据库操作Service实现
* @createDate 2024-09-24 16:26:30
*/
@Service("SysUserService")
@Transactional
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>implements SysUserService {

    @Autowired
    private SysMenuService sysMenuService;


    @Override
    public IPage<SysUser> queryUserPage(IPage<SysUser> page1, SysUserQueryVo sysUserQueryVo) {
        //调用自己定义的方法selectUserByPage
        return this.baseMapper.selectUserByPage(page1, sysUserQueryVo);
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        //1.查询出当前用户
        SysUser sysUser = this.baseMapper.selectById(id);
        //2.设置状态
        sysUser.setStatus(status);
        //3.更新
        this.baseMapper.updateById(sysUser);
    }

    @Override
    public SysUser getUserInfoUserName(String username) {
        QueryWrapper<SysUser> qw = new QueryWrapper<>();
        qw.eq("username", username);
        SysUser sysUser = this.baseMapper.selectOne(qw);
        return sysUser;
    }

    @Override
    public Map<String, Object> getUserInfo(String username) {
        SysUser sysUser = getUserInfoUserName(username);

        //根据用户id得到菜单相关的数据
        List<RouterVo> routerVoList = this.sysMenuService.getUserMenuList(sysUser.getId());
        //根据用户id获取用户按钮权限
        List<String> permsList = sysMenuService.findUserPermsList(sysUser.getId());

        Map<String, Object> map = new HashMap<>();
        map.put("name", sysUser.getName());
        map.put("avatar", "https://img.zcool.cn/community/0111b4559cbdd96ac7257aea76a5ea.gif");
        map.put("roles",  "[admin]");

        map.put("buttons", permsList);
        map.put("routers", routerVoList);
        return map;
    }

}




