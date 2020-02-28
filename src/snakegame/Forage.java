package snakegame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javafx.scene.shape.Ellipse;
import javax.swing.JLabel;

public class Forage extends JLabel{
    public int mwidth=20;
    Forage(){
        setPosition(20, 20);
    }
    @Override
     public void paint(Graphics g){
         Graphics2D g2=(Graphics2D)g;
        
        Ellipse2D elp=new Ellipse2D.Double(1,1,mwidth-2,mwidth-2);
        g2.setColor(Color.red);
        g2.setStroke(new BasicStroke(2));
        g2.draw(elp);
        g2.setColor(Color.red);
        g2.fill(elp);
     }
     public void setPosition(int Posx,int Posy){
         setBounds(Posx,Posy,mwidth,mwidth);
     }
}
