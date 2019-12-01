package tankGame;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.io.*;
/*
* 0-----void
* 1-----cob
* 2-----iron
* 3-----sea
* 4-----forest
* 51-----eagle1
* 52-----eagle2
* 53-----eagle3
* 54-----eagle4
* 61-----deadeagle1
* 62-----deadeagle2
* 63-----deadeagle3
* 64-----deadeagle4
*
*
* */
public class Map {
    private ArrayList<Blocks> map=new ArrayList<>();
    int[][] maplist=new int[26][26];
    String[] mapSel={"level/level1","level/level2","level/level3","level/level4","level/level5","level/level6","level/level7","level/level8","level/level9","level/level10"};

    public ArrayList<Blocks> getMap() {
        return map;
    }

    public int[][] getMaplist() {
        return maplist;
    }

    public void setMap(ArrayList<Blocks> map) {
        this.map = map;
    }

    public void setMaplist(int[][] maplist) {
        this.maplist = maplist;
    }
    public Map(int level){
        super();
        ArrayList<String> arrayList=new ArrayList<>();
        try {
            File f=new File(mapSel[level]);
            InputStreamReader input = new InputStreamReader(new FileInputStream(f));
            BufferedReader bf = new BufferedReader(input);
            String str;
            while ((str = bf.readLine()) != null) {
                arrayList.add(str);
            }
            bf.close();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int i=0;i<arrayList.size();i++){
            maplist[i/26][i%26]=Integer.parseInt(arrayList.get(i));
        }
        /*for(int i=0;i<24;i++) {
            for(int j=0;j<24;j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.print("\n");
        }*/
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                if (maplist[i][j] == 1) {
                    map.add(new Blocks(j * 10, i * 10, BLockstype.COB));
                } else if (maplist[i][j] == 2) {
                    map.add(new Blocks(j * 10, i * 10, BLockstype.IRON));
                }else if (maplist[i][j] == 3) {
                    map.add(new Blocks(j * 10, i * 10, BLockstype.SEA));
                }else if (maplist[i][j] == 4) {
                    map.add(new Blocks(j * 10, i * 10, BLockstype.FOREST));
                }else if (maplist[i][j] == 51) {
                    map.add(new Blocks(j * 10, i * 10, BLockstype.EAGLE1));
                }else if (maplist[i][j] == 52) {
                    map.add(new Blocks(j * 10, i * 10, BLockstype.EAGLE2));
                }else if (maplist[i][j] == 53) {
                    map.add(new Blocks(j * 10, i * 10, BLockstype.EAGLE3));
                }else if (maplist[i][j] == 54) {
                    map.add(new Blocks(j * 10, i * 10, BLockstype.EAGLE4));
                }else if (maplist[i][j] == 61) {
                    map.add(new Blocks(j * 10, i * 10, BLockstype.DEADEAGLE1));
                }else if (maplist[i][j] == 62) {
                    map.add(new Blocks(j * 10, i * 10, BLockstype.DEADEAGLE2));
                }else if (maplist[i][j] == 63) {
                    map.add(new Blocks(j * 10, i * 10, BLockstype.DEADEAGLE3));
                }else if (maplist[i][j] == 64) {
                    map.add(new Blocks(j * 10, i * 10, BLockstype.DEADEAGLE4));
                }else if (maplist[i][j] == 0) {

                }
            }
        }
    }
    public Map() {
        super();
        for (int i = 0; i < maplist.length; i++) {
            for (int j = 0; j < maplist[i].length; j++) {
                if (maplist[i][j] == 1) {
                    map.add(new Blocks(j * 10, i * 10, BLockstype.COB));
                } else if (maplist[i][j] == 2) {
                    map.add(new Blocks(j * 10, i * 10, BLockstype.IRON));
                }else if (maplist[i][j] == 3) {
                    map.add(new Blocks(j * 10, i * 10, BLockstype.SEA));
                }else if (maplist[i][j] == 4) {
                    map.add(new Blocks(j * 10, i * 10, BLockstype.FOREST));
                }else if (maplist[i][j] == 51) {
                    map.add(new Blocks(j * 10, i * 10, BLockstype.EAGLE1));
                }else if (maplist[i][j] == 52) {
                    map.add(new Blocks(j * 10, i * 10, BLockstype.EAGLE2));
                }else if (maplist[i][j] == 53) {
                    map.add(new Blocks(j * 10, i * 10, BLockstype.EAGLE3));
                }else if (maplist[i][j] == 54) {
                    map.add(new Blocks(j * 10, i * 10, BLockstype.EAGLE4));
                }else if (maplist[i][j] == 61) {
                    map.add(new Blocks(j * 10, i * 10, BLockstype.DEADEAGLE1));
                }else if (maplist[i][j] == 62) {
                    map.add(new Blocks(j * 10, i * 10, BLockstype.DEADEAGLE2));
                }else if (maplist[i][j] == 63) {
                    map.add(new Blocks(j * 10, i * 10, BLockstype.DEADEAGLE3));
                }else if (maplist[i][j] == 64) {
                    map.add(new Blocks(j * 10, i * 10, BLockstype.DEADEAGLE4));
                }else if (maplist[i][j] == 0) {

                }
            }
        }
    }
    public void shovelChange(boolean t){
        for(int i=0;i<map.size();i++){
            if((map.get(i).getX()==110&&map.get(i).getY()==250)||(map.get(i).getX()==110&&map.get(i).getY()==240)||(map.get(i).getX()==110&&map.get(i).getY()==230)||(map.get(i).getX()==120&&map.get(i).getY()==230)||(map.get(i).getX()==130&&map.get(i).getY()==230)||(map.get(i).getX()==140&&map.get(i).getY()==230)||(map.get(i).getX()==140&&map.get(i).getY()==240)||(map.get(i).getX()==140&&map.get(i).getY()==250))
                map.remove(i);
        }
        map.add(new Blocks(110,250, t? BLockstype.IRON:BLockstype.COB));
        map.add(new Blocks(110,240, t? BLockstype.IRON:BLockstype.COB));
        map.add(new Blocks(110,230, t? BLockstype.IRON:BLockstype.COB));
        map.add(new Blocks(120,230, t? BLockstype.IRON:BLockstype.COB));
        map.add(new Blocks(130,230, t? BLockstype.IRON:BLockstype.COB));
        map.add(new Blocks(140,230, t? BLockstype.IRON:BLockstype.COB));
        map.add(new Blocks(140,240, t? BLockstype.IRON:BLockstype.COB));
        map.add(new Blocks(140,250, t? BLockstype.IRON:BLockstype.COB));

    }
    public void eagleDead(){
        for(int i=0;i<map.size();i++){
            if((map.get(i).getX()==120&&map.get(i).getY()==250)||(map.get(i).getX()==130&&map.get(i).getY()==250)||(map.get(i).getX()==120&&map.get(i).getY()==240)||(map.get(i).getX()==130&&map.get(i).getY()==240))
                map.remove(i);

        }
        map.add(new Blocks(120,240,BLockstype.DEADEAGLE1 ));
        map.add(new Blocks(130,240, BLockstype.DEADEAGLE2));
        map.add(new Blocks(120,250, BLockstype.DEADEAGLE3));
        map.add(new Blocks(130,250, BLockstype.DEADEAGLE4));

    }
    public boolean gameover(){
        for(int i=0;i<map.size();i++){
            if((map.get(i).getX()==120&&map.get(i).getY()==250)){
                if(map.get(i).getBlocktype()==BLockstype.DEADEAGLE3){
                    return true;
                }
            }
        }
        return false;
    }
    public void draw(Graphics g) {
        for(int i= 0;i<map.size();i++){
            map.get(i).draw();
            g.drawImage(map.get(i).getBlocksImage(), map.get(i).getX(), map.get(i).getY(), 10, 10, null);
        }
    }



}
