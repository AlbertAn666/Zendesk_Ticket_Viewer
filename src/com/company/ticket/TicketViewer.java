package com.company.ticket;

import com.company.ViewerConfig;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

/**
 * TicketViewer is used to show the tickets information.
 * Attributes:
 * allTickets: An array of all the tickets in json format
 * ticketPages: An array of all the pages. Each page contain fixed number of tickets in json format
 * ticketController: Used to get all the tickets
 * pagesNum & ticketsNum: The number of pages and tickets.
 */
public class TicketViewer {
    private List<JSONObject> allTickets;
    private final List<List<JSONObject>> ticketsPages;
    TicketController ticketController;
    private int pagesNum;
    private int ticketsNum;


    public TicketViewer() {
        allTickets = new ArrayList<>();
        ticketsPages = new ArrayList<>();
        ticketController = new TicketController();
        pagesNum = 0;
        ticketsNum = 0;
    }

    /**
     * Fetch all the tickets by calling getAllTickets method of TicketController
     * ALl the tickets will be stored in the private attribute allTickets.
     */
    public void initializeTickets() {
        allTickets = ticketController.getAllTickets();
        ticketsNum = allTickets.size();
        pagesNum = ticketsNum/25 + 1;
        for(int i = 1; i <= pagesNum; i++) {
            List<JSONObject> singlePage;
            if(i != pagesNum) {
                singlePage = allTickets.subList((i - 1) * 25, i * 25);
            } else {
                singlePage = allTickets.subList((i-1) * 25, ticketsNum);
            }
            ticketsPages.add(singlePage);
        }
    }

    /**
     * Show some information of all the tickets.
     * The tickets are shown by pages, every page contains at most 25 tickets
     */
    public void showAllTickets() {
        if(ticketsNum == 0) {
            System.out.println(ViewerConfig.RED_COLOR + "There's no ticket." + ViewerConfig.RESET_COLOR);
            return;
        }
        int currentPage = 1;
        int currentTicket = 1;
        System.out.println("All the tickets are listed as below pages.");
        while(currentPage <= pagesNum) {
            System.out.println("\nPage " + currentPage + "/" + pagesNum);
            List<JSONObject> currentPageTickets = ticketsPages.get(currentPage - 1);
            for (JSONObject currentPageTicket : currentPageTickets) {
                System.out.println("[" + currentTicket + "] " + ViewerConfig.YELLOW_COLOR + "ticket subject: " +
                        ViewerConfig.RESET_COLOR + currentPageTicket.getString("subject") + ViewerConfig.YELLOW_COLOR +
                        " requested by " + ViewerConfig.RESET_COLOR + currentPageTicket.getInt("requester_id"));
                currentTicket++;
            }
            currentPage++;
            if(currentPage < pagesNum) {
                System.out.println(ViewerConfig.BLUE_COLOR + "\nShow next page? y/n" + ViewerConfig.RESET_COLOR);
                String input = ViewerConfig.getInput();
                while(!input.equals("y") && !input.equals("n")) {
                    System.out.println(ViewerConfig.RED_COLOR + "Input error, please input y/n" + ViewerConfig.RESET_COLOR);
                }
                if(input.equals("n")) {
                    break;
                }
            }
        }
    }

    /**
     * Show a specific ticket with its id ticketId
     * @param ticketId ticketId is not the initial value in ticket json. It is the index of the ticket in the allTickets array.
     */
    public void showATicket(int ticketId) {
        JSONObject ticket = allTickets.get(ticketId-1);
        System.out.println("\nTicket " + ticketId );
        System.out.println(ViewerConfig.YELLOW_COLOR + "ticket subject: " + ViewerConfig.RESET_COLOR + ticket.getString("subject") +
                ViewerConfig.YELLOW_COLOR + " submitted by " + ViewerConfig.RESET_COLOR + ticket.getInt("submitter_id") +
                ViewerConfig.YELLOW_COLOR + " requested by " + ViewerConfig.RESET_COLOR + ticket.getInt("requester_id") +
                ViewerConfig.YELLOW_COLOR + " status " + ViewerConfig.RESET_COLOR + ticket.getString("status") +
                ViewerConfig.YELLOW_COLOR + " priority " + ViewerConfig.RESET_COLOR + ticket.getString("priority"));
    }


    public int getTicketsNum() { return this.ticketsNum; }

    public List<JSONObject> getAllTickets() {
        return allTickets;
    }

    public List<List<JSONObject>> getTicketsPages() {
        return ticketsPages;
    }
}
