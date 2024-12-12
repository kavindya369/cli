package threads;

import core.BaseTicketHandler;
import core.TicketPool;
import logging.Logger;

public class Customer extends BaseTicketHandler implements Runnable {
    private final int customerId;
    private final int retrievalInterval;

    public Customer(TicketPool ticketPool, int customerId, int retrievalInterval) {
        super(ticketPool);
        this.customerId = customerId;
        this.retrievalInterval = retrievalInterval;
    }

    @Override
    public void run() {
        while (true) {
            String ticket = ticketPool.removeTicket();
            if (ticket != null) {
                Logger.log("Customer " + customerId + " retrieved: " + ticket);
            } else {
                Logger.log("Customer " + customerId + " found no tickets available.");
                break;
            }

            try {
                Thread.sleep(retrievalInterval);
            } catch (InterruptedException e) {
                Logger.log("Customer " + customerId + " interrupted.");
                break;
            }
        }
    }

    @Override
    public void handleTickets() {
        run();
    }
}
