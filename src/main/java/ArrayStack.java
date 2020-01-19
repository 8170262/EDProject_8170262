public class ArrayStack<T> implements StackADT<T>
{
    private final int DEFAULT_CAPACITY = 100;
    private int top;
    private T[] stack;

    public ArrayStack()
    {
        top = 0;
        stack = (T[])(new Object[DEFAULT_CAPACITY]);
    }

    public ArrayStack (int initialCapacity)
    {
        top = 0;
        stack = (T[])(new Object[initialCapacity]);
    }

    public void push (T element)
    {
        if (size() == stack.length)
            expandCapacity();

        stack[top] = element;
        top++;
    }

    public T pop() throws EmptyStackException
    {
        if (isEmpty())
            throw new EmptyStackException();

        top--;
        T result = stack[top];
        stack[top] = null;

        return result;
    }

    public T peek() throws EmptyStackException
    {
        if (isEmpty())
            throw new EmptyStackException();


        return stack[top-1];
    }

    public boolean isEmpty()
    {
        return (top == 0);
    }

    public int size()
    {
        return top;
    }

    public String toString()
    {
        String result = "";

        for (int i=0; i < top; i++)
            result = result + stack[i].toString() + "\n";

        return result;
    }

    private void expandCapacity()
    {
        T[] larger = (T[])(new Object[stack.length*2]);

        for (int i=0; i < stack.length; i++)
            larger[i] = stack[i];

        stack = larger;
    }
}