package cn.szu.gulimall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.szu.common.utils.PageUtils;
import cn.szu.gulimall.coupon.entity.HomeSubjectSpuEntity;

import java.util.Map;

/**
 * 专题商品
 *
 * @author chenyajun
 * @email 554811048@qq.com
 * @date 2022-07-27 13:45:54
 */
public interface HomeSubjectSpuService extends IService<HomeSubjectSpuEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

