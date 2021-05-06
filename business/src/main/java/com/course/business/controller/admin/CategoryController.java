package com.course.business.controller.admin;


import com.course.server.dto.CategoryDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.CategoryService;
import com.course.server.util.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@ComponentScan({"com.course.server"})
@RequestMapping("/admin/category")
@Slf4j
public class CategoryController {

    public static final String BUSINESS_NAME = "分类";


    @Resource
    private CategoryService categoryService;

    /**
     * 查询
     */
    @PostMapping("/all")
    public ResponseDto all() {
        ResponseDto responseDto = new ResponseDto();
        List<CategoryDto> categoryDtoList = categoryService.all();
        responseDto.setContent(categoryDtoList);
        return responseDto;
    }

    /**
     * 查询
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();

        categoryService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     * 保存
     * 有id时更新 无id时插入
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody CategoryDto categoryDto) {

//保存校验

        ValidatorUtil.require(categoryDto.getParent(), "父id");
        ValidatorUtil.require(categoryDto.getName(), "名称");
        ValidatorUtil.length(categoryDto.getName(), "名称", 1, 50);

        ResponseDto responseDto = new ResponseDto();

        categoryService.save(categoryDto);
        responseDto.setContent(categoryDto);

        return responseDto;
    }

    /**
     * 删除
     */
    @PostMapping("/delete/{id}")
    public ResponseDto save(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();

        categoryService.delete(id);

        return responseDto;
    }

}
