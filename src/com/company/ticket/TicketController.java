package com.company.ticket;

import com.company.ViewerConfig;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * TicketController class is used to fetch all the tickets via zendesk api.
 * Given domain, username and password, the getAllTickets() method will return all the tickets in json format.
 * Use Process to execute curl command.
 */
public class TicketController {
    private final String subDomain = ViewerConfig.DOMAIN;
    private final String userName = ViewerConfig.USER_NAME;
    private final String password = ViewerConfig.PASSWORD;

    public ArrayList<JSONObject> getAllTickets() {
        ArrayList<JSONObject> tickets = new ArrayList<>();
        String curl = "curl https://" + subDomain + ".zendesk.com/api/v2/tickets.json -u " + userName + ":" + password;
        ProcessBuilder processBuilder = new ProcessBuilder(curl.split(" "));
        JSONObject jsonTickets = new JSONObject();
        try {
            Process process = processBuilder.start();
            InputStream inputStream = process.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String str = bufferedReader.readLine();
            while(str != null) {
                stringBuilder.append(str);
                str = bufferedReader.readLine();
            }
            jsonTickets = new JSONObject(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONArray ticketsArray = jsonTickets.getJSONArray("tickets");

        for(int i = 0; i < ticketsArray.length(); i++) {
            tickets.add(ticketsArray.getJSONObject(i));
        }
        return tickets;
    }


}
