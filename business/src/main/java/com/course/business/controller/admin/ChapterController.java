package com.course.business.controller.admin;


import com.course.server.domain.Chapter;
import com.course.server.domain.Test;
import com.course.server.domain.User;
import com.course.server.dto.ChapterDto;
import com.course.server.dto.PageDto;
import com.course.server.service.ChapterService;
import com.course.server.service.UserServiceTest;
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

    @Resource
    private ChapterService chapterService;

    @RequestMapping("/test")
    public String test() {
        return "success";
    }

//    @GetMapping("/user/{id}")
//    public User queryById(@PathVariable Long id) {
//        return chapterService.queryById(id);
//    }

    @RequestMapping("/list")
    public PageDto list(@RequestBody PageDto pageDto) {
        chapterService.list(pageDto);
        log.info("pageDto：{}",pageDto);
        return pageDto;
    }

}
