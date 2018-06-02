public abstract class Tetrimino {
	int colorID;
	Point[] blocks = new Point[4];
	final int row = 22;
	final int col = 10;

	// Move Tetrimino Left One Unit
	void Left() {
		for (Point e : blocks) 
			e.Left();
		if (outOfBounds()) 
			for (Point e : blocks) e.Right();
	}

	// Move Tetrimino Right One Unit
	void Right() {
		for (Point e : blocks) e.Right();
		if (outOfBounds())
			for (Point e : blocks) e.Left();
	}

	// Move Tetrimino Down One Unit
	void Down() {
		for (Point e : blocks) e.Down();
		// if (outOfBounds())
			// for (Point e : blocks) e.Up();
	}

	void Up() {
		for (Point e : blocks) e.Up();
		if (outOfBounds())
			for (Point e : blocks) e.Down();
	}

	public boolean outOfBounds() {
		for (Point e : blocks) {
			if (e.getRow() < 0 || e.getCol() < 0) return true;
			if (e.getRow() >= row || e.getCol() >= col) return true;
		}
		return false;
	}

	public int getColorID() {
		return colorID;
	}

	abstract void rotateClockwise();
	abstract void rotateCounterClockwise();

	// return random Tetrimino
	static Tetrimino randomTetrimino() {
		switch ((int) (Math.random() * 7)) {
			case 0: return new Square();
			case 1: return new Line();
			case 2: return new LLeft();
			case 3: return new LRight();
			case 4: return new Tee();
			case 5: return new LeftN();
			case 6: return new RightN();
			default: return randomTetrimino();
		}
	}

	public String toString() {
		String s = "";
		for (Point e : blocks)
			s += e + " ";
		return s;
	}
}

class Square extends Tetrimino {
	public Square() {
		/* Numbering Convention
			0 1
			2 3
		*/
		blocks[0] = new Point(0, col / 2 - 1);
		blocks[1] = new Point(0, col / 2);
		blocks[2] = new Point(1, col / 2 - 1);
		blocks[3] = new Point(1, col / 2);
		colorID = 1;
	}

	void rotateClockwise() {};
	void rotateCounterClockwise() {};
}

class Line extends Tetrimino {
	// Oriented in the horizontal direction
	private boolean orientUp = false;

	public Line() {
		/* Numbering Convention
			0 1 2 3
		*/
		blocks[0] = new Point(0, (col / 2) - 2);
		blocks[1] = new Point(0, (col / 2) - 1);
		blocks[2] = new Point(0, col / 2);
		blocks[3] = new Point(0, (col / 2) + 1);
		colorID = 2;
	}

	void rotateClockwise() {
		Point pivot = blocks[2];

		if (orientUp) {
			for (int i = 0; i < 4; i++) {
				blocks[i] = blocks[i].subtract(pivot);
				blocks[i].rotateClockwise();
				blocks[i] = pivot.add(blocks[i]);
			}
		} else {
			for (int i = 0; i < 4; i++) {
				blocks[i] = blocks[i].subtract(pivot);
				blocks[i].rotateCounterClockwise();
				blocks[i] = pivot.add(blocks[i]);
			}
		}

		if (outOfBounds()) rotateCounterClockwise();

		orientUp = !orientUp;
	}

	void rotateCounterClockwise() {
		rotateClockwise();
	}
}

class LRight extends Tetrimino {
	public LRight() {
		/* Numbering Convention
			0 1 2
			3
		*/
		blocks[0] = new Point(0, (col / 2) - 1);
		blocks[1] = new Point(0, col / 2);
		blocks[2] = new Point(0, (col / 2) + 1);
		blocks[3] = new Point(1, (col / 2) - 1);
		colorID = 3;
	}

	void rotateCounterClockwise() {
		Point pivot = blocks[1];

		for (int i = 0; i < 4; i++) {
			blocks[i] = blocks[i].subtract(pivot);
			blocks[i].rotateCounterClockwise();
			blocks[i] = pivot.add(blocks[i]);
		}

		if (outOfBounds()) rotateClockwise();
	}

	void rotateClockwise() {
		Point pivot = blocks[1];

		for (int i = 0; i < 4; i++) {
			blocks[i] = blocks[i].subtract(pivot);
			blocks[i].rotateClockwise();
			blocks[i] = pivot.add(blocks[i]);
		}

		if (outOfBounds()) rotateCounterClockwise();
	}
}

class LLeft extends Tetrimino {
	public LLeft() {
		/* Numbering Convention
			0 1 2
			    3
		*/
		blocks[0] = new Point(0, (col / 2) - 1);
		blocks[1] = new Point(0, col / 2);
		blocks[2] = new Point(0, (col / 2) + 1);
		blocks[3] = new Point(1, (col / 2) + 1);
		colorID = 4;
	}

