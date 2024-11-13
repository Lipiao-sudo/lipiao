package com.gec.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gec.model.system.SysUser;
import com.gec.model.vo.SysUserQueryVo;
import com.gec.system.service.SysUserService;
import com.gec.system.util.MD5Helper;
import com.gec.system.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "用户管理控制器")
@RestController
@RequestMapping("/admin/system/sysUser")

public class SysUserController {

    @Autowired
    private SysUserService sysUserService;


    //分页查询的方法
    @ApiOperation("用户的分页查询")
    @GetMapping("/{pageIndex}/{pageSize}")
    public Result querySysRoleByPage(@ApiParam(name = "pageIndex",value = "当前页码",required = true) @PathVariable Long pageIndex,
                                     @ApiParam(name = "pageSize",value = "每页记录数",required = true) @PathVariable Long pageSize,
                                      SysUserQueryVo sysUserQueryVo){
        //封装分页的条件
        IPage<SysUser> page1 = new Page<>(pageIndex,pageSize);
        //分页查询之后的结果
        page1 = sysUserService.queryUserPage(page1,sysUserQueryVo);
        return Result.ok(page1);
    }


    // 根据id 去逻辑删除
    @ApiOperation("逻辑删除接口")
    @DeleteMapping("/remove/{id}")
    public Result removeById(@PathVariable Long id)
    {
        boolean isDel = this.sysUserService.removeById(id);
        return isDel?Result.ok():Result.fail();
    }


    @ApiOperation("添加用户")
    @PostMapping("addUser")
    public Result addUser(@RequestBody SysUser sysUser){
        //获得密码
        String pwd = sysUser.getPassword();
        //对密码进行加密
        String password_md5 = MD5Helper.encrypt(pwd);
        //把密码封装回去
        sysUser.setPassword(password_md5);

        boolean isOk = this.sysUserService.save(sysUser);
        return isOk?Result.ok():Result.fail();
    }

    @ApiOperation("根据id查询用户")
    @GetMapping("findSysUserById/{id}")
    public Result findUserById(@PathVariable Long id){
        SysUser user = this.sysUserService.getById(id);
        return Result.ok(user);
    }

    @ApiOperation("修改用户")
    @PostMapping("updateUser")
    public Result updateUser(@RequestBody SysUser sysUser){
        boolean isOk = this.sysUserService.updateById(sysUser);
        return isOk?Result.ok():Result.fail();
    }

    // 更改用户状态
    @ApiOperation("更改用户状态")
    @GetMapping("/updateStatus/{id}/{status}")
    public Result updateStatus(@PathVariable Long id,
                               @PathVariable Integer status)
    {
        this.sysUserService.updateStatus(id,status);
        return Result.ok();
    }
}
