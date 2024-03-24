package oy.tol.tra;

import java.util.Arrays;

public class QueueImplementation<E> implements QueueInterface<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private E[] elements;
    private int size;
    private int head;
    private int tail;

    public QueueImplementation() {
        this(DEFAULT_CAPACITY);
    }

    public QueueImplementation(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Error, capacity must be greater than 0");
        }
        elements = (E[]) new Object[capacity];
        size = 0;
        head = 0;
        tail = 0;
    }

    @Override
    public int capacity() {
        return elements.length;
    }

    @Override
    public void enqueue(E element) throws QueueAllocationException, NullPointerException {
        if (element == null) {
            throw new NullPointerException("Element cannot be null");
        }
        if (size == elements.length) {
            resize();
        }
        elements[tail++] = element;
        size++;
    }

    @Override
    public E dequeue() throws QueueIsEmptyException {
        if (isEmpty()) {
            throw new QueueIsEmptyException("Queue is empty");
        }
        E removedElement = elements[head];
        elements[head++] = null;
        size--;
        return removedElement;
    }

    @Override
    public E element() throws QueueIsEmptyException {
        if (isEmpty()) {
            throw new QueueIsEmptyException("Queue is empty");
        }
        return elements[head];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        Arrays.fill(elements, null);
        size = 0;
        head = 0;
        tail = 0;
    }
    public String toString() {
        return Arrays.toString(Arrays.copyOfRange(elements, head, head + size));
    }

    private void resize() throws QueueAllocationException {
        int newCapacity = elements.length * 2;
        try {
            elements = Arrays.copyOf(elements, newCapacity);
        } catch (OutOfMemoryError e) {
            throw new QueueAllocationException("Error");
        }
    }
}


