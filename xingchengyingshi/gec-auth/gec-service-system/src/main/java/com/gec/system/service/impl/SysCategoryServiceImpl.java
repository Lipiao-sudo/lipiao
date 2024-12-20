package com.gec.system.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gec.model.system.SysCategory;
import com.gec.model.vo.SysCategoryQueryVo;
import com.gec.system.service.SysCategoryService;
import com.gec.system.mapper.SysCategoryMapper;
import org.springframework.stereotype.Service;

/**
* @author l
* @description 针对表【sys_category】的数据库操作Service实现
* @createDate 2024-09-30 18:57:48
*/
@Service
public class SysCategoryServiceImpl extends ServiceImpl<SysCategoryMapper, SysCategory>
    implements SysCategoryService{

    @Override
    public IPage<SysCategory> selectPage(IPage<SysCategory> ipage, SysCategoryQueryVo sysCategoryQueryVo) {
        return this.baseMapper.selectPage(ipage,sysCategoryQueryVo);
    }
}




