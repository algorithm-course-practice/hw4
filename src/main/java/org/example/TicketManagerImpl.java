package org.example;

import java.util.*;

public class TicketManagerImpl implements TicketManager {


    private final TicketHeap queue;

    public TicketManagerImpl(int capacity) {
        this.queue = new TicketHeap(capacity);
    }


    @Override
    public void add(Ticket ticket) {
        queue.add(ticket);
    }

    @Override
    public Ticket next() {

        return queue.remove();
    }


}
