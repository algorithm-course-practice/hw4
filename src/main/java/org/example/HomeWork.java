package org.example;

import java.util.*;


public class HomeWork implements TicketManager{

    private Ticket heap[] = new Ticket[1024];

    private int size = 0;

    /**
     * <h1>Задание 1.</h1>
     * Требуется реализовать интерфейс TicketManager в соответствии с JavaDoc описанием.
     * Реализации очередей из стандартной библиотеки не используем.
     */
    public TicketManager managerFabric() {


        return null;
    }

    @Override
    public void add(Ticket ticket) {
        heap[size] = ticket;
        checkRules(size++);
    }

    private void checkRules(int current) {
        int parent = getParent(current);
        Ticket value = heap[current];
        while (current > 0 && value.compare(heap[parent])) {
            heap[current] = heap[parent];
            current = parent;
            parent = getParent(current);
        }
        heap[current] = value;
    }

    private int getParent(int i) {
        return (i - 1) / 2;
    }
    @Override
    public Ticket next() {
        if (size == 0) {
            return null;
        }
        Ticket result = heap[0];
        int newSize = --size;
        heap[0] = heap[newSize];
        heap[newSize] = null;
        heapify(0);
        return result;
    }

    private void heapify(int i) {
        int left;
        int right;
        int largest;
        while (heap[i] != null) {
            largest = i;
            left = 2 * i + 1;
            right = 2 * i + 2;
            if(left < heap.length &&  bigger(largest, left)){
                largest = left;
            }
            if(right < heap.length &&  bigger(largest, right)){
                largest = right;
            }
            if(largest == i) {
                break;
            }
            Ticket tmp = heap[i];
            heap[i] = heap[largest];
            heap[largest] = tmp;
            i = largest;
        }
    }

    private boolean bigger(int largest, int left) {
        return heap[left] != null && heap[left].compare(heap[largest]);
    }

    public int size() {
        return size;
    }


    /**
     * Задача со звездочкой (опционально)
     * <br/>
     * На вход строки:
     *  номер_впереди : номер_сзади
     * Если впереди или сзади никого нет - то 0, для первого и последнего в очереди.
     * На выход нужно восстановить порядок номеров в очереди.
     *
     * В тестах генератор тестовых данных (очереди и пары).
     * @see <a href="https://codeforces.com/problemset/problem/490/B?locale=ru">https://codeforces.com/problemset/problem/490/B?locale=ru</a>
     */
    public List<Integer> check(List<String> records){
        return null;
    }
}
