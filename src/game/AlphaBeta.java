package game;

import java.util.ArrayList;

public class AlphaBeta {

	private static int maxDepth = 1;
	private Evaluator evaluator = new Evaluator();
	
	public Move getMove(OthelloGame game) {
		int alpha = Integer.MIN_VALUE;
		int beta = Integer.MAX_VALUE;
		Result result;
		if (game.getCurrentPlayer().getColor() != Piece.Black)
			System.out.println("Wrong AI!");
		switch (game.getCurrentPlayer().getColor()) {
		case Black:
			result = max(game, 0, alpha, beta);
			break;
		case White:
			result = min(game, 0, alpha, beta);
			break;
		default:
			result = new Result();
			break;
			
		}
		return result.getMove();
	}
	
	private Result max(OthelloGame game, int depth, int alpha, int beta) {
		Result result = new Result();
		if (depth >= maxDepth) 
			return new Result(evaluator.evaluate(game));
		ArrayList<Move> moves = (ArrayList<Move>) game.getLegalMove(game.getCurrentPlayer().getColor());
		if (moves.size() == 0)
			return new Result(evaluator.evaluate(game));
		int maxScore = Integer.MIN_VALUE;
		Move bestMove = null;
		for (int i = 0; i < moves.size(); i++) {
			OthelloGame newGame = new OthelloGame(game);
			if (newGame.getCurrentPlayer().getColor() != Piece.Black) {
				System.out.println("Color Inconsistency!");
				return result;
			}
			newGame.place(moves.get(i), newGame.getCurrentPlayer().getColor());
			Result minResult = min(newGame, depth + 1, alpha, beta);
			int score = minResult.getScore();
			if (score > maxScore) {
				maxScore = score;
				bestMove = moves.get(i);
			}
			if (score >= beta)
				return new Result(bestMove, maxScore);
			if (score > alpha)
				alpha = score;
		}
		result.setMove(bestMove);
		result.setScore(maxScore);
		return result;
	}
	
	private Result min(OthelloGame game, int depth, int alpha, int beta) {
		Result result = new Result();
		if (depth >= maxDepth)
			return new Result(evaluator.evaluate(game));
		ArrayList<Move> moves = (ArrayList<Move>) game.getLegalMove(game.getCurrentPlayer().getColor());
		if (moves.size() == 0)
			return new Result(evaluator.evaluate(game));
		int minScore = Integer.MAX_VALUE;
		Move bestMove = null;
		for (int i = 0; i < moves.size(); i++) {
			OthelloGame newGame = new OthelloGame(game);
			if (newGame.getCurrentPlayer().getColor() != Piece.White) {
				System.out.println("Color Inconsistency!");
				return result;
			}
			newGame.place(moves.get(i), newGame.getCurrentPlayer().getColor());
			Result maxResult = max(newGame, depth + 1, alpha, beta);
			int score = maxResult.getScore();
			if (score < minScore) {
				minScore = score;
				bestMove = moves.get(i);
			}
			if (score <= alpha)
				return new Result(bestMove, minScore);
			if (score < beta)
				beta = score;
		}
		result.setMove(bestMove);
		result.setScore(minScore);
		return result;
	}
}
