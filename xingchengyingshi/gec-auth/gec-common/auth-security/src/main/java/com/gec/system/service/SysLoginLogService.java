package com.gec.system.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gec.model.system.SysLoginLog;
import com.gec.model.vo.SysLoginLogQueryVo;

/**
* @author l
* @description 针对表【sys_login_log(系统访问记录)】的数据库操作Service
* @createDate 2024-10-08 20:03:37
*/
public interface SysLoginLogService extends IService<SysLoginLog> {

    /**
     *
     * @param username
     * @param status
     * @param ipaddr
     * @param message
     */
    public void recordLoginLog(String username,Integer status,String ipaddr,String message);
    //条件分页查询登录日志
    IPage<SysLoginLog> selectPage(long page, long limit, SysLoginLogQueryVo sysLoginLogQueryVo);

    SysLoginLog getById(Long id);


}
