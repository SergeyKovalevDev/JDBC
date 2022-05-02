package ru.sf.jdbc.jdbctemplate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sf.jdbc.jdbctemplate.entity.Repository;
import ru.sf.jdbc.jdbctemplate.repository.RepoRepository;

@Service
@RequiredArgsConstructor
public class RepoService {

    private final RepoRepository repository;

    public void save(Repository repository) {
        this.repository.save(repository);
    }
    public void edit(Repository repository) {
        this.repository.save(repository);
    }
    public Repository getById(Long id) {
        return this.repository.getById(id);
    }
    public void delete(Repository repository) {
        this.repository.delete(repository);
    }
}
