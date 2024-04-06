package oy.tol.tra;
import oy.tol.tra.StackAllocationException;
import oy.tol.tra.StackInterface;
import oy.tol.tra.StackIsEmptyException;

public class StackImplementation<E> implements StackInterface<E> {

   private Object [] itemArray;
   private int capacity;
   private int currentIndex = -1;
   private static final int DEFAULT_STACK_SIZE = 10;


   public StackImplementation() throws StackAllocationException {
      // TODO: call the constructor with size parameter with default size of 10.
      this(DEFAULT_STACK_SIZE);
   }

   public StackImplementation(int capacity) throws StackAllocationException {
      if (capacity < 2) {
         throw new StackAllocationException("the allocation of the array throws with Java exception");
      }
      try {
         itemArray = new Object[capacity];
      } catch (StackAllocationException e) {
         throw new StackAllocationException("Cannot allocate room for the internal array");
      }
      this.capacity = capacity;
   }

   @Override
   public int capacity() {
      return capacity;
   }

   @Override
   public void push(E element) throws StackAllocationException, NullPointerException {
      if(element==null){
         throw new NullPointerException("The element that need to push cannot be null");
      }
      if(currentIndex+1>=capacity){
         Object [] newArray;
         int newCapacity=capacity*2;

         try{
            newArray=new Object [newCapacity];
            for(int i=0; i<itemArray.length; i++){
               newArray[i]=itemArray[i];
            }

            capacity=newCapacity;
            itemArray=newArray;

         }
         catch(Exception e){
            throw new StackAllocationException("Allocating room for the internal array is forbidden");
         }
      }
      itemArray[++currentIndex]=element;

   }

   @Override
   public E pop() throws StackIsEmptyException {
      if (isEmpty()) {
         throw new StackIsEmptyException("Stack is empty");
      }
      return (E) itemArray[currentIndex--];
   }

   @Override
   public E peek() throws StackIsEmptyException {
      if (isEmpty()) {
         throw new StackIsEmptyException("Stack is empty");
      }
      return (E) itemArray[currentIndex];
   }


   @Override
   public int size() {
      return currentIndex + 1;

   }

   @Override
   public void clear() {
      currentIndex = -1;

   }

   @Override
   public boolean isEmpty() {
      return currentIndex == -1;
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder("[");
      for (var index = 0; index <= currentIndex; index++) {
         builder.append(itemArray[index].toString());
         if (index < currentIndex) {
            builder.append(", ");
         }
      }
      builder.append("]");
      return builder.toString();
   }
}