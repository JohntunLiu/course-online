package com.course.server.domain;


import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "user")
public class User {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private String name;
//    private String username;
//    private String password;
//    private Date birthday;

}