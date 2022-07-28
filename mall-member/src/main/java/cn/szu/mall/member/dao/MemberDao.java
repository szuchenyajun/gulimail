package cn.szu.mall.member.dao;

import cn.szu.mall.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author chenyajun
 * @email 554811048@qq.com
 * @date 2022-07-27 13:49:57
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
