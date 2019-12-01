package tankGame;
import javazoom.jl.decoder.JavaLayerException;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;


public class RunGame extends JFrame {
    public static int FrameWidth = 260+20;
    public static int FrameHeight = 260+20;
    MyPanel mp=new MyPanel(0,false);
    StartGame sg=new StartGame();
    File music=new File("music/start_game.mp3");
    Music start=new Music(music);
    public void prepareUI(){

        Container c=getContentPane();
        setTitle("battle city");
        setSize(FrameWidth+120,FrameHeight+18);
        setLocation(100,100);
        c.add(mp);
        //addKeyListener(sg);

        addKeyListener(mp.atank.c);





        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        //Thread a=new Thread(sg);
        Thread b=new Thread(mp);
        //a.start();
        //try{
        //    a.join();
        //}catch(InterruptedException e){
        //    e.printStackTrace();
        //}


        b.start();
        new Thread(start).start();



    }
    public static void main(String[] args){

        new RunGame().prepareUI();
    }

}
