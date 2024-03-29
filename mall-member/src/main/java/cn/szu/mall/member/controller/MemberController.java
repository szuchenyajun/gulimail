package cn.szu.mall.member.controller;

import java.util.Arrays;
import java.util.Map;

import cn.szu.mall.member.feign.CouponFeignService;
import cn.szu.mall.member.entity.MemberEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cn.szu.mall.member.service.MemberService;
import cn.szu.mall.common.utils.PageUtils;
import cn.szu.mall.common.utils.R;



/**
 * 会员
 *
 * @author chenyajun
 * @email 554811048@qq.com
 * @date 2022-07-27 13:49:57
 */
@RestController
@RequestMapping("member/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @Autowired
    CouponFeignService couponFeignService;//调用这个接口的方法，会发远程调用

    @GetMapping("/test")
    public R test(){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setNickname("张三");
        R memberCoupons = couponFeignService.testFeignMemberCoupon();
        return R.ok().put("member",memberEntity).put("coupons",memberCoupons);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("member:member:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = memberService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("member:member:info")
    public R info(@PathVariable("id") Long id){
		MemberEntity member = memberService.getById(id);

        return R.ok().put("member", member);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("member:member:save")
    public R save(@RequestBody MemberEntity member){
		memberService.save(member);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("member:member:update")
    public R update(@RequestBody MemberEntity member){
		memberService.updateById(member);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("member:member:delete")
    public R delete(@RequestBody Long[] ids){
		memberService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
