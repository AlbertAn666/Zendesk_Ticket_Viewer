package com.company;
import com.company.ticket.*;

public class ViewerUI {
    private final TicketViewer ticketViewer;

    public ViewerUI() {
        ticketViewer = new TicketViewer();
        ticketViewer.initializeTickets();
    }

    public void run() {
        ViewerConfig.showTitle();
        ViewerConfig.showMenu();
        int option = ViewerConfig.chooseOption(3);
        while(option != 3) {
            if(option == 1) {
                ticketViewer.showAllTickets();
            } else if(option == 2) {
                int totalTickets = ticketViewer.getTicketsNum();
                int ticketId = ViewerConfig.chooseOption(totalTickets);
                ticketViewer.showATicket(ticketId);
            }
            System.out.println("\n");
            ViewerConfig.showMenu();
            option = ViewerConfig.chooseOption(3);
        }
    }
}
