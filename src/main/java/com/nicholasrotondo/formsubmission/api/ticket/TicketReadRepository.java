package com.nicholasrotondo.formsubmission.api.ticket;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketReadRepository extends JpaRepository<Ticket, Integer> {
}
