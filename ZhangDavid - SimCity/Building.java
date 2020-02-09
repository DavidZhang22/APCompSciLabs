import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.Random;
import java.lang.Math;

public class Building{

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

	private int[] light = new int[10000];

	
	public Building(){



 		lightBlue = new Color(108,206,209);
		darkBlue = new Color(24,42,132);
		raincolor = new Color(3, 74, 236);
		white = new Color(254,225,234);
		green = new Color(12,172,28);
		lightYellow = new Color(247,238,99);
		brown = new Color(139,69,19);
		brown1 = new Color(150,89,49);
		orange = new Color(225,135,10);
		black = new Color(10,20,8);
		lightgray = new Color(150,150,150);
		darkgray = new Color(60,60,60);


 	}

	public void draw(Graphics g, int x, int y, int r, int c, int t){

		if(t == 850){
			

		}

		for(int i = 0; i< r; i++){
			for(int j = 0; j< c; j++){
				g.setColor(darkgray);
				g.fillRect(x+i*30, y+j*50, 20, 40);

				for(int k = 0; k< 3; k++){
					for(int l = 0; l<2; l++){
						g.setColor(lightgray);
						g.fillRect(x+4+i*30+l*7, y+5+j*50+k*7, 5, 5);
					}

				}
				g.setColor(brown);
				g.fillRect(x+8+i*30, y+30+j*50, 4, 10);


			}
		}

	}
}