package com.nicholasrotondo.formsubmission.api.ticket;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @RequestMapping(value="/api/ticket/fetchTicket", method = RequestMethod.GET)
    @ResponseBody
    public List<Ticket> ticket() {
        return ticketService.fetchTicket();
    }

    @RequestMapping(value="/api/ticket/postTicket", method = RequestMethod.POST)
    @ResponseBody
    public Ticket postTicket(@RequestBody Ticket incomingTicket) {
        return ticketService.postTicket(incomingTicket);
    }


}
