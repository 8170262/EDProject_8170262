public class Demo {
    public static void main(String[] args){

        LinkedList ll=new LinkedList();

        Animal cao=new Animal(1,"Ca", 1);
        Animal gato=new Animal(2,"Ga", 2);
        Animal vaca=new Animal(3,"Va", 2);
        Animal boi=new Animal(4,"Bo", 2);
        Animal coelho=new Animal(5,"Co", 2);

        ll.addLast(boi);
        System.out.println(ll.toString());
        ll.removeFirst();
        System.out.println(ll.toString());
        ll.addLast(boi);
        System.out.println(ll.toString());
        ll.removeLast();
        System.out.println(ll.toString());
        ll.addLast(boi);
        System.out.println(ll.toString());
        ll.remove(boi);
        System.out.println(ll.toString());




        ll.addFirst(cao);
        System.out.println(ll.toString());
        ll.addFirst(gato);
        System.out.println(ll.toString());
        ll.addLast(vaca);
        System.out.println(ll.toString());
        ll.addLast(boi);
        System.out.println(ll.toString());
        ll.addFirst(coelho);
        System.out.println(ll.toString());
        ll.addFirst(gato);
        System.out.println(ll.toString());
        ll.removeFirst();
        System.out.println(ll.toString());
        ll.removeLast();
        System.out.println(ll.toString());
        ll.remove(cao);
        System.out.println(ll.toString());

    }
}
