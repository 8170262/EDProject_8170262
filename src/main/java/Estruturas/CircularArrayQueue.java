package Estruturas;

import Exceptions.EmptyCollectionException;
import Interfaces.QueueADT;

public class CircularArrayQueue<T> implements QueueADT<T> {

    private final int DEFAULT_CAPACITY = 100;
    private int front, rear, count;
    private T[] queue;

    public CircularArrayQueue()
    {
        front = rear = count = 0;
        queue = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    public CircularArrayQueue (int initialCapacity)
    {
        front = rear = count = 0;
        queue = ( (T[])(new Object[initialCapacity]) );
    }

    public void enqueue (T element)
    {
        if (size() == queue.length)
            expandCapacity();

        queue[rear] = element;

        rear = (rear+1) % queue.length;

        count++;
    }

    public T dequeue() throws EmptyCollectionException
    {
        if (isEmpty())
            throw new EmptyCollectionException ("queue");

        T result = queue[front];
        queue[front] = null;

        front = (front+1) % queue.length;

        count--;

        return result;
    }

    public T first() throws EmptyCollectionException
    {
        if (isEmpty())
            throw new EmptyCollectionException ("queue");

        return queue[front];
    }

    public boolean isEmpty()
    {
        return (count == 0);
    }

    public int size()
    {
        return count;
    }

    public String toString()
    {
        String result = "";
        int i = 0;

        while(i < count)
        {
            if(queue[i]!=null)
            {
                result += queue[i].toString()+"\n";
            }
            i++;
        }

        return result;

    }

    public void expandCapacity()
    {
        T[] larger = (T[])(new Object[queue.length *2]);

        for(int i=0; i < count; i++)
        {
            larger[i] = queue[front];
            front=(front+1) % queue.length;
        }

        front = 0;
        rear = count;
        queue = larger;
    }
}
