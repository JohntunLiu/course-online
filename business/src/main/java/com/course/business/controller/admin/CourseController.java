package com.course.business.controller.admin;


import com.course.server.dto.CourseDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.CourseService;
import com.course.server.util.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@ComponentScan({"com.course.server"})
@RequestMapping("/admin/course")
@Slf4j
public class CourseController {

public static final String BUSINESS_NAME = "课程";


@Resource
private CourseService courseService;

/**
* 查询
*
*/
@PostMapping("/list")
public ResponseDto list(@RequestBody PageDto pageDto) {
ResponseDto responseDto = new ResponseDto();

courseService.list(pageDto);
responseDto.setContent(pageDto);
return responseDto;
}

/**
* 保存
* 有id时更新 无id时插入
*/
@PostMapping("/save")
public ResponseDto save(@RequestBody CourseDto courseDto) {

//保存校验

            ValidatorUtil.require(courseDto.getName(), "名称");
            ValidatorUtil.length(courseDto.getName(), "名称", 1, 50);
            ValidatorUtil.length(courseDto.getSummary(), "概述", 1, 2000);
            ValidatorUtil.length(courseDto.getImage(), "封面", 1, 100);

ResponseDto responseDto = new ResponseDto();

courseService.save(courseDto);
responseDto.setContent(courseDto);

return responseDto;
}

/**
* 删除
*
*/
@PostMapping("/delete/{id}")
public ResponseDto save(@PathVariable String   id) {
ResponseDto responseDto = new ResponseDto();

courseService.delete(id);

return responseDto;
}

}
