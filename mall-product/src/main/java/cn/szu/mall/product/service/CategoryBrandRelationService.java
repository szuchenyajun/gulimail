package cn.szu.mall.product.service;

import cn.szu.mall.product.entity.CategoryBrandRelationEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.szu.mall.common.utils.PageUtils;

import java.util.Map;

/**
 * 品牌分类关联
 *
 * @author chenyajun
 * @email 554811048@qq.com
 * @date 2022-07-27 13:38:54
 */
public interface CategoryBrandRelationService extends IService<CategoryBrandRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

