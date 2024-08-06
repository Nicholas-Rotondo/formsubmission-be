package com.nicholasrotondo.formsubmission.api.ticket;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketWriteRepository ticketWriteRepository;

    @Autowired
    private TicketReadRepository ticketReadRepository;
    public List<Ticket> fetchTicket() {
        List<Ticket> tickets = new ArrayList<>();
        tickets.addAll(ticketWriteRepository.findAll());
        return tickets;
    }

    @Transactional
    public Ticket postTicket(Ticket incomingTicket) {
        Logger logger = LoggerFactory.getLogger(TicketService.class);
        logger.info("Processing incoming ticket: {}", incomingTicket);
        try {
            LocalDateTime now = LocalDateTime.now();
            Ticket ticket = new Ticket();
            ticket.setEmail(incomingTicket.getEmail());
            ticket.setDescription(incomingTicket.getDescription());
            ticket.setName(incomingTicket.getName());
            ticket.setCreatedAt(now);

            Ticket savedTicket = ticketWriteRepository.save(ticket);
            logger.info("Successfully saved ticket: {}", savedTicket);
            return savedTicket;
        } catch (Exception e) {
            logger.error("Error while saving ticket: ", e);
            throw new RuntimeException("Failed to save ticket: " + e.getMessage(), e);
        }
    }

    public Ticket updateTicket(Ticket incomingTicket) {
        Logger logger = LoggerFactory.getLogger(TicketService.class);
        logger.info("Processing incoming ticket: {}", incomingTicket);
        try {
            Ticket ticket = ticketReadRepository.findById(incomingTicket.getId()).orElseThrow(() -> new RuntimeException("Ticket not found"));
            ticket.setStatus(incomingTicket.getStatus());
            Ticket savedTicket = ticketWriteRepository.save(ticket);
            logger.info("Successfully updated ticket: {}", savedTicket);
            return savedTicket;
        } catch (Exception e) {
            logger.error("Error while updating ticket: ", e);
            throw new RuntimeException("Failed to update ticket: " + e.getMessage(), e);
        }
    }
}
