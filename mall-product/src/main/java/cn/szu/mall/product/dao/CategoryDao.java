package cn.szu.mall.product.dao;

import cn.szu.mall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author chenyajun
 * @email 554811048@qq.com
 * @date 2022-07-27 13:38:54
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