	void rotateCounterClockwise() {
		Point pivot = blocks[1];

		for (int i = 0; i < 4; i++) {
			blocks[i] = blocks[i].subtract(pivot);
			blocks[i].rotateCounterClockwise();
			blocks[i] = pivot.add(blocks[i]);
		}

		if (outOfBounds()) rotateClockwise();
	}

	void rotateClockwise() {
		Point pivot = blocks[1];

		for (int i = 0; i < 4; i++) {
			blocks[i] = blocks[i].subtract(pivot);
			blocks[i].rotateClockwise();
			blocks[i] = pivot.add(blocks[i]);
		}

		if (outOfBounds()) rotateCounterClockwise();
	}
}

class Tee extends Tetrimino {
	public Tee() {
		/* Numbering Convention
			0 1 2
			  3
		*/
		blocks[0] = new Point(0, (col / 2) - 1);
		blocks[1] = new Point(0, col / 2);
		blocks[2] = new Point(0, (col / 2) + 1);
		blocks[3] = new Point(1, col / 2);
		colorID = 5;
	}

	void rotateCounterClockwise() {
		Point pivot = blocks[1];

		for (int i = 0; i < 4; i++) {
			blocks[i] = blocks[i].subtract(pivot);
			blocks[i].rotateCounterClockwise();
			blocks[i] = pivot.add(blocks[i]);
		}

		if (outOfBounds()) rotateClockwise();
	}

	void rotateClockwise() {
		Point pivot = blocks[1];

		for (int i = 0; i < 4; i++) {
			blocks[i] = blocks[i].subtract(pivot);
			blocks[i].rotateClockwise();
			blocks[i] = pivot.add(blocks[i]);
		}

		if (outOfBounds()) rotateCounterClockwise();
	}
}

class LeftN extends Tetrimino {
	public LeftN() {
		/* Numbering Convention
			  0 1
			2 3
		*/
		blocks[0] = new Point(0, col / 2);
		blocks[1] = new Point(0, (col / 2) + 1);
		blocks[2] = new Point(1, (col / 2) - 1);
		blocks[3] = new Point(1, col / 2);
		colorID = 6;
	}

	void rotateCounterClockwise() {
		Point pivot = blocks[0];

		for (int i = 0; i < 4; i++) {
			blocks[i] = blocks[i].subtract(pivot);
			blocks[i].rotateCounterClockwise();
			blocks[i] = pivot.add(blocks[i]);
		}

		if (outOfBounds()) rotateClockwise();
	}

	void rotateClockwise() {
		Point pivot = blocks[0];

		for (int i = 0; i < 4; i++) {
			blocks[i] = blocks[i].subtract(pivot);
			blocks[i].rotateClockwise();
			blocks[i] = pivot.add(blocks[i]);
		}

		if (outOfBounds()) rotateCounterClockwise();
	}
}

class RightN extends Tetrimino {
	public RightN() {
		/* Numbering Convention
			0 1
			  2 3
		*/
		blocks[0] = new Point(0, (col / 2) - 1);
		blocks[1] = new Point(0, col / 2);
		blocks[2] = new Point(1, col / 2);
		blocks[3] = new Point(1, (col / 2) + 1);
		colorID = 7;
	}

	void rotateCounterClockwise() {
		Point pivot = blocks[1];

		for (int i = 0; i < 4; i++) {
			blocks[i] = blocks[i].subtract(pivot);
			blocks[i].rotateCounterClockwise();
			blocks[i] = pivot.add(blocks[i]);
		}

		if (outOfBounds()) rotateClockwise();
	}

	void rotateClockwise() {
		Point pivot = blocks[1];

		for (int i = 0; i < 4; i++) {
			blocks[i] = blocks[i].subtract(pivot);
			blocks[i].rotateClockwise();
			blocks[i] = pivot.add(blocks[i]);
		}

		if (outOfBounds()) rotateCounterClockwise();
	}
}

class Point {
	int row;
	int col;

	public Point(int r, int c) {
		row = r;
		col = c;
	}

	public void Right() {
		col++;
	}

	public void Left() {
		col--;
	}

	public void Down() {
		row++;
	}

	public void Up() {
		row--;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public void setRow(int r) {
		row = r;
	}

	public void setCol(int c) {
		col = c;
	}

	public Point add(Point a) {
		return new Point(row + a.row, col + a.col);
	}

	public Point subtract(Point a) {
		return new Point(row - a.row, col - a.col);
	}

	public void rotateCounterClockwise() {
		int temp = row;
		row = -col;
		col = temp;
	}

	public void rotateClockwise() {
		int temp = row;
		row = col;
		col = -temp;
	}

	public String toString() {
		return "(" + row + "," + col + ")";
	}
}