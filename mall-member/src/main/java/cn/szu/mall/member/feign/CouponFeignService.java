package cn.szu.mall.member.feign;

import cn.szu.mall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description
 * @Author yajun
 * @Date 2022/7/27
 * @Version 1.0
 */
@FeignClient("mall-coupon")//指定 这个接口要调用的服务名
public interface CouponFeignService {
    //配置要调用的远程url

    @GetMapping("/coupon/coupon/member/list")
    R testFeignMemberCoupon();

}
