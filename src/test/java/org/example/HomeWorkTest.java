package org.example;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HomeWorkTest {

    HomeWork homeWork = new HomeWork();

    @Test
    void managerFabric() {

        final int CAPACITY = 1 << 6;
        TicketManager ticketManager = homeWork.managerFabric(CAPACITY);

        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                ticketManager.add(new Ticket("pension"));
            } else {
                ticketManager.add(new Ticket(RandomString()));
            }
        }
        System.out.println(ticketManager.print());

        for (int i = 0; i < 10; i++) {
            System.out.println(ticketManager.next());
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

    private String RandomString() {
        int leftLimit = 65; // numeral 'A'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}