package cn.szu.mall.coupon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description
 * @Author yajun
 * @Date 2022/7/27
 * @Version 1.0
 */
@MapperScan("cn.szu.gulimall.coupon.dao")
@EnableDiscoveryClient //开启nacos 服务注册与发现
@SpringBootApplication
public class GulimallCouponApplication {
    public static void main(String[] args) {
        SpringApplication.run(GulimallCouponApplication.class,args);
    }
}
