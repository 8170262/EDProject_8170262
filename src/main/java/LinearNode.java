public class LinearNode<T> {
    private T elem;
    private LinearNode<T> next;

    public LinearNode(){

    }

    public LinearNode(T elem){
        this.elem=elem;
    }

    public void setNext(LinearNode<T> next){
        this.next=next;
    }

    public T getElement(){
        return this.elem;
    }

    public LinearNode<T> getNext(){
        return this.next;
    }

    @Override
    public String toString(){
        return "LinearNode{ elem="+elem+"}";
    }
}
