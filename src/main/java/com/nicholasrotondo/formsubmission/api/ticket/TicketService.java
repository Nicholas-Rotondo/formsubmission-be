package com.nicholasrotondo.formsubmission.api.ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketWriteRepository ticketWriteRepository;
    public List<Ticket> fetchTicket() {
        List<Ticket> tickets = new ArrayList<>();
        return tickets;
    }

    public Ticket postTicket(Ticket incomingTicket) {
        LocalDateTime loc = LocalDateTime.now();
        Ticket ticket = new Ticket();
        ticket.setEmail(incomingTicket.getEmail());
        ticket.setDescription(incomingTicket.getDescription());
        ticket.setStatus(incomingTicket.getStatus());
        ticket.setTitle(incomingTicket.getTitle());
        ticket.setName(incomingTicket.getName());
        ticket.setCreatedAt(loc);
        ticketWriteRepository.save(ticket);
        return ticket;
    }
}
