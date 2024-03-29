package cn.szu.mall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.szu.mall.common.utils.PageUtils;
import cn.szu.mall.order.entity.OrderEntity;

import java.util.Map;

/**
 * 订单
 *
 * @author chenyajun
 * @email 554811048@qq.com
 * @date 2022-07-27 13:51:46
 */
public interface OrderService extends IService<OrderEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

