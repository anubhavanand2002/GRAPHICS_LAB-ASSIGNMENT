/*This is the program for zoom-out and zoom-in using graphics */
/*Thus program can run only on Java-jdk(8) */


import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class first extends Applet implements  ActionListener,MouseWheelListener{
    //It is for generating a rectangle corresponding to a particular point in cartesian coordinate syatem
        int gap = 4;
    public void plotPoint(Graphics g,int x,int y,Color c)
    {
        g.setColor(c);
        g.fillRect(
            (getX()+getWidth())/2+(x*gap)-(gap/2),
            (getY()+getHeight())/2-(y*gap)-(gap/2),
            gap,gap
        );
    }
    // public void plotPoint(Graphics g,int x,int y,Color c)
    // {
    //     g.setColor(c);
    //     g.fillRect(x-gap/2,y-gap/2,gap,gap);
    // }
    //function to return slope
    public int slope(int x1,int x2,int y1,int y2)
    {
        int x=x2-x1;
        int y=y2-y1;
        int m=y/x;
        return m;
    }
    //function to return line 
    public void midpoint(Graphics g,int X1,int Y1,int X2,int Y2)
    {
         int dx = X2 - X1;
    int dy = Y2 - Y1;
    double m=(double)dy/dx;
    if(m>=0)
    {
    if(Math.abs(dy)<=Math.abs(dx)){
    // initial value of decision parameter d
    double d = (double)1/2-(m);
    int x=X1,y=Y1;
     // Plot initial given point
    // putpixel(x,y) can be used to print pixel
    // of line in graphics
    plotPoint(g,x,-y,Color.green);
 
    // iterate through value of X
    while (x < X2)
    {
        x++;
 
        // E or East is chosen
        if (d < 0)
        {
            d = d + 1-(m);
            y++;
        }
 
        // NE or North East is chosen
        else
        {
            d =d-(m);
        }
 
        // Plot intermediate points
        // putpixel(x,y) is used to print pixel
        // of line in graphics
        // plotPoint(g,-x,y,Color.green);
        plotPoint(g,x,y,Color.green);
    }
    }
    
   
  else if(Math.abs(dx)<Math.abs(dy))
  {
    // initial value of decision parameter d
    double d = 1-(double)(m/2);
    int x=X1,y=Y1;
    // Plot initial given point
    // putpixel(x,y) can be used to print pixel
    // of line in graphics
    plotPoint(g,x,y,Color.green);
 
    // iterate through value of X
    while (y < Y2)
    {
        y++;
 
        // E or East is chosen
        if (d < 0)
        {
            d = d + 1;
            x++;
        }
 
        // NE or North East is chosen
        // NE or North East is chosen
        else
        {
            d += 1-(m);
        }
 
        // Plot intermediate points
        // putpixel(x,y) is used to print pixel
        // of line in graphics
        // plotPoint(g,-x,y,Color.green);
        plotPoint(g,x,y,Color.green);
    }
  }
    }
  else
  {
     if(Math.abs(dy)<=Math.abs(dx)){
    // initial value of decision parameter d
    double d = (double)1/2+(m);
    int x=X1,y=Y1;
     // Plot initial given point
    // putpixel(x,y) can be used to print pixel
    // of line in graphics
    plotPoint(g,x,y,Color.green);
 
    // iterate through value of X
    while (x > X2)
    {
        x--;
 
        // E or East is chosen
        if (d < 0)
        {
            d = d + 1+(m);
            y++;
        }
 
        // NE or North East is chosen
        else
        {
            d =d+(m);
        }
 
        // Plot intermediate points
        // putpixel(x,y) is used to print pixel
        // of line in graphics
        // plotPoint(g,-x,y,Color.green);
        plotPoint(g,x,y,Color.green);
    }
    }
    
   
  else 
  {
    // initial value of decision parameter d
    double d = 1+(double)(m/2);
    int x=X1,y=Y1;
    // Plot initial given point
    // putpixel(x,y) can be used to print pixel
    // of line in graphics
    plotPoint(g,x,y,Color.green);
 
    // iterate through value of X
    while (y > Y2)
    {
        y--;
 
        // E or East is chosen
        if (d < 0)
        {
            d = d + 1;
            x++;
        }
 
        // NE or North East is chosen
        // NE or North East is chosen
        else
        {
            d += 1+(m);
        }
 
        // Plot intermediate points
        // putpixel(x,y) is used to print pixel
        // of line in graphics
        // plotPoint(g,-x,y,Color.green);
        plotPoint(g,x,y,Color.green);
    }
  }
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
    //It is for creating the cartesian grids
    // public void paintGrid(Graphics g,int gap,int originx,int originy)
    // {
    //     g.setColor(Color.green);
        
    //     for(int i = gap;i<=getWidth();i+=gap)
    //     {
    //         g.drawLine(originx+i, originy-getHeight()/2, originx+i, originy+getHeight()/2);
    //         g.drawLine(originx-i, originy-getHeight()/2, originx-i, originy+getHeight()/2);
    //     }
    //     for(int i = gap;i<=getHeight();i+=gap)
    //     {
    //         g.drawLine(originx-getWidth()/2, originy+i, originx+getWidth()/2, originy+i);
    //         g.drawLine(originx-getWidth()/2, originy-i, originx+getWidth()/2, originy-i);

    //     }
    // }

    // int gap = 50;
    //It ia a normal paint function to call other functions to generate the graphics in applet
    public void paint(Graphics g){
    
            g.setColor(Color.orange);
            int originx=getX()+getWidth()/2;
            int originy=getY()+getHeight()/2;
            g.drawLine(originx-getWidth()/2, originy, originx+getWidth()/2, originy);
            g.drawLine(originx, originy-getHeight()/2, originx, originy+getHeight()/2);
            // paintGrid(g,gap,originx,originy);
            Color c=new Color(100,100,100);
            int i=0;
            // midpoint(g,1,2,200,300);
            // midpoint(g,1,2,300,200);
            int x1=200,y1=101;
            // while(y1!=0){
            // midpoint(g,1,2,x1+i,y1-i);
            // y1=y1-i;
            // i++;
            // try{
            //     Thread.sleep(500);
            // }
            // catch(Exception e)
            // {
            //     System.out.println(e);
            // }
            // }
            // midpoint(g,1,2,x1,y1-100);
            // int x1,x2,y1,y2;
            // Scanner in=new Scanner(System.in);
            // x1=in.nextInt();
            // x2=in.nextInt();
            // y1=in.nextInt();
            // y2=in.nextInt();
            midpoint(g,0,0,50,100);
            midpoint(g,0,0,100,50);
            midpoint(g,0,0,75,75);
            midpoint(g,-100,-100,200,100);
            midpoint(g,0,0,-100,100);
            // plotPoint(g,originx+(gap*2),originy-(gap*1),c);
            // g.fillOval(originx-10,originy-10,20,20);
            // plotPoint(g,originx,originy,Color.red);
            // g.clearRect(0,0,getHeight(),getWidth());
            
            // try{Thread.sleep(3000);}
            // catch (InterruptedException ie){ie.printStackTrace();}
            // g.clearRect(0,0,getWidth(),getHeight());
    }
}





// for negative slop
// public void negativemidpoint(Graphics g,int X1,int Y1,int X2,int Y2)
//     {
//          int dx = X2 - X1;
//     int dy = Y2 - Y1;
   
//     if(dy<=dx){
//     // initial value of decision parameter d
//     int d = 1/2-(dy/dx);
//     int x=X1,y=Y1;
//      // Plot initial given point
//     // putpixel(x,y) can be used to print pixel
//     // of line in graphics
//     plotPoint(g,x,y,Color.green);
 
//     // iterate through value of X
//     while (x < X2)
//     {
//         x++;
 
//         // E or East is chosen
//         if (d < 0)
//         {
//             d = d + 1-(dy/dx);
//             y++;
//         }
 
//         // NE or North East is chosen
//         else
//         {
//             d =d-(dy/dx);
//         }
 
//         // Plot intermediate points
//         // putpixel(x,y) is used to print pixel
//         // of line in graphics
//         plotPoint(g,x,y,Color.green);
//     }
//     }
   
//   else if(dx<dy)
//   {
//     // initial value of decision parameter d
//     int d = 1-(dy/(2*dx));
//     int x=X1,y=Y1;
//     // Plot initial given point
//     // putpixel(x,y) can be used to print pixel
//     // of line in graphics
//     plotPoint(g,x,y,Color.green);
 
//     // iterate through value of X
//     while (y < Y2)
//     {
//         y++;
 
//         // E or East is chosen
//         if (d < 0)
//         {
//             d = d + 1;
//             x++;
//         }
 
//         // NE or North East is chosen
//         // NE or North East is chosen
//         else
//         {
//             d += 1-(dy/dx);
//         }
 
//         // Plot intermediate points
//         // putpixel(x,y) is used to print pixel
//         // of line in graphics
//         plotPoint(g,x,y,Color.green);
//     }
//   }

// }