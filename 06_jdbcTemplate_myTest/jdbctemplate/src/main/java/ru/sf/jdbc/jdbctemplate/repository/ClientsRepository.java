package ru.sf.jdbc.jdbctemplate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sf.jdbc.jdbctemplate.entity.Clients;

public interface ClientsRepository extends JpaRepository<Clients, Long> {
}
