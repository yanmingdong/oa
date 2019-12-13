package com.imooc.oa.biz;

import com.imooc.oa.entity.Employee;

public interface GlobalBiz {

    /**
     * 登录
     * @param sn
     * @param password
     * @return
     */
    Employee login(String sn,String password);

    /**
     * 修改密码
     * @param employee
     */
    void changePassword(Employee employee);

}
