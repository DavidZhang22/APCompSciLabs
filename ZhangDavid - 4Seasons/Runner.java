import javax.swing.JFrame;
	
public class Runner{
	public static void main(String[] args){
		JFrame fr = new JFrame("4Seasons");
		Scenery s = new Scenery();
		fr.add(s);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.pack();
		fr.setVisible(true);
	
	}

}