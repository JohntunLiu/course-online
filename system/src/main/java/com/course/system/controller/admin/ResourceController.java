package com.course.system.controller.admin;


import com.course.server.dto.PageDto;
import com.course.server.dto.ResourceDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.ResourceService;
import com.course.server.util.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
    public ResponseDto save(@RequestBody String jsonStr) {

//保存校验
        ValidatorUtil.require(jsonStr, "资源");

        ResponseDto responseDto = new ResponseDto();
        resourceService.saveJson(jsonStr);
        return responseDto;
    }

    /**
     * 删除
     */
    @PostMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        resourceService.delete(id);
        return responseDto;
    }


    /**
     * 资源树查询
     */
    @GetMapping("/load-tree")
    public ResponseDto loadTree() {
        ResponseDto responseDto = new ResponseDto();
        List<ResourceDto> resourceDtoList = resourceService.loadTree();
        responseDto.setContent(resourceDtoList);
        return responseDto;
    }

}
