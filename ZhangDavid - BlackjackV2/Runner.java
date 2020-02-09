import javax.swing.JFrame;
	
public class Runner{
	public static void main(String[] args){
		JFrame fr = new JFrame("Blackjack");
		Table s = new Table();
		fr.add(s);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.pack();
		fr.setVisible(true);
	
	}

}