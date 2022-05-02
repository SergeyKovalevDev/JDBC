package ru.sf.jdbc.jdbctemplate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sf.jdbc.jdbctemplate.entity.Clients;
import ru.sf.jdbc.jdbctemplate.repository.ClientsRepository;
import ru.sf.jdbc.jdbctemplate.repository.ClientsSession;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientsService {

    private final ClientsRepository clientsRepository;

    public Clients getById(Long id) {
        Optional<Clients> optionalClients = clientsRepository.findById(id);
        if (optionalClients.isPresent()) return optionalClients.get();
        else throw new RuntimeException("Client not found");
    }

    public Clients addClient(Clients client) {
        return clientsRepository.save(client);
    }

    public Clients updateClients(Clients client) {
        clientsRepository.save(client);
        return null;
    }

    public void deleteClient(Long id) {
        clientsRepository.deleteById(id);
    }
}
