package snakegame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JLabel;

public class Box  extends JLabel{
    public int mwidth=20;
    public int mDirection=Direction.Right;
    Box(){
        setBounds(100, 100, mwidth, mwidth);
    }
    @Override
     public void paint(Graphics g){
         Graphics2D g2=(Graphics2D)g;
        
        Rectangle2D rect=new Rectangle2D.Double(1,1,getWidth()-2,getHeight()-2);
        g2.setColor(Color.black);
        g2.setStroke(new BasicStroke(2));
        g2.draw(rect);
        g2.setColor(Color.black);
        g2.fill(rect);
     }
     
     public void goLeft(){
         int posx=getX();
                int posy=getY();
                
                posx-=mwidth;
                setBounds(posx,posy,mwidth,mwidth);
     }
     public void goRight(){
         int posx=getX();
                int posy=getY();
                
                posx+=mwidth;
                setBounds(posx,posy,mwidth,mwidth);
     }
     public void goUp(){
         int posx=getX();
                int posy=getY();
                
                posy-=mwidth;
                setBounds(posx,posy,mwidth,mwidth);
     }
     public void goDown(){
         int posx=getX();
                int posy=getY();
                
                posy+=mwidth;
                setBounds(posx,posy,mwidth,mwidth);
     }
     public Box Tail(){
         Box box=new Box();
         int X=getX();
         int Y=getY();
         
         box.setBounds(X, Y, mwidth, mwidth);
         
         box.mDirection=-mDirection;
         box.Move();
         box.mDirection=mDirection;
         return box;
     }
     
    public void Move(){
        if (mDirection==Direction.Left) {
           goLeft();
        }
        else if (mDirection==Direction.Right) {
            goRight();
        }
        else if (mDirection==Direction.Down) {
            goDown();
        }
        else {
         goUp();
        }
    }
}
