package oy.tol.tra;

/**
 * This class instantiates different types of stacks implementing the {@code StackInterface} interface.
 * <p>
 * TODO: Students, implement the createCharacterStack method for instantiating {@code StackImplementation<Character>}
 * objects in the this task.
 *
 * @author Antti Juustila
 * @version 1.0
 */
public class StackFactory {

    private StackFactory() {
    }

    /**
     * Creates an instance of StackImplementation for Integer type.
     * @return The stack object.
     */
    public static StackInterface<Character> createIntegerStack() {
        return new StackImplementation<Character>();
    }

    /**
     * Creates an instance of StackImplementation for Integer type.
     * @param capacity Number of elements the stack can hold.
     * @return The stack object.
     */
    public static StackInterface<Character> createIntegerStack(int capacity) {
        return new StackImplementation<Character>(capacity);
    }

    /**
     * Instantiates a stack of Characters using the stack default constructor.
     * TODO: Students, implement this method in this task.
     * @return The stack implementation holding Characters.
     */
    public static StackInterface<Character> createCharacterStack() {
        return new StackImplementation<>();
    }

    /**
     * Instantiates a stack of Characters using the stack default constructor.
     * TODO: Students, implement this method in this task.
     * @return The stack implementation holding Characters.
     */
    public static StackInterface<Character> createCharacterStack(int capacity) {
        return new StackImplementation<>(capacity);
    }
}