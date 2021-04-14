package com.course.server.service;

import com.course.server.domain.Chapter;
import com.course.server.dto.ChapterDto;
import com.course.server.mapper.ChapterMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service
public class ChapterService {

    @Resource
    private ChapterMapper chapterMapper;


    public ArrayList<ChapterDto> list() {

        List<Chapter> chapters = chapterMapper.selectByExample(null);
        ArrayList<ChapterDto> chapterDtos = new ArrayList<ChapterDto>();

        for (Chapter chapter : chapters) {
            ChapterDto chapterDto = new ChapterDto();
            BeanUtils.copyProperties(chapter,chapterDto);
            chapterDtos.add(chapterDto);
        }

        return chapterDtos;
    }


}
