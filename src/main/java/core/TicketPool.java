package core;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class TicketPool implements TicketManager {
    private final List<String> tickets = Collections.synchronizedList(new LinkedList<>());
    private final int maxTicketCapacity;

    public TicketPool(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }

    @Override
    public synchronized void addTickets(String ticket) {
        if (tickets.size() < maxTicketCapacity) {
            tickets.add(ticket);
            showPoolStatus();
        }
    }

    @Override
    public synchronized String removeTicket() {
        String ticket = null;
        if (!tickets.isEmpty()) {
            ticket = tickets.remove(0);
            showPoolStatus();
        }
        return ticket;
    }

    public int getTicketCount() {
        return tickets.size();
    }

    public boolean isFull() {
        return tickets.size() >= maxTicketCapacity;
    }

    public synchronized void showPoolStatus() {
        System.out.println("Tickets remaining in pool: " + tickets.size() + "/" + maxTicketCapacity);
    }
}
