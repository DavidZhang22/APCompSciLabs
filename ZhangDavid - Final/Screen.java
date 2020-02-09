import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.Random;
import java.lang.Math;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextField;
import java.awt.Font;

import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Screen extends JPanel implements KeyListener{


	private int t = 0;
	private int n = 10;
	private boolean flag = false;
	private boolean won;
	private boolean lost = true;

	private Projectile p;
	private Ship s;
	private Enemy[] en = new Enemy[15];
	private int[] px = new int[70];
	private int[] py = new int[70];

	private int[] pdir = new int[70];
	private int[] plen = new int[70];
	private int[] pshift = new int[70];


	private int level = 0;
	private int lives = 3;
	private int score = 0;
	private int badkey = 0;
	private boolean next = true;
	private Color c = new Color(10, 10, 20);
	private Color f = new Color(100,100, 100);

	private Color flash = new Color(80, 80, 170);
	private Color warp = new Color(220, 220, 220);

	public Screen(){

		p = new Projectile(0,0);
		s = new Ship();

		for(int i = 0; i<14; i++){
			en[i] = new Enemy(0,0);
			en[i].di(level);
		}

		setFocusable(true);
		addKeyListener(this);

		for(int i =0; i< pdir.length; i++){
			pdir[i] = (int)(Math.random()*360);
			px[i] = 400 + (int)((Math.abs(Math.cos(pdir[i]))/Math.cos(pdir[i])));
			py[i] = 300 + (int)((Math.abs(Math.sin(pdir[i]))/Math.sin(pdir[i])));
			plen[i] = (int)(400*Math.random());
			pshift[i] = (int)(1000*Math.random());
		}
	}

	public Dimension getPreferredSize(){

		return new Dimension(800, 600);

	}


	public void playSound() {
 
        try {
            URL url = this.getClass().getClassLoader().getResource("Shooting_Stars.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(url));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
    }

    public void drawBackground(Graphics g){
    	for(int i =0; i< 20; i++){
			g.setColor(c);
			g.fillOval(400-i*40, 300-i*30, i*80,i*60);
		}
    }

	@Override
	
	public void paintComponent(Graphics g){

		super.paintComponent(g);
		drawBackground(g);

		g.setColor(f);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 30)); 
		g.drawString("Lives: "+ lives, 10, 35);
		g.drawString("Level: "+ (level+1), 10, 65);
		g.drawString("Score: "+ score, 10, 95);

		if(t%22700 == 0){
			playSound();
		}

		//System.out.println(t);

		if(t<200){			
			if(t%30 ==0){
				flash = new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
			}
			g.setColor(flash);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 70)); 
			g.drawString("Entering Hyperspace", 120, 100);		
		}

		// background animation
		if(level == 0){
			for(int i =0; i< pdir.length; i++){
					g.setColor(warp);
					g.drawLine(px[i], py[i], px[i]+(int)(15*Math.cos(pdir[i]*3.14/180)) , py[i]+(int)((15)*Math.sin(pdir[i]*3.14/180)));
				if(t%4 == 0 || t > i*5){
					
					if(px[i]>800 || px[i]<0 || py[i] > 600 || py[i] <0){
						px[i] = 400;// + (int)(10*(Math.cos(pdir[i])));
						py[i] = 300;//+ (int)(10*Math.sin(pdir[i]));
						pdir[i] = (int)(Math.random()*360);
					}

					px[i]+=(int)(Math.cos(pdir[i]*3.14/180)*14);//- (Math.abs(Math.cos(pdir[i]))/Math.cos(pdir[i])));
					py[i]+=(int)(Math.sin(pdir[i]*3.14/180)*14);//- (Math.abs(Math.sin(pdir[i]))/Math.sin(pdir[i])));
				}
				//System.out.println()

			}
		}else if(level == 1){

			for(int i =0; i<pdir.length; i++){
				if(t>i*(int)(30*Math.random())){
					g.setColor(warp);
					g.fillOval(px[i], py[i], 4, 5);

					if(px[i]>800 || px[i]<0 || py[i] > 700 || py[i] <-100){
						px[i] = 400;
						py[i] = 300;
						plen[i] = 0;
					}

					px[i]=400+(int)(Math.cos((t+pshift[i]%1000)*2*3.14/600)*plen[i])+1;
					py[i]=300+(int)(Math.sin((t+pshift[i]%1000)*2*3.14/600)*plen[i])+1;
					if(t%2 == 0){
						plen[i]++;
					}
				}
			}
		}

		if(next){

			if(level == 0){
				for(int i =0; i< n; i++){

					en[i].setX(50+74*i);
					en[i].setY(20);
					en[i].see();

				}
			}else if(level == 1){
				int k = 0;

				for(int i =0; i< 7; i++){
					for(int j =0; j< 2; j++){
						en[k].setX(50+80*i+30*j);
						en[k].setY(20+40*j);
						en[k].see();
						k++;

					}
				}
			}else if(level == 2){
				for(int i =0; i< n; i++){
					en[i].setX(375);
					en[i].setY(375);
				}
			}
		}

		if(level !=2){
			p.proj(g);

			flag = false;

			s.draw(g);

			for(int i =0; i< n; i++){
				if(en[i].checkHit(p.getX(), p.getY())){
					score ++;
				}
				en[i].draw(g, level);
			}

			next = false;
			won = true;


			for(int i =0; i< n; i++){
				if(en[i].lose()){

					//System.out.print(i);

					next = true;
					lives--;
					if(lives == 0){
						lives = 3;
						level = 0;
						for(int j =0; j <n; j++){
							en[j].di(level+1);
						}
					}
				}

				if(en[i].win()){
					won = false;
				}
			}

			if(won){

				won = false;
				level++;
				next = true;
				n= 14;
				for(int i =0; i <n; i++){
					en[i].di(level+1);
				}
			}
		}else{
			if(t%30 ==0){
				flash = new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
			}
			g.setColor(flash);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 90)); 
			g.drawString("You Won!", 200, 300);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 30));		
			g.setColor(f); 
			g.drawString("Press any key to restart", 260, 370);
		}
		animate();

	}

	public void keyPressed(KeyEvent e){

		if(e.getKeyCode() == 32){

			p.fire(s.shipx()+(int)(s.getWidth()/2), s.shipy());

		}

		if(e.getKeyCode() == 39){

			s.right();

		}

		if(e.getKeyCode() == 37){

			s.left();

		}

		if(e.getKeyCode() == 40){

			s.up();

		}

		if(e.getKeyCode() == 38){

			s.down();


		}

		if(e.getKeyCode() == 80){

			if(level <2){
				level++;
				next = true;
				n = 14;
				
				for(int i=0; i<n; i++){
					en[i].di(level+1);
				}
			}
		}
	}

	public void keyReleased(KeyEvent e){

	}

	public void keyTyped(KeyEvent e){

		if(level == 2 && e.getKeyCode()!=1 && badkey!=0){
			level = 0;
			badkey = 0;
			next = true;
			t = 0;
			n = 10;
			score = 0;
			for(int i=0; i<n; i++){
				en[i].di(level+1);
			}

		}else if(level == 2){
			badkey++;
		}
	}

	public void animate(){

		t++;

		try{

			Thread.sleep(10);

		} catch (InterruptedException e){

			Thread.currentThread().interrupt();
		}
		repaint();
	}
}
