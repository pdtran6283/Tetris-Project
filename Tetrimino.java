public abstract class Tetrimino {
	int colorID;
	Point[] blocks = new Point[4];

	// Move Tetrimino Left One Unit
	void Left() {
		if (boundaries()) return;
		for (Point e : blocks) e.Left();
	}

	// Move Tetrimino Right One Unit
	void Right() {
		if (boundaries()) return;
		for (Point e : blocks) e.Right();
	}

	// Move Tetrimino Down One Unit
	void Down() {
		if (boundaries()) return;
		for (Point e : blocks) e.Down();
	}

	void Up() {
		if (boundaries()) return;
		for (Point e : blocks) e.Up();
	}

	public boolean boundaries() {
		for (Point e : blocks) {
			if (e.getRow() <= 0 || e.getCol() <= 0) return true;
			if (e.getRow() >= 10 || e.getCol() >= 10) return true;
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
}

class Square extends Tetrimino {
	public Square() {
		/* Numbering Convention
			0 1
			2 3
		*/
		blocks[0] = new Point(0, 0);
		blocks[1] = new Point(0, 1);
		blocks[2] = new Point(1, 0);
		blocks[3] = new Point(1, 1);
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
		blocks[0] = new Point(0, 0);
		blocks[1] = new Point(0, 1);
		blocks[2] = new Point(0, 2);
		blocks[3] = new Point(0, 3);
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
		blocks[0] = new Point(0, 0);
		blocks[1] = new Point(0, 1);
		blocks[2] = new Point(0, 2);
		blocks[3] = new Point(1, 0);
		colorID = 3;
	}

	void rotateCounterClockwise() {
		Point pivot = blocks[1];

		for (int i = 0; i < 4; i++) {
			blocks[i] = blocks[i].subtract(pivot);
			blocks[i].rotateCounterClockwise();
			blocks[i] = pivot.add(blocks[i]);
		}
	}

	void rotateClockwise() {
		Point pivot = blocks[1];

		for (int i = 0; i < 4; i++) {
			blocks[i] = blocks[i].subtract(pivot);
			blocks[i].rotateClockwise();
			blocks[i] = pivot.add(blocks[i]);
		}
	}
}

class LLeft extends Tetrimino {
	public LLeft() {
		/* Numbering Convention
			0 1 2
			    3
		*/
		blocks[0] = new Point(0, 0);
		blocks[1] = new Point(0, 1);
		blocks[2] = new Point(0, 2);
		blocks[3] = new Point(1, 2);
		colorID = 4;
	}

	void rotateCounterClockwise() {
		Point pivot = blocks[1];

		for (int i = 0; i < 4; i++) {
			blocks[i] = blocks[i].subtract(pivot);
			blocks[i].rotateCounterClockwise();
			blocks[i] = pivot.add(blocks[i]);
		}
	}

	void rotateClockwise() {
		Point pivot = blocks[1];

		for (int i = 0; i < 4; i++) {
			blocks[i] = blocks[i].subtract(pivot);
			blocks[i].rotateClockwise();
			blocks[i] = pivot.add(blocks[i]);
		}
	}
}

class Tee extends Tetrimino {
	public Tee() {
		/* Numbering Convention
			0 1 2
			  3
		*/
		blocks[0] = new Point(0, 0);
		blocks[1] = new Point(0, 1);
		blocks[2] = new Point(0, 2);
		blocks[3] = new Point(1, 1);
		colorID = 5;
	}

	void rotateCounterClockwise() {
		Point pivot = blocks[1];

		for (int i = 0; i < 4; i++) {
			blocks[i] = blocks[i].subtract(pivot);
			blocks[i].rotateCounterClockwise();
			blocks[i] = pivot.add(blocks[i]);
		}
	}

	void rotateClockwise() {
		Point pivot = blocks[1];

		for (int i = 0; i < 4; i++) {
			blocks[i] = blocks[i].subtract(pivot);
			blocks[i].rotateClockwise();
			blocks[i] = pivot.add(blocks[i]);
		}
	}
}

class LeftN extends Tetrimino {
	public LeftN() {
		/* Numbering Convention
			  0 1
			2 3
		*/
		blocks[0] = new Point(0, 1);
		blocks[1] = new Point(0, 2);
		blocks[2] = new Point(1, 0);
		blocks[3] = new Point(1, 1);
		colorID = 6;
	}

	void rotateCounterClockwise() {
		Point pivot = blocks[0];

		for (int i = 0; i < 4; i++) {
			blocks[i] = blocks[i].subtract(pivot);
			blocks[i].rotateCounterClockwise();
			blocks[i] = pivot.add(blocks[i]);
		}
	}

	void rotateClockwise() {
		Point pivot = blocks[0];

		for (int i = 0; i < 4; i++) {
			blocks[i] = blocks[i].subtract(pivot);
			blocks[i].rotateClockwise();
			blocks[i] = pivot.add(blocks[i]);
		}
	}
}

class RightN extends Tetrimino {
	public RightN() {
		/* Numbering Convention
			0 1
			  2 3
		*/
		blocks[0] = new Point(0, 0);
		blocks[1] = new Point(0, 1);
		blocks[2] = new Point(1, 1);
		blocks[3] = new Point(1, 2);
		colorID = 7;
	}

	void rotateCounterClockwise() {
		Point pivot = blocks[1];

		for (int i = 0; i < 4; i++) {
			blocks[i] = blocks[i].subtract(pivot);
			blocks[i].rotateCounterClockwise();
			blocks[i] = pivot.add(blocks[i]);
		}
	}

	void rotateClockwise() {
		Point pivot = blocks[1];

		for (int i = 0; i < 4; i++) {
			blocks[i] = blocks[i].subtract(pivot);
			blocks[i].rotateClockwise();
			blocks[i] = pivot.add(blocks[i]);
		}
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
		row = -col;
		col = row;
	}

	public void rotateClockwise() {
		row = col;
		col = -row;
	}
}