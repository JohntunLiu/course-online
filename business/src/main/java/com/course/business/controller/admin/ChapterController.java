package com.course.business.controller.admin;


import com.course.server.domain.Chapter;
import com.course.server.domain.Test;
import com.course.server.domain.User;
import com.course.server.dto.ChapterDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.ChapterService;
import com.course.server.service.UserServiceTest;
import com.course.server.util.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@ComponentScan({"com.course.server"})
@RequestMapping("/admin/chapter")
@Slf4j
public class ChapterController {

    public static final String BUSINESS_NAME = "大章";


    @Resource
    private ChapterService chapterService;

    @RequestMapping("/test")
    public String test() {
        return "success";
    }



    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();

        chapterService.list(pageDto);
        log.info("pageDto：{}",pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    @PostMapping("/save")
    public ResponseDto save(@RequestBody ChapterDto chapterDto) {


        ValidatorUtil.require(chapterDto.getName(),"名称");
        ValidatorUtil.require(chapterDto.getCourseId(),"课程ID");
        ValidatorUtil.length(chapterDto.getCourseId(),"课程ID",1,8);


        ResponseDto responseDto = new ResponseDto();

        chapterService.save(chapterDto);
        log.info("pageDto：{}",chapterDto);
        responseDto.setContent(chapterDto);

        return responseDto;
    }

    @PostMapping("/delete/{id}")
    public ResponseDto save(@PathVariable String   id) {
        ResponseDto responseDto = new ResponseDto();

        chapterService.delete(id);

        return responseDto;
    }

}
