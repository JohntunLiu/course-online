package com.course.server.service;

import com.course.server.domain.User;
import com.course.server.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("UserServiceTest")
public class UserServiceTest {

    @Resource
    private UserMapper userMapper;

    public User queryById(Long id) {

        return userMapper.selectByPrimaryKey(id);
    }
}
