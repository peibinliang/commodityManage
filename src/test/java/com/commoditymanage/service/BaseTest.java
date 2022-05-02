package com.commoditymanage.service;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * 单元测试基类
 * @author ~
 * @date 2022/04/09 17:56
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("/spring.xml")
@Slf4j
public class BaseTest {

    /**
     * 打印测试结果
     * @author ~
     * @date 2022/04/09 17:56
     */
    protected void print(Object obj) {
        log.info("测试结果: {}", obj == null ? "无返回" : JSON.toJSONString(obj));
    }
}
