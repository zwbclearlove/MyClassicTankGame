package tankGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.*;


public class MyPanel extends JPanel implements Runnable {
    int total_score=0;
    int level=0;
    int timeFlag;
    int shovelFlag;
    boolean gameOver=false;
    boolean  gameFinish=false;
    int gameOverFlag=60;
    int gameFinishFlag=60;
    int etankNum=5;
    int etankDead=5;
    int enemyTankDied=0;
    boolean isEndlessMode;
    boolean isPause=false;

    Music boomMusic=new Music(new File("music/boom.mp3"));
    Music get_bonusMusic=new Music(new File("music/get_bonus.mp3"));
    Music medieMusic=new Music(new File("music/me_die.mp3"));
    Music rank_addMusic=new Music(new File("music/rank_add.mp3"));
    Music helmetMusic=new Music(new File("music/helmet.mp3"));
    Music life_addMusic=new Music(new File("music/life_add.mp3"));
    Music pauseMusic=new Music(new File("music/pause.mp3"));
    Blocks background=new Blocks(0,0,BLockstype.BLACK);
    Blocks score_table=new Blocks(260,0,BLockstype.GREY);
    MyTank atank=new MyTank(90,240,false);
    ArrayList<EnemyTank> etank=new ArrayList<>();
    ArrayList<Boom> boom=new ArrayList<>();
    ArrayList<NewTankCreating> ntc=new ArrayList<>();
    ArrayList<Bonus> bonuses=new ArrayList<>();
    ArrayList<Score> scores=new ArrayList<>();
    NewTankCreating createAtank=new NewTankCreating(90,220);
    TankHelmet tankHelmet=new TankHelmet(atank.getX(),atank.getY(),50);
    Map map;
    //FriendTank btank=new FriendTank(300,100,false);
    MyPanel(int level,boolean isEndless){
        isEndlessMode=isEndless;
        map=new Map(level);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        if(isPause!=atank.isPause){
            isPause=atank.isPause;
            new Thread(pauseMusic).start();
        }

        //g.fillRect(0,0,RunGame.FrameWidth,RunGame.FrameHeight);
        //g.clearRect();
        background.draw();
        g.drawImage(background.getBlocksImage(),0,0,260,260,this);
        score_table.draw();
        g.drawImage(score_table.getBlocksImage(),260,0,120,260,this);
        g.setFont(new Font("consolas", Font.BOLD,15));


        //paintATank(g,atank);
        atank.draw();
        tankHelmet.draw();
        createAtank.draw();
        if(tankHelmet.getKeyFrame()==0)atank.setUnbeatable(true);
        else atank.setUnbeatable(false);
        if(createAtank.getKeyFrame()<1){
            atank.setCreating(false);
        }
        boolean hit=false;
        g.drawImage(atank.getTankImage(),atank.getX(), atank.getY(),atank.getWidth(), atank.getHeight(),this);
        g.drawImage(tankHelmet.getDEImage(),atank.getX(),atank.getY(),tankHelmet.getWidth(),tankHelmet.getHeight(),this);
        g.drawImage(createAtank.getDEImage(),atank.getX(),atank.getY(),tankHelmet.getWidth(),tankHelmet.getHeight(),this);
        for(int i=0;i<etank.size();i++){
            if(atank.isCollisionTank(etank.get(i))){
                hit=true;
                break;
            }
        }
        if(!atank.isCollisionBlocks(map)&&!hit&&!atank.isCreating()&&!atank.isDead()&&!isPause)
            atank.move();
        System.out.println("in "+atank.getX()+" "+atank.getY()+(atank.isDead()?"   Dead":("  current life = "+atank.getLife())));
        for(int i=0;i<bonuses.size();i++){
            Bonus n=bonuses.get(i);
            if(atank.isCollisionBonus(n)){
                total_score+=500;
                scores.add(new Score(n.getX(),n.getY(),500));
                switch (n.getBonusType()){
                    case SHOVEL:
                        System.out.println("shovel");
                        map.shovelChange(true);
                        shovelFlag=250;
                        new Thread(helmetMusic).start();
                        break;
                    case HELMET:
                        tankHelmet=new TankHelmet(atank.getX(), atank.getY(),500);
                        new Thread(helmetMusic).start();
                        break;
                    case TIME:
                        System.out.println("time");
                        timeFlag=250;
                        new Thread(helmetMusic).start();
                        break;
                    case STAR:
                        atank.rankUP();
                        new Thread(rank_addMusic).start();
                        break;
                    case LIFE:
                        atank.lifeUP();
                        new Thread(life_addMusic).start();
                        System.out.println("current atank life :"+atank.getLife());
                        break;
                    case BOOM:
                        for(int j=0;j<etank.size();j++){
                            boom.add(new Boom(etank.get(j).getX(),etank.get(j).getY(),20,20));
                        }
                        if(isEndlessMode){
                            enemyTankDied+=etank.size();
                        }else{
                            etankDead-=etank.size();
                        }

                        new Thread(boomMusic).start();
                        etank=new ArrayList<>();

                        break;
                    case GUN:
                        atank.rankUP();
                        atank.rankUP();
                        atank.rankUP();
                        new Thread(rank_addMusic).start();
                        break;
                }
                n.setDead(true);
            }
        }
        for(int i=0;i< atank.getBullets().size();i++){
            Bullet b= atank.getBullets().get(i);
            for(int j=0;j<etank.size();j++){
                if(etank.get(j).isBounsTank()&&b.isCollisonTank(etank.get(j))){
                    Random r=new Random();
                    bonuses.add(new Bonus(r.nextInt(48)*5,r.nextInt(48)*5,500));
                    new Thread(get_bonusMusic).start();
                    System.out.println("bonus enemy was hit!");
                    scores.add(new Score(etank.get(j).getX(),etank.get(j).getY(),200));
                    total_score+=200;
                }else if(!etank.get(j).isBounsTank()&&b.isCollisonTank(etank.get(j))){
                    System.out.println("enemy was hit!");
                }
            }
            if(b.isDead()==true){
                boom.add(new Boom(b.getX(),b.getY(),6,6));

                atank.getBullets().remove(i);
            }else{
                b.draw();
                g.drawImage(b.getBulletImage(),b.getX(),b.getY(),b.getWidth(),b.getHeight(),this);
                if(!b.isCollisionBlocks(map)&&!isPause)
                    b.move();
            }
        }
        for(int i=0;i<etank.size();i++){
            //System.out.println("enemy in "+etank.get(i).getX()+" "+etank.get(i).getY());
            for(int j=0;j< etank.get(i).getBullets().size();j++){
                Bullet b=etank.get(i).getBullets().get(j);
                if(b.isCollisonTank(atank)&&atank.isUnbeatable()){
                    System.out.println("you are killed!");
                    if(atank.getRank()>=1)atank.rankDOWN();
                    else{
                        boom.add(new Boom(atank.getX(),atank.getY(),20,20));
                        new Thread(medieMusic).start();
                        if(!atank.isDead()){
                            atank.setX(90);
                            atank.setY(240);
                            if(atank.getLife()>1){
                                tankHelmet=new TankHelmet(atank.getX(), atank.getY(),50);
                                atank.setCreating(true);
                                createAtank.setKeyFrame(19);
                            }
                            atank.lifeLoss();
                        }else {
                            atank.setCreating(true);
                            atank.setDead(true);
                            createAtank.setKeyFrame(0);
                        }
                    }
                }
                for(int k=0;k<atank.getBullets().size();k++){
                    Bullet c=atank.getBullets().get(k);
                    if(b.getCurrentPosition().intersects(c.getCurrentPosition())) {
                        System.out.println("two bullet was dead");

                    }
                }
                if(b.isDead()){
                    boom.add(new Boom(b.getX(),b.getY(),6,6));
                    etank.get(i).getBullets().remove(j);
                }else{
                    b.draw();
                    g.drawImage(b.getBulletImage(),b.getX(),b.getY(),b.getWidth(),b.getHeight(),this);
                    if(!b.isCollisionBlocks(map)&&!isPause)
                        b.move();
                }


            }
            if(etank.get(i).isDead()){

                scores.add(new Score(etank.get(i).getX(),etank.get(i).getY(),(etank.get(i).tankType+1)*100));
                total_score+=(etank.get(i).tankType+1)*100;
                boom.add(new Boom(etank.get(i).getX(),etank.get(i).getY(),20,20));
                new Thread(boomMusic).start();
                etank.remove(i);

                if(isEndlessMode){
                    enemyTankDied++;
                }else {
                    if(etankDead>=1)etankDead--;
                }


            }else {
                etank.get(i).draw();
                g.drawImage(etank.get(i).getTankImage(),etank.get(i).getX(),etank.get(i).getY(),etank.get(i).getWidth(),etank.get(i).getHeight(),this);
                hit=false;
                for(int j=0;j<etank.size();j++){
                    if(j==i){
                        if(etank.get(i).isCollisionTank(atank)){
                            hit=true;
                            break;
                        }
                    }else{
                        if(etank.get(i).isCollisionTank(etank.get(j))){
                            hit=true;
                            break;
                        }
                    }
                }
                if(timeFlag==0&&!isPause) {
                    if (!etank.get(i).isCollisionBlocks(map) && !hit)
                        etank.get(i).move();
                    else
                        etank.get(i).changeDirect();
                }

            }
            //paintATank(g,etank.get(i));
        }
        map.draw(g);
        for(int i=0;i<boom.size();i++){
            if(boom.get(i).isDead()){
                boom.remove(i);
            } else{
                Boom m=boom.get(i);
                m.draw();
                g.drawImage(m.getDEImage(),m.getX(),m.getY(),m.getWidth(),m.getHeight(),this);
            }
        }
        for(int i=0;i<ntc.size();i++){
            if(ntc.get(i).isDead()){

                etank.add(new EnemyTank(ntc.get(i).getX(),ntc.get(i).getY()));


                ntc.remove(i);
            } else{
                NewTankCreating n=ntc.get(i);
                n.draw();
                g.drawImage(n.getDEImage(),n.getX(),n.getY(),n.getWidth(),n.getHeight(),this);
            }
        }
        for(int i=0;i<bonuses.size();i++){
            if(bonuses.get(i).isDead()){
                bonuses.remove(i);
            } else{
                Bonus n=bonuses.get(i);
                n.draw();
                System.out.println("bonus "+n.getBonusType()+" in "+n.getX()+" "+n.getY());
                g.drawImage(n.getBounsImage(),n.getX(),n.getY(),n.getWidth(),n.getHeight(),this);
            }

        }
        for(int i=0;i<scores.size();i++){
            if(scores.get(i).isDead()){
                scores.remove(i);
            } else{
                Score n=scores.get(i);
                n.draw();

                g.drawImage(n.getDEImage(),n.getX(),n.getY(),n.getWidth(),n.getHeight(),this);
            }

        }
        if(timeFlag>=1)timeFlag--;
        if(shovelFlag>=1)shovelFlag--;
        if(shovelFlag<=50&&shovelFlag>0) {
            if (shovelFlag%4 == 0||shovelFlag%4==1) {
               map.shovelChange(false);
            }
            if (shovelFlag%4 == 2||shovelFlag%4==3) {
               map.shovelChange(true);
            }
        }
        if(etankDead==0)gameFinish=true;
        if(gameFinish){
            System.out.println("Game finish!!!");
            g.drawString("Game finish!!!",270,130);
        }
        if(atank.isDead()||map.gameover()){
            gameOver=true;
            System.out.println("Game over");
            g.drawString("Game over!!!",270,130);
        }

        if(isEndlessMode){
            if(isPause)
                g.drawString("PAUSE",270,130);
            g.drawString("life : "+atank.getLife(),270,40);
            g.drawString("enemy : "+(enemyTankDied),270,60);
            g.drawString("score : "+total_score,270,80);
        }else{
            if(isPause)
                g.drawString("PAUSE",270,130);
            g.drawString("level : "+(level+1),270,20);
            g.drawString("life : "+atank.getLife(),270,40);
            g.drawString("enemy : "+(etankNum),270,60);
            g.drawString("score : "+total_score,270,80);
            System.out.println("rest enemy tank num = "+etankNum);
            System.out.println("total score = "+total_score);
        }


    }


