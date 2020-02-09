import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.Random;
import java.lang.Math;

public class Trees{

	private Color lightBlue;
	private Color darkBlue;
	private Color raincolor;
	private Color white;
	private Color green;
	private Color lightGreen;
	private Color brown;
	private Color brown1;
	private Color orange;
	private Color tan;
	private Color gray;
	private Color black;
	private Color darkgray;
	private Color lightgray;

	
	public Trees(){



 		lightBlue = new Color(108,206,209);
		darkBlue = new Color(24,42,132);
		raincolor = new Color(3, 74, 236);
		white = new Color(254,225,234);
		green = new Color(20,100,20);
		lightGreen = new Color(122,172,128);
		brown = new Color(139,69,19);
		brown1 = new Color(150,89,49);
		orange = new Color(225,135,10);
		black = new Color(10,20,8);
		lightgray = new Color(150,150,150);
		darkgray = new Color(60,60,60);


 	}

	public void draw(Graphics g, int x, int y, int r, int c){

		g.setColor(lightGreen);
		g.fillRect(x-5, y-17, r*30, c*50-10);

		for(int i = 0; i< r; i++){
			for(int j = 0; j< c; j++){

				g.setColor(brown);
				g.fillRect(x+6+i*30, y-10+j*50, 4, 20);
				g.setColor(green);
				int[] xs = {x+i*30, x+8+i*30, x+16+i*30};
				int[] ys = {y+j*50, y-25+j*50, y+j*50};
				g.fillPolygon(xs,ys, 3);


			}
		}


	}
}