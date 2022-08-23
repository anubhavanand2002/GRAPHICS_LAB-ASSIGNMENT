package pack1;
import pack1.pack2.xyz;
import pack1.pack2.pack3.pqr;
public class abc{
   public static void main(String args[])
   {
    System.out.println("This is abc class and it is printed in abc");
    System.out.println("Now i m creating the object of xyz and pqr and calling its function from here:");
    xyz ob=new xyz();
    ob.func();
    pqr ob1=new pqr();
    ob1.function();

   ob.function();
   }
}
