package assignment1;

public class HighScoreFactory {
	
	public HighScore makeHighScore(String highScoreType, String name, double time) {
		if (highScoreType == "Random"){
				RandomHighScore random = new RandomHighScore(name, time);
				return random;
		}
		
		else {
			GreedyHighScore greedy = new GreedyHighScore(name, time);
			return greedy;
		}
		
	}

}
