package com.imooc.oa.biz.impl;

import com.imooc.oa.biz.DepartmentBiz;
import com.imooc.oa.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

public class AD {

    @Autowired
    @Qualifier("departmentBiz")
    private DepartmentBiz departmentBiz;

    public void sd(Department department) {
        departmentBiz.add(department);
    }
}
