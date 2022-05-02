package ru.sf.jdbc.jdbctemplate.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "repository")
@Entity
@Getter
@Setter
public class Repository {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "code")
    private String code;

    @JoinColumn(name = "user_id")
    private Integer user_id;
}
