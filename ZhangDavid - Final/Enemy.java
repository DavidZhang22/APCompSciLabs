import java.awt.Color;
import java.awt.Graphics;
import java.lang.Math;

import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

 
public class Enemy{
    private int x;
    private int y;
     
    private int width;
    private int height;

    private int count = 0;
    private int dir = 2;
     
    private Color green;

    private boolean down = false;
 
    private boolean visible = false;


    private Projectile p;

    private boolean hit;
     
    public Enemy(int x, int y){

        this.x = x;
        this.y = y;
         
        this.width = 30;
        this.height = 30;
         
        this.green = new Color(100, 200, 100);


    }
     
    public void playSound() {
 
        try {
            URL url = this.getClass().getClassLoader().getResource("Hit.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(url));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
    }

    public void setX(int xp){
        x = xp;
    }

    public void setY(int yp){
        y = yp;
    }

    public void see(){

        visible = true;

    }

    public boolean seen(){
        return visible;
    }

    public boolean lose(){
        if(y>600 && visible){
            System.out.println(y);

            return true;
        }
        return false;
    }

    public boolean win(){
        return visible;
    }

    public boolean checkHit(int xp, int yp){

        if(visible && (x+(int)(width))>=xp && x<=xp && y<yp && y+height>=yp){
            playSound();
            visible = false;
            return true;

        }

        return false;

    }

    public void di(int level){
        dir = 2*level+1;
    }

    public void draw(Graphics g, int level){

        if(visible){

            g.setColor(green);
            g.fillRect(x,y,width,height);
            for(int i =1; i<10; i++){
                int[] xs = new int[i];
                int[] ys = new int[i];
                for(int j = 0; j<i; j++){
                    xs[j] = x+(int)(j*width*1.2/i);
                    ys[j] = y+(int)(((j+1)%i)*height*1.2/i);

                }
                Color sd = new Color(i*20, i*i+20, 100/i+100);
                g.setColor(sd);

                g.fillPolygon(xs, ys, i);
            }
        }


        if(x>700 || x<50){

            down = true;

        }
        
        if(down){
            y+=Math.abs(dir);
            count+=Math.abs(dir);
        }else{
            x+=dir;
        }

        if(count>60){
            count = 0;
            down = false;
            dir = -1*dir;
            if(Math.abs(x-700) > Math.abs(x-50)){
                x = 50;
            }else{
                x = 700;
            }
        }
    }
     
}