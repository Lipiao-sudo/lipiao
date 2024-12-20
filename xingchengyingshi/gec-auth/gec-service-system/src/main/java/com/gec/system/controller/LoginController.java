package com.gec.system.controller;

import com.gec.model.system.SysUser;
import com.gec.model.vo.LoginVo;
import com.gec.system.exception.MyCustomerException;

import com.gec.system.service.SysLoginLogService;
import com.gec.system.service.SysUserService;
import com.gec.system.util.JwtHelper;
import com.gec.system.util.MD5Helper;
import com.gec.system.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@Api(tags = "登录管理控制器")
@RequestMapping(value = "/admin/system/index")
public class LoginController {

    @Autowired
    private SysLoginLogService  sysLoginLogService;

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation("用户登录")
    @PostMapping("login")
    public Result dologin(@RequestBody LoginVo loginVo){

        SysUser sysUser = sysUserService.getUserInfoUserName(loginVo.getUsername());

        //进行用户的非空判断
        if (sysUser==null){
            throw  new MyCustomerException(20001,"用户不存在..");
        }

        //从数据库中得到密码
        String pwd_db = sysUser.getPassword();
        //把客户端传递过来的密码进行加密处理
        String pwd_md5 = MD5Helper.encrypt(loginVo.getPassword());
        if(pwd_db.equals(pwd_md5)){
            throw new MyCustomerException(20001,"密码不正确");
        }

        //判断当前用户状态是否是停用状态：0
        if(sysUser.getStatus() == 0){
            throw new MyCustomerException(20001,"账号被停用");
        }

        //把用户id和用户名加工生成token
        String token = JwtHelper.createToken(sysUser.getId().toString(),sysUser.getUsername());
        //必须要把token转换成前端需要的数据格式
        Map<String,Object> map = new HashMap<>();
        map.put("token",token);
        return Result.ok(map);
    }

    @ApiOperation("获得info信息")
    @GetMapping("info")
    public Result getInfo(HttpServletRequest request){
        //通过请求中获得token
        String token = request.getHeader("token");
        if(token==null){
            System.out.println("token为空!!");
        }
        //通过token获得用户名
        String username = JwtHelper.getUsername(token);
        if(username==null){
            System.out.println("username用户名为空!!");
        }
        // c.根据用户名称获取用户信息 （a.基本信息  b.菜单权限信息  和 c.按钮权限信息）
        Map<String,Object> map = this.sysUserService.getUserInfo(username);
        return Result.ok(map);
    }



    //退出
    @PostMapping("/logout")
    public Result logout(){
        return Result.ok();
    }
}
