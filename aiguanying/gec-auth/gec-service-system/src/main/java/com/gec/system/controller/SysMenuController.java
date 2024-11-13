package com.gec.system.controller;

import com.gec.model.system.SysMenu;
import com.gec.model.vo.AssginMenuVo;
import com.gec.system.service.SysMenuService;
import com.gec.system.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(tags = "菜单管理控制器")
@RestController
@RequestMapping("/admin/system/sysMenu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    //加载树形菜单
    @ApiOperation("菜单列表")
    @GetMapping("getMenuList")
    public Result getMenuList() {
        List<SysMenu> list = sysMenuService.findNodes();
        return Result.ok(list);
    }

    @ApiOperation("添加菜单")
    @PostMapping("addMenu")
    public Result addMenu(@RequestBody SysMenu sysMenu){
        boolean isOk = sysMenuService.save(sysMenu);
        return isOk?Result.ok():Result.fail();
    }

    @ApiOperation("根据id去查询菜单")
    // 根据id去查询菜单
    @GetMapping("/findMenuById/{id}")
    public Result getMenuById(@PathVariable Long id){
        SysMenu sysMenu = this.sysMenuService.getById(id);
        return Result.ok(sysMenu);
    }

    @ApiOperation("修改菜单")
    @PostMapping("updateMenu")
    public Result updateMenu(@RequestBody SysMenu sysMenu){
        boolean isOk = sysMenuService.updateById(sysMenu);
        return isOk?Result.ok():Result.fail();
    }

    @ApiOperation("删除菜单")
    @DeleteMapping("removeMenuById/{id}")
    public Result removeMenu(@PathVariable Long id){
        boolean isOk = sysMenuService.removeMenuById(id);
        return isOk?Result.ok():Result.fail();
    }

    @ApiOperation("根据角色获取菜单信息")
    @GetMapping("toAssign/{roleId}")
    public Result getMenuByRole(@PathVariable long roleId){
        List<SysMenu> list = this.sysMenuService.findSysMenuByRoleId(roleId);
        return Result.ok(list);
    }

    @ApiOperation("给角色分配权限")
    @PostMapping("doAssign")
    public Result doAssign(@RequestBody AssginMenuVo assginMenuVo){
        sysMenuService.doAssign(assginMenuVo);
        return Result.ok();
    }
}
