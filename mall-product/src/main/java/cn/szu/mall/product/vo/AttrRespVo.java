package cn.szu.mall.product.vo;

import lombok.Data;

/**
 * @Description
 * @Author yajun
 * @Date 2022/7/31
 * @Version 1.0
 */
@Data
public class AttrRespVo extends AttrVo{

    private String catelogName;
    private String groupName;
    private Long[] catelogPath;
}
