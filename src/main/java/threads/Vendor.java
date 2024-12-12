package threads;

import core.BaseTicketHandler;
import core.TicketPool;
import logging.Logger;

public class Vendor extends BaseTicketHandler implements Runnable {
    private final int ticketReleaseRate;
    private final int vendorId;

    public Vendor(TicketPool ticketPool, int ticketReleaseRate, int vendorId) {
        super(ticketPool);
        this.ticketReleaseRate = ticketReleaseRate;
        this.vendorId = vendorId;
    }

    @Override
    public void run() {
        for (int i = 0; i < ticketReleaseRate; i++) {
            if (ticketPool.isFull()) {
                Logger.log("Vendor " + vendorId + " stopped. Ticket pool is full.");
                break;
            }

            String ticket = "Ticket-" + System.nanoTime() + "-Vendor-" + vendorId;
            ticketPool.addTickets(ticket);
            Logger.log("Vendor " + vendorId + " added: " + ticket);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Logger.log("Vendor " + vendorId + " interrupted.");
                break;
            }
        }
    }

    @Override
    public void handleTickets() {
        run();
    }
}
