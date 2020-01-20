package Exceptions;

public class EmptyStackException extends RuntimeException{
    public EmptyStackException(){
        super ("The Stack is empty");
    }
}