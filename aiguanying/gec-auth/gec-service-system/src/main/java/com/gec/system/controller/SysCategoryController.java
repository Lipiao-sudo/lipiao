package com.gec.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gec.model.system.SysCategory;
import com.gec.model.vo.SysCategoryQueryVo;
import com.gec.system.service.SysCategoryService;
import com.gec.system.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(tags = "影视分类管理")
@RestController
@RequestMapping("/admin/system/sysCategory")
public class SysCategoryController {

    @Autowired
    private SysCategoryService sysCategoryService;

    @ApiOperation("获得所有影视分类")
    @GetMapping("findAll")
    public Result getAllCategory() {
        List<SysCategory> list = this.sysCategoryService.list();
        return Result.ok(list);
    }

    @ApiOperation("根据分类id删除分类信息")
    @DeleteMapping("removeCategory/{id}")
    public Result removeCategoryById(@PathVariable Long id) {
        boolean isDel = this.sysCategoryService.removeById(id);
        return isDel?Result.ok():Result.fail();
    }


    @ApiOperation("分页查询")
    @GetMapping("/{page}/{limit}")
    public Result findCategoryByPageQuery(
            @PathVariable Long page,
            @PathVariable Long limit,
            SysCategoryQueryVo sysCategoryQueryVo){

        IPage<SysCategory> ipage =new Page<>(page,limit);

        ipage = this.sysCategoryService.selectPage(ipage,sysCategoryQueryVo);

        return Result.ok(ipage);
    }

    @ApiOperation("添加影视分类")
    @PostMapping("addCategory")
    public Result addCategory(@RequestBody SysCategory sysCategory) {
        boolean isOk = this.sysCategoryService.save(sysCategory);
        return isOk?Result.ok():Result.fail();
    }

    @ApiOperation("根据id查找分类")
    @GetMapping("findCategoryById/{id}")
    public Result findCategoryById(@PathVariable Long id) {
        SysCategory Category = this.sysCategoryService.getById(id);
        return Result.ok(Category);
    }

    @ApiOperation("修改影视分类")
    @PostMapping("updateCategory")
    public  Result updateCategory(@RequestBody SysCategory sysCategory) {
        boolean isOK = this.sysCategoryService.updateById(sysCategory);
        return isOK?Result.ok():Result.fail();
    }

    @ApiOperation("批量删除影视分类")
    @DeleteMapping("removeCategoryByIds")
    public Result removeCategoryByIds(@RequestBody List<Long> ids) {
        boolean isDel = this.sysCategoryService.removeByIds(ids);
        return isDel?Result.ok():Result.fail();
    }
}


