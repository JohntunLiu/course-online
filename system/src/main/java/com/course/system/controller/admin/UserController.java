package com.course.system.controller.admin;


import com.course.server.dto.UserDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.UserService;
import com.course.server.util.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@ComponentScan({"com.course.server"})
@RequestMapping("/admin/user")
@Slf4j
public class UserController {

public static final String BUSINESS_NAME = "用户";


@Resource
private UserService userService;

/**
* 查询
*
*/
@PostMapping("/list")
public ResponseDto list(@RequestBody PageDto pageDto) {
ResponseDto responseDto = new ResponseDto();

userService.list(pageDto);
responseDto.setContent(pageDto);
return responseDto;
}

/**
* 保存
* 有id时更新 无id时插入
*/
@PostMapping("/save")
public ResponseDto save(@RequestBody UserDto userDto) {

//保存校验

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
* 删除
*
*/
@PostMapping("/delete/{id}")
public ResponseDto save(@PathVariable String   id) {
ResponseDto responseDto = new ResponseDto();

userService.delete(id);

return responseDto;
}

}
