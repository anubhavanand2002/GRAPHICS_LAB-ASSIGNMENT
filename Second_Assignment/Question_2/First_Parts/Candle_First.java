/*This is the program for zoom-out and zoom-in using graphics */
/*Thus program can run only on Java-jdk(8) */


import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Candle_First extends Applet implements  ActionListener,MouseWheelListener{
    //It is for generating a rectangle corresponding to a particular point in cartesian coordinate syatem
        int gap = 4;
    public void plotPoint(Graphics g,int x,int y,Color c)
    {
        int r=255,gr=255,b=255;
        gr= 255-(x*x +y*y);
        if(gr<=0){
            b = 255 +gr/8;
             gr=0;
        }
        if(b<=0)
            b= 0;

        c = new Color(r,b,gr);
        g.setColor(c);
        if(y>=x*x/10)
            g.fillOval(
                (getX()+getWidth())/2+(x*gap)-(gap/2),
                (getY()+getHeight())/2-(y*gap)-(gap/2),
                gap,gap
            );
    }
    public void plotRect(Graphics g,int x,int y,Color C)
    {
        g.setColor(Color.orange);
        g.fillRect(
            (getX()+getWidth())/2-50,
            (getY()+getHeight())/2,
            100,300);
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
    //Round function
    public int round(float n) {
    if (n - (int)n < 0.5)
        return (int)n;
    return (int)(n + 1);
}
    //function to return line 
    public void DDALine(Graphics g,int x0, int y0, int x1, int y1,Color c) {
     
      //calculate dx and dy
      int dx = x1 - x0;
    int dy= y1 - y0;
       
    int step;
     
      //if dx > dy we will take step as dx
      //else we will take step as dy to draw the complete line
    if (Math.abs(dx) > Math.abs(dy))
        step = Math.abs(dx);
    else
        step = Math.abs(dy);
     
      //calculate x-increment and y-increment for each step
    float x_incr = (float)dx / step;
    float y_incr = (float)dy / step;
       
      //take the initial points as x and y
    float x = x0;
    float y = y0;
     
    for (int i = 0; i < step; i ++) {
        //putpixel(round(x), round(y), WHITE);
       plotPoint(g, round(x), round(y), c);
        x += x_incr;
        y += y_incr;
        //delay(10);
    }
}
    //It is for initialisation purpose
    public void init(){
        setBackground(Color.black);
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
        // setBackground(Color.black);
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
    public void infinite(Graphics g)
    {
        
        int x1=-150,x2=150;
        int a=200;
        Color c1;
        while(x1!=x2)
        {
            if(a-(x1*x1)>0)
            {
                int r = (int)(Math.random()*10);
                // if(x1<=10 && x1>=-10)
                //   c1=new Color(255,255,255);
                // else if(x1>=50 && x1<=200 ||x1<=-50 && x1>=-200)
                
                
                
            c1=new Color(255,255,255);
            DDALine(g,0,1,x1,((a-x1*x1)/10)+r,c1);
            DDALine(g,0,1,x1,((a-x1*x1)/10)+r+7,c1);
            DDALine(g,0,1,x1,((a-x1*x1)/10)+r+10,c1);
            DDALine(g,0,1,x1,((a-x1*x1)/10)+r+10,c1);
            DDALine(g,0,1,x1,((a-x1*x1)/10)+r+20,c1);
            DDALine(g,0,1,x1,((a-x1*x1)/10)+r+30,c1);
            }
            x1++;
        }
        
    }
    public void paint(Graphics g){
            plotRect(g,-10,0,Color.red);
            g.setColor(Color.orange);
            int originx=getX()+getWidth()/2;
            int originy=getY()+getHeight()/2;
            g.drawLine(originx-getWidth()/2, originy, originx+getWidth()/2, originy);
            g.drawLine(originx, originy-getHeight()/2, originx, originy+getHeight()/2);
            // paintGrid(g,gap,originx,originy);
            Color c=new Color(100,100,100);
        //    int i=1;
        //    int x1=-50;
        //    int y1=+150;
        //    int x2=50,y2=150;
        //    while(x1!=originx && y1!=+30 && x2!=0 && y2!=30)
        //    {
        //     DDALine(g,0,0,x1,y1);
        //     x1+=1;
        //     y1+=1;
        //     DDALine(g,0,0,x2,y2);
        //     x2-=1;
        //     y2+=1;
        //    }
       int i=0;
        
            try
            {

                Thread.sleep(100);
                repaint();
                //millisecond
                //...YOUR LOGIC
                  infinite(g);
            }

            
            catch (InterruptedException ie)
            {
            ie.printStackTrace();

            }
         
            // plotPoint(g,originx+(gap*2),originy-(gap*1),c);
            // g.fillOval(originx-10,originy-10,20,20);
            // plotPoint(g,originx,originy,Color.red);
            // g.clearRect(0,0,getHeight(),getWidth());
            
            // try{Thread.sleep(3000);}
            // catch (InterruptedException ie){ie.printStackTrace();}
            // g.clearRect(0,0,getWidth(),getHeight());
    }
}