package Estruturas;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import Interfaces.ListADT;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayList<T> implements ListADT<T>
{
    protected final int DEFAULT_CAPACITY = 100;
    private final int NOT_FOUND = -1;
    protected int rear;
    protected T[] list;

    public ArrayList()
    {
        rear = 0;
        list = (T[])(new Object[DEFAULT_CAPACITY]);
    }



    public ArrayList (int initialCapacity)
    {
        rear = 0;
        list = (T[])(new Object[initialCapacity]);
    }

    public T removeLast () throws EmptyCollectionException
    {
        T result;

        if (isEmpty())
            throw new EmptyCollectionException ("list");

        rear--;
        result = list[rear];
        list[rear] = null;

        return result;
    }

    public T removeFirst() throws EmptyCollectionException
    {
        if (isEmpty())
            throw new EmptyCollectionException ("list");

        T result = list[0];
        rear--;
        for (int i=0; i < rear; i++)
            list[i] = list[i+1];


        list[rear] = null;

        return result;
    }

    public T remove (T element)
    {
        T result;
        int index = find (element);

        if (index == NOT_FOUND)
            throw new ElementNotFoundException();

        result = list[index];
        rear--;
        for (int i=index; i < rear; i++)
            list[i] = list[i+1];


        list[rear] = null;

        return result;
    }

    public T first() throws EmptyCollectionException
    {
        if (isEmpty())
            throw new EmptyCollectionException ("list");

        return list[0];
    }

    public T last() throws EmptyCollectionException
    {
        if (isEmpty())
            throw new EmptyCollectionException ("list");

        return list[rear-1];
    }

    public boolean contains (T target)
    {
        return (find(target) != NOT_FOUND);
    }

    private int find (T target)
    {
        int i = 0, result = NOT_FOUND;
        boolean found = false;

        if (! isEmpty())
            while (! found && i < rear)
                if (target.equals(list[i]))
                    found = true;
                else
                    i++;

        if (found)
            result = i;

        return result;
    }

    public boolean isEmpty()
    {
        return (rear == 0);
    }

    public int size()
    {
        return rear;
    }

    public Iterator<T> iterator()
    {
        return new ArrayIterator<T>(list, rear);
    }

    public String toString()
    {
        String result = "";

        for (int i=0; i < rear; i++)
            result = result + list[i].toString() + "\n";

        return result;
    }

    protected void expandCapacity()
    {
        T[] larger = (T[])(new Object[list.length*2]);

        for (int i=0; i < list.length; i++)
            larger[i] = list[i];

        list = larger;
    }
}