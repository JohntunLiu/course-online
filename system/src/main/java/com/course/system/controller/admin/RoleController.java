package com.course.system.controller.admin;


import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.dto.RoleDto;
import com.course.server.service.RoleService;
import com.course.server.util.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@ComponentScan({"com.course.server"})
@RequestMapping("/admin/role")
@Slf4j
public class RoleController {

    public static final String BUSINESS_NAME = "角色";


    @Resource
    private RoleService roleService;

    /**
     * 查询
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();

        roleService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     * 保存
     * 有id时更新 无id时插入
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody RoleDto roleDto) {

//保存校验

        ValidatorUtil.require(roleDto.getName(), "角色");
        ValidatorUtil.length(roleDto.getName(), "角色", 1, 50);
        ValidatorUtil.require(roleDto.getDesc(), "描述");
        ValidatorUtil.length(roleDto.getDesc(), "描述", 1, 100);

        ResponseDto responseDto = new ResponseDto();

        roleService.save(roleDto);
        responseDto.setContent(roleDto);

        return responseDto;
    }

    /**
     * 删除
     */
    @PostMapping("/delete/{id}")
    public ResponseDto save(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();

        roleService.delete(id);

        return responseDto;
    }

    /**
     * 保存资源
     * @param roleDto
     */
    @PostMapping("/save-resource")
    public ResponseDto saveResource(@RequestBody RoleDto roleDto) {
        log.info("保存角色资源关联开始");
        ResponseDto<RoleDto> responseDto = new ResponseDto<>();
        roleService.saveResource(roleDto);
        responseDto.setContent(roleDto);
        return responseDto;
    }

    /**
     * 加载已关联的资源
     */
    @GetMapping("/list-resource/{roleId}")
    public ResponseDto listResource(@PathVariable String roleId) {
        log.info("加载资源开始");
        ResponseDto responseDto = new ResponseDto<>();
        List<String> resourceIdList = roleService.listResource(roleId);
        responseDto.setContent(resourceIdList);
        return responseDto;
    }

    /**
     * 保存用户
     * @param roleDto
     */
    @PostMapping("/save-user")
    public ResponseDto saveUser(@RequestBody RoleDto roleDto) {
        log.info("保存角色用户关联开始");
        ResponseDto<RoleDto> responseDto = new ResponseDto<>();
        roleService.saveUser(roleDto);
        responseDto.setContent(roleDto);
        return responseDto;
    }

    /**
     * 加载用户
     * @param roleId
     */
    @GetMapping("/list-user/{roleId}")
    public ResponseDto listUser(@PathVariable String roleId) {
        log.info("加载用户开始");
        ResponseDto responseDto = new ResponseDto<>();
        List<String> userIdList = roleService.listUser(roleId);
        responseDto.setContent(userIdList);
        return responseDto;
    }


}
