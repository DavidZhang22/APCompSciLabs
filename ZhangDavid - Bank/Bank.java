public class Bank{
	
	private String name;
	private double balance;
	private int pin;
	private boolean access;
	
	public Bank(String n, double b, int p){
		name = n;
		balance = b;
		pin = p;
		access = false;
		
	}
	
	public String getName(){
		if(access){
			return name;
			
		}else{
			return "no access";
		}
	}
	
	public double getBalance(){
		if(access){
			return balance;
			
		}else{
			return 0;
		}
	}
	
	public void disableAccess(){
		access = false;
	}
	
	public boolean getAccess(){
		return access;
	}
	
	public void checkPin(int pin){
		if(this.pin == pin){
			access = true;
		}else{
			access = false;
		}
	}
	
	public void withdraw(double w){
		if(access){
			balance -= w;
		}
		
	}
	
	public void deposit(double d){
		if(access){
			balance += d;
		}
		
	}
	
	public void updateName(String name){
		if(access){
			this.name = name;
		}	
		
	}

}