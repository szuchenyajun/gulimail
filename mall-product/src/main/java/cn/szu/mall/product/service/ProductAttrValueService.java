package cn.szu.mall.product.service;

import cn.szu.mall.product.entity.ProductAttrValueEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.szu.mall.common.utils.PageUtils;

import java.util.Map;

/**
 * spu属性值
 *
 * @author chenyajun
 * @email 554811048@qq.com
 * @date 2022-04-02 18:24:19
 */
public interface ProductAttrValueService extends IService<ProductAttrValueEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

