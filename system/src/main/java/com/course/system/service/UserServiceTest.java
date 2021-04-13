package com.course.system.service;

import com.course.system.domain.User;
import com.course.system.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceTest {

    @Resource
    private UserMapper userMapper;

    public User queryById(Long id) {

        return userMapper.selectByPrimaryKey(id);
    }
}
