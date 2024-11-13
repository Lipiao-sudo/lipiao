package com.gec.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.gec.model.system.SysMenu;
import com.gec.model.vo.AssginMenuVo;
import com.gec.model.vo.RouterVo;

import java.util.List;


/**
* @author l
* @description 针对表【sys_menu(菜单表)】的数据库操作Service
* @createDate 2024-09-26 19:18:32
*/
public interface SysMenuService extends IService<SysMenu> {
    //加载菜单列表
    List<SysMenu> findNodes();

    boolean removeMenuById(Long id);

    List<SysMenu> findSysMenuByRoleId(long roleId);

    void doAssign(AssginMenuVo assginMenuVo);

    List<RouterVo> getUserMenuList(Long id);

    List<String> findUserPermsList(Long id);

}


