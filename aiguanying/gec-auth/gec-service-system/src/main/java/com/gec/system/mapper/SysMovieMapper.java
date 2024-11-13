package com.gec.system.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gec.model.system.SysMovie;
import com.gec.model.vo.SysMovieQueryVo;
import org.apache.ibatis.annotations.Param;

/**
* @author l
* @description 针对表【sys_movie】的数据库操作Mapper
* @createDate 2024-09-30 18:57:48
* @Entity com.gec.system.system.SysMovie
*/
public interface SysMovieMapper extends BaseMapper<SysMovie> {
    IPage<SysMovie> selectPage(IPage<SysMovie> page,@Param("vo") SysMovieQueryVo sysMovieQueryVo);
}




