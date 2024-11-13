package com.gec.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gec.model.system.SysMenu;

import com.gec.model.system.SysRole;
import com.gec.model.system.SysRoleMenu;
import com.gec.model.vo.AssginMenuVo;
import com.gec.model.vo.RouterVo;
import com.gec.system.exception.MyCustomerException;
import com.gec.system.mapper.SysMenuMapper;
import com.gec.system.mapper.SysRoleMenuMapper;
import com.gec.system.service.SysMenuService;
import com.gec.system.utils.MenuHelper;
import com.gec.system.utils.RouterHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
* @author l
* @description 针对表【sys_menu(菜单表)】的数据库操作Service实现
* @createDate 2024-09-26 19:18:32
*/
@Service
@Transactional
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu>implements SysMenuService {

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public List<SysMenu> findNodes() {
        //获取所有菜单信息
        List<SysMenu> sysMenus = this.baseMapper.selectList(null);

        //把菜单数据转换成树形的数据格式
        List<SysMenu> buildTree = MenuHelper.buildTree(sysMenus);
        return buildTree;
    }

    @Override
    public boolean removeMenuById(Long id) {
        //先根据要删除的菜单id中查询看它是否有子菜单，因为有子菜单就不能直接删除
        QueryWrapper<SysMenu> qw = new QueryWrapper<>();
        qw.eq("parent_id", id);

        //如果返回的条数大于0说明有子菜单
        Integer count = this.baseMapper.selectCount(qw);
        if (count > 0) {
            //有子菜单，不能直接删除，直接抛出一个异常
            throw new MyCustomerException(201,"请先删除子菜单");
        }
        //调用删除
        int iret = this.baseMapper.deleteById(id);

        return iret > 0?true:false;

    }

    @Override
    public List<SysMenu> findSysMenuByRoleId(long roleId) {
        QueryWrapper<SysMenu> qw = new QueryWrapper<>();
        qw.eq("status", 1);
        List<SysMenu> menus = this.baseMapper.selectList(qw);

        QueryWrapper<SysRoleMenu> qw2 = new QueryWrapper<>();
        qw2.eq("role_id", roleId);
        List<SysRoleMenu> roleMenus = this.sysRoleMenuMapper.selectList(qw2);

        //获得当前角色已经具有的旧的菜单信息
        List<Long> roleMenuIds = new ArrayList<>();
        for (SysRoleMenu roleMenu : roleMenus) {
            roleMenuIds.add(roleMenu.getMenuId());
        }

        //遍历所有菜单列表
        for (SysMenu menu : menus) {
            if (roleMenuIds.contains(menu.getId())) {
                menu.setSelect(true); //已经分配过该权限了
            }else{
                menu.setSelect(false);
            }
        }

        //最后将修改后的菜单列表转化成树形结构
        List<SysMenu> sysMenus = MenuHelper.buildTree(menus);

        return sysMenus;
    }

    @Override
    public void doAssign(AssginMenuVo assginMenuVo) {
        //先删除旧的权限数据
        QueryWrapper<SysRoleMenu> delQw = new QueryWrapper<>();
        delQw.eq("role_id", assginMenuVo.getRoleId());

        this.sysRoleMenuMapper.delete(delQw);

        //添加新的权限数据到数据库中
        //遍历已经选中的权限信息
        for (Long menuId : assginMenuVo.getMenuIdList()) {
            if(menuId!=null){
                SysRoleMenu roleMenu = new SysRoleMenu();
                roleMenu.setMenuId(menuId);
                roleMenu.setRoleId(assginMenuVo.getRoleId());
                this.sysRoleMenuMapper.insert(roleMenu);
            }
        }
    }

    @Override
    public List<RouterVo> getUserMenuList(Long userId) {
        //超级管理员admin账号id为：1
        // 我们约定 admin 是 超级管理员拥有所有的权限
        List<SysMenu> sysMenuList = null;

        if (userId.longValue() == 1) {
            //a.  表示是超级管理员
            sysMenuList = baseMapper.selectList(new QueryWrapper<SysMenu>().eq("status", 1).orderByAsc("sort_value"));
        } else {

            // b. 其他非超级管理员的 用户
            sysMenuList = baseMapper.findMenuListByUserId(userId);
        }



        //c.构建树形数据
        List<SysMenu> sysMenuTreeList = MenuHelper.buildTree(sysMenuList);

        //TODO  MeunHelper返回的数据属性和 前端路由的属性不一致，所以要处理
        //d.构建路由
        List<RouterVo> routerVoList = RouterHelper.buildRouters(sysMenuTreeList);

        return routerVoList;
    }

    @Override
    public List<String> findUserPermsList(Long id) {
        //超级管理员admin账号id为：1
        List<SysMenu> sysMenuList = null;
        if (id.longValue() == 1) {
            sysMenuList = this.baseMapper.selectList(new QueryWrapper<SysMenu>().eq("status", 1));
        } else {
            sysMenuList = this.baseMapper.findMenuListByUserId(id);
        }
        //创建返回的集合
        List<String> permissionList = new ArrayList<>();
        for (SysMenu sysMenu : sysMenuList) {
            if(sysMenu.getType() == 2){
                permissionList.add(sysMenu.getPerms());
            }
        }
        return permissionList;
    }
}




