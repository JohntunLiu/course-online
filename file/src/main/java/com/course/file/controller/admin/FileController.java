package com.course.file.controller.admin;


import com.course.server.dto.FileDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.FileService;
import com.course.server.util.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@ComponentScan({"com.course.server"})
@RequestMapping("/admin/file")
@Slf4j
public class FileController {

public static final String BUSINESS_NAME = "文件";


@Resource
private FileService fileService;

/**
* 查询
*
*/
@PostMapping("/list")
public ResponseDto list(@RequestBody PageDto pageDto) {
ResponseDto responseDto = new ResponseDto();

fileService.list(pageDto);
responseDto.setContent(pageDto);
return responseDto;
}

/**
* 保存
* 有id时更新 无id时插入
*/
@PostMapping("/save")
public ResponseDto save(@RequestBody FileDto fileDto) {

//保存校验

            ValidatorUtil.require(fileDto.getPath(), "相对路径");
            ValidatorUtil.length(fileDto.getPath(), "相对路径", 1, 100);
            ValidatorUtil.length(fileDto.getName(), "文件名", 1, 100);
            ValidatorUtil.length(fileDto.getSuffix(), "后缀", 1, 10);
            ValidatorUtil.length(fileDto.getKey(), "文件标识", 1, 32);

ResponseDto responseDto = new ResponseDto();

fileService.save(fileDto);
responseDto.setContent(fileDto);

return responseDto;
}

/**
* 删除
*
*/
@PostMapping("/delete/{id}")
public ResponseDto save(@PathVariable String   id) {
ResponseDto responseDto = new ResponseDto();

fileService.delete(id);

return responseDto;
}

}
