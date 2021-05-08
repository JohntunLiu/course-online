package com.course.server.service;

import com.course.server.domain.Course;
import com.course.server.domain.CourseExample;
import com.course.server.dto.CourseDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.CourseMapper;
import com.course.server.mapper.my.MyCourseMapper;
import com.course.server.util.CopyUtil;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Service
@Slf4j
public class CourseService {

    @Resource
    private CourseMapper courseMapper;

    @Resource
    private MyCourseMapper myCourseMapper;

    @Resource
    private CourseCategoryService courseCategoryService;

    /**
    * 列表查询
    */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        CourseExample courseExample = new CourseExample();

                courseExample.setOrderByClause("sort asc");

        List<Course> courses = courseMapper.selectByExample(courseExample);
        PageInfo<Course> pageInfo = new PageInfo<>(courses);
        pageDto.setTotal(pageInfo.getTotal());
        List<CourseDto> courseDtoList = CopyUtil.copyList(courses, CourseDto.class);
        pageDto.setList(courseDtoList);

    }

    /**
     * 保存
     * @param courseDto
     */
    public void save(CourseDto courseDto) {

        Course course = CopyUtil.copy(courseDto, Course.class);
        if (StringUtils.isEmpty(courseDto.getId())) {
        this.insert(course);
        } else {
        this.update(course);
        }
        // 批量保存课程分类
        courseCategoryService.saveBatch(course.getId(), courseDto.getCategorys());

    }
    /**
    * 新增
    */
    private void insert(Course course) {
                Date now = new Date();
                course.setCreatedAt(now);
                course.setUpdatedAt(now);
        course.setId(UuidUtil.getShortUuid());
        courseMapper.insert(course);
    }

    /**
    * 更新
    */
    private void update(Course course) {
                course.setUpdatedAt(new Date());
        courseMapper.updateByPrimaryKey(course);
    }

    /**
    * 删除
    */
    public void delete(String id) {
    courseMapper.deleteByPrimaryKey(id);
    }

    /**
     * 更新课程时长
     * @param courseId
     * @return
     */
    public void updateTime(String courseId) {
        log.info("更新课程时长：{}", courseId);
        myCourseMapper.updateTime(courseId);
    }
}