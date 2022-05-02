package ru.sf.jdbc.jdbctemplate.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto {

    private Long id;
    private String username;
    private String email;
    private Long experience;
    private List<Repository> repositories;
}
