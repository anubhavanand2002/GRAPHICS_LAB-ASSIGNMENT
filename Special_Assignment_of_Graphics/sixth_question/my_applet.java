package PACK1;
import PACK1.PACK2.second;
import PACK1.PACK2.PACK3.third;
import java.applet.*;
import java.awt.*;
public class my_applet extends Applet
{
public void init()
{
setBackground(Color.red);
}
public void paint(Graphics g)
{
second ob1=new second();
ob1.plotPoint(g,50,50);
third ob2=new third();
ob2.drawOval(g);
}
}

