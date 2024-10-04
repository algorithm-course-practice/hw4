package org.example;

import java.util.ArrayList;
import java.util.List;

public class TicketManagerImpl implements TicketManager {

    // Про запрет использования стандартных коллекции ничего сказано не было
    private final List<Ticket> heap = new ArrayList<>();

    @Override
    public void add(Ticket ticket) {
        heap.add(ticket);
        heapifyUp(heap.size() - 1);
    }

    @Override
    public Ticket next() {
        if (heap.isEmpty()) {
            return null;
        }
        Ticket topTicket = heap.get(0);
        if (heap.size() == 1) {
            heap.remove(0);
        } else {
            heap.set(0, heap.remove(heap.size() - 1));
            heapifyDown(0);
        }
        return topTicket;
    }

    private void heapifyUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (compare(heap.get(index), heap.get(parentIndex)) >= 0) {
                break;
            }
            swap(index, parentIndex);
            index = parentIndex;
        }
    }

    private void heapifyDown(int index) {
        int leftChild;
        int rightChild;
        int smallestChild;
        while ((leftChild = 2 * index + 1) < heap.size()) {
            rightChild = leftChild + 1;
            smallestChild = leftChild;

            if (rightChild < heap.size() && compare(heap.get(rightChild), heap.get(leftChild)) < 0) {
                smallestChild = rightChild;
            }

            if (compare(heap.get(smallestChild), heap.get(index)) >= 0) {
                break;
            }

            swap(index, smallestChild);
            index = smallestChild;
        }
    }

    private int compare(Ticket t1, Ticket t2) {
        // Приоритет по типу "pension"
        if ("pension".equals(t1.type) && !"pension".equals(t2.type)) {
            return -1;
        } else if (!"pension".equals(t1.type) && "pension".equals(t2.type)) {
            return 1;
        }
        return t1.registerTime.compareTo(t2.registerTime);
    }

    private void swap(int i, int j) {
        Ticket temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}
