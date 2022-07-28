package cn.szu.mall.product.service;

import cn.szu.mall.product.entity.SkuInfoEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.szu.mall.common.utils.PageUtils;

import java.util.Map;

/**
 * sku信息
 *
 * @author chenyajun
 * @email 554811048@qq.com
 * @date 2022-04-02 18:24:19
 */
public interface SkuInfoService extends IService<SkuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

