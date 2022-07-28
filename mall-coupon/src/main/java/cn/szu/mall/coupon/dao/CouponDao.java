package cn.szu.mall.coupon.dao;

import cn.szu.mall.coupon.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author chenyajun
 * @email 554811048@qq.com
 * @date 2022-07-27 13:45:54
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}
