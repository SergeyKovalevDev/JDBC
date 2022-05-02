package ru.sf.jdbc.jdbctemplate.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.sf.jdbc.jdbctemplate.entity.Clients;
import ru.sf.jdbc.jdbctemplate.service.ClientsService;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientsController {

    private final ClientsService clientsService;

    @RequestMapping("/get/{id}")
    public Clients getById(@PathVariable(value = "id") Long id) {
        return clientsService.getById(id);
    }

    @PostMapping("/add")
    public Clients addClient(@RequestBody Clients client) {
        return clientsService.addClient(client);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable(value = "id") Long id) {
        clientsService.deleteClient(id);
    }

    @PutMapping("/update")
    public Clients updateClient(@RequestBody Clients client) {
        return clientsService.updateClients(client);
    }
}
