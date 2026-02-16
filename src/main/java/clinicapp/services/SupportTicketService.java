package clinicapp.services;

import clinicapp.domain.SupportTicket;

import java.util.List;

public class SupportTicketService {
    private final Repository<SupportTicket, String> repository;

    public SupportTicketService() {
        this.repository = new InMemoryRepository<>(SupportTicket::getId);
    }

    public SupportTicket crear(SupportTicket ticket) {
        return repository.save(ticket);
    }

    public List<SupportTicket> listar() {
        return repository.findAll();
    }
}
