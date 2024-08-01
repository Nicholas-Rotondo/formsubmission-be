package com.nicholasrotondo.formsubmission.api.ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketWriteRepository extends JpaRepository<Ticket, Integer> {
}
