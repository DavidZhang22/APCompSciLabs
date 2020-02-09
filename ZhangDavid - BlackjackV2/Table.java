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
	private JButton start;
	
	private boolean err = true;
	private int enter = 0;

	private int wins = 0;
	private int loses = 0;
	private int cnum = 0;


	private String text = "";


	private ArrayList<Card> c = new ArrayList<Card>(0);

	private ArrayList<Card> player = new ArrayList<Card>(0);
	private ArrayList<Card> dealer = new ArrayList<Card>(0);


	private JTextPane allcontacts;
	private String temp;

	private boolean badkey = false;
	private boolean twentyone = false;

	public ArrayList<Card> scramble(ArrayList<Card> c){
		for(int i =0; i< c.size(); i++){
			Card temp = c.get(i);
			int j = (int)(c.size()*Math.random());
			if(j!=i){
				Collections.swap(c, i, j);
			}
		}
		return c;
	}

	public void buttonOn(boolean b){
		add.setVisible(b);
		de.setVisible(b);
		start.setVisible(!b);
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
			c.add(new Card(name[i%13],i%13+1,v[i/13]));
		}

		de = new JButton("Stand");
		de.setBounds(300, 50, 130, 40);
		add(de);
		de.addActionListener(this);

		add = new JButton("Hit");
		add.setBounds(150, 50, 130, 40);
		add(add);
		add.addActionListener(this);

		start = new JButton("Start");
		start.setBounds(150, 50, 130, 40);
		add(start);
		start.addActionListener(this);

		c = scramble(c);

		buttonOn(false);

		setFocusable(true);	
	
	}

	public int getTotal(ArrayList<Card> c){
		int ace = 0;
		int total = 0;
		for(int i =0; i< c.size(); i++){
			if(c.get(i).visi()){
				total+=c.get(i).getV();
			}
			if(c.get(i).cardName() == "A"){
				ace++;
			}
		}
		for(int i =0; i< ace; i++){
			if(total+10<=21){
				total +=10;
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


		for(int i =0; i< dealer.size(); i++){
			dealer.get(i).drawMe(g, 50+i*90, 100);
		}

		for(int i =0; i< player.size(); i++){
			player.get(i).drawMe(g, 50+i*90, 300);
		}

		int dtotal = getTotal(dealer);
		int ptotal = getTotal(player);

		g.setColor(Color.BLACK);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 30)); 

		g.drawString("Your total: "+Integer.toString(ptotal), 300, 555);
		g.drawString("Dealer's total: "+Integer.toString(dtotal), 50, 555);


		g.drawString("Wins: "+Integer.toString(wins), 570, 100);
		g.drawString("Loses: "+Integer.toString(loses), 570, 200);

		g.drawString(text, 190, 505);

		
		repaint();
	}




	public void actionPerformed(ActionEvent e){

		int s = getTotal(c);


		for(int i =0; i< c.size(); i++){
			c.get(i).cove();
		}

		if(e.getSource() == start){


			player.clear();
			dealer.clear();
			cnum = 0;
			for(int i =0; i<c.size(); i++){
				c.get(i).cover(false);
			}
			for(int i =0; i< 2; i++){

				dealer.add(c.get(cnum+2));
				player.add(c.get(cnum));
				dealer.get(i).setVisible(true);
				player.get(i).setVisible(true);
				cnum++;
			}
			dealer.get(1).setVisible(false);
			dealer.get(1).cover(true);
			cnum+=2;

			scramble(c);

			buttonOn(true);

			text = " ";

		}else if(e.getSource() == add){

			playSound("hit.wav");

			text = "";

			player.add(c.get(cnum));
			player.get(player.size()-1).setVisible(true);


			cnum++;
			
			s = getTotal(player);

			if(s>21	){
				text = "Bust! You lost";
				badkey = true;
				playSound("lose.wav");
				buttonOn(false);
				loses++;

			}else if(s == 21){
				twentyone = true;
			}
		}

		if(e.getSource() == de || twentyone == true){

			twentyone = false;

			if(!badkey){
				badkey = true;
			}

			if(getTotal(player)<21){
				dealer.get(1).setVisible(true);
				dealer.get(1).cover(false);
				while(getTotal(dealer)<17){
					dealer.add(c.get(cnum));
					dealer.get(dealer.size()-1).setVisible(true);
					cnum++;
				}	
			}

			if(getTotal(dealer)>getTotal(player) && getTotal(dealer)<22){
				text = "You Lost";
				loses ++;
			}else if(getTotal(dealer) == getTotal(player)){

				text = "Tie";
			}else{
				text = "You won";
				wins ++;
			}
			buttonOn(false);
		}
	}


}