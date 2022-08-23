package pack1;
import pack1.pack2.xyz;
import pack1.pack2.pack3.pqr;
public class abc{
   public static void main(String args[])
   {
    System.out.println("This is abc class");
    System.out.println("Now i m creating object of xyz and pqr and them i am calling its function:");
    xyz ob=new xyz();
    ob.func();
    pqr ob1=new pqr();
    ob1.function();
   }
}
