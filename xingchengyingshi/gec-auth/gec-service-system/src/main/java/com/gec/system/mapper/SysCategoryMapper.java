package com.gec.system.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gec.model.system.SysCategory;
import com.gec.model.vo.SysCategoryQueryVo;
import org.apache.ibatis.annotations.Param;

/**
* @author l
* @description 针对表【sys_category】的数据库操作Mapper
* @createDate 2024-09-30 18:57:48
* @Entity com.gec.system.system.SysCategory
*/
public interface SysCategoryMapper extends BaseMapper<SysCategory> {
    IPage<SysCategory> selectPage(IPage<SysCategory> ipage,@Param("vo") SysCategoryQueryVo sysCategoryQueryVo);
}




