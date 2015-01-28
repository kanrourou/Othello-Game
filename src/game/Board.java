package game;

public class Board {

	private static int rows = 8;
	private static int cols = 8;
	private int whiteCount;
	private int blackCount;
	private char[][] board;

	public Board() {
		board = new char[rows][cols];
		whiteCount = 0;
		blackCount = 0;
		//initialize
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				board[i][j] = ' ';
			}
		}
	}

	public Board(Board b) {
		this.whiteCount = b.whiteCount;
		this.blackCount = b.blackCount;
		this.board = new char[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < rows; j++) {
				board[i][j] = b.getArray()[i][j];
			}
		}
	}

	public void print() {
		System.out.printf("%s ", " ");
		for (int i = 0; i < rows; i++) {
			System.out.printf("%d ", i);
		}
		System.out.println();
		for (int i = 0 ; i < rows; i++) {
			System.out.printf("%d ", i);
			for (int j = 0; j < cols; j++) {
				System.out.printf("%c ", board[i][j]);
			}
			System.out.println();
		}
		System.out.println("White: " + whiteCount + "    " + "Black: " + blackCount);
		System.out.println();
	}

	public void place(Move move, Piece piece) {
		int x = move.getX();
		int y = move.getY();
		switch(piece) {
		case White:
			board[x][y] = 'O';
			whiteCount++;
			break;
		case Black:
			board[x][y] = 'X';
			blackCount++;
			break;
		}
	}

	public void flip(Move move, Piece piece) {
		int x = move.getX();
		int y = move.getY();
		switch(piece) {
		case White:
			board[x][y] = 'O';
			whiteCount++;
			blackCount--;
			break;
		case Black:
			board[x][y] = 'X';
			blackCount++;
			whiteCount--;
			break;
		}
	}

	public int getWhiteCount() {
		return whiteCount;
	}

	public int getBlackCount() {
		return blackCount;
	}

	public char[][] getArray() {
		return board;
	}

	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}

	public boolean isFull() {
		return whiteCount + blackCount == rows * cols;
	}
}
