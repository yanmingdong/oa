package com.imooc.oa.dao;

import com.imooc.oa.entity.Employee;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeDao {
    void insert(Employee employee);

    void update(Employee employee);

    void delete(String sn);

    Employee select(String sn);

    List<Employee> selectAll();

    /**
     * 7.根据部门编号和职位获取员工
     * @param dsn
     * @param post
     * @return
     */
    List<Employee> selectByDepartmentAndPost(@Param("dsn") String dsn, @Param("post") String post);
}
