import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.Random;
import java.lang.Math;

public class Mountains{

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
	private Color lightgray;
	private Color fallgreen;

	private int size = 120+(int)(Math.random()*75);
	private int height = 120+(int)(Math.random()*65);

	private int x = (int)(Math.random()*600);


	public Mountains(){


		lightBlue = new Color(180,180,255);
		darkBlue = new Color(24,42,132);
		raincolor = new Color(3, 74, 236);
		white = new Color(214,215,214);
		green = new Color(12,172,28);
		lightYellow = new Color(247,238,99);
		brown = new Color(139,69,19);
		brown1 = new Color(150+(int)(20*Math.random()),89+(int)(20*Math.random()),49+(int)(20*Math.random()));
		orange = new Color(225,135,10);
		black = new Color(10,20,8);
		lightgray = new Color(150,150,150);
		fallgreen = new Color(100, 150, 100);

	}


	public void draw(Graphics g, int x, int s){


		int[] xs = {x, x+size, x+size*2};
		int[] ys = {400, 400-height, 400};

		g.setColor(brown1);
		g.fillPolygon(xs, ys, 3);


		if(s == 1){

			white = new Color(244,235,244);

			g.setColor(white);
			g.fillRect(0, 400, 800, 400);

		 	int[] x2 = {x+(int)(4*size/5)-2, x+size, 2+x+(int)(6*size)/5};
			int[] y2 = {400-(int)(4*height/5), 400-height, 400-(int)(4*height/5)};

			g.fillPolygon(x2, y2, 3);


		}else if(s == 3){

			g.setColor(green);
			g.fillRect(0, 400, 800, 400);


		}else{
			g.setColor(green);
			g.fillRect(0, 400, 800, 400);
		}

		if(s == 2 || s== 0){

			for(int j=20; j >=0; j--){
				Color q = new Color(240, 230-3*j, 86-j*2);
				g.setColor(q);
				g.fillOval(100-j , 50-j , j*3 , j*3);

			}
		}


	}
	
	
}