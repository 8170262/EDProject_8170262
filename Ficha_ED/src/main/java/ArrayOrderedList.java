public class ArrayOrderedList<T> extends ArrayList<T>
        implements OrderedListADT<T>
{
    public ArrayOrderedList()
    {
        super();
    }

    public ArrayOrderedList (int initialCapacity)
    {
        super(initialCapacity);
    }

    public void add (T element)
    {
        if (size() == list.length)
            expandCapacity();

        Comparable<T> temp = (Comparable<T>)element;

        int i = 0;
        while (i < rear && temp.compareTo(list[i]) > 0)
            i++;

        for (int j=rear; j > i; j--)
            list[j] = list[j-1];

        list[i] = element;
        rear++;
    }
}