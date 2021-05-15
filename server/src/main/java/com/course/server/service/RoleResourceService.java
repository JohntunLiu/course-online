package com.course.server.service;

import com.course.server.domain.RoleResource;
import com.course.server.domain.RoleResourceExample;
import com.course.server.dto.RoleResourceDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.RoleResourceMapper;
import com.course.server.util.CopyUtil;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;


@Service
public class RoleResourceService {

    @Resource
    private RoleResourceMapper roleResourceMapper;

    /**
    * 列表查询
    */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        RoleResourceExample roleResourceExample = new RoleResourceExample();


        List<RoleResource> roleResources = roleResourceMapper.selectByExample(roleResourceExample);
        PageInfo<RoleResource> pageInfo = new PageInfo<>(roleResources);
        pageDto.setTotal(pageInfo.getTotal());
        List<RoleResourceDto> roleResourceDtoList = CopyUtil.copyList(roleResources, RoleResourceDto.class);
        pageDto.setList(roleResourceDtoList);

    }

    public void save(RoleResourceDto roleResourceDto) {

        RoleResource roleResource = CopyUtil.copy(roleResourceDto, RoleResource.class);
        if (StringUtils.isEmpty(roleResourceDto.getId())) {
        this.insert(roleResource);
        } else {
        this.update(roleResource);
        }

    }
    /**
    * 新增
    */
    private void insert(RoleResource roleResource) {
        roleResource.setId(UuidUtil.getShortUuid());
        roleResourceMapper.insert(roleResource);
    }

    /**
    * 更新
    */
    private void update(RoleResource roleResource) {
        roleResourceMapper.updateByPrimaryKey(roleResource);
    }

    /**
    * 删除
    */
    public void delete(String id) {
    roleResourceMapper.deleteByPrimaryKey(id);
    }
}