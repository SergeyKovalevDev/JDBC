package ru.sf.jdbc.jdbctemplate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sf.jdbc.jdbctemplate.entity.Repository;

@org.springframework.stereotype.Repository
public interface RepoRepository extends JpaRepository<Repository, Long> {
}
