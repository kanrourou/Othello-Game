package game;

public class AiPlayer extends Player {

	public AiPlayer() {
		super("AI");
		piece = Piece.Black;
	}
	
	public AiPlayer(Player ai) {
		super("AI");
		piece = ai.piece;
	}

	@Override
	public void place(Board board, Move move) {
		board.place(move, piece);
	}
	
	@Override
	public Move getMove(OthelloGame game) {
		AlphaBeta alphaBeta = new AlphaBeta();
		return alphaBeta.getMove(game);
	}
}
