package cn.szu.mall.product.service;

import cn.szu.mall.product.entity.AttrEntity;
import cn.szu.mall.product.vo.AttrRespVo;
import cn.szu.mall.product.vo.AttrVo;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.szu.mall.common.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * 商品属性
 *
 * @author chenyajun
 * @email 554811048@qq.com
 * @date 2022-07-27 13:38:54
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveAttr(AttrVo attrVo);

    PageUtils queryPageByMulti(Map<String, Object> params, Long catelogId,String attrType);

    AttrRespVo getAttrInfo(Long attrId);

    //有分组 就是更新 ，原来没分组 就新增分组
    void updateAttr(AttrVo attrVo);

    //根据分组id 查属性
    List<AttrEntity> getAttrByGroupId(Long groupId);

    PageUtils getNoAttrRelation(Long groupId, Map<String, Object> params);
}

