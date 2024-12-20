package com.gec.system.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gec.model.system.SysMovie;
import com.gec.model.vo.SysMovieQueryVo;
import com.gec.system.service.SysMovieService;
import com.gec.system.mapper.SysMovieMapper;
import org.springframework.stereotype.Service;

/**
* @author l
* @description 针对表【sys_movie】的数据库操作Service实现
* @createDate 2024-09-30 18:57:48
*/
@Service
public class SysMovieServiceImpl extends ServiceImpl<SysMovieMapper, SysMovie>
    implements SysMovieService{

    @Override
    public IPage<SysMovie> selectPage(IPage<SysMovie> p1, SysMovieQueryVo sysMovieQueryVo) {
        return this.baseMapper.selectPage(p1,sysMovieQueryVo);
    }
}




