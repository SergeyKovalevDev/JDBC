package ru.sf.jdbc.jdbctemplate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import ru.sf.jdbc.jdbctemplate.entity.UserDto;
import ru.sf.jdbc.jdbctemplate.entity.Repository;
import ru.sf.jdbc.jdbctemplate.entity.Users;
import ru.sf.jdbc.jdbctemplate.repository.RepoRepository;
import ru.sf.jdbc.jdbctemplate.repository.UsersRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UsersRepository usersRepository;
    private final RepoRepository repoRepository;

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void save(Users users) {
        usersRepository.save(users);
    }

    public void edit(Users users) {
        usersRepository.save(users);
    }

//    public UserDto getById(Long id) {
//        Users users = jdbcTemplate.queryForObject("SELECT * FROM users WHERE id = ?", new UsersRowMapper(), id);
//        if (users == null) {
//            throw new RuntimeException("User not found");
//        } else {
//            UserDto userDto = new UserDto();
//            userDto.setId(users.getId());
//            userDto.setUsername(users.getUsername());
//            userDto.setEmail(users.getEmail());
//            userDto.setExperience(users.getExperience());
//            userDto.setRepositories(getRepositories(userDto.getId()));
//            return userDto;
//        }
//    }


        public UserDto getById(Long id) {
        Optional<Users> optionalUsers = usersRepository.findById(id);
        if (optionalUsers.isPresent()) {
            Users users = optionalUsers.get();
            UserDto userDto = new UserDto();
            userDto.setId(users.getId());
            userDto.setUsername(users.getUsername());
            userDto.setEmail(users.getEmail());
            userDto.setExperience(users.getExperience());
            userDto.setRepositories(getRepositories(userDto.getId()));
            return userDto;
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public void delete(Users users) {
        usersRepository.delete(users);
    }

    public Users getByIdNew(Long id) {
//        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE id = " + id, new UsersRowMapper());
//        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE id = " + id, Users.class);
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE id = " + id, new BeanPropertyRowMapper<>(Users.class));
    }

    public Users getByNamedParameter(Long id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource().addValue("id", id);
//        return namedParameterJdbcTemplate.queryForObject("SELECT * FROM users WHERE id = :id",
//                mapSqlParameterSource, new UsersRowMapper());
        return namedParameterJdbcTemplate.queryForObject("SELECT * FROM users WHERE id = :id",
                mapSqlParameterSource, new BeanPropertyRowMapper<>(Users.class));
    }

    private List<Repository> getRepositories(Long id) {
        List<Repository> repositories = new ArrayList<>();
        List<Map<String, Object>> maps =
                jdbcTemplate.queryForList("SELECT * FROM repository WHERE user_id = " + id);
        for (Map<String, Object> data : maps){
            Repository repository = new Repository();
            Object oId = data.get("id");
            String sId = String.valueOf(oId);
            Long lId = Long.parseLong(sId);
            repository.setId(lId);
            repository.setTitle((String) data.get("title"));
            repository.setDescription((String) data.get("description"));
            repository.setCode((String) data.get("code"));
            repositories.add(repository);
        }
        return repositories;
    }

//    private static class UsersRowMapper implements RowMapper<Users> {
//        @Override
//        public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
//            Users users = new Users();
//            users.setId(rs.getLong("id"));
//            users.setEmail(rs.getString("email"));
//            users.setUsername(rs.getString("username"));
//            users.setExperience(rs.getLong("experience"));
//            return users;
//        }
//    }
}
