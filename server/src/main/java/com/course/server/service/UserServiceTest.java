package com.course.server.service;

import com.course.server.domain.Test;
import com.course.server.domain.TestExample;
import com.course.server.domain.User;
import com.course.server.mapper.TestMapper;
import com.course.server.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("UserServiceTest")
public class UserServiceTest {

    @Resource
    private UserMapper userMapper;

    @Resource
    private TestMapper testMapper;

    public User queryById(Long id) {

        return userMapper.selectByPrimaryKey(id);
    }

    public List<Test> findAll() {

        TestExample testExample = new TestExample();
        testExample.createCriteria().andIdEqualTo("1");
        testExample.setOrderByClause("id desc");

        return testMapper.selectByExample(testExample);
    }
}
