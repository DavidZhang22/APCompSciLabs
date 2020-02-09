import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.Random;
import java.lang.Math;

public class Ship{
	
	private int shipx = 400;
	private int shipy = 500;

	private int height = 10;
	private int width = 30;

	private Color red = new Color(255, 0, 0);
	private Color green = new Color(0, 255, 0);
	private Color yellow = new Color(240, 240, 0);

	public void draw(Graphics g){

		int[] xs = {shipx, shipx+10, shipx +20, shipx+width};
		int[] ys = {shipy, shipy-2*height, shipy-2*height, shipy};

		g.setColor(red);
		g.fillRect(shipx, shipy, width, height);
		g.setColor(yellow);
		g.fillPolygon(xs, ys, 4);
		g.setColor(green);
		g.fillRect(shipx+(int)(width/2)-5, shipy-2*height, 10,(int)(3.2*height) );


	}

	public void left(){
		if(shipx>0){
			shipx-=10;
		}
	}

	public void right(){
		if(shipx<800){
			shipx+=10;
		}
	}

	public void down(){
		if(shipy>400){
			shipy-=10;
		}
	}

	public void up(){
		if(shipy<800){
			shipy+=10;
		}
	}

	public int getHeight(){
		return height;
	}
	
	public int getWidth(){
		return width;
	}

	public int shipx(){
		return shipx;
	}

	public int shipy(){
		return shipy;
	}
}