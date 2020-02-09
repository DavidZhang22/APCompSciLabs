import java.lang.Math;

public class SlotMachine{
	private int[] num = new int[3];

	private int bal;

	public SlotMachine(int i){

		bal = i;

	}

	public int[] setRandom(){

		for(int i = 0; i< 3; i++){

			num[i] = (int)(1+Math.random()*9.0);

		}

		return num;

	}

	public void checkWin(int bet){

		if(num[0]== num[1] && num[1] == num[2]  && num[2] == 7){

			changeBal(100*bet);
		}else if(num[0] == num[1] && num[1] == num[2]){

			changeBal(5*bet);

		}else if(num[0]== num[1] || num[1] == num[2] || num[2] == num[0]){

			changeBal(2*bet);

		}

	}

	public int getBal(){

		return bal;

	}

	public int changeBal(int i){

		if(bal+i>=0){

			bal+=i;
			return 0;

		}

		return 1;

	}

}