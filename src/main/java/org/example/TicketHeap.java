package org.example;

class TicketHeap {

    private final Ticket[] heapArray;

    private final int capacity;

    private int current_heap_size;

    public TicketHeap(int n) {
        capacity = n;
        heapArray = new Ticket[capacity];
        current_heap_size = 0;
    }

    private void swap(Ticket[] arr, int a, int b) {
        Ticket temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }


    private int parent(int key) {
        return (key - 1) / 2;
    }

    public boolean add(Ticket ticket) {
        if (current_heap_size == capacity) {
            return false;
        }

        int i = current_heap_size;
        heapArray[i] = ticket;
        current_heap_size++;

        while (i != 0 && heapArray[i].compareTo(heapArray[parent(i)]) < 0) {
            swap(heapArray, i, parent(i));
            i = parent(i);
        }
        return true;
    }

    public Ticket remove() {
        if (current_heap_size <= 0) {
            return null;
        }

        if (current_heap_size == 1) {
            current_heap_size--;
            return heapArray[0];
        }

        Ticket root = heapArray[0];

        heapArray[0] = heapArray[current_heap_size - 1];
        current_heap_size--;
        ticketHeapify(0);

        return root;
    }

    private void ticketHeapify(int key) {
        int l = left(key);
        int r = right(key);

        int smallest = key;
        if (l < current_heap_size && heapArray[l].compareTo(heapArray[smallest]) < 0)  {
            smallest = l;
        }
        if (r < current_heap_size && heapArray[r].compareTo(heapArray[smallest]) < 0) {
            smallest = r;
        }

        if (smallest != key) {
            swap(heapArray, key, smallest);
            ticketHeapify(smallest);
        }
    }

    private int left(int key) {
        return 2 * key + 1;
    }

    private int right(int key) {
        return 2 * key + 2;
    }



}


// This code is contributed by rishabmalhdijo
