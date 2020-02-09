import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;

public class Screen extends JPanel implements ActionListener{
	
	private JTextField pinInput;
	private JButton x1;
	private JButton x5;
	private JButton x10;
	private JButton scam;
	private JButton one;

	private String num1 = " ";
	private String num2 = " ";
	private String num3 = " ";
	
	private boolean err = true;

	private int bet = 1;
	private SlotMachine sm;

	private int[] num = new int[3];
	
	public Screen(){
		
		setLayout(null);

		sm = new SlotMachine(100);

		x1 = new JButton("x1");
		x1.setBounds(140, 525, 170, 50);
		add(x1);
		x1.addActionListener(this);
		
		x5 = new JButton("x5");
		x5.setBounds(330, 525, 170, 50);
		add(x5);
		x5.addActionListener(this);
		
		x10 = new JButton("x10");
		x10.setBounds(530, 525, 170, 50);
		add(x10);
		x10.addActionListener(this);

		scam = new JButton("Spin!");
		scam.setBounds(300, 435, 200, 50);
		add(scam);
		scam.addActionListener(this);

		one = new JButton("+1");
		one.setBounds(100, 550, 10, 10);
		add(one);
		one.addActionListener(this);

		setFocusable(true);	
	
	}
	
	@Override
	public Dimension getPreferredSize(){

		return new Dimension(800, 600);

	}

	@Override
	public void paintComponent(Graphics g){

		super.paintComponent(g);

		Color gray = new Color(100, 100, 100);
		Color blue = new Color(120, 110, 235);
		Color black = new Color(0, 0, 0);

		for(int i = 0 ; i< 32; i++){	

			gray = new Color(100+i*2, 100+i*2, 100+i*2);
			g.setColor(gray);
			g.fillRect(i*5,i*3, 800-10*i, 600-6*i);

		}

		g.setColor(black);
		g.fillRect(162, 96, 476, 408);

		for(int i = 0 ; i< 33; i++){

			gray = new Color(i+97, 200-2*i, 222+i);
			g.setColor(gray);
			g.fillRect(166+i*7, 100+i*6, 468-14*i ,400-12*i);
		}

		g.setColor(black);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 80)); 
		g.drawString(num1, 210, 280);
		g.drawString(num2, 360, 280);
		g.drawString(num3, 510, 280);	

		g.setFont(new Font("TimesRoman", Font.PLAIN, 40)); 

		g.drawString("Balance: " + Integer.toString(sm.getBal()), 380, 75);
		g.drawString("Bet: " + Integer.toString(bet), 200, 75);
		
		animate();

	}

	
	public void actionPerformed(ActionEvent e){

		if(e.getSource() == scam && sm.changeBal(-bet) == 0 ){

			num = sm.setRandom();

			num1 = Integer.toString(num[0]);
			
			num2 = Integer.toString(num[1]);

			num3 = Integer.toString(num[2]);

			sm.checkWin(bet);

		}else if(e.getSource() == x1){
			bet = 1;
		}else if(e.getSource() == x5){
			bet = 5;
		}else if(e.getSource() == x10){
			bet = 10;
		}else if(e.getSource() == one){
			sm.changeBal(1);
		}
	}

	public void animate(){


		try{

			Thread.sleep(30);

		} catch (InterruptedException e){

			Thread.currentThread().interrupt();

		}

		repaint();

	}
		
}	