package game;

import java.util.List;

public class Evaluator {
	
	private final int EvalStable[][]={{100,-5,10,5,5,10,-5,100},
			                {-5,-45,1,1,1,1,-45,-5},
			                {10,1,3,2,2,3,1,10},
			                {5,1,2,1,1,2,1,5},
			                {5,1,2,1,1,2,1,5},
			                {10,1,3,2,2,3,1,10},
			                {-5,-45,1,1,1,1,-45,-5},
			                {100,-5,10,5,5,10,-5,100}
	                               };
	
	public int evaluate(OthelloGame game)
	{
		int score;
		int pieceDiff;
		int mobility;
		int stability;
		int cornerOccup;
		
		pieceDiff=calPieceDiff(game);
		mobility=calMobility(game);
		stability=calStability(game);
		cornerOccup=calCornerOccup(game);
		
		score=1*pieceDiff+100*mobility+10*stability+4*cornerOccup;
		return score;
		
	}
	
	
	
	private int calPieceDiff(OthelloGame game)
	{
		Board board=game.getBoard();
		int score;
		int B=board.getBlackCount();
		int W=board.getWhiteCount();
		
		score=B-W;
		
		return score;		
	}
	
	
	private int calMobility(OthelloGame game)
	{
		List<Move> blackMoves=game.getLegalMove(Piece.Black);
		List<Move> whiteMoves=game.getLegalMove(Piece.White);
		
		int mobility;
		int B=blackMoves.size();
		int W=whiteMoves.size();
		
		mobility=B-W;
		return mobility;
	}
	
	
	private int calStability(OthelloGame game)
	{
		Board board=game.getBoard();
		char chessBoard[][]=board.getArray();
		int dime=board.getRows();
		int B=0,W=0,score;
		
		for(int i=0;i<dime;i++)
		{
			for(int j=0;j<dime;j++)
			{
				if(chessBoard[i][j]=='X')
					B+=EvalStable[i][j];
				
				if(chessBoard[i][j]=='O')
					W+=EvalStable[i][j];		
			}
		}
		
		score=B-W;
		return score;
		
	}
	
	
	private int calCornerOccup(OthelloGame game)
	{
		Board board=game.getBoard();
		char chessBoard[][]=board.getArray();
		int row=board.getRows();
		int score=0;
		
		if(chessBoard[0][0]=='X')
			score+=25;
		
		if(chessBoard[0][row-1]=='X')
			score+=25;
		
		if(chessBoard[row-1][0]=='X')
			score+=25;
		
		if(chessBoard[row-1][row-1]=='X')
			score+=25;
		
		if(chessBoard[0][0]=='O')
			score-=25;
		
		if(chessBoard[0][row-1]=='O')
			score-=25;
		
		if(chessBoard[row-1][0]=='O')
			score-=25;
		
		if(chessBoard[row-1][row-1]=='O')
			score=-25;
	
		return score;
		
	}

}
