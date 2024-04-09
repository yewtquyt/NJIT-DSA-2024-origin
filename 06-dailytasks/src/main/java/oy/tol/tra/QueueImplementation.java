package oy.tol.tra;

public class QueueImplementation<E> implements QueueInterface<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private E[] itemArray;
    private int size;
    private int head;
    private int tail;
    private int capacity;


    public QueueImplementation(int capacity) {
        this.capacity = capacity > 0 ? capacity : DEFAULT_CAPACITY;
        this.itemArray = (E[]) new Object[this.capacity];
        this.size = 0;
        this.head = 0;
        this.tail = 0;
    }

    public QueueImplementation() throws QueueAllocationException{
        this(DEFAULT_CAPACITY);
    }


    @Override
    public int capacity() {
        return capacity;
    }


    @Override
    public void enqueue(E element) throws QueueAllocationException, NullPointerException {
        if (element == null) {
            throw new NullPointerException("Element cannot be null");
        }
        if (size >= capacity) {
            try{
                int newCapacity = 2 * capacity;
                E[] newitemArray = (E[]) new Object[newCapacity];
                for (int i = 0; i < size; i++) {
                    newitemArray[i] = itemArray[(head + i) % capacity];
                }
                itemArray = newitemArray;
                head = 0;
                tail = size;
                capacity = newCapacity;
            }catch (OutOfMemoryError e) {
                throw new QueueAllocationException("Failed to allocate more room for the queue.");
            }
        }
        itemArray[tail] = element;
        tail = (tail + 1) % capacity;
        size++;
    }



    @Override
    public E dequeue() throws QueueIsEmptyException {
        if (isEmpty()) {
            throw new QueueIsEmptyException("Queue is empty");
        }
        E element = itemArray[head];
        itemArray[head] = null;
        head = (head + 1) % capacity;
        size--;
        return element;
    }

    @Override
    public E element() throws QueueIsEmptyException {
        if (isEmpty()) {
            throw new QueueIsEmptyException("Queue is empty");
        }
        return (E)itemArray[head];
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
        for (int i = 0; i < size; i++) {
            itemArray[(head + i) % capacity] = null;
        }
        this.head = 0;
        this.tail = 0;
        this.size = 0;
    }
    @Override
    public String toString() {
        StringBuilder Bu = new StringBuilder();
        Bu.append("[");
        for (int i = 0; i < size; i++) {
            Bu.append(itemArray[(head + i) % capacity].toString());
            if (i < size - 1) {
                Bu.append(", ");
            }
        }
        Bu.append("]");
        return Bu.toString();
    }
}




