package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class TicketManagerTest {


    @Test
    void add() throws InterruptedException {
        TicketManager ticketManager = new TicketManagerImpl(6);

        List<Ticket> expected = new ArrayList<>();

        Ticket pension1 = new Ticket("pension");
        ticketManager.add(pension1);
        TimeUnit.SECONDS.sleep(1);

        Ticket pension2 = new Ticket("pension");
        ticketManager.add(pension2);
        TimeUnit.SECONDS.sleep(1);

        Ticket ticket = new Ticket("1");
        ticketManager.add(ticket);
        TimeUnit.SECONDS.sleep(1);

        Ticket ticket2 = new Ticket("2");
        ticketManager.add(ticket2);
        TimeUnit.SECONDS.sleep(1);

        Ticket ticket3 = new Ticket("3");
        ticketManager.add(ticket3);
        TimeUnit.SECONDS.sleep(1);

        Ticket pension3 = new Ticket("pension");
        ticketManager.add(pension3);

        List<Ticket> expectedTickets = new ArrayList<>();
        expectedTickets.add(pension1);
        expectedTickets.add(pension2);
        expectedTickets.add(pension3);
        expectedTickets.add(ticket);
        expectedTickets.add(ticket2);
        expectedTickets.add(ticket3);
        List<Ticket> actualTickets = new ArrayList<>();
        while ((ticket = ticketManager.next()) != null) {
            actualTickets.add(ticket);
        }

        assertEquals(expectedTickets.size(), actualTickets.size());

        System.out.println("actual: " + actualTickets);
        System.out.println("expected: " + expectedTickets);

        for (int i = 0; i < expectedTickets.size(); i++) {
            assertEquals(expectedTickets.get(i), actualTickets.get(i));
        }

    }

    @Test
    void next() {
    }
}