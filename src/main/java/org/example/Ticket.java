package org.example;

import java.time.OffsetDateTime;

/**
 * Можно изменять по своему усмотрению, не нарушая правила приоритезации очереди
 */
public class Ticket {

    private static int idSeq;

    /**
     * Автогенерация id
     */
    int id = ++idSeq;

    /**
     * Приоритеты типов:
     * 1) pension
     * 2) любые другие
     */
    String type;

    /**
     * Приоритет для ранней регистрации
     */
    OffsetDateTime registerTime = OffsetDateTime.now();

    public Ticket(String type) {
        this.type = type;
    }

    public int compareTo(Ticket ticket) {
        int compareDate = this.registerTime.compareTo(ticket.registerTime);
        if (this.type.equals("pension")) {
            if (ticket.type.equals("pension")) {
                if (compareDate == 0) {
                    return compareId(ticket.id);
                }
                return -compareDate;
            } else {
                return -1;
            }
        } else {
            if (ticket.type.equals("pension")) {
                return  1;
            } else {
                if (compareDate == 0) {
                    return -compareId(ticket.id);
                }
                return compareDate;
            }
        }
    }

    private int compareId(int id) {
        if (this.id > id) {
            return -1;
        } else if (this.id == id) {
            return 0;
        }
        return 1;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", registerTime=" + registerTime +
                '}';
    }
}
