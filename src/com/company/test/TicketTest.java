package com.company.test;

import com.company.ticket.TicketController;
import com.company.ticket.TicketViewer;
import org.json.JSONObject;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class TicketTest {
    private TicketController ticketController;
    private TicketViewer ticketViewer;

    @Test
    public void showAllTicketsTest() {
        ticketViewer = new TicketViewer();
        ticketViewer.initializeTickets();
        List<JSONObject> tickets = ticketViewer.getAllTickets();
        assertNotNull(tickets);
        assertNotNull(tickets.get(0));
    }

    @Test
    public void showATicketTest() {
        ticketViewer = new TicketViewer();
        ticketViewer.initializeTickets();
        JSONObject ticket = ticketViewer.getAllTickets().get(0);
        assertNotNull(ticket);
    }

    @Test
    public void pageTest() {
        ticketViewer = new TicketViewer();
        ticketViewer.initializeTickets();
        List<List<JSONObject>> pages = ticketViewer.getTicketsPages();
        assertNotNull(pages);
        assertNotNull(pages.get(0));
        if(pages.size() > 1) {
            assertEquals(pages.get(0).size(), 25);
        }
    }

    @Test
    public void controllerTest() {
        ticketController = new TicketController();
        ArrayList<JSONObject> getTickets = ticketController.getAllTickets();
        assertNotNull(getTickets);
    }

}
