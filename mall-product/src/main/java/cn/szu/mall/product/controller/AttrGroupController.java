package cn.szu.mall.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import cn.szu.mall.product.entity.AttrEntity;
import cn.szu.mall.product.entity.AttrGroupEntity;
import cn.szu.mall.product.service.AttrGroupService;
import cn.szu.mall.product.service.AttrService;
import cn.szu.mall.product.service.CategoryService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cn.szu.mall.common.utils.PageUtils;
import cn.szu.mall.common.utils.R;

import javax.annotation.Resource;


/**
 * 属性分组
 *
 * @author chenyajun
 * @email 554811048@qq.com
 * @date 2022-07-27 13:38:54
 */
@RestController
@RequestMapping("product/attrgroup")
public class AttrGroupController {
    @Autowired
    private AttrGroupService attrGroupService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private AttrService attrService;

    //查出分组没有关联的属性
    @GetMapping("/{groupId}/noattr/relation")
    public R listNoAttrByGroup(@PathVariable Long groupId,
                               @RequestParam Map<String, Object> params ){
        //c
        PageUtils page = attrService.getNoAttrRelation(groupId,params);
        return R.ok().put("paeg",page);
    }
    @GetMapping("/{groupId}/attr/relation")
    public R listAttrByGroup(@PathVariable Long groupId){
        //c
        List<AttrEntity> attrEntities = attrService.getAttrByGroupId(groupId);
        return R.ok().put("data",attrEntities);
    }
    /**
     * 列表
     */
    @RequestMapping("/list/{catelogId}")
    //@RequiresPermissions("product:attrgroup:list")
    public R list(@RequestParam Map<String, Object> params,
                  @PathVariable Long catelogId
                  ){
        //PageUtils page = attrGroupService.queryPage(params);
        PageUtils page = attrGroupService.queryPageByCatelogId(params,catelogId);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{attrGroupId}")
    @RequiresPermissions("product:attrgroup:info")
    public R info(@PathVariable("attrGroupId") Long attrGroupId){
		AttrGroupEntity attrGroup = attrGroupService.getById(attrGroupId);
        Long catelogId = attrGroup.getCatelogId();
        attrGroup.setCatelogPath(categoryService.findCatelogPath(catelogId));

        return R.ok().put("attrGroup", attrGroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:attrgroup:save")
    public R save(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.save(attrGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:attrgroup:update")
    public R update(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.updateById(attrGroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:attrgroup:delete")
    public R delete(@RequestBody Long[] attrGroupIds){
		attrGroupService.removeByIds(Arrays.asList(attrGroupIds));

        return R.ok();
    }

}
