public class DemoStack {
    public static void main(String[] args){

        StackADT ll=new LinkedStack(); //ArrayStack();

        Animal cao=new Animal(1,"Ca", 12);
        Animal gato=new Animal(2,"Ga", 22);
        Animal vaca=new Animal(3,"Va", 22);
        Animal boi=new Animal(4,"Bo", 22);
        Animal coelho=new Animal(5,"Co", 22);


        ll.push(cao);
        System.out.println(ll.toString());
        ll.push(gato);
        System.out.println(ll.toString());
        ll.push(vaca);
        System.out.println(ll.toString());
        ll.pop();
        System.out.println(ll.toString());
        ll.push(boi);
        System.out.println(ll.toString());
        ll.push(coelho);
        System.out.println(ll.toString());
        ll.push(vaca);
        System.out.println(ll.toString());
        ll.pop();
        System.out.println(ll.toString());


    }
}
