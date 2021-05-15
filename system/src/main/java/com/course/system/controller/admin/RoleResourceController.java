package com.course.system.controller.admin;


import com.course.server.dto.RoleResourceDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.RoleResourceService;
import com.course.server.util.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@ComponentScan({"com.course.server"})
@RequestMapping("/admin/roleResource")
@Slf4j
public class RoleResourceController {

public static final String BUSINESS_NAME = "角色资源关联";


@Resource
private RoleResourceService roleResourceService;

/**
* 查询
*
*/
@PostMapping("/list")
public ResponseDto list(@RequestBody PageDto pageDto) {
ResponseDto responseDto = new ResponseDto();

roleResourceService.list(pageDto);
responseDto.setContent(pageDto);
return responseDto;
}

/**
* 保存
* 有id时更新 无id时插入
*/
@PostMapping("/save")
public ResponseDto save(@RequestBody RoleResourceDto roleResourceDto) {

//保存校验

            ValidatorUtil.require(roleResourceDto.getRoleId(), "角色");
            ValidatorUtil.require(roleResourceDto.getResourceId(), "资源");

ResponseDto responseDto = new ResponseDto();

roleResourceService.save(roleResourceDto);
responseDto.setContent(roleResourceDto);

return responseDto;
}

/**
* 删除
*
*/
@PostMapping("/delete/{id}")
public ResponseDto save(@PathVariable String   id) {
ResponseDto responseDto = new ResponseDto();

roleResourceService.delete(id);

return responseDto;
}

}
