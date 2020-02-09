import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.Random;
import java.lang.Math;

import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Projectile{
	
	private int x;
	private int y;

	private Color p;

	private boolean visible;

	public Projectile(int x, int y){

		visible = false;

		this.x = x;
		this.y = y;


	}

	public void playSound() {
 
        try {
            URL url = this.getClass().getClassLoader().getResource("Shot.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(url));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
    }

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}
	
	public boolean seen(){
		return visible;
	}

	public void fire(int xp, int yp){

		if(!visible){
			playSound();
			visible = true;
			p = new Color(255-(int)(Math.random()*110), 255-(int)(Math.random()*110), 255-(int)(Math.random()*110));

			x = xp;	
			y = yp;

		}
	}

	public void proj(Graphics g){

		if(visible){
			y-=10;
			g.setColor(p);
			g.fillOval(x-3, y, 6, 20);


			if(y<0){
				y = -100;
				x = -100;
				visible = false;
			}
		}
	}
}