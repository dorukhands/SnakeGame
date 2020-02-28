package snakegame;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class MainWindow extends JFrame{
    
    private int width=600;
    private int height=600;
    
    private static MainWindow mWindow=null;
    private MainWindow(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        SetDimension(width, height);
        setResizable(false);
        setTitle("Snake Game");
        
      Snake s=new Snake();
      add(s);
        
       
    }
    public static MainWindow GetWindow(){
        if (mWindow==null) {
            mWindow=new MainWindow();
        }
        return mWindow;
    }
  
    public void SetDimension(int mwidth,int mheight){
        Dimension Dim=Toolkit.getDefaultToolkit().getScreenSize();
        int Posx=0;
        int Posy=0;
        
        if (mwidth+100>Dim.width) {
            mwidth=Dim.width-100;
        }
        if (mheight+100>Dim.height) {
            mheight=Dim.height-100;
        }
        
        Posx=(Dim.width-mwidth)/2;
        Posy=(Dim.height-mheight)/2;
        setBounds(Posx, Posy, mwidth, mheight);
    }
    
}
