package ru.sf.jdbc.jdbctemplate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sf.jdbc.jdbctemplate.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
}
