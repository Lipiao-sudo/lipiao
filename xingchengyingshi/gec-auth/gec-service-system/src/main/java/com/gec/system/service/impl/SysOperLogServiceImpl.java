package com.gec.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gec.model.system.SysOperLog;
import com.gec.system.service.SysOperLogService;
import com.gec.system.mapper.SysOperLogMapper;
import org.springframework.stereotype.Service;

/**
* @author l
* @description 针对表【sys_oper_log(操作日志记录)】的数据库操作Service实现
* @createDate 2024-10-08 19:55:54
*/
@Service
public class SysOperLogServiceImpl extends ServiceImpl<SysOperLogMapper, SysOperLog>
    implements SysOperLogService{

}




