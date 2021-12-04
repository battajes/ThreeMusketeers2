package assignment1;

public class HighScore {
	
	private String name;
	private double time;
	private String type;
	
	public HighScore(String name, double time, String type) {
		this.name = name;
		this.time = time;
		this.type = type;
	}
	
	public String getName() { 
		return this.name;
	}
	
	public double getTime() { 
		return this.time;
	}
	
	public String getType() {
		return this.type;
	}
}
