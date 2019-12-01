package tankGame;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Music extends Thread {
    Player play;
    File music;

    public Music(File file){
        this.music=file;
    }

    @Override
    public void run(){
        super.run();
        try{
            play();
        }catch (FileNotFoundException| JavaLayerException e){
            e.printStackTrace();
        }
    }
    public  void play()throws FileNotFoundException,JavaLayerException{
        BufferedInputStream buffer=new BufferedInputStream(new FileInputStream(music));
        play=new Player(buffer);
        play.play();

    }
}
