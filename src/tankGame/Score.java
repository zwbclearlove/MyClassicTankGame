package tankGame;

import java.awt.*;

public class Score extends DynamicEffect {
    int keyFrame=16;
    int width=14;
    int height=7;
    int type;


    Score(int x,int y,int score){
        super(x,y,14,7);
        type=score/100;
    }
    @Override
    public void draw(){
        if(keyFrame==0){
            setDEImage(Toolkit.getDefaultToolkit().getImage("img/score_100.png"));
            setDead(true);
        }
        switch(type){
            case 1:
                setDEImage(Toolkit.getDefaultToolkit().getImage("img/score_100.png"));
                break;
            case 2:
                setDEImage(Toolkit.getDefaultToolkit().getImage("img/score_200.png"));
                break;
            case 3:
                setDEImage(Toolkit.getDefaultToolkit().getImage("img/score_300.png"));
                break;
            case 4:
                setDEImage(Toolkit.getDefaultToolkit().getImage("img/score_400.png"));
                break;
            case 5:
                setDEImage(Toolkit.getDefaultToolkit().getImage("img/score_500.png"));
                break;
        }
        if(keyFrame>=1){
            keyFrame--;
        }
    }
}
