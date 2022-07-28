package cn.szu.mall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.szu.mall.common.utils.PageUtils;
import cn.szu.mall.coupon.entity.HomeAdvEntity;

import java.util.Map;

/**
 * 首页轮播广告
 *
 * @author chenyajun
 * @email 554811048@qq.com
 * @date 2022-07-27 13:45:54
 */
public interface HomeAdvService extends IService<HomeAdvEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

