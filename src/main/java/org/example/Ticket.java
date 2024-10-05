package org.example;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.OffsetDateTime;

/**1
 * Можно изменять по своему усмотрению, не нарушая правила приоритезации очереди
 */
@Getter
@EqualsAndHashCode
public class Ticket implements Comparable<Ticket> {

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

    @Override
    public int compareTo(Ticket o) {
        if ((this.type.equals("pension") && o.type.equals("pension")) ||
                (!this.type.equals("pension") && !o.type.equals("pension"))) {
            return this.registerTime.compareTo(o.registerTime);
        }
        else if (this.type.equals("pension")) {
            return -1;
        }
        else {
            return 1;
        }
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", registerTime=" + registerTime +
                "}\n";
    }
}
