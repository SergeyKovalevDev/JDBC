package ru.sf.jdbc.jdbctemplate.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.sf.jdbc.jdbctemplate.entity.UserDto;
import ru.sf.jdbc.jdbctemplate.entity.Users;
import ru.sf.jdbc.jdbctemplate.service.UserService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UserService userService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void save(@RequestBody Users user) {
        userService.save(user);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public void edit(@RequestBody Users user) {
        userService.edit(user);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public UserDto getById(@PathVariable(value = "id") Long id) {
        return userService.getById(id);
    }

    @RequestMapping(value = "/get/new/{id}", method = RequestMethod.GET)
    public Users getByIdNew(@PathVariable(value = "id") Long id) {
        return userService.getByIdNew(id);
    }

    @RequestMapping(value = "/get/named/{id}", method = RequestMethod.GET)
    public Users getByIdNamed(@PathVariable(value = "id") Long id) {return userService.getByNamedParameter(id);}

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public void delete(Users user) {
        userService.delete(user);
    }
}
