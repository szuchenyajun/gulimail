package cn.szu.mall.product.service;

import cn.szu.mall.product.entity.SpuInfoDescEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.szu.mall.common.utils.PageUtils;

import java.util.Map;

/**
 * spu信息介绍
 *
 * @author chenyajun
 * @email 554811048@qq.com
 * @date 2022-07-27 13:38:54
 */
public interface SpuInfoDescService extends IService<SpuInfoDescEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

