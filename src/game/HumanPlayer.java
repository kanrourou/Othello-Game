package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class HumanPlayer extends Player {

	public HumanPlayer() {
		super("human");
		piece = Piece.White;
	}
	
	public HumanPlayer(Player human) {
		super("human");
		piece = human.piece;
	}
	
	@Override
	public void place(Board board, Move move) {
		board.place(move, piece);
	}
	
	@Override
	public Move getMove(OthelloGame game) {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String row = null, col = null;
		Move move = null;
		boolean isValid = false;
		while (true) {
			try {
				System.out.println("Please input row number...");
				row = in.readLine();
				if (!isNum(row)) {
					System.out.println("Input is not number, please retry!");
					continue;
				}
				System.out.println("Please input column number...");
				col = in.readLine();
				if (!isNum(row)) {
					System.out.println("Input is not number, please retry!");
					continue;
				}
				
				isValid = row.length() != 0 &&
						col.length() != 0 && game.testLegal(row.charAt(0) - '0', col.charAt(0) - '0', piece);
				if (isValid)
					break;
				System.out.println("Invalid Position, please retry!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		move = new Move(Integer.parseInt(row), Integer.parseInt(col));
		return move;
	}
	
	private boolean isNum(String str) {
		if (str.length() == 0)
			return false;
		char c = str.charAt(0);
		int num = c - '0';
		return num >= 0 && num <= 9;
	}

}
