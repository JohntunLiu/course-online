package com.course.server.service;

import com.course.server.domain.User;
import com.course.server.domain.UserExample;
import com.course.server.dto.LoginUserDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.UserDto;
import com.course.server.exception.BusinessException;
import com.course.server.exception.BusinessExceptionCode;
import com.course.server.mapper.UserMapper;
import com.course.server.util.CopyUtil;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;


@Service
@Slf4j
public class UserService {

    @Resource
    private UserMapper userMapper;

    /**
    * 列表查询
    */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        UserExample userExample = new UserExample();
        List<User> users = userMapper.selectByExample(userExample);
        PageInfo<User> pageInfo = new PageInfo<>(users);
        pageDto.setTotal(pageInfo.getTotal());
        List<UserDto> userDtoList = CopyUtil.copyList(users, UserDto.class);
        pageDto.setList(userDtoList);

    }

    public void save(UserDto userDto) {

        User user = CopyUtil.copy(userDto, User.class);
        if (StringUtils.isEmpty(userDto.getId())) {
        this.insert(user);
        } else {
        this.update(user);
        }

    }

    /**
     * 重置密码
     * @param userDto
     */

    public void savePassword(UserDto userDto) {
        User user = CopyUtil.copy(userDto, User.class);
        userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 登录
     * @param userDto
     */

    public LoginUserDto login(UserDto userDto) {
        User user = selectByLoginName(userDto.getLoginName());
        if (user == null) {
            log.info("用户不存在, {}", userDto.getLoginName());
            throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);
        } else if (user.getPassword().equals(userDto.getPassword())) {

            LoginUserDto loginUserDto = CopyUtil.copy(user, LoginUserDto.class);
            return loginUserDto;
        } else {
            log.info("密码错误, 原始密码:{}, 输入密码:{}",user.getPassword(),userDto.getPassword());
            throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);
        }
    }

//    /**
//     * 登出
//     */
//
//    public LoginUserDto logout(UserDto userDto) {
//        User user = selectByLoginName(userDto.getLoginName());
//        if (user == null) {
//            log.info("用户不存在, {}", userDto.getLoginName());
//            throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);
//        } else if (user.getPassword().equals(userDto.getPassword())) {
//            LoginUserDto loginUserDto = CopyUtil.copy(user, LoginUserDto.class);
//            return loginUserDto;
//        } else {
//            log.info("密码错误, 原始密码:{}, 输入密码:{}",user.getPassword(),userDto.getPassword());
//            throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);
//        }
//    }
    /**
    * 新增
    */
    private void insert(User user) {
        user.setId(UuidUtil.getShortUuid());
        User userDb = this.selectByLoginName(user.getLoginName());
        if (userDb != null) {
            throw new BusinessException(BusinessExceptionCode.USER_LOGIN_NAME_EXIST);
        }
        userMapper.insert(user);
    }

    /**
    * 更新
    */
    private void update(User user) {
        user.setPassword(null);
        userMapper.updateByPrimaryKeySelective(user);
    }

    /**
    * 删除
    */
    public void delete(String id) {
    userMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据登录名查询用户信息
     * @param loginName
     * @return
     */
    public User selectByLoginName(String loginName) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andLoginNameEqualTo(loginName);
        List<User> userList = userMapper.selectByExample(userExample);
        if (CollectionUtils.isEmpty(userList)) {
            return null;
        } else {
            return userList.get(0);
        }
    }
}