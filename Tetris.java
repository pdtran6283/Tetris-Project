public class Tetris {
	private int[][] board;
	final int row;
	final int col;
	int score = 0;
	Tetrimino playerPiece;
	boolean playerPieceLock = false;
	boolean gameOver = false;

	public Tetris() {
		row = 10;
		col = 10;
		board = new int[row][col];
		playerPiece = Tetrimino.randomTetrimino();
	}

	public int[][] getBoard() {
		return board;
	}

	private void newPiece() {
		playerPiece = Tetrimino.randomTetrimino();
		if (!playerPiece.boundaries()) gameOver = true;
	}

	public void Down() {
		playerPiece.Down();

		if (!checker()) {
			playerPiece.Up();

			if (playerPieceLock) {
				for (Point e : playerPiece.blocks)
					board[e.getRow()][e.getCol()] = playerPiece.colorID;

				rowClearCheck();
				newPiece();
			} else {
				playerPieceLock = true;
			}
		} else {
			playerPieceLock = false;
		}
	}

	public void Left() {
		playerPiece.Left();

		if (!checker())
			playerPiece.Right();
	}

	public void Right() {
		playerPiece.Right();

		if (!checker())
			playerPiece.Left();
	}

	public void rotateClockwise() {
		playerPiece.rotateClockwise();

		if (!checker())
			playerPiece.rotateCounterClockwise();
	}

	public void rotateCounterClockwise() {
		playerPiece.rotateCounterClockwise();

		if (!checker())
			playerPiece.rotateClockwise();
	}

	private boolean checker() {
		for (Point e : playerPiece.blocks)
			if (board[e.getRow()][e.getCol()] != 0)
				return false;
		return true;
	}

	public void rowClearCheck() {
		for (int r = row - 1; r >= 0; r--) {
			for (int c = 0; c < col; c++) {
				if (board[r][c] == 0)
					break;
				if (c == col - 1)
					reduceRow(r);
			}
		}
	}

	public void reduceRow(int r) {
		for (int i = r + 1; i >= 0; i--) 
			board[i - 1] = board[i];

		score += 100;
	}
}