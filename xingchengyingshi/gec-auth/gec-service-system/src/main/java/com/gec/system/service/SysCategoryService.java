package com.gec.system.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gec.model.system.SysCategory;
import com.gec.model.vo.SysCategoryQueryVo;

/**
* @author l
* @description 针对表【sys_category】的数据库操作Service
* @createDate 2024-09-30 18:57:48
*/
public interface SysCategoryService extends IService<SysCategory> {

    IPage<SysCategory> selectPage(IPage<SysCategory> ipage, SysCategoryQueryVo sysCategoryQueryVo);
}

