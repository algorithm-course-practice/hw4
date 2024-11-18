package org.example;

import java.util.LinkedList;
import java.util.List;

public class TicketManagerImpl implements TicketManager{

    /**
     * Очередь для талонов с типом "pension"
     */
    private final List<Ticket> pensionQueue = new LinkedList<>();

    /**
     * Очередь для талонов с другими типами
     */
    private final List<Ticket> regularQueue = new LinkedList<>();

    @Override
    public void add(Ticket ticket) {
        if ("pension".equals(ticket.type)) {
            pensionQueue.add(ticket);
        } else {
            regularQueue.add(ticket);
        }
    }

    @Override
    public Ticket next() {

        // Проверяем приоритетную очередь
        if (!pensionQueue.isEmpty()) {
            return pensionQueue.remove(0); // Удаляем и возвращаем первый элемент
        }
        // Проверяем обычную очередь
        if (!regularQueue.isEmpty()) {
            return regularQueue.remove(0); // Удаляем и возвращаем первый элемент
        }
        // Если все очереди пусты
        return null;
    }
}
