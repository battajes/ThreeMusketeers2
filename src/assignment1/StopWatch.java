package assignment1;

import java.util.Timer;
import java.util.TimerTask;

public class StopWatch extends Observable{
	public Time time = new Time();
	private Timer timer = new Timer();
	private double seconds = 0.00;
	private int minutes = 0;
	private static boolean running = true;
	
	TimerTask task = new TimerTask() {
		public void run() {
			seconds += 0.01;
			if (seconds >= 0.60) {
				seconds = 0;
				minutes += 1;
			}
		}
	};
	
	public void start() {
		timer.scheduleAtFixedRate(task, 1000, 1000);
	}

	public static void main(String[] args) {
		StopWatch stopwatch = new StopWatch();
		if (running) {
			stopwatch.start();
		}
	}
	
	public double getTime() {
		return this.minutes + this.seconds;
	}
	
	public void stopTimer() {
		this.running = false;
	}

	public void notifyObserver() {
		this.time.update(this);
	}
}
