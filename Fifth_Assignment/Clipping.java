import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Clipping extends Applet implements ActionListener, MouseWheelListener {

  int originX, originY;
  int height, width;
  int gap = 40;
  int temp = 0;
  static final int INSIDE = 0; // 0000
  static final int LEFT = 1; // 0001
  static final int RIGHT = 2; // 0010
  static final int BOTTOM = 4; // 0100
  static final int TOP = 8; // 1000

  // static final int x_max = 100;
  // static final int y_max = 100;
  // static final int x_min = -100;
  // static final int y_min = -100;

  static final int x_max = 25;
  static final int y_max = 25;
  static final int x_min = -25;
  static final int y_min = -25;

  Button b1 = new Button(" + ");
  Button b2 = new Button(" - ");
  Button b3 = new Button(" Clip ");

  public void init() {
    setBackground(Color.white);
    b1.setBackground(Color.red);
    b2.setBackground(Color.green);
    b3.setBackground(Color.GREEN);
    add(b1);
    add(b2);
    add(b3);
    addMouseWheelListener(this);
    b1.addActionListener(this);
    b2.addActionListener(this);
    b3.addActionListener(this);
  }

  

  //Function for plotting points
  public void plotPoint(Graphics g, int x,int y ,Color c){
    int originX = (getX() + getWidth()) / 2;
    int originY = (getY() + getHeight()) / 2;
    g.setColor(c);
    g.fillRect(originX+(gap*x)-(gap/4), originY-(gap*y)-(gap/4),gap ,gap );
}

  //function to make grid
  public void makeGrid(Graphics g)
  {
      if(gap<=0|| gap>getHeight())
          return ;
      int originX = (getX() + getWidth()) / 2;
      int originY = (getY() + getHeight()) / 2;
      g.setColor(Color.red);
      g.drawLine(originX, originY - getHeight() / 2, originX, originY + getHeight() / 2);
      g.drawLine(originX - getWidth() / 2, originY, originX + getWidth() / 2, originY);
      g.setColor(Color.black);
      for (int x = gap; x <= getWidth(); x += gap) {
         
          g.drawLine(originX + x, 0, originX + x, getHeight());
          g.drawLine(originX - x, 0, originX - x, getHeight());
        
      }
      for (int y = gap; y <= getHeight(); y += gap) {
       
          g.drawLine(0, originY + y, getWidth(), originY + y);
          g.drawLine(0, originY - y, getWidth(), originY - y);
      }
  }


  //Function for the buttons
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == b1) zoom(10);
    if (e.getSource() == b2) zoom(-10);
    if (e.getSource() == b3) {
      if (temp == 0) temp = 1; else temp = 0;
      repaint();
    }
  }

  //Function for the mousewheel
  public void mouseWheelMoved(MouseWheelEvent e) {
    int z = e.getWheelRotation();
    zoom(z);
  }

  //Function for the zoom in feature
  public void zoom(int i)
  {
      if(i>0)
          gap+=gap/10+1;
      else if(i<0)
          gap-=gap/10+1;
      repaint();
      
  }
  //function to compute code
  static int computeCode(int x, int y) {
    // initialized as being inside
    int code = INSIDE;

    if (x < x_min) code |= LEFT; else if ( // to the left of rectangle
      x > x_max
    ) code |= RIGHT; // to the right of rectangle
    if (y < y_min) code |= BOTTOM; else if ( // below the rectangle
      y > y_max
    ) code |= TOP; // above the rectangle

    return code;
  }

  public void cohenSutherlandClip(Graphics g, int x1, int y1, int x2, int y2) {
    // Compute region codes for P1, P2
    int code1 = computeCode(x1, y1);
    int code2 = computeCode(x2, y2);

    // Initialize line as outside the rectangular window
    boolean accept = false;

    while (true) {
      if ((code1 == 0) && (code2 == 0)) {
        // If both endpoints lie within rectangle
        accept = true;
        break;
      } else if ((code1 & code2) != 0) {
        // If both endpoints are outside rectangle,
        // in same region
        break;
      } else {
        // Some segment of line lies within the
        // rectangle
        int code_out;
        int x = 0, y = 0;

        // At least one endpoint is outside the
        // rectangle, pick it.
        if (code1 != 0) code_out = code1; else code_out = code2;

        // Find intersection point;
        // using formulas y = y1 + slope * (x - x1),
        // x = x1 + (1 / slope) * (y - y1)
        if ((code_out & TOP) != 0) {
          // point is above the clip rectangle
          x = x1 + (x2 - x1) * (y_max - y1) / (y2 - y1);
          y = y_max;
        } else if ((code_out & BOTTOM) != 0) {
          // point is below the rectangle
          x = x1 + (x2 - x1) * (y_min - y1) / (y2 - y1);
          y = y_min;
        } else if ((code_out & RIGHT) != 0) {
          // point is to the right of rectangle
          y = y1 + (y2 - y1) * (x_max - x1) / (x2 - x1);
          x = x_max;
        } else if ((code_out & LEFT) != 0) {
          // point is to the left of rectangle
          y = y1 + (y2 - y1) * (x_min - x1) / (x2 - x1);
          x = x_min;
        }

        // Now intersection point x, y is found
        // We replace point outside rectangle
        // by intersection point
        if (code_out == code1) {
          x1 = x;
          y1 = y;
          code1 = computeCode(x1, y1);
        } else {
          x2 = x;
          y2 = y;
          code2 = computeCode(x2, y2);
        }
      }
    }
    if (accept) {
      DDALine(g, x1, y1, x2, y2,Color.RED);
    } else System.out.println("Line rejected");
  }

  void DDALine(Graphics g, int x0, int y0, int x1, int y1,Color c) {
    int dx = (x1 - x0);
    int dy = (y1 - y0);

    int step;
    if (Math.abs(dx) > Math.abs(dy)) {
      step = Math.abs(dx);
    } else {
      step = Math.abs(dy);
    }

    float x_incr = (float) dx / step;
    float y_incr = (float) dy / step;
    float x = (float) x0;
    float y = (float) y0;

    for (int i = 0; i < step; i++) {
      plotPoint(g, Math.round(x), Math.round(y), c);
      x += x_incr;
      y += y_incr;
    }
  }
  //paint function
  public void paint(Graphics g) {
    g.setColor(Color.white);
    height = getHeight();
    width = getWidth();
    originX = (getX() + width) / 2;
    originY = (getY() + height) / 2;
    //drawGrid(g);
    DDALine(g, x_min, y_min, x_max, y_min,Color.GREEN);
    DDALine(g, x_min, y_max, x_max, y_max,Color.GREEN);
    DDALine(g, x_min, y_min, x_min, y_max,Color.GREEN);
    DDALine(g, x_max, y_min, x_max, y_max,Color.GREEN);
    makeGrid(g);
    if (temp == 0) {
   //Simple

    // DDALine(g, -50, -50, 150, 150,Color.red);
    // DDALine(g, 50, 50, -150, 150,Color.red);
    // DDALine(g, 50, -50, -150, 150,Color.red);
    
    //Pentagon

    // DDALine(g, 0, 150, -150, 0,Color.red);
    // DDALine(g, 0, 150, 150, 0,Color.red);
    // DDALine(g, -150, 0, -150, -150,Color.red);
    // DDALine(g, 150, 0, 150, -150,Color.red);
    // DDALine(g, -150, -150, 150, -150,Color.red);
   
    //for making square
      DDALine(g, 0, -40, -40, 0,Color.GREEN);
      DDALine(g, 0, -40, 40, 0,Color.GREEN);
      DDALine(g, 0, 40, 40, 0,Color.GREEN);
      DDALine(g, 0, 40, -40, 0,Color.GREEN);
    } else {
        //Simple

    // cohenSutherlandClip(g, -50, -50, 150, 150);
    // cohenSutherlandClip(g, 50, 50, -150, 150);
    // cohenSutherlandClip(g, 50, -50, -150, 150);

    //Pentagon

    // cohenSutherlandClip(g, 0, 150, -150, 0);
    // cohenSutherlandClip(g, 0, 150, 150, 0);
    // cohenSutherlandClip(g, -150, 0, 0, -300);
    // cohenSutherlandClip(g, 150, 0, 0, -300);
    // cohenSutherlandClip(g, -150, -300, 150, -300);

    //for making square
      cohenSutherlandClip(g, 0, -40, -40, 0);
      cohenSutherlandClip(g, 0, -40, 40, 0);
      cohenSutherlandClip(g, 0, 40, 40, 0);
      cohenSutherlandClip(g, 0, 40, -40, 0);
    }
  }
}



















