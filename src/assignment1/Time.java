package assignment1;

public class Time implements Observer{
	private double time; 
	
	public void setTime(double d) { 
		this.time = d;
	}
	
	public void getTime() {
		System.out.print("Time: ");
		System.out.format("%.2f", this.time);
		System.out.println();
	}

	public void update(Object o) {
		this.setTime(((StopWatch) o).getTime());
	}

}
