package cn.szu.mall.product.service.impl;

import cn.szu.mall.common.constant.ProductConstant;
import cn.szu.mall.product.dao.AttrAttrgroupRelationDao;
import cn.szu.mall.product.dao.AttrDao;
import cn.szu.mall.product.dao.AttrGroupDao;
import cn.szu.mall.product.dao.CategoryDao;
import cn.szu.mall.product.entity.AttrAttrgroupRelationEntity;
import cn.szu.mall.product.entity.AttrEntity;
import cn.szu.mall.product.entity.AttrGroupEntity;
import cn.szu.mall.product.service.CategoryService;
import cn.szu.mall.product.vo.AttrRespVo;
import cn.szu.mall.product.vo.AttrVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.szu.mall.common.utils.PageUtils;
import cn.szu.mall.common.utils.Query;

import cn.szu.mall.product.service.AttrService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {

    @Resource
    AttrAttrgroupRelationDao relationDao;
    @Resource
    CategoryDao categoryDao;
    @Resource
    AttrGroupDao attrGroupDao;
    @Resource
    CategoryService categoryService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                new QueryWrapper<AttrEntity>()
        );

        return new PageUtils(page);
    }

    @Transactional
    @Override
    public void saveAttr(AttrVo attrVo) {
        AttrEntity attrEntity = new AttrEntity();
        BeanUtils.copyProperties(attrVo,attrEntity);
        save(attrEntity);
        //销售属性没有分组
        //更新中间表 ,基本属性菜关联
        if(attrVo.getAttrType() == ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode()){
            AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = new AttrAttrgroupRelationEntity();
            attrAttrgroupRelationEntity.setAttrGroupId(attrVo.getAttrGroupId());
            attrAttrgroupRelationEntity.setAttrId(attrEntity.getAttrId());
            relationDao.insert(attrAttrgroupRelationEntity);
        }


    }

    @Override
    public PageUtils queryPageByMulti(Map<String, Object> params, Long catelogId,String attrType) {
        //1 是基本属性
        QueryWrapper<AttrEntity> queryWrapper = new QueryWrapper<AttrEntity>().eq("attr_type","base".equalsIgnoreCase(attrType)?1:0);
        if(catelogId != 0){
            queryWrapper.eq("catelogId",catelogId);
        }
        String key = (String) params.get("key");
        if(StringUtils.isNotBlank(key)){
            queryWrapper.and(wrapper->{
                wrapper.eq("attr_id",key).or().like("attr_name",key);
            });
        }
        //分类和分组
        IPage<AttrEntity> page = this.page(new Query<AttrEntity>().getPage(params), queryWrapper);

        List<AttrEntity> records = page.getRecords();
        //响应数据要多包装两个数据
        List<AttrRespVo> attrRespVos = records.stream().map(attrEntity -> {
            AttrRespVo attrRespVo = new AttrRespVo();
            BeanUtils.copyProperties(attrEntity, attrRespVo);
            String catelogName = categoryDao.selectById(attrEntity.getCatelogId()).getName();
            if(catelogName != null){
                attrRespVo.setCatelogName(catelogName);
            }
            //分组信息
            if("base".equalsIgnoreCase(attrType)){
                AttrAttrgroupRelationEntity relationEntity = relationDao.selectOne(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attrEntity.getAttrId()));
                if(relationEntity != null){
                    String groupName = attrGroupDao.selectById(relationEntity.getAttrGroupId()).getAttrGroupName();
                    if(StringUtils.isNotBlank(groupName)){
                        attrRespVo.setGroupName(groupName);
                    }

                }
            }

            return attrRespVo;
        }).collect(Collectors.toList());

        PageUtils pageUtils = new PageUtils(page);
        pageUtils.setList(attrRespVos);
        return pageUtils;
    }

    @Override
    public AttrRespVo getAttrInfo(Long attrId) {
        AttrRespVo attrRespVo = new AttrRespVo();
        AttrEntity attrEntity = getById(attrId);
        BeanUtils.copyProperties(attrEntity,attrRespVo);
        //分组id  和路径
        if(attrEntity.getAttrType()==ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode()){
            AttrAttrgroupRelationEntity relationEntity = relationDao.selectOne(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attrEntity.getAttrId()));
            if(relationEntity != null){
                attrRespVo.setAttrGroupId(relationEntity.getAttrGroupId());
                AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(relationEntity.getAttrGroupId());
                attrRespVo.setGroupName(attrGroupEntity.getAttrGroupName());
            }
        }

        //分类信息
        Long[] path = categoryService.findCatelogPath(attrEntity.getCatelogId());
        if(path != null){
            attrRespVo.setCatelogPath(path);
        }

        return attrRespVo;
    }

    @Override
    public void updateAttr(AttrVo attrVo) {
        AttrEntity attrEntity = new AttrEntity();
        BeanUtils.copyProperties(attrVo,attrEntity);
        updateById(attrEntity);
        //销售属性没有分组
        //更新中间表 ,基本属性菜关联  销售属性不关联
        if(attrVo.getAttrType() == ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode()){
            AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
            relationEntity.setAttrGroupId(attrVo.getAttrGroupId());
            relationEntity.setAttrId(attrEntity.getAttrId());
            relationDao.update(relationEntity,new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id",attrEntity.getAttrId()));
            //如果关联表 没有当前的记录 新增
            Integer count = relationDao.selectCount(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attrEntity.getAttrId()));

            if(count ==0){
                relationDao.insert(relationEntity);
            }
        }
    }

    @Override
    public List<AttrEntity> getAttrByGroupId(Long groupId) {
        List<AttrAttrgroupRelationEntity> relationEntities = relationDao.selectList(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_group_id", groupId));
        //查到 id
        List<Long> ids = relationEntities.stream().map(attrEntity -> {
            return attrEntity.getAttrId();
        }).collect(Collectors.toList());
        //根据id查出属性
        if(ids == null || ids.size() == 0){
            return null;
        }
        List<AttrEntity> attrEntities = listByIds(ids);
        return  attrEntities;
    }

    //当前分组只能关联 自己所属分类里面的属性
    //当前分组只能关联别的分组没有引用的属性
    @Override
    public PageUtils getNoAttrRelation(Long groupId, Map<String, Object> params) {
        AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(groupId);
        //获取分类
        Long catelogId = attrGroupEntity.getCatelogId();
        //获取所有的组
        List<AttrGroupEntity> groupEntities = attrGroupDao.selectList(new QueryWrapper<AttrGroupEntity>().eq("catelog_id", catelogId));
        List<Long> ids = groupEntities.stream().map(AttrGroupEntity::getAttrGroupId).collect(Collectors.toList());
        //其他组已经关联过的
        List<AttrAttrgroupRelationEntity> group_ids = relationDao.selectList(new QueryWrapper<AttrAttrgroupRelationEntity>().in("attr_group_id", ids));
        //拿到被其他组关联的属性id
        List<Long> attrIds = group_ids.stream().map(item -> {
            return item.getAttrId();
        }).collect(Collectors.toList());
        QueryWrapper<AttrEntity> wrapper = new QueryWrapper<AttrEntity>().eq("catelog_id", catelogId).eq("attr_type", ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode());

        if(attrIds != null && attrIds.size()>0){
            wrapper.notIn("attr_id",attrIds);
        }
        String key = (String) params.get("key");
        if(StringUtils.isNotBlank(key)){
            wrapper.and(w->{
                w.eq("attr_id",key).or().like("attr_name",key);
            });
        }
        IPage<AttrEntity> page = this.page(new Query<AttrEntity>().getPage(params), wrapper);

        return new PageUtils(page);

    }

}