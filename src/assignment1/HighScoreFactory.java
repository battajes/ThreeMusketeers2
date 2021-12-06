package assignment1;

public class HighScoreFactory {
	
	public static HighScore makeHighScore(String highScoreType, String name, double time, String type) {
		if (highScoreType == "Random"){
				RandomHighScore random = new RandomHighScore(name, time, type);
				return random;
		}
		
		if (highScoreType == "Greedy") {
			GreedyHighScore greedy = new GreedyHighScore(name, time, type);
			return greedy;
		}
		
		return null;
		
	}

}
