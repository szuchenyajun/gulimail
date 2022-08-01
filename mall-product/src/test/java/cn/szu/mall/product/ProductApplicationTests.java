package cn.szu.mall.product;

import cn.szu.mall.product.entity.BrandEntity;
import cn.szu.mall.product.service.BrandService;

import cn.szu.mall.product.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


@SpringBootTest
@Slf4j
class ProductApplicationTests {
    @Autowired
    BrandService brandService;

    @Resource
    CategoryService categoryService;
    @Test
    void contextLoads() {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setDescript("hello");
        brandEntity.setName("华为");
        brandService.save(brandEntity);
        log.info("保存成功");
    }
    @Test
    void testPath(){
        Long[] catelogPath = categoryService.findCatelogPath(165L);
        log.info(catelogPath.toString());
    }

}
