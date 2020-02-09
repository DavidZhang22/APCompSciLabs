import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.Random;
import java.lang.Math;

public class Falling{
	
	private double speeds;

	private int speed;
	private int xpos;
	private int ypos;

	private int n;

	private Color lightBlue;
	private Color darkBlue;
	private Color raincolor;
	private Color white;
	private Color green;
	private Color lightYellow;
	private Color brown;
	private Color brown1;
	private Color orange;
	private Color tan;
	private Color gray;
	private Color black;
	private Color darkgray;
	private Color lightgray;


	public Falling(){

		speeds = Math.random();

		xpos = (int)(799*Math.random());
		ypos = (int)(600*Math.random());

		lightBlue = new Color(250,250,255);
		darkBlue = new Color(24,42,132);
		raincolor = new Color(3, 74, 236);
		white = new Color(224,225,234);
		green = new Color(12,172,28);
		lightYellow = new Color(247,238,99);
		brown = new Color(139,69,19);
		brown1 = new Color(150,89,49);
		orange = new Color(225,135,10);
		black = new Color(10,20,8);
		lightgray = new Color(150,150,150);
		darkgray = new Color(60,60,60);

	}

	public void draw(Graphics g, int s, int t){

		if(s == 0){
			n = 9;
			g.setColor(darkBlue);
			g.fillRect(xpos, ypos, 1, 5);
			
		}else if(s == 1){
			n = 3;
			g.setColor(white);
			g.fillOval(xpos, ypos, 4, 4);

		}
		speed = (int)(speeds*n+n);


		ypos+=speed;

		if(ypos>590){

			ypos = (int)(20*Math.random());
			xpos = (int)(799*Math.random());
			speed = (int)(n*Math.random()+n);
		}

	}
	
}