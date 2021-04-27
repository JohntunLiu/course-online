package com.course.${module}.controller.admin;


import com.course.server.dto.${Domain}Dto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.${Domain}Service;
import com.course.server.util.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@ComponentScan({"com.course.server"})
@RequestMapping("/admin/${domain}")
@Slf4j
public class ${Domain}Controller {

public static final String BUSINESS_NAME = "${tableNameZh}";


@Resource
private ${Domain}Service ${domain}Service;

/**
* 查询
*
*/
@PostMapping("/list")
public ResponseDto list(@RequestBody PageDto pageDto) {
ResponseDto responseDto = new ResponseDto();

${domain}Service.list(pageDto);
responseDto.setContent(pageDto);
return responseDto;
}

/**
* 保存
* 有id时更新 无id时插入
*/
@PostMapping("/save")
public ResponseDto save(@RequestBody ${Domain}Dto ${domain}Dto) {

//保存校验


ResponseDto responseDto = new ResponseDto();

${domain}Service.save(${domain}Dto);
responseDto.setContent(${domain}Dto);

return responseDto;
}

/**
* 删除
*
*/
@PostMapping("/delete/{id}")
public ResponseDto save(@PathVariable String   id) {
ResponseDto responseDto = new ResponseDto();

${domain}Service.delete(id);

return responseDto;
}

}
