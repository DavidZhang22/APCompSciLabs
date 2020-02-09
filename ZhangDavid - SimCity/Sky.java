import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.Random;
import java.lang.Math;


public class Sky{

	private double pi = Math.PI;
	private Color n;


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

	private int suny = 0;
	private int sunx = 0;

	private int moonx = 0;
	private int moony = 0;

	private int stars = 300;
 	private int[] starx = new int[stars];
 	private int[] stary = new int[stars];


	private int[] d = {800, 600};

	private int t2 = 0;

 	public Sky(){


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

	public int check(int j){

		if(j>255){
			return 255;
		}else if(j<0){
			return 0;
		}
		return j;

	}
	
	public void yb(int i, int j, int ti, int dir){

			if(dir==1){

				n = new Color(check(cast(200-ti/2+dir*(j/5-i/8))), check(cast(180-ti/1.9+dir*(j/5-i/8))), check(cast(100+ti/2.2-dir*(j/10-i/15))));

			}else{

				n = new Color(check(80+cast(dir*(-ti/2+(j/5-i/8)))), check(60+cast(dir*(-ti/1.9+(j/5-i/8)))),check(cast(212+dir*(ti/2-(j/10-i/15)))));

			}

	}

	public void by(int i, int j, int ti, int dir){

			if(dir==1){

				n = new Color(check(180-cast(-i/12-j/10+2.05*ti)), check(160-cast(-i/12-j/10+1.90*ti)), check(100-cast(-i/12-j/10+ti/1.5)));

			}else{

				n = new Color(check(31+cast(-i/10+j/8+1.52*ti)), check(28+cast(-i/10+j/8+1.35*ti)), check(26+cast(-i/10+j/8+ti/1.77)));

			}


	}


	public int cast(double i){
		return (int)i;
	}



	public void draw(int t, Graphics g){



		//Draw sky

		if(t>1200 || t< 150){
			t2++;

		}else{

			t2 = 0;

		}

		for(int i =0; i< 160; i++){
			for(int j = 0; j< 100; j++){
				if(t<800 && t>550){

					yb(i, j,t-550, -1);

				}else if(t>1200 || t<150){

					yb(i, j, t2, 1);				

				}else if(t>850 && t< 950){

					by(i, j, t-800, 1);

				}else if(t<1200 && t> 1100){

					by(i, j, t-1100, -1);

				}

				g.setColor(n);
				g.fillRect(i*5, j*3,5, 3);
			}     
		}

		//Draw Sun

		for(int j=20; j >=0; j--){
			Color q = new Color(240, 230-2*j, 86-j*2);
			g.setColor(q);
			g.fillOval(sunx-j , suny+30-j , j*3 , j*3);

		}



		//Stars

		if(t == 800){

			for(int i =0; i<stars; i++){

				starx[i] = cast(800.0*Math.random());
				stary[i] = cast(350.0*Math.random());

			}
		}

		if(t>=820 && t<=1100){
			for(int i =0; i<stars; i++){

				int s = 2+cast(2*Math.sin((t-700+i*1.5)*pi/300));
				g.setColor(lightYellow);
				g.fillOval(starx[i], stary[i], s, s);
				
			}

		}

		//Draw Moon

		for(int j=20; j >=0; j--){
			Color q = new Color(240-cast(10.0*Math.random()), 230-cast(15.0*Math.random()), 235-cast(10.0*Math.random()));
			g.setColor(q);
			g.fillOval(moonx-j , moony+30-j , j*3 , j*3);

		}

		sunx = cast(1.1*t);
		suny = 450-(int)(450.0*Math.sin(((1.1*t+300)/1500.0)*pi));
		moonx = cast(1.5*t)-1250;
		moony = 250-(int)(300.0*Math.sin(((t-800)/500.0)*pi));

		//Draw Ground 

		for(int i = 0; i< 50; i++){
			Color c = new Color(100+2*i, 100+2*i, 100+2*i);
			g.setColor(c);
			g.fillRect(0, 300+i*9, d[0], 50);
		}

	}

}