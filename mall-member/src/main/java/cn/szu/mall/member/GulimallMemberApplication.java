package cn.szu.mall.member;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Description
 * @Author yajun
 * @Date 2022/7/27
 * @Version 1.0
 */

@EnableFeignClients(basePackages = "cn.szu.mall.member.feign")//指定接口包名
@MapperScan("cn.szu.gulimall.member.dao")
@EnableDiscoveryClient
@SpringBootApplication
public class GulimallMemberApplication {
    public static void main(String[] args) {
        SpringApplication.run(GulimallMemberApplication.class,args);
    }
}
