package cn.szu.mall.product.service.impl;

import cn.szu.mall.product.dao.BrandDao;
import cn.szu.mall.product.dao.CategoryBrandRelationDao;
import cn.szu.mall.product.dao.CategoryDao;
import cn.szu.mall.product.entity.BrandEntity;
import cn.szu.mall.product.entity.CategoryBrandRelationEntity;
import cn.szu.mall.product.entity.CategoryEntity;
import cn.szu.mall.product.service.CategoryService;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.szu.mall.common.utils.PageUtils;
import cn.szu.mall.common.utils.Query;

import cn.szu.mall.product.service.CategoryBrandRelationService;

import javax.annotation.Resource;


@Service("categoryBrandRelationService")
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationDao, CategoryBrandRelationEntity> implements CategoryBrandRelationService {

    @Resource
    CategoryService categoryService;

    @Resource
    CategoryDao categoryDao;
    @Resource
    BrandDao brandDao;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        IPage<CategoryBrandRelationEntity> page = this.page(
                new Query<CategoryBrandRelationEntity>().getPage(params),
                new QueryWrapper<CategoryBrandRelationEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryBrandRelationEntity> queryCategoryByBrandId(Long brandId) {

        QueryWrapper<CategoryBrandRelationEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("brand_id",brandId);
        List<CategoryBrandRelationEntity> brandRelationEntities = list(wrapper);

        return brandRelationEntities;
    }

    @Override
    public void saveCategoryBrand(CategoryBrandRelationEntity categoryBrandRelation) {
        Long catelogId = categoryBrandRelation.getCatelogId();
        Long brandId = categoryBrandRelation.getBrandId();
        String cateName = categoryDao.selectById(catelogId).getName();
        String brandName = brandDao.selectById(brandId).getName();
        categoryBrandRelation.setCatelogName(cateName);
        categoryBrandRelation.setBrandName(brandName);
        save(categoryBrandRelation);
    }

    @Override
    public void updateBrand(BrandEntity brandEntity) {
        //更新 品牌名字
        CategoryBrandRelationEntity relation = new CategoryBrandRelationEntity();
        relation.setBrandName(brandEntity.getName());
        relation.setBrandId(brandEntity.getBrandId());
        QueryWrapper<CategoryBrandRelationEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("brand_id",brandEntity.getBrandId());
        update(relation,wrapper);
    }

    @Override
    public void updateCategory(CategoryEntity categoryEntity) {
        //更新category name
        CategoryBrandRelationEntity relation = new CategoryBrandRelationEntity();
        relation.setBrandName(categoryEntity.getName());
        relation.setBrandId(categoryEntity.getCatId());
        QueryWrapper<CategoryBrandRelationEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("cat_id", categoryEntity.getCatId());
        update(relation,wrapper);
    }

}