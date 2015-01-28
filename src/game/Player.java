package game;

public abstract class Player {

	protected String name;
	protected Piece piece;
	
	public Player(String name) {
		this.name = name;
	}
	
	
	public abstract void place(Board board, Move move);
	public abstract Move getMove(OthelloGame game);
	
	public String getName() {
		return name;
	}
	
	public Piece getColor(){
		return piece;
	}
}
