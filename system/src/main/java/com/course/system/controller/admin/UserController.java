package com.course.system.controller.admin;


import com.alibaba.fastjson.JSON;
import com.course.server.dto.LoginUserDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.dto.UserDto;
import com.course.server.service.UserService;
import com.course.server.util.UuidUtil;
import com.course.server.util.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;


@RestController
@ComponentScan({"com.course.server"})
@RequestMapping("/admin/user")
@Slf4j
public class UserController {

    public static final String BUSINESS_NAME = "用户";


    @Resource
    private UserService userService;

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 查询
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();

        userService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody UserDto userDto) {
        userDto.setPassword(DigestUtils.md5DigestAsHex(userDto.getPassword().getBytes()));
        // 保存校验
        ValidatorUtil.require(userDto.getLoginName(), "登陆名");
        ValidatorUtil.length(userDto.getLoginName(), "登陆名", 1, 50);
        ValidatorUtil.length(userDto.getName(), "昵称", 1, 50);
        ValidatorUtil.require(userDto.getPassword(), "密码");

        ResponseDto responseDto = new ResponseDto();
        userService.save(userDto);
        responseDto.setContent(userDto);
        return responseDto;
    }


    /**
     * 保存密码
     */
    @PostMapping("/save-password")
    public ResponseDto savePassword(@RequestBody UserDto userDto) {
        userDto.setPassword(DigestUtils.md5DigestAsHex(userDto.getPassword().getBytes()));

        ResponseDto responseDto = new ResponseDto();
        userService.savePassword(userDto);
        responseDto.setContent(userDto);
        return responseDto;
    }

    /**
     * 删除
     */
    @PostMapping("/delete/{id}")
    public ResponseDto save(@PathVariable String id) {


        ResponseDto responseDto = new ResponseDto();

        userService.delete(id);

        return responseDto;
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public ResponseDto login(@RequestBody UserDto userDto, HttpServletRequest request) {
        log.info("用户登录开始");
        userDto.setPassword(DigestUtils.md5DigestAsHex(userDto.getPassword().getBytes()));
        ResponseDto responseDto = new ResponseDto();

//        log.info( (String) request.getSession().getId());
        // 根据验证码token去获取缓存中的验证码，和用户输入的验证码是否一致
//         String imageCode = (String) request.getSession().getAttribute(userDto.getImageCodeToken());
        String imageCode = (String) redisTemplate.opsForValue().get(userDto.getImageCodeToken());
        if (StringUtils.isEmpty(imageCode)) {
            responseDto.setSuccess(false);
            responseDto.setMessage("验证码已过期");
            log.info("用户登录失败，验证码已过期");
            return responseDto;
        }
        if (!imageCode.equalsIgnoreCase(userDto.getImageCode())) {
            responseDto.setSuccess(false);
            responseDto.setMessage("验证码不对");
            log.info("用户登录失败，验证码不对");
            return responseDto;
        } else {
            // 验证通过后，移除验证码
            request.getSession().removeAttribute(userDto.getImageCodeToken());
//            redisTemplate.delete(userDto.getImageCodeToken());
        }

        LoginUserDto loginUserDto = userService.login(userDto);
        String token = UuidUtil.getShortUuid();
        loginUserDto.setToken(token);
//        request.getSession().setAttribute(Constants.LOGIN_USER, loginUserDto);
        redisTemplate.opsForValue().set(token, JSON.toJSONString(loginUserDto), 3600, TimeUnit.SECONDS);
        responseDto.setContent(loginUserDto);
        return responseDto;
    }

    /**
     * 登出
     */
    @GetMapping("/logout/{token}")
    public ResponseDto logout(@PathVariable String token) {
        ResponseDto responseDto = new ResponseDto();
//        request.getSession().removeAttribute(Constants.LOGIN_USER);
        redisTemplate.delete(token);
        log.info("从redis中删除token:{}", token);
        return responseDto;
    }

}
