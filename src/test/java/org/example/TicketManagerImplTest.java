package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class TicketManagerImplTest {
    private TicketManager ticketManager;

    @BeforeEach
    public void setUp() {
        ticketManager = new HomeWork().managerFabric();
    }

    @Test
    public void testAddAndNext_withPensionTickets() {
        Ticket pensionTicket1 = new Ticket("pension");
        Ticket pensionTicket2 = new Ticket("pension");
        Ticket regularTicket = new Ticket("regular");

        ticketManager.add(pensionTicket1);
        ticketManager.add(regularTicket);
        ticketManager.add(pensionTicket2);

        assertEquals(pensionTicket1, ticketManager.next(), "Первым должен быть пенсионный талон, добавленный раньше.");
        assertEquals(pensionTicket2, ticketManager.next(), "Вторым должен быть пенсионный талон, добавленный позже.");
        assertEquals(regularTicket, ticketManager.next(), "Третьим должен быть обычный талон.");
        assertNull(ticketManager.next(), "Очередь должна быть пуста, следующий элемент — null.");
    }

    @Test
    public void testAddAndNext_withRegularTicketsOnly() {
        Ticket regularTicket1 = new Ticket("regular");
        Ticket regularTicket2 = new Ticket("regular");

        // Добавляем талоны
        ticketManager.add(regularTicket1);
        ticketManager.add(regularTicket2);

        // Проверяем правильный порядок извлечения
        assertEquals(regularTicket1, ticketManager.next(), "Первым должен быть обычный талон, добавленный раньше.");
        assertEquals(regularTicket2, ticketManager.next(), "Вторым должен быть обычный талон, добавленный позже.");
        assertNull(ticketManager.next(), "Очередь должна быть пуста, следующий элемент — null.");
    }

    @Test
    public void testNext_whenQueueIsEmpty() {
        assertNull(ticketManager.next(), "Очередь пуста, следующий элемент должен быть null.");
    }

    @Test
    public void testAddAndNext_withMixedTypesAndTimes() {
        Ticket regularTicket1 = new Ticket("regular");
        Ticket pensionTicket1 = new Ticket("pension");

        // Делаем задержку для имитации разного времени регистрации
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Ticket regularTicket2 = new Ticket("regular");
        Ticket pensionTicket2 = new Ticket("pension");

        ticketManager.add(regularTicket1);
        ticketManager.add(pensionTicket1);
        ticketManager.add(regularTicket2);
        ticketManager.add(pensionTicket2);

        assertEquals(pensionTicket1, ticketManager.next(), "Первым должен быть пенсионный талон, добавленный раньше.");
        assertEquals(pensionTicket2, ticketManager.next(), "Вторым должен быть пенсионный талон, добавленный позже.");
        assertEquals(regularTicket1, ticketManager.next(), "Третьим должен быть обычный талон, добавленный раньше.");
        assertEquals(regularTicket2, ticketManager.next(), "Четвертым должен быть обычный талон, добавленный позже.");
        assertNull(ticketManager.next(), "Очередь должна быть пуста, следующий элемент — null.");
    }
}