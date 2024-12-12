package core;

public abstract class BaseTicketHandler {
    protected TicketPool ticketPool;

    public BaseTicketHandler(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    public abstract void handleTickets();
}
