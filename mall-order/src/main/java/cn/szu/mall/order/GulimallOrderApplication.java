package cn.szu.mall.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description
 * @Author yajun
 * @Date 2022/7/27
 * @Version 1.0
 */
@MapperScan("cn.szu.gulimall.order.dao")
@SpringBootApplication
public class GulimallOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(GulimallOrderApplication.class,args);
    }
}
