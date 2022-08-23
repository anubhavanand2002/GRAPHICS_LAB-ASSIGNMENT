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
second obj=new second();
obj.plotPoint(g,10,10);
}
};
