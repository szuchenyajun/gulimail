package cn.szu.gulimall.order.dao;

import cn.szu.gulimall.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author chenyajun
 * @email 554811048@qq.com
 * @date 2022-07-27 13:51:46
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
