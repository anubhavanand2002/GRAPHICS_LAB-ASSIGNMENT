package pack1;
import pack1.pack2.xyz;
public class abc{
   public static void main(String args[])
   {
    System.out.println("This is printed in abc.java");
    System.out.println("Now i m creating the object of xyz class and calling its function:");
    xyz ob=new xyz();
    ob.func();
   }
}
