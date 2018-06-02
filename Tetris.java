import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Tetris extends JPanel implements ActionListener {
	private int[][] board;
	final int row;
	final int col;
	int score = 0;
	Tetrimino playerPiece;
	Tetrimino shadow;
	boolean playerPieceLock = false;
	boolean gameOver = false;

	Timer timer;
	Color palette[] = {
		new Color(0, 0, 0), new Color(204, 102, 102),
 		new Color(102, 204, 102), new Color(102, 102, 204),
 		new Color(204, 204, 102), new Color(204, 102, 204),
 		new Color(102, 204, 204), new Color(218, 170, 0)
 	};

	public Tetris(Game parent) {
		row = 22;
		col = 10;
		setFocusable(true);
		timer = new Timer(400, this);
		board = new int[row][col];
		addKeyListener(new keyInput());
		playerPiece = Tetrimino.randomTetrimino();
	}

	public void actionPerformed(ActionEvent e) {
		if (playerPieceLock) {
			newPiece();
			rowClearCheck();
			playerPieceLock = false;
		} else {
			Down();
		}
	}

	int squareWidth() { 
		return (int) getSize().getWidth() / col;
	}

	int squareHeight() { 
		return (int) getSize().getHeight() / row;
	}

	public void startGame() {
		clearBoard();
		gameOver = false;
		playerPieceLock = false;
		score = 0;

		timer.start();
	}

	public void clearBoard() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				board[i][j] = 0;
			}
		}
	}

	private void newPiece() {
		for (Point p : playerPiece.blocks)
			board[p.getRow()][p.getCol()] = playerPiece.colorID;

		playerPiece = Tetrimino.randomTetrimino();
		if (!checker()) {
			gameOver = true;
			timer.stop();
		}
		repaint();
	}

	public void paint(Graphics g) {
		super.paint(g);
		drawBoard(g);
		drawTetrimino(g);
	}

	public void drawBoard(Graphics g) {
		for (int i = 0; i < row; i++) 
			for (int j = 0; j < col; j++) 
				drawBlock(g, i, j);
	}

	public void drawBlock(Graphics g, int r, int c, int colorID) {
		int boardTop = (int) getSize().getHeight() - row * squareHeight();

		Color color = palette[colorID];

		int ctemp = c * squareWidth();
		int rtemp = boardTop + r * squareHeight();

		g.setColor(color);
 		g.fillRect(ctemp + 1, rtemp + 1, squareWidth() - 2, squareHeight() - 2);
 		g.setColor(color.brighter());
 		g.drawLine(ctemp, rtemp + squareHeight() - 1, ctemp, rtemp);
 		g.drawLine(ctemp, rtemp, ctemp + squareWidth() - 1, rtemp);
 		g.setColor(color.darker());
 		g.drawLine(ctemp + 1, rtemp + squareHeight() - 1, ctemp + squareWidth() - 1, rtemp + squareHeight() - 1);
 		g.drawLine(ctemp + squareWidth() - 1, rtemp + squareHeight() - 1, ctemp + squareWidth() - 1, rtemp + 1);
	}

	public void drawBlock(Graphics g, int r, int c) {
		int boardTop = (int) getSize().getHeight() - row * squareHeight();

		Color color = palette[board[r][c]];

		int ctemp = c * squareWidth();
		int rtemp = boardTop + r * squareHeight();

		g.setColor(color);
 		g.fillRect(ctemp + 1, rtemp + 1, squareWidth() - 2, squareHeight() - 2);
 		g.setColor(color.brighter());
 		g.drawLine(ctemp, rtemp + squareHeight() - 1, ctemp, rtemp);
 		g.drawLine(ctemp, rtemp, ctemp + squareWidth() - 1, rtemp);
 		g.setColor(color.darker());
 		g.drawLine(ctemp + 1, rtemp + squareHeight() - 1, ctemp + squareWidth() - 1, rtemp + squareHeight() - 1);
 		g.drawLine(ctemp + squareWidth() - 1, rtemp + squareHeight() - 1, ctemp + squareWidth() - 1, rtemp + 1);
	}

	public void drawTetrimino(Graphics g) {
		Color color = palette[playerPiece.colorID];
		for (Point p : playerPiece.blocks) 
			drawBlock(g, p.getRow(), p.getCol(), playerPiece.colorID);
	}

	public void Down() {
		playerPiece.Down();

		if (!checker()) {
			playerPiece.Up();
			playerPieceLock = true;
		}

		repaint();
	}

	public void fullDown() {
		while (!playerPieceLock)
			Down();
	}

	public void Left() {
		playerPiece.Left();

		if (!checker())
			playerPiece.Right();

		repaint();
	}

	public void Right() {
		playerPiece.Right();

		if (!checker())
			playerPiece.Left();

		repaint();
	}

	public void rotateClockwise() {
		playerPiece.rotateClockwise();

		if (!checker())
			playerPiece.rotateCounterClockwise();

		repaint();
	}

	public void rotateCounterClockwise() {
		playerPiece.rotateCounterClockwise();
		if (!checker())
			playerPiece.rotateClockwise();
		repaint();
	}

	private boolean checker() {
		for (Point e : playerPiece.blocks) {
			if (e.getRow() == row) return false;
			if (board[e.getRow()][e.getCol()] != 0)
				return false;
		}
		return true;
	}

	public void rowClearCheck() {
		for (int r = row - 1; r >= 0; r--) {
			for (int c = 0; c < col; c++) {
				if (board[r][c] == 0)
					break;
				if (c == col - 1) {
					reduceRow(r++);
					repaint();
				}
			}
		}
	}

	public void reduceRow(int r) {
		System.out.println(r);
		for (int i = r - 1; i >= 0; i--) 
			board[i + 1] = board[i];

		score += 100;
	}

	class keyInput extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			if (gameOver)
				return;

			int keyPressed = e.getKeyCode();

			switch (keyPressed) {
				case KeyEvent.VK_LEFT:
					Left();
					break;
				case KeyEvent.VK_RIGHT:
					Right();
					break;
				case KeyEvent.VK_UP:
					rotateClockwise();
					break;
				case KeyEvent.VK_DOWN:
					rotateCounterClockwise();
					break;
				case 68:
					Down();
					break;
				case KeyEvent.VK_SPACE:
					fullDown();
					break;
				default:
					break;
			}
		}
	}
}