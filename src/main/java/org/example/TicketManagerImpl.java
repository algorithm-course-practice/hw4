package org.example;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TicketManagerImpl implements TicketManager {
    private Ticket[] tickets;
    private int size;
    private int next = 0;

    TicketManagerImpl(int capacity) {
        this.tickets = new Ticket[capacity];
//        this.size = capacity;
    }

    @Override
    public void add(Ticket ticket) {
        this.tickets[this.size] = ticket;
        rotateTickets(this.size++);
        this.next = this.size - 1;
    }

    private void rotateTickets(int current) {
        Ticket value = this.tickets[current];
        while (current > 0) {
            int parent = getParent(current);
            if (value.compareTo(this.tickets[parent]) <  0) {
                break;
            }
            this.tickets[current] = this.tickets[parent];
            current = parent;
        }
        this.tickets[current] = value;
    }

    private int getParent(int i) {
        return (i - 1) / 2;
    }

    @Override
    public Ticket next() {
        if (this.size == 0) {
            return null;
        }

        return this.tickets[this.next--];
    }

    @Override
    public String print() {
        VirtualNode<Ticket> root = new VirtualNode<>(tickets, 0);
        TreePrinter<VirtualNode<Ticket>> printer = new TreePrinter<>(VirtualNode::getLabel, VirtualNode::getLeft, VirtualNode::getRight);
        printer.setSquareBranches(true);
        printer.setHspace(1);
        printer.setTspace(1);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        printer.setPrintStream(new PrintStream(byteArrayOutputStream));
        printer.printTree(root);
        return byteArrayOutputStream.toString();

    }

    @Override
    public String toString() {
        return "MyBinaryHeap{size=" + size + '\n' + print() + "\n}";
    }

    static class VirtualNode<V> {
        private int index;
        private V heap[];

        public VirtualNode(V heap[], int index) {
            this.index = index;
            this.heap = heap;
        }

        String getLabel() {
            return String.format("[%d]%s", index, heap[index]);
        }

        VirtualNode<V> getLeft() {
            int left = 2 * index + 1;
            if (check(left)) {
                return null;
            }
            return new VirtualNode<>(heap, left);
        }

        VirtualNode<V> getRight() {
            int right = 2 * index + 2;
            if (check(right)) {
                return null;
            }
            return new VirtualNode<>(heap, right);
        }

        boolean check(int index) {
            return index < 0 || index >= heap.length || heap[index] == null;
        }
    }
}
