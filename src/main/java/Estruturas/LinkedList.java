package Estruturas;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;

public class LinkedList<T> {
    protected int count;
    protected LinearNode<T> head,tail;

    public LinkedList(){
        count=0;
        head=tail=null;
    }

    public T addFirst(T element){
        LinearNode<T> nElement=new LinearNode<T>(element);
        if(isEmpty()){
            head=tail=nElement;
        }else{
            if(head==tail){
                head=nElement;
                head.setNext(tail);
            }else{
                nElement.setNext(head);
                head=nElement;
            }
        }
        count++;

        return nElement.getElement();
    }

    public T addLast(T element){
        LinearNode<T> nElement = new LinearNode<T>(element);
        if(isEmpty()){
            head=tail=nElement;
        }else {

            tail.setNext(nElement);
            tail = nElement;
        }
        count++;

        return nElement.getElement();
    }


    public T removeFirst() throws EmptyCollectionException {
        if(isEmpty()){
            throw new EmptyCollectionException("List");
        }

        LinearNode<T> result =head;
        head=head.getNext();
        if(head==null){
            tail=null;
        }
        count--;

        result.setNext(null);
        return result.getElement();

    }

    public T removeLast() throws EmptyCollectionException{
        if(isEmpty()){
            throw new EmptyCollectionException("List");
        }
        LinearNode<T> current=head;
        LinearNode<T> previous=null;
        LinearNode<T> tailRem=tail;

        while(current.getNext()!=null){
            previous=current;
            current=current.getNext();
        }
        tail=previous;

        if(tail==null) {
            head=null;
        }
        else{
            tail.setNext(null);
        }

        count--;
        tailRem.setNext(null);
        return tailRem.getElement();
    }

    public T remove(T element) throws EmptyCollectionException{
        if(isEmpty()){
            throw new EmptyCollectionException("List");
        }
        if(!contains(element)){
            throw new ElementNotFoundException();
        }

        LinearNode<T> current=head;
        LinearNode<T> previous=null;
        boolean found=false;

        while (current!=null && !found){
            if(element.equals(current.getElement())){
                found=true;
            }else{
                previous=current;
                current=current.getNext();
            }
        }

        if (size()==1){
            head=tail=null;
        }else{
            if (current.equals(head)){
                tail=previous;
                tail.setNext(null);
            }else{
                previous.setNext(current.getNext());
            }
        }
        count--;

        return current.getElement();

    }


    public boolean isEmpty(){
        return (count==0);
    }

    public int size(){
        return count;
    }

    public boolean contains(T target) throws EmptyCollectionException{
        if(isEmpty()){
            throw new EmptyCollectionException("List");
        }

        boolean found=false;

        LinearNode<T> current=head;

        while (current!= null && !found){
            if(target.equals(current.getElement())){
                found=true;
            }
            else{
                current=current.getNext();
            }
        }
        return found;

    }


    public T first(){
        return head.getElement();
    }

    public T last(){
        return tail.getElement();
    }


    @Override
    public String toString(){

        LinearNode<T> current=head;
        String result="";

        while(current!=null){
            result=result + (current.getElement()).toString() + "\n";
            current=current.getNext();
        }

        return result;
    }

}
