import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Ellipse extends Applet implements  ActionListener,MouseWheelListener{
    //It is for generating a rectangle corresponding to a particular point in cartesian coordinate syatem
        int gap = 10;
    public void plotPoint(Graphics g,int x,int y,Color c)
    {
        g.setColor(c);
        g.fillRect(
            (getX()+getWidth())/2+(x*gap)-(gap/2),
            (getY()+getHeight())/2-(y*gap)-(gap/2),
            gap,gap
        );
    }
    public int slope(int x1,int x2,int y1,int y2)
    {
        int x=x2-x1;
        int y=y2-y1;
        int m=y/x;
        return m;
    }


//Ellipse drawing algorithm
public void midptellipse(Graphics g,float rx, float ry,
                        float xc, float yc)
{
 
    float dx, dy, d1, d2, x, y;
    x = 0;
    y = ry;
 
    // Initial decision parameter of region 1
    d1 = (ry * ry) - (rx * rx * ry) +
                    (0.25f * rx * rx);
    dx = 2 * ry * ry * x;
    dy = 2 * rx * rx * y;
    // DecimalFormat df = new DecimalFormat("#,###,##0.00000");
     
    // For region 1
    while (dx < dy)
    {
        
     plotPoint(g,(int)(x+xc),(int)(y+yc),Color.red);
     plotPoint(g,(int)(-x+xc),(int)(y+yc),Color.red);
     plotPoint(g,(int)(x+xc),(int)(-y+yc),Color.red);
     plotPoint(g,(int)(-x+xc),(int)(-y+yc),Color.red);
 
        if (d1 < 0)
        {
            x++;
            dx = dx + (2 * ry * ry);
            d1 = d1 + dx + (ry * ry);
        }
        else
        {
            x++;
            y--;
            dx = dx + (2 * ry * ry);
            dy = dy - (2 * rx * rx);
            d1 = d1 + dx - dy + (ry * ry);
        }
    }
 
    // Decision parameter of region 2
    d2 = ((ry * ry) * ((x + 0.5f) * (x + 0.5f)))
        + ((rx * rx) * ((y - 1) * (y - 1)))
        - (rx * rx * ry * ry);
 
    // Plotting points of region 2
    while (y >= 0) {
 
        // printing points based on 4-way symmetry
        plotPoint(g,(int)(x+xc),(int)(y+yc),Color.red);
        plotPoint(g,(int)(-x+xc),(int)(y+yc),Color.red);
        plotPoint(g,(int)(x+xc),(int)(-y+yc),Color.red);
        plotPoint(g,(int)(-x+xc),(int)(-y+yc),Color.red);
       
        if (d2 > 0) {
            y--;
            dy = dy - (2 * rx * rx);
            d2 = d2 + (rx * rx) - dy;
        }
        else {
            y--;
            x++;
            dx = dx + (2 * ry * ry);
            dy = dy - (2 * rx * rx);
            d2 = d2 + dx - dy + (rx * rx);
        }
    }
}
public void paintGrid(Graphics g,int gap,int originx,int originy)
    {
        g.setColor(Color.yellow);
        
        for(int i = gap;i<=getWidth();i+=gap)
        {
            g.drawLine(originx+i, originy-getHeight()/2, originx+i, originy+getHeight()/2);
            g.drawLine(originx-i, originy-getHeight()/2, originx-i, originy+getHeight()/2);
        }
        for(int i = gap;i<=getHeight();i+=gap)
        {
            g.drawLine(originx-getWidth()/2, originy+i, originx+getWidth()/2, originy+i);
            g.drawLine(originx-getWidth()/2, originy-i, originx+getWidth()/2, originy-i);

        }
    }








    //It is for initialisation purpose
    public void init(){
        addMouseWheelListener(this);
        button1 = new Button("+");
        add(button1);
        button1.addActionListener(this);
        button2 = new Button("-");
        add(button2);
        button1.setBackground(Color.white);
        button2.setBackground(Color.white);
        button2.addActionListener(this);
        setForeground(Color.green);
        setBackground(Color.black);
    }
    //it is for implementing button function
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == button1){
         gap+=gap+gap/10;
         repaint();
        }
        else if(e.getSource()==button2)
            {
             gap-=gap/10;
             repaint();
            }
    }
    //It is for mouse wheel operation
    public void mouseWheelMoved(MouseWheelEvent e)
    {
        int z=e.getWheelRotation();
        gap+=z;
        repaint();
    }

    Button button1, button2;
    public void paint(Graphics g){
    
            g.setColor(Color.orange);
            int originx=getX()+getWidth()/2;
            int originy=getY()+getHeight()/2;
            g.drawLine(originx-getWidth()/2, originy, originx+getWidth()/2, originy);
            g.drawLine(originx, originy-getHeight()/2, originx, originy+getHeight()/2);
            // paintGrid(g,gap,originx,originy);
            Color c=new Color(100,100,100);
            int i=0;
            int x1=200,y1=101;
            midptellipse(g,(float)50,(float)25,(float)0,(float)0);
            // midptellipse(g,(float)3,(float)6,(float)1,(float)2);
            // paintGrid(g,gap,originx,originy);
    }
}



