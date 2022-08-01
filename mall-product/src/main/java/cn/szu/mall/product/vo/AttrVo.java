package cn.szu.mall.product.vo;

import cn.szu.mall.product.entity.AttrEntity;
import lombok.Data;

/**
 * @Description vo对象 负责从页面收集数据
 * @Author yajun
 * @Date 2022/7/31
 * @Version 1.0
 */
@Data
public class AttrVo extends AttrEntity {

    private Long attrGroupId;
}
