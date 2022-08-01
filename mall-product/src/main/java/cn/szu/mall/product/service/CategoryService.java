package cn.szu.mall.product.service;

import cn.szu.mall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.szu.mall.common.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 *
 * @author chenyajun
 * @email 554811048@qq.com
 * @date 2022-07-27 13:38:54
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<CategoryEntity> listWithTree();

    void removeMenuByIdsLogic(List<Long> ids);

    //根据菜单id拿到 完成路径
    Long[] findCatelogPath(Long catelogId);

    //更新自己的同时更新中间表
    void updateDetail(CategoryEntity categoryEntity);
}

