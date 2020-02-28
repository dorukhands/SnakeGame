package snakegame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Snake extends JLabel{
    
    public Box mhead=new Box();
    public Timer mtimer=null;
    public Forage mforage=new Forage();
    public Random mRandom=null;
    public ArrayList<Box>Liste=new ArrayList<Box>();
    
    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
       
        Graphics2D g2=(Graphics2D)g;
        
        Rectangle2D rect=new Rectangle2D.Double(5,5,getWidth()-10,getHeight()-10);
        g2.setColor(Color.green);
        g2.setStroke(new BasicStroke(10));
        g2.draw(rect);
    }
    Snake(){
        mRandom=new Random(System.currentTimeMillis());
        addKeyListener(new keyboardControl());
        setFocusable(true);
        mtimer=new Timer(100,new TimerControl());
        mtimer.start();
        Liste.add(mhead);
        for (int i = 1; i < 10; i++) {
           AddQueue();
        }
        add(mforage);
        add(mhead);
    }
    
    public void AddQueue(){
         Box b=Liste.get(Liste.size()-1).Tail();
            Liste.add(b);
            add(b);
    }
    public void AddForage(){
        int width=getWidth()-30-mforage.mwidth;
        int height=getHeight()-30-mforage.mwidth;
        
        int Posx=20+Math.abs(mRandom.nextInt())%width;
        int Posy=20+Math.abs(mRandom.nextInt())%height;
        Posx=Posx-Posx%20;
        Posy=Posy-Posy%20;
        
        for (int i = 0; i <Liste.size(); i++) {
            if ((Posx==Liste.get(i).getX()) && (Posy==Liste.get(i).getY())) {
                AddForage();
                return;
            }
        }
        
        mforage.setPosition(Posx, Posy);
    }
    public void AllMove(){
        for (int i = Liste.size()-1; i>0; i--) {
            Box prev=Liste.get(i-1);
            Box next=Liste.get(i);
            Liste.get(i).Move();
            next.mDirection=prev.mDirection;
        }
        mhead.Move();
    }
    public boolean collision(){
        int pen=10;
        int width=getWidth();
        int height=getHeight();
        if (mhead.getX()<=10) {
            return true;
        }
        if (mhead.getX()+mhead.mwidth>=width-pen) {
            return true;
        }
        
            if (mhead.getY()<=10) {
            return true;
        }
        if (mhead.getY()+mhead.mwidth>=height-pen) {
            return true;
        }
        
        for (int i = 1; i < Liste.size(); i++) {
            int x=Liste.get(i).getX();
            int y=Liste.get(i).getY();
            if ((x==mhead.getX())&&(y==mhead.getY())) 
                return true;
            
        }
        if ((mforage.getX()==mhead.getX())&&(mforage.getY()==mhead.getY())) {
            AddQueue();
            AddForage();
        }
        return false;
    }
    
    class keyboardControl implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode()==KeyEvent.VK_LEFT) {
                
                if (mhead.mDirection!=Direction.Right) {
                     mhead.mDirection=Direction.Left;
                }
            }
               if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
                   if (mhead.mDirection!=Direction.Left) {
                     mhead.mDirection=Direction.Right;
                }
            }
                  if (e.getKeyCode()==KeyEvent.VK_UP) {
                      if (mhead.mDirection!=Direction.Down) {
                     mhead.mDirection=Direction.Up;
                }
            }
                  if (e.getKeyCode()==KeyEvent.VK_DOWN) {
                        if (mhead.mDirection!=Direction.Up) {
                     mhead.mDirection=Direction.Down;
                }
            }   
                    
                  
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
        
    }
    
    
    class TimerControl implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            AllMove();
              if (collision()) {
                mtimer.stop();
            }
        }
        
    }
}
