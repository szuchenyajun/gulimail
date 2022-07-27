package cn.szu.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.szu.common.utils.PageUtils;
import cn.szu.gulimall.product.entity.AttrGroupEntity;

import java.util.Map;

/**
 * 属性分组
 *
 * @author chenyajun
 * @email 554811048@qq.com
 * @date 2022-07-27 13:38:54
 */
public interface AttrGroupService extends IService<AttrGroupEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

