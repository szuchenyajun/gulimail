package cn.szu.mall.product.service.impl;

import cn.szu.mall.product.dao.CategoryDao;
import cn.szu.mall.product.entity.CategoryEntity;
import cn.szu.mall.product.service.CategoryBrandRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.szu.mall.common.utils.PageUtils;
import cn.szu.mall.common.utils.Query;

import cn.szu.mall.product.service.CategoryService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Autowired
    CategoryDao categoryDao;
    @Resource
    CategoryBrandRelationService relationService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    //查出所有分类 并组装父子结构  long型比较 xx.longValue
    @Override
    public List<CategoryEntity> listWithTree() {
        List<CategoryEntity> categoryEntities = baseMapper.selectList(null);
        //组装父子结构
        List<CategoryEntity> allCategories = categoryEntities.stream().filter(categoryEntity -> categoryEntity.getParentCid() == 0)
                .map(categoryEntity -> {
                    categoryEntity.setChildrenCategory(getChildrenCategory(categoryEntity, categoryEntities));
                    return categoryEntity;
                }).sorted(Comparator.comparingInt(c -> (c.getSort() == null ? 0 : c.getSort())))
                .collect(Collectors.toList());
        return allCategories;
    }

    @Override
    public void removeMenuByIdsLogic(List<Long> ids) {
        //TODO 真正的应该检查其他没有引用这个项 才能删除
        baseMapper.deleteBatchIds(ids);
    }

    @Override
    public Long[] findCatelogPath(Long catelogId) {
        ArrayList<Long> ids = new ArrayList<>();
        //ids.add(catelogId);
        CategoryEntity categoryEntity = getById(catelogId);
        while(categoryEntity != null){
            ids.add(categoryEntity.getCatId());
            categoryEntity = getById(categoryEntity.getParentCid());
        }
        Collections.reverse(ids);
        return ids.toArray(new Long[0]);
    }

    @Transactional
    @Override
    public void updateDetail(CategoryEntity categoryEntity) {
        updateById(categoryEntity);
        //更新中间表
        relationService.updateCategory(categoryEntity);
    }

    // 查出子分类
    private List<CategoryEntity> getChildrenCategory(CategoryEntity categoryEntity, List<CategoryEntity> categoryEntities) {
        List<CategoryEntity> childrenCategories = categoryEntities.stream().filter(categoryEntity1 -> categoryEntity1.getParentCid().longValue() == categoryEntity.getCatId())
                .map(category -> {
                    category.setChildrenCategory(getChildrenCategory(category, categoryEntities));
                    return category;
                }).sorted(Comparator.comparingInt(c -> (c.getSort() == null ? 0 : c.getSort())))
                .collect(Collectors.toList());
        return childrenCategories;
    }

}