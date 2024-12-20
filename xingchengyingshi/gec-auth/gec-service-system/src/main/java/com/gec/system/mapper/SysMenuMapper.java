package com.gec.system.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gec.model.system.SysMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author l
* @description 针对表【sys_menu(菜单表)】的数据库操作Mapper
* @createDate 2024-09-26 19:18:32
* @Entity com.gec.system.system.SysMenu
*/
@Repository
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<SysMenu> findMenuListByUserId(@Param("userId") Long userId);
}




