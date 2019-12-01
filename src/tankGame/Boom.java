package tankGame;

import java.awt.*;

public class Boom extends DynamicEffect {

    private int keyFrame=8;

    public int getKeyFrame() {
        return keyFrame;
    }

    public void setKeyFrame(int keyFrame) {
        this.keyFrame = keyFrame;
    }

    Boom(int x,int y,int width,int height){
        super(x,y,width,height);
    }
    @Override
    public void draw(){
        switch(this.keyFrame--){
            case 8:
                setDEImage(Toolkit.getDefaultToolkit().getImage("img/boom8.gif"));
                break;
            case 7:
                setDEImage(Toolkit.getDefaultToolkit().getImage("img/boom7.gif"));
                break;
            case 6:
                setDEImage(Toolkit.getDefaultToolkit().getImage("img/boom6.gif"));
                break;
            case 5:
                setDEImage(Toolkit.getDefaultToolkit().getImage("img/boom5.gif"));
                break;
            case 4:
                setDEImage(Toolkit.getDefaultToolkit().getImage("img/boom4.gif"));
                break;
            case 3:
                setDEImage(Toolkit.getDefaultToolkit().getImage("img/boom3.gif"));
                break;
            case 2:
                setDEImage(Toolkit.getDefaultToolkit().getImage("img/boom2.gif"));
                break;
            case 1:
                setDEImage(Toolkit.getDefaultToolkit().getImage("img/boom1.gif"));
                break;
            default:
                setDead(true);
                break;

        }
        System.out.println("boom"+keyFrame);

    }

}
