package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HomeWorkTest {

    HomeWork homeWork = new HomeWork();
    TicketManager ticketManager = homeWork.managerFabric();

    @Test
    void managerFabric() {
        assertEquals(null, ticketManager.next());

        for (int i = 0; i < 10; i++) {
            ticketManager.add(new Ticket("pension"));
            ticketManager.add(new Ticket("other"));
        }

        Ticket ticket = ticketManager.next();
        while (ticketManager.size() > 0){
            Ticket ticket2 = ticketManager.next();
            assertTrue(ticket.compare(ticket2));
            ticket = ticket2;
        }
    }

    @Test
    void check() {
        List<Integer> expectedQueue = generateQueue(1, 4);
        List<String> pairs = generatePairs(expectedQueue);
        assertEquals(expectedQueue, homeWork.check(pairs));
    }

    private List<String> generatePairs(List<Integer> expectedQueue) {
        List<String> pairs = new ArrayList<>();
        Function<Integer, Integer> map = (x) -> (x < 0 || x >= expectedQueue.size()) ? 0 : expectedQueue.get(x);

        for (int i = 0;
             i < expectedQueue.size(); i++) {
            pairs.add(String.format("%d:%d", map.apply(i - 1), map.apply(i + 1)));
        }
        Collections.shuffle(pairs);
        return pairs;
    }

    private List<Integer> generateQueue(int seed, int length) {
        return new Random(seed)
                .ints(1, length * 100)
                .limit(length)
                .boxed()
                .collect(Collectors.toList());
    }


}