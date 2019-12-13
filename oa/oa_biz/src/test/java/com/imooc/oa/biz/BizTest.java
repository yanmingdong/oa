package com.imooc.oa.biz;

import com.imooc.oa.biz.DepartmentBiz;
import com.imooc.oa.entity.Department;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-biz.xml")
public class BizTest {

    @Autowired
    private DepartmentBiz departmentBiz;

    @Test
    public void testAdd(){
        Department department = new Department();
        department.setSn("10086");
        department.setName("维修部");
        department.setAddress("杭州市");
        System.out.println(departmentBiz);
        departmentBiz.add(department);
    }



}
