package game;

public class BoardGame {

	protected Board board;
	protected Player ai;
	protected Player human;
	protected Player currentPlayer;
	
	public BoardGame () {
		board = new Board();
		ai = new AiPlayer();
		human = new HumanPlayer();
		currentPlayer = human;
	}
	
	public void start() {
		
	}
	
	public void place(Move move, Piece piece) {
		switch(piece) {
		case White:
			human.place(board, move);
			break;
		case Black:
			ai.place(board, move);
			break;
		}
	}
	
	public Player getHumanPlayer() {
		return human;
	}
	
	public Player getAiPlayer() {
		return ai;
	}
	
	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	
	public void display() {
		board.print();
	}
	
	public Board getBoard() {
		return board;
	}
	
	protected void switchPlayer() {
		switch(currentPlayer.getColor()) {
		case White:
			currentPlayer = ai;
			break;
		case Black:
			currentPlayer = human;
			break;
		}
	}
}