    @Override
    public void run()
    {



        while (true){
            if(isEndlessMode){
                if(etank.size()<10&&canCreateNewEnemyTank(atank,etank,ntc)){
                    Random r=new Random();
                    int x=r.nextInt(3)*120;
                    int y=0;
                    ntc.add(new NewTankCreating(x,y));
                }

            }else {
                if(etank.size()<6&&etankNum!=0&&canCreateNewEnemyTank(atank,etank,ntc))
                {
                    Random r=new Random();
                    int x=r.nextInt(3)*120;
                    int y=0;
                    ntc.add(new NewTankCreating(x,y));
                    if(etankNum>=1)etankNum--;
                }
            }


            if(!gameOver&&!gameFinish) {
                repaint();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else if(gameFinish){
                repaint();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                gameFinishFlag--;
                System.out.println("gamefinishflag  =  "+gameFinishFlag);
                if(gameFinishFlag==0){
                    map=new Map(level+1);
                    level++;
                    gameFinish=false;
                    etankDead=5;
                    etankNum=5;

                    gameFinishFlag=60;
                }

            } else if(gameOver){
                repaint();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                gameOverFlag--;
                System.out.println("gameoverflag  =  "+gameOverFlag);
                if(gameOverFlag==0){
                    map=new Map(level);
                    gameOver=false;
                }

            }

        }
    }
    public boolean canCreateNewEnemyTank(Tank atank,ArrayList<EnemyTank> etank,ArrayList<NewTankCreating> ntc){
        boolean ans=true;
        Rectangle[] r={new Rectangle(0,0,20,20),new Rectangle(120,0,20,20),new Rectangle(240,0,20,20) };
        for(int i=0;i<etank.size();i++){
            for(int j=0;j<3;j++){
                if(r[j].intersects(etank.get(i).getCurrentPosition())&&r[j].intersects(etank.get(i).getNextPosition()))ans=false;
            }
        }
        for(int j=0;j<3;j++){
            if(r[j].intersects(atank.getCurrentPosition())&&r[j].intersects(atank.getNextPosition()))ans=false;
        }
        for(int i=0;i<ntc.size();i++){
            for(int j=0;j<3;j++){
                if(r[j].intersects(ntc.get(i).getCurrentPosition()))ans=false;
            }
        }
        return ans;
    }

}
