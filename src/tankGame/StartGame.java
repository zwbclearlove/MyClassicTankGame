package tankGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class StartGame extends JPanel implements Runnable, KeyListener {
    Image startImage=Toolkit.getDefaultToolkit().getImage("img/startgame1.png");
    int mode=1;
    boolean quit=false;
    Blocks score_table=new Blocks(260,0,BLockstype.GREY);
    public Image getStartImage() {
        return startImage;
    }


    public void setStartImage(Image startImage) {
        this.startImage = startImage;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw();
        g.drawImage(quit?null:(mode==1)?Toolkit.getDefaultToolkit().getImage("img/startgame1.png"):Toolkit.getDefaultToolkit().getImage("img/startgame2.png"),0,0,260,260,this);
        score_table.draw();
        g.drawImage(quit?null:score_table.getBlocksImage(),260,0,120,260,this);
    }
    @Override
    public void run(){
        while (true){

            try {
                repaint();
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(quit)
            {
                break;
            }
        }

    }
    public void draw(){
        if(mode==1){
            setStartImage(Toolkit.getDefaultToolkit().getImage("img/startgame1.png"));
        }else if(mode==2){
            setStartImage(Toolkit.getDefaultToolkit().getImage("img/startgame2.png"));
        }
    }
    @Override
    public void keyTyped(KeyEvent e){

    }
    @Override
    public void keyPressed(KeyEvent e){
        int d = e.getKeyCode();
        if(d==KeyEvent.VK_UP){
            mode=1;

            System.out.print("1");
        }
        else if(d==KeyEvent.VK_DOWN){
            mode=2;

            System.out.print("2");
        }
        else if(d==KeyEvent.VK_ENTER){
            quit=true;
            System.out.print("quit");
        }
    }
    @Override
    public void keyReleased(KeyEvent e){

    }


}
