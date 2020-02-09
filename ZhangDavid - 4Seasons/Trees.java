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
	private Color lightOrange;
	private Color brown;
	private Color brown1;
	private Color orange;
	private Color tan;
	private Color gray;
	private Color black;
	private Color lightgray;
	private Color fallgreen;

	private int ypos = 0;

	private int time = 0;

	private double[] err = {Math.random(), Math.random(), Math.random()};

	public Trees(){


		lightBlue = new Color(180,180,255);
		darkBlue = new Color(24,42,132);
		raincolor = new Color(3, 74, 236);
		white = new Color(204,205,204);
		green = new Color(34, 139, 34);
		lightOrange = new Color(255,148,10);
		brown = new Color(139,69,19);
		brown1 = new Color(91+(int)(20*Math.random()),57+(int)(20*Math.random()),23+(int)(20*Math.random()));
		orange = new Color(225,135,10);
		black = new Color(10,20,8);
		lightgray = new Color(150,150,150);
		fallgreen = new Color(100, 150, 100);

	}

 	public void draw(Graphics g, int s, int x, int y, int t){

        g.setColor(brown);

        g.fillRect(x-6,y+10,14,35);

        if(s == 2 || s== 0){
       		g.setColor(green);
       	}else if(s == 3){

            g.setColor(lightOrange);

            for(int i =0; i< (int)(err[0]*3)+1; i++){	
            	g.fillOval(x-20+(int)(err[i]*40)+(int)(Math.sin(ypos*Math.PI/30)), y+ypos+ (int)(20*err[err.length-1-i]), 9, 5); 

            }

            if(ypos<50){
            	ypos++;
            }

           	g.setColor(orange);

        }

        if(!(s == 1)){

	        int[] xArray = {x, x-27, x+27};
	        int[] yArray = {y-20, y+30, y+30};
	        g.fillPolygon(xArray, yArray, 3);

    	}
    }

    public void yposto0(){

    	ypos = 0;
    	
    }
	

}