import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.Random;
import java.lang.Math;

public class Screen extends JPanel{
	
	private int[] d = {800, 600};


	private int t = 700;

 	private double pi = Math.PI;
 	private Color n;

 	private Sky sky;
 	private Building build;
 	private Trees tree;
 	private Houses house;

	public Screen(){

		sky = new Sky();
		build = new Building();
		tree = new Trees();
		house = new Houses();

		setFocusable(true);	

	}
	
	public Dimension getPreferredSize(){

		return new Dimension(d[0],d[1]);

	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);

		//Draw Background

		sky.draw(t, g);

		//Area 1
		build.draw(g, 10, 270, 5, 5, t);

		//Area 2
		tree.draw(g, 10, 550, 6, 2);
		tree.draw(g,190, 320, 8, 2);
		tree.draw(g, 165, 320, 1, 6);

		//Area 3
		tree.draw(g, 540, 320, 5, 4);
		
		//Area 4
		house.draw(g, 200, 400, 6, 6);

		//Area 5		
		build.draw(g, 440, 270, 3, 8, t);

		//Area 6
		house.draw(g, 540, 510, 3, 7);
		house.draw(g, 690, 300, 6, 3);


		animate();

	}


	public void animate(){

		t++;

		if(t>1300){
				t = 0;
			}


		try{

			Thread.sleep(3);

		} catch (InterruptedException e){

			Thread.currentThread().interrupt();

		}

		repaint();
	}
}
