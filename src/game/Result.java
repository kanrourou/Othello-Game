package game;

public class Result {

	private Move move;
	private int score;
	
	public Result() {
		move = null;
		score = 0;
	}
	
	public Result(int score) {
		move = null;
		this.score = score;
	}
	
	public Result(Move move, int score) {
		this.move = move;
		this.score = score;
	}
	
	public void setMove(Move move) {
		this.move = move;
	}
	
	public Move getMove() {
		return move;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}
}
