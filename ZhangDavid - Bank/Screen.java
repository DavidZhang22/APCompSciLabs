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
	private JButton checkPinButton;
	private JButton deposit;
	private JButton withdraw;
	private JButton namec;

	private String name = " ";
	private String balance = " ";
	private String text = "Welcome!";
	
	private boolean err = true;

	private int acc = 0;

	private Bank[] a1 = new Bank[3];
	
	public Screen(){
		
		setLayout(null);
		
		a1[0] = new Bank("John", 100.99, 1234);

		a1[1] = new Bank("Jen", 1000.01, 4321);

		a1[2] = new Bank("Jerry", 50.50, 1111);

		pinInput = new JTextField();
		pinInput.setBounds(300, 300, 200, 40);
		add(pinInput);
		
		checkPinButton = new JButton("Login");
		checkPinButton.setBounds(300, 355, 200, 50);
		add(checkPinButton);
		checkPinButton.addActionListener(this);
		
		deposit = new JButton("Deposit");
		deposit.setBounds(225, 425, 170, 50);
		add(deposit);
		deposit.addActionListener(this);
		
		withdraw = new JButton("Withdraw");
		withdraw.setBounds(405, 425, 170, 50);
		add(withdraw);
		withdraw.addActionListener(this);

		namec = new JButton("Change Name");
		namec.setBounds(450, 145, 130, 40);
		add(namec);
		namec.addActionListener(this);
		namec.setVisible(false);

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
		g.setFont(new Font("TimesRoman", Font.PLAIN, 30)); 
		g.drawString(name, 190, 165);
		g.drawString(balance, 190, 200);
		g.drawString(text, 330, 280);	
		
		repaint();
	}
	
	public boolean exception(){
		
		try{

			Double.parseDouble(pinInput.getText());
			
		}
		catch(Exception e){

			text = "Try again";
			return false;

		}
		return true;
	}
	
	public void actionPerformed(ActionEvent e){

		text = " ";

		if(a1[acc].getAccess() && e.getSource() == namec){

			a1[acc].updateName(pinInput.getText());
			name = "Name: " + a1[acc].getName();
			
		}else if(e.getSource() == checkPinButton){
		
			if(a1[acc].getAccess()){

				balance = " ";
				name = " ";
				text = "Welcome!";
				a1[acc].checkPin(0);
				checkPinButton.setText("Login");
				namec.setVisible(false);
				
			}else if(exception()){

				for(int i =0; i<3; i++){

					a1[i].checkPin(Integer.parseInt(pinInput.getText()));

					if(a1[i].getAccess()){

						acc = i;
						i = 3;

					}
				}
				if(a1[acc].getAccess()){

					name = "Name: " + a1[acc].getName();
					checkPinButton.setText("Sign Out");
					balance = "Balance: $" + Double.toString(a1[acc].getBalance());
					namec.setVisible(true);

				}else{

					text = a1[acc].getName();

				}	
			}

		}else if(e.getSource() == deposit && a1[acc].getAccess() && exception()){

			a1[acc].deposit(Double.parseDouble(pinInput.getText()));
			balance = "Balance: $" +Double.toString(a1[acc].getBalance());	
			
		}else if(e.getSource() == withdraw && a1[acc].getAccess() && exception()){

			double a = Double.parseDouble(pinInput.getText());

			if(a1[acc].getBalance() > a){

				a1[acc].withdraw(a);
				balance = Double.toString(a1[acc].getBalance());	

			}else{

				balance = Double.toString(a1[acc].getBalance());	
				text = "Not Enough Money";

			}

			balance = "Balance: $" + balance;

		}

		pinInput.setText("");

	}
		
}