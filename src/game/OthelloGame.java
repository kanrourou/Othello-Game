package game;

import java.util.ArrayList;
import java.util.List;

public class OthelloGame extends BoardGame {

	public OthelloGame() {
		super();
		initializeBoard();

	}

	public OthelloGame(OthelloGame game) {
		board = new Board(game.board);
		ai = new AiPlayer(game.ai);
		human = new HumanPlayer(game.human);
		switch(game.currentPlayer.piece) {
		case White:
			currentPlayer = human;
			break;
		case Black:
			currentPlayer = ai;
			break;
		}

	}

	@Override
	public void start() {
		System.out.println("Game Start...");
		System.out.println("AI is black, represented by X...");
		System.out.println("You are white, represented by O...");
		System.out.println("Blank areas are empty boxes...");
		System.out.println("Make sure you input is between 0 - 7 for rows and cols...");
		display();
		while (!isEnd()) {
			System.out.println( currentPlayer.getName() + "'s turn...");
			if (getLegalMove(currentPlayer.getColor()).size() == 0) {
				System.out.println("No place for " + currentPlayer.getName() + " to move, switch player...");
				switchPlayer();
			}
			Move move = currentPlayer.getMove(this);
			System.out.println();
			System.out.println("After " + currentPlayer.getName() +
					"'s move (" + move.getX() + ", " + move.getY() + ")...");
			place(move, currentPlayer.getColor());
			System.out.println("=================================================================");
			display();
		}
		System.out.println("Game end!");
		if (board.getBlackCount() > board.getWhiteCount())
			System.out.println("You lose! Winner is AI!");
		else if (board.getWhiteCount() > board.getBlackCount())
			System.out.println("You win! Winner is Human!");
		else
			System.out.println("Draw!");
	}

	public boolean isEnd() {
		return board.isFull() || board.getBlackCount() == 0 || board.getWhiteCount() == 0;
	}

	@Override
	public void place(Move move, Piece piece) {
		if (currentPlayer.getColor() != piece) {
			System.out.println("Wrong Turn");
			return;
		}
		switch(piece) {
		case White:
			human.place(board, move);
			flipBoard(move,piece);
			break;
		case Black:
			ai.place(board, move);
			flipBoard(move,piece);
			break;
		}
		switchPlayer();
	}

	private void initializeBoard() {
		board.place(new Move(3, 3), Piece.White);
		board.place(new Move(4, 4), Piece.White);
		board.place(new Move(3, 4), Piece.Black);
		board.place(new Move(4, 3), Piece.Black);
	}

	public List<Move> getLegalMove(Piece piece){
		List<Move> LegalMove=new ArrayList<Move>();
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++){
				if(testLegal(i,j,piece)){
					LegalMove.add(new Move(i,j));
				}
			}
		}
		return LegalMove;
	}

	public boolean testLegal(int a,int b,Piece piece){
		if(a<0||a>7||b<0||b>7){
			return false;
		}else{
			if(board.getArray()[a][b]==' '){
				if(checkDirection(a,b,0,1,piece)){
					return true;
				}
				if(checkDirection(a,b,1,1,piece)){
					return true;
				}
				if(checkDirection(a,b,1,0,piece)){
					return true;
				}
				if(checkDirection(a,b,1,-1,piece)){
					return true;
				}
				if(checkDirection(a,b,0,-1,piece)){
					return true;
				}
				if(checkDirection(a,b,-1,-1,piece)){
					return true;
				}
				if(checkDirection(a,b,-1,0,piece)){
					return true;
				}
				if(checkDirection(a,b,-1,1,piece)){
					return true;
				}
			}
			return false;
		}
	}

	private boolean checkDirection(int a,int b, int i, int j, Piece piece){
		if(a+i<0||a+i>7||b+j<0||b+j>7){
			return false;
		}else{
			int tempa=a+i,tempb=b+j;
			switch(piece){
			case White:
				if(board.getArray()[tempa][tempb]=='X'){
					tempa+=i;
					tempb+=j;
					while(tempa<=7&&tempa>=0&&tempb<=7&&tempb>=0){
						if(board.getArray()[tempa][tempb]=='O'){
							return true;
						}else if(board.getArray()[tempa][tempb]==' '){
							return false;
						}
						tempa+=i;
						tempb+=j;
					}
				}
				break;
			case Black:
				if(board.getArray()[tempa][tempb]=='O'){
					tempa+=i;
					tempb+=j;
					while(tempa<=7&&tempa>=0&&tempb<=7&&tempb>=0){
						if(board.getArray()[tempa][tempb]=='X'){
							return true;
						}else if(board.getArray()[tempa][tempb]==' '){
							return false;
						}
						tempa+=i;
						tempb+=j;
					}
				}
			}
			return false;
		}

	}

	private void flipBoard(Move move, Piece piece){
		flipDirection(move,0,1,piece);
		flipDirection(move,1,1,piece);
		flipDirection(move,1,0,piece);
		flipDirection(move,1,-1,piece);
		flipDirection(move,0,-1,piece);
		flipDirection(move,-1,-1,piece);
		flipDirection(move,-1,0,piece);
		flipDirection(move,-1,1,piece);		
	}

	private void flipDirection(Move move,int i, int j, Piece piece){
		int a=move.getX();
		int b=move.getY();
		if(a+i<0||a+i>7||b+j<0||b+j>7){
			return;
		}else{
			boolean needchangestate=false;
			int tempa=a+i,tempb=b+j;
			switch(piece){
			case White:
				if(board.getArray()[tempa][tempb]=='X'&&tempa<=7&&tempa>=0&&tempb<=7&&tempb>=0){
					tempa+=i;
					tempb+=j;
					while(tempa<=7&&tempa>=0&&tempb<=7&&tempb>=0){
						if(board.getArray()[tempa][tempb]==' '){
							break;
						}else if(board.getArray()[tempa][tempb]=='O'){
							needchangestate=true;
							break;
						}
						tempa+=i;
						tempb+=j;
					}
					if(needchangestate){
						tempa=a+i;
						tempb=b+j;
						while(board.getArray()[tempa][tempb]=='X'){
							board.flip(new Move(tempa,tempb), piece);
							tempa+=i;
							tempb+=j;
						}
					}
				}
				break;
			case Black:
				if(board.getArray()[tempa][tempb]=='O'&&tempa<=7&&tempa>=0&&tempb<=7&&tempb>=0){
					tempa+=i;
					tempb+=j;
					while(tempa<=7&&tempa>=0&&tempb<=7&&tempb>=0){
						if(board.getArray()[tempa][tempb]==' '){
							break;
						}else if(board.getArray()[tempa][tempb]=='X'){
							needchangestate=true;
							break;
						}
						tempa+=i;
						tempb+=j;
					}
					if(needchangestate){
						tempa=a+i;
						tempb=b+j;
						while(board.getArray()[tempa][tempb]=='O'){
							board.flip(new Move(tempa,tempb),piece);
							tempa+=i;
							tempb+=j;
						}
					}
				}
			}
		}	
	}

}
