package cn.szu.gulimall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.szu.common.utils.PageUtils;
import cn.szu.gulimall.order.entity.RefundInfoEntity;

import java.util.Map;

/**
 * 退款信息
 *
 * @author chenyajun
 * @email 554811048@qq.com
 * @date 2022-07-27 13:51:46
 */
public interface RefundInfoService extends IService<RefundInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

