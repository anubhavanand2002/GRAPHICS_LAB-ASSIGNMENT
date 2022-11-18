import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class B_Spline extends Applet implements  ActionListener,MouseWheelListener{
    //It is for generating a rectangle corresponding to a particular point in cartesian coordinate syatem
        int gap = 4;
    public void plotPoint(Graphics g,int x,int y,Color c)
    {
        g.setColor(c);
        g.fillRect(
            (getX()+getWidth())/2+(x*gap)-(gap/2),
            (getY()+getHeight())/2-(y*gap)-(gap/2),
            3*gap,3*gap
        );
    }
    public int slope(int x1,int x2,int y1,int y2)
    {
        int x=x2-x1;
        int y=y2-y1;
        int m=y/x;
        return m;
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
    // It is for creating the cartesian grids
    public void paintGrid(Graphics g,int gap,int originx,int originy)
    {
        g.setColor(Color.green);
        
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

    public long ncr(int n, int r) {
        long result = 1;

        for (int i = 1; i <= r; i++) {
            result *= n - r + i;
            result /= i;
        }

        return result;
    }

//it is made using b_spline
    public void drawBSplineCurve(ArrayList<Integer> polyx,ArrayList<Integer> polyy)
    {
        int n, d;
        // cout << "Enter degree of curve: ";
        // cin >> d;
        d = 3;
        n = polyx.size();
        ArrayList<Double> uVec = new ArrayList<>(); 
        int i;
        for(i=0;i<n+d;i++)
        {
            uVec.add(((double)i/(n+d-1)));
        }
        double x, y, basis, u;
        for(u=0;u<=1;u+=0.0001)
        {
            x = 0;
            y = 0;
            for(i=0;i<polyx.size();i++)
            {
                basis = blend(uVec, u, i, d);
                x += basis*polyx.get(i);
                y += basis*polyy.get(i);
            }
            // putpixel(Math.round(x), Math.round(y), YELLOW);
            plotPoint(getGraphics(),(int)x,(int)y, Color.YELLOW);
        }
    }
    
    public double blend(ArrayList<Double> uVec, double u, int k, int d)
{
    if(d==1)
    {
        if(uVec.get(k)<=u && u< uVec.get(k+1) )
            return 1;
        return 0;
    }
    double b;
    b = ((u-uVec.get(k))/(uVec.get(k+d-1)-uVec.get(k))*blend(uVec, u, k, d-1)) + ((uVec.get(k+d)-u)/(uVec.get(k+d)-uVec.get(k+1))*blend(uVec, u, k+1, d-1));
   return b;
}


//it is bezier
    public void b_Spline(int[] x , int[] y)
    {
        double xu = 0.0 , yu = 0.0 , u = 0.0 ;
        for(int i=0;i<x.length;i++)
        {
                plotPoint(getGraphics(), x[i], y[i], Color.white);
        }
        for(u = 0.0 ; u <= 1.0 ; u += 0.0001)
        {
            xu = Math.pow(1-u,3)*x[0]+3*u*Math.pow(1-u,2)*x[1]+3*Math.pow(u,2)*(1-u)*x[2]
                 +Math.pow(u,3)*x[3];
            yu = Math.pow(1-u,3)*y[0]+3*u*Math.pow(1-u,2)*y[1]+3*Math.pow(u,2)*(1-u)*y[2]
                +Math.pow(u,3)*y[3];
            plotPoint(getGraphics(), (int)xu , (int)yu,Color.orange) ;
        }
    }

//It ia a normal paint function to call other functions to generate the graphics in applet
    public void paint(Graphics g){
    
            g.setColor(Color.orange);
            int originx=getX()+getWidth()/2;
            int originy=getY()+getHeight()/2;
            g.drawLine(originx-getWidth()/2, originy, originx+getWidth()/2, originy);
            g.drawLine(originx, originy-getHeight()/2, originx, originy+getHeight()/2);
            Point pts[] = new Point[12];
            int k=2;
            int[] x0 = {-100*k , -80*k , -60*k , -40*k};
            int[] y0 = {0 , -20*k , -30*k , 0};
            int[] x1 = {-40*k , -20*k , 0 , 20*k};
            int[] y1 = {0 , 20*k , 25*k , 0};
            int[] x2 = {20*k , 40*k , 80*k , 60*k};
            int[] y2 = {0 , -20*k , -25*k , 0};
            b_Spline(x0,y0);
            b_Spline(x1,y1);
            b_Spline(x2,y2);
            k=4;
            int[] x3 = {-100*k , -80*k , -60*k , -40*k};
            int[] y3 = {0 , -20*k , -30*k , 0};
            int[] x4 = {-40*k , -20*k , 0 , 20*k};
            int[] y4 = {0 , 20*k , 25*k , 0};
            int[] x5 = {20*k , 40*k , 80*k , 60*k};
            int[] y5 = {0 , -20*k , -25*k , 0};
            b_Spline(x3,y3);
            b_Spline(x4,y4);
            b_Spline(x5,y5);
            paintGrid(g, 40, originx, originy);
        //     int k = 1;
       
        //     ArrayList<Integer> xarr = new ArrayList<>(Arrays.asList(-100*k, -90*k, -60*k, -40*k,100*k,90*k,60*k, 40*k));
        //     ArrayList<Integer> yarr = new ArrayList<>(Arrays.asList(   10*k, -80*k, -80*k,   -10*k,10*k,80*k,80*k,-10*k));
        
        // drawBSplineCurve(xarr,yarr);

            
    }
}





