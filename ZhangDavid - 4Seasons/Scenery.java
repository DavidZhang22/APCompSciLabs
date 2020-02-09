import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.Random;
import java.lang.Math;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class Scenery extends JPanel implements ActionListener{
	
	private int[] d = {800, 600};

	private int s = 1;

	private int t = 700;

 	private double pi = Math.PI;

 	private Falling[] f = new Falling[400];
 	private Mountains[] m = new Mountains[5];
 	private Trees[] tr = new Trees[30];

 	private double[] rand = new double[1000];

 	private JButton winter;
 	private JButton spring;
 	private JButton summer;
 	private JButton fall;
 	private Color gray = new Color(120, 120, 120);

	public Scenery(){


		for(int i =0; i< f.length; i++){
			f[i] = new Falling();
		}

		for(int i =0; i< m.length; i++){
			m[i] = new Mountains();
		}

		for(int i =0; i< tr.length; i++){
			tr[i] = new Trees();
		}

		for(int i =0; i<rand.length; i++){
			rand[i] = Math.random();
		}



		winter = new JButton("Winter");
		winter.setBounds(150, 600, 200, 50);
		add(winter);
		winter.addActionListener(this);


		spring = new JButton("Spring");
		spring.setBounds(150, 600, 200, 50);
		add(spring);
		spring.addActionListener(this);


		summer = new JButton("Summer");
		summer.setBounds(150, 600, 200, 50);
		add(summer);
		summer.addActionListener(this);

		fall = new JButton("Autumn");
		fall.setBounds(150, 600, 200, 50);
		add(fall);
		fall.addActionListener(this);

		setFocusable(true);	

		

	}
	
	@Override

	public Dimension getPreferredSize(){

		return new Dimension(d[0],d[1]);

	}

	@Override
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);

		Color lightBlue = new Color(180,180,255);
		g.setColor(lightBlue);
		g.fillRect(0,0, 800, 600);
		int n = 0;

		if(s!=2){
			if(s ==0 || s== 3){
				n = 0;
			}else{
				n = 1;
			}

			for(int i =0; i< 5+n*5; i++){

				g.setColor(gray);

				g.fillOval(-20+i*800/(5*n+5), 50, 190, 80);
			}
		}

		for(int i =0; i<m.length; i++){
			m[i].draw(g, i*150-20-(int)(rand[i]*20), s);
		}	

		for(int i =0; i< f.length; i++){
			f[i].draw(g, s, t);

		}

		for(int i =0; i< tr.length; i++){


			tr[i].draw(g, s, (int)(rand[i]*790+5), 400+(int)(rand[tr.length-i]*200), t);

		}

		animate();

	}

	public void actionPerformed(ActionEvent e){
		if(e.getSource() == winter){

			s = 1;

		}else if(e.getSource() == spring){

			s = 0;
			
		}else if(e.getSource() == summer){

			s = 2;
			
		}else{

			for(int i =0; i<tr.length; i++){

				tr[i].yposto0();

			}
			
			s = 3;
		}
	}

	public void animate(){

		t++;

		if(t>1300){
				t = 0;
			}


		try{

			Thread.sleep(10);

		} catch (InterruptedException e){

			Thread.currentThread().interrupt();

		}

		repaint();
	}
}
