package com.yuluo.auto;

import com.yuluo.auto.source.Resource;
import org.junit.Assert;
import org.junit.Test;

import java.util.Properties;

/**
 * @Description
 * @Author 蚂蚁不是ant
 * @Date 2020/8/5 21:46
 * @Version V1.0
 */
public class ResourceTest {

    @Test
    public void getResource() {
        Resource resource = new Resource();
        resource.load();
        Properties resource1 = resource.getResource(0);
        Assert.assertEquals("zhangsan" , resource1.getProperty("name") );
    }
}
