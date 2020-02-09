import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;



public class Card{

	private String name;
	private int value;
	private String suit;
	private BufferedImage suitImage;
	private boolean visible = false;

	private boolean cover = false;
	private int symbolnum;

	public Card(String n, int v, String suit){

		name = n;
		if(v>10){
			value = 10;
			symbolnum = 2;
		}else{
			value = v;
			symbolnum = v;
		}

		this.suit = suit;

		if(suit.equals("Hearts")){
			try{
				suitImage = ImageIO.read(new File("Images/heart.png"));	
			}catch(IOException e){
				System.out.println(e);
			}
		}else if(suit.equals("Spades")){
			try{
				suitImage = ImageIO.read(new File("Images/spade.png"));	
			}catch(IOException e){
				System.out.println(e);
			}
		}else if(suit.equals("Clubs")){
			try{
				suitImage = ImageIO.read(new File("Images/club.png"));	
			}catch(IOException e){
				System.out.println(e);
			}
		}else if(suit.equals("Diamonds")){
			try{
				suitImage = ImageIO.read(new File("Images/diamond.png"));	
			}catch(IOException e){
				System.out.println(e);
			}
		}

	}

	public void setVisible(boolean s){
		visible = s;
	}

	public boolean visi(){
		return visible;
	}

	public void cover(boolean b){
		cover = b;
	}

	public void cove(){
		if(cover){
			System.out.println(symbolnum);
		}
	}

	public String cardName(){
		return name;
	}
	public int getV(){
		return value;
	}

	public void drawMe(Graphics g, int x, int y){

		if(visible){

			g.setColor(Color.WHITE);

			g.fillRoundRect(x, y, 100, 170, 10, 10);

			g.setColor(Color.BLACK);

			g.drawRoundRect(x, y, 100, 170, 10, 10);

			Font font = new Font("Arial", Font.PLAIN, 50);
			g.setFont(font);

			if(suit.equals("hearts")|| suit.equals("diamonds")){
				g.setColor(Color.RED);
			}else{
				g.setColor(Color.BLACK);
			}
			
			g.drawString(name, x+30, y+100);
			int k = 0;
			int j = 0;
			for(int i =0; i< symbolnum/2 && symbolnum%2 == 0; i++){
				g.drawImage(suitImage, x+10+j, y+10+k, null);
				g.drawImage(suitImage, x+70-j, y+140-k, null);
				j+=20;
				if(j+20>80){
					j = 0;
					k+=25;
				}
			}
			
			for(int i =0; i< symbolnum/2+1 && symbolnum%2 == 1; i++){
				g.drawImage(suitImage, x+10+j, y+10+k, null);

				if(i !=symbolnum/2){
					g.drawImage(suitImage, x+70-j, y+140-k, null);
				}
				j+=20;
				if(j>60){
					j = 0;
					k+=25;
				}
			}


		}
		if(cover){

			g.setColor(Color.BLUE);

			g.fillRoundRect(x, y, 100, 170, 10, 10);
		}
	}
}