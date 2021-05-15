package com.course.system.controller.admin;


import com.course.server.dto.RoleUserDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.RoleUserService;
import com.course.server.util.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@ComponentScan({"com.course.server"})
@RequestMapping("/admin/roleUser")
@Slf4j
public class RoleUserController {

public static final String BUSINESS_NAME = "角色用户关联";


@Resource
private RoleUserService roleUserService;

/**
* 查询
*
*/
@PostMapping("/list")
public ResponseDto list(@RequestBody PageDto pageDto) {
ResponseDto responseDto = new ResponseDto();

roleUserService.list(pageDto);
responseDto.setContent(pageDto);
return responseDto;
}

/**
* 保存
* 有id时更新 无id时插入
*/
@PostMapping("/save")
public ResponseDto save(@RequestBody RoleUserDto roleUserDto) {

//保存校验

            ValidatorUtil.require(roleUserDto.getRoleId(), "角色");
            ValidatorUtil.require(roleUserDto.getUserId(), "用户");

ResponseDto responseDto = new ResponseDto();

roleUserService.save(roleUserDto);
responseDto.setContent(roleUserDto);

return responseDto;
}

/**
* 删除
*
*/
@PostMapping("/delete/{id}")
public ResponseDto save(@PathVariable String   id) {
ResponseDto responseDto = new ResponseDto();

roleUserService.delete(id);

return responseDto;
}

}
