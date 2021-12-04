package assignment1;

public class HighScore {
	
	private String name;
	private double time;
	
	public HighScore(String n, double t) {
		this.name = n;
		this.time = t;
	}
	
	public String getName() { 
		return this.name;
	}
	
	public double getTime() { 
		return this.time;
	}
}
