import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.Random;

public class Scenery extends JPanel{
	
	private int background; //1 = day, 2 = night
	private int sky;
	private int grass;
	private int[] d = {800, 600};
	private Random r = new Random();
	
	public Scenery(int b, int s){
		background = b;
		sky = s;
	}
	
	public Dimension getPreferredSize(){
		return new Dimension(d[0],d[1]);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);

		//Colors
		Color lightBlue = new Color(108,206,209);
		Color darkBlue = new Color(24,42,132);
		Color raincolor = new Color(3, 74, 236);
		Color white = new Color(254,225,234);
		Color green = new Color(12,172,28);
		Color lightYellow = new Color(247,238,99);
		Color brown = new Color(139,69,19);
		Color brown1 = new Color(150,89,49);
		Color orange = new Color(225,135,10);
		Color black = new Color(10,20,8);
		Color tan = new Color(245,190,173);
		Color gray = new Color(127,127,127);

	
		//Draw day
		if(background == 1) {

			g.setColor(lightBlue);
			g.fillRect(0, 0, d[0], d[1]);

		}else if(background == 2){

			g.setColor(darkBlue);
			g.fillRect(0, 0, d[0], d[1]);

		}
		
		//Draw sky
		if(sky == 1){
			
		}else if(sky ==2){
			g.setColor(white);
            g.fillOval(200,100,70,40);
            g.fillOval(220,110, 100,60);//cloud1
            g.fillOval(400,95,70,40);
            g.fillOval(420,70,100,60);//cloud2
            g.fillOval(600,120,80,40);
            g.fillOval(620,125,100,70);//cloud3

		}	
		
		//Draw Sun

		for(int j=20; j >=0; j--){
			Color q = new Color(240, 230-3*j, 86-j*2);
			g.setColor(q);
			g.fillOval(100-j , 50-j , j*3 , j*3);

		}

		//Draw tree
		g.setColor(brown);
		g.fillRect(d[0]-250, d[1]-150, 50, 80);
		g.setColor(green);
		g.fillOval(d[0]-270, d[1]-300, 100, 170);

		
		//Draw house
		g.fillRect(0, 500, 800 , 100 );
		g.setColor(brown);
		g.fillRect(100 , 300 , 288 , 210 );
		int xs[] = {100, 244, 388};
		int ys[] = {300, 200, 300};
		g.setColor(brown1);
		g.fillPolygon(xs, ys, 3);
		g.setColor(tan);
		g.fillRect(130 , 390 , 70 , 120 );
		g.setColor(orange);
		g.fillRect(300 , 370 , 50 , 50 );

	
		//Draw person

		 int ppos = r.nextInt(400)+100;
		 g.setColor(orange);
		 g.fillRect(ppos+210,450,40,70);
         g.fillRect(ppos+190,465,80,20);
         g.setColor(tan);
         g.fillOval(ppos+200,400,60,60);
		 g.setColor(black);
         g.fillOval(ppos+215,423,10,7);
         g.fillOval(ppos+239,423,10,7);

		 
		//


	}
}
