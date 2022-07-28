package cn.szu.mall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.szu.mall.common.utils.PageUtils;
import cn.szu.mall.ware.entity.UndoLogEntity;

import java.util.Map;

/**
 * 
 *
 * @author chenyajun
 * @email 554811048@qq.com
 * @date 2022-07-27 13:53:31
 */
public interface UndoLogService extends IService<UndoLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

