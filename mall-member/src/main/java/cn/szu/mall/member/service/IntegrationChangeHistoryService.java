package cn.szu.mall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.szu.mall.common.utils.PageUtils;
import cn.szu.mall.member.entity.IntegrationChangeHistoryEntity;

import java.util.Map;

/**
 * 积分变化历史记录
 *
 * @author chenyajun
 * @email 554811048@qq.com
 * @date 2022-07-27 13:49:57
 */
public interface IntegrationChangeHistoryService extends IService<IntegrationChangeHistoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

