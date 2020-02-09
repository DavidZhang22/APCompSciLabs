import javax.swing.JFrame;
import java.util.Scanner;
public class Runner{
	public static void main(String[] args){
		JFrame f = new JFrame("Scenery");	
		Scanner keyboard = new Scanner(System.in);
		int time = 1; int sky = 2;

		System.out.println("Input 1 for Day, and 2 for Night");
		time = keyboard.nextInt();
		
		System.out.println("Input 1 for clear sky, and 2 for cloudy sky");
		sky = keyboard.nextInt();
		
		Scenery sc = new Scenery(time, sky);
		
		
		f.add( sc );
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setVisible(true);
			
	}
}
