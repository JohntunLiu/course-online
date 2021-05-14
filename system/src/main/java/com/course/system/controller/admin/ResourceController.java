package com.course.system.controller.admin;


import com.course.server.dto.ResourceDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.ResourceService;
import com.course.server.util.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@ComponentScan({"com.course.server"})
@RequestMapping("/admin/resource")
@Slf4j
public class ResourceController {

public static final String BUSINESS_NAME = "资源";


@Resource
private ResourceService resourceService;

/**
* 查询
*
*/
@PostMapping("/list")
public ResponseDto list(@RequestBody PageDto pageDto) {
ResponseDto responseDto = new ResponseDto();

resourceService.list(pageDto);
responseDto.setContent(pageDto);
return responseDto;
}

/**
* 保存
* 有id时更新 无id时插入
*/
@PostMapping("/save")
public ResponseDto save(@RequestBody ResourceDto resourceDto) {

//保存校验

            ValidatorUtil.require(resourceDto.getName(), "名称");
            ValidatorUtil.length(resourceDto.getName(), "名称", 1, 100);
            ValidatorUtil.length(resourceDto.getPage(), "页面", 1, 50);
            ValidatorUtil.length(resourceDto.getRequest(), "请求", 1, 200);

ResponseDto responseDto = new ResponseDto();

resourceService.save(resourceDto);
responseDto.setContent(resourceDto);

return responseDto;
}

/**
* 删除
*
*/
@PostMapping("/delete/{id}")
public ResponseDto save(@PathVariable String   id) {
ResponseDto responseDto = new ResponseDto();

resourceService.delete(id);

return responseDto;
}

}
