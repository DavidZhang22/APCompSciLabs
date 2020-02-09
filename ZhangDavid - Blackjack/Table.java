import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JTextPane;

import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


import java.util.*;

public class Table extends JPanel implements ActionListener{
	

	private JButton de;
	private JButton add;
	
	private boolean err = true;
	private int enter = 0;

	private int point = 0;
	private int cnum = 0;

	private String text = "";


	private Card[] c = new Card[52];

	private JTextPane allcontacts;
	private String temp;

	private boolean badkey = false;

	public Card[] scramble(Card[] c){
		for(int i =0; i< c.length; i++){
			Card temp = c[i];
			int j = (int)(c.length*Math.random());
			c[i] = c[j];
			c[j] = temp;
		}
		return c;
	}
	
	public void playSound(String sound) {
 
        try {
            URL url = this.getClass().getClassLoader().getResource(sound);
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(url));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
    }
	
	public Table(){

		setLayout(null);
		
		String[] v = new String[]{"Hearts", "Clubs", "Spades", "Diamonds"};
		String[] name = new String[]{"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
		for(int i =0; i< 52; i++){
			c[i] = new Card(name[i%13],i%13+1,v[i/13]);
		}

		de = new JButton("Stand");
		de.setBounds(300, 50, 130, 40);
		add(de);
		de.addActionListener(this);

		add = new JButton("Hit");
		add.setBounds(150, 50, 130, 40);
		add(add);
		add.addActionListener(this);

		c = scramble(c);


		setFocusable(true);	
	
	}

	public int getTotal(Card[] c){
		int total = 0;
		for(int i =0; i< c.length; i++){
			if(c[i].visi()){
				total+=c[i].getV();
			}
		}
		return total;
	}


	@Override
	public Dimension getPreferredSize(){

		return new Dimension(800, 600);

	}

	@Override
	public void paintComponent(Graphics g){

		super.paintComponent(g);

		g.setColor(Color.GREEN);
		g.fillRect(0,0,800, 600);
		int total = 0;

		for(int i =0; i< c.length; i++){
			c[i].drawMe(g, 50+i*90, 100);
			if(c[i].visi()){
				total+=c[i].getV();
			}
		}

		g.setFont(new Font("TimesRoman", Font.PLAIN, 30)); 


		g.drawString("Score: "+Integer.toString(total), 190, 455);


		g.drawString("Points: "+Integer.toString(point), 190, 555);

		g.drawString(text, 190, 355);

		
		repaint();
	}




	public void actionPerformed(ActionEvent e){

		int s = getTotal(c);

		if(e.getSource() == add){

			playSound("hit.wav");

			if(badkey){
				badkey = false;
				for(int i =0; i< c.length; i++){
					c[i].setVisible(false);

				}
				cnum = 0;
				scramble(c);
			}

			text = "";

			c[cnum].setVisible(true);

			cnum++;
			
			s = getTotal(c);

			if(s>21	){
				text = "Bust! Press Hit to continue";
				badkey = true;
				playSound("lose.wav");

			}



		}

		if(e.getSource() == de){

			if(!badkey){
				boolean playsound = true;

				if(s == 21){
					point+=5;
					text = "You won 5 points";

				}else if( s == 20){
					point += 3;
					text = "You won 3 points";

				}else if ( s== 19){
					text = "You won 2 points";

					point +=2;
				}else if(s>15){
					text = "You won 1 points";

					point ++;
				}else{
					text = "You won 0 points";
					playsound = false;

				}

				if(playsound){
					playSound("win.wav");
				}
				badkey = true;
			}

		}
	}


}