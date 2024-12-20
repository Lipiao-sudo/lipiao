package com.gec.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gec.model.system.SysRole;
import com.gec.model.vo.AssginRoleVo;
import com.gec.model.vo.SysRoleQueryVo;
import com.gec.system.service.SysRoleService;
import com.gec.system.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Api(tags = "角色管理控制器")
@RestController
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    // http://localhost:8080/admin/system/sysRole/findAll

    // http://localhost:8080/admin/system/sysRole/findAll

    // 查询全部记录
    @ApiOperation("查询所有角色信息")
    @GetMapping("/findAll")
    public Result getRoles(){
        List<SysRole> list = sysRoleService.list();
        return Result.ok(list);
    }

    @PreAuthorize("hasAuthority('bnt.sysRole.remove')") // 根据id 去逻辑删除
    @ApiOperation("逻辑删除接口")
    @DeleteMapping("/remove/{id}")
    public Result removeById(@PathVariable Long id)
    {
        boolean isDel = this.sysRoleService.removeById(id);
        return isDel?Result.ok():Result.fail();
    }


    //分页查询的方法
    @PreAuthorize("hasAuthority('bnt.sysRole.list')")
    @ApiOperation("角色的分页查询")
    @GetMapping("/{pageIndex}/{pageSize}")
    public Result querySysRoleByPage(@ApiParam(name = "pageIndex",value = "当前页码",required = true) @PathVariable Long pageIndex,
                                     @ApiParam(name = "pageSize",value = "每页记录数",required = true) @PathVariable Long pageSize,
                                     @ApiParam(name = "SysRoleQueryVo",value = "查询对象",required = false) SysRoleQueryVo sysRoleQueryVo){
        //封装分页的条件
        IPage<SysRole> page1 = new Page<>(pageIndex,pageSize);
        //分页查询之后的结果
        page1 = sysRoleService.queryRolePage(page1,sysRoleQueryVo);
        return Result.ok(page1);
    }


    @PreAuthorize("hasAuthority('bnt.sysRole.add')")
    @ApiOperation("添加角色")
    @PostMapping("addRole")
    public Result addRole(@RequestBody SysRole sysRole){//@RequestBody:表示前端发送过来的参数是一个json格式的数据
        boolean isOk = this.sysRoleService.save(sysRole);
        return isOk?Result.ok():Result.fail();
    }

    @PreAuthorize("hasAuthority('bnt.sysRole.list')")
    @ApiOperation("根据id查询角色")
    @GetMapping("findSysRoleById/{id}")
     public Result findRoleById(@PathVariable Long id){
        SysRole role = this.sysRoleService.getById(id);
        return Result.ok(role);
     }

    @PreAuthorize("hasAuthority('bnt.sysRole.update')")
    @ApiOperation("修改角色")
    @PostMapping("updateRole")
     public Result updateRole(@RequestBody SysRole sysRole){
        boolean isOk = this.sysRoleService.updateById(sysRole);
        return isOk?Result.ok():Result.fail();
     }

     @PreAuthorize("hasAuthority('bnt.sysRole.remove')")
     @ApiOperation("批量删除角色")
     @DeleteMapping("batchRemove")
     public Result batchRemove(@RequestBody List<Long> ids){
         boolean isDel = this.sysRoleService.removeByIds(ids);
         return isDel?Result.ok():Result.fail();
     }

     @ApiOperation("根据用户获取角色数据")
     @GetMapping("toAssign/{userId}")
     public Result getAssignById(@PathVariable Long userId){
         Map<String,Object> roleMap = sysRoleService.getRoleByUserId(userId);
         return Result.ok(roleMap);
     }

     @ApiOperation("根据用户分配角色")
     @PostMapping("doAssign")
     public Result toAssign(@RequestBody AssginRoleVo assginRoleVo){
        this.sysRoleService.doAssign(assginRoleVo);
        return Result.ok();
     }
}