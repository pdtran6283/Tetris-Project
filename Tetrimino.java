public abstract class Tetrimino {
	Point[] blocks = new Point[4];

	// Move Tetrimino Left One Unit
	void Left() {
		if (Boundaries()) return;
		for (Point e : blocks) e.Left();
	}

	// Move Tetrimino Right One Unit
	void Right() {
		if (Boundaries()) return;
		for (Point e : blocks) e.Right();
	}

	// Move Tetrimino Down One Unit
	void Down() {
		if (Boundaries()) return;
		for (Point e : blocks) e.Down();
	}

	public boolean Boundaries() {
		for (Point e : blocks)
			if (e.getRow() <= 0 || e.getCol() <= 0) return true;
		return false;
	}

	abstract void RotateClockwise();
	abstract void RotateCounterClockwise();

	// return random Tetrimino
	static Tetrimino RandomTetrimino() {
		switch ((int) (Math.random() * 7)) {
			case 0: return new Square();
			case 1: return new Line();
			case 2: return new LLeft();
			case 3: return new LRight();
			case 4: return new Tee();
			case 5: return new LeftN();
			case 6: return new RightN();
			default: return new Square();
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
	}

	void RotateClockwise() {};
	void RotateCounterClockwise() {};
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
	}

	void RotateClockwise() {
		Point pivot = blocks[2];

		if (orientUp) {
			for (int i = 0; i < 4; i++) {
				blocks[i] = blocks[i].subtract(pivot);
				blocks[i].RotateClockwise();
				blocks[i] = pivot.add(blocks[i]);
			}
		} else {
			for (int i = 0; i < 4; i++) {
				blocks[i] = blocks[i].subtract(pivot);
				blocks[i].RotateCounterClockwise();
				blocks[i] = pivot.add(blocks[i]);
			}
		}

		orientUp = !orientUp;
	}

	void RotateCounterClockwise() {
		RotateClockwise();
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
	}

	void RotateCounterClockwise() {
		Point pivot = blocks[1];

		for (int i = 0; i < 4; i++) {
			blocks[i] = blocks[i].subtract(pivot);
			blocks[i].RotateCounterClockwise();
			blocks[i] = pivot.add(blocks[i]);
		}
	}

	void RotateClockwise() {
		Point pivot = blocks[1];

		for (int i = 0; i < 4; i++) {
			blocks[i] = blocks[i].subtract(pivot);
			blocks[i].RotateClockwise();
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
	}

	void RotateCounterClockwise() {
		Point pivot = blocks[1];

		for (int i = 0; i < 4; i++) {
			blocks[i] = blocks[i].subtract(pivot);
			blocks[i].RotateCounterClockwise();
			blocks[i] = pivot.add(blocks[i]);
		}
	}

	void RotateClockwise() {
		Point pivot = blocks[1];

		for (int i = 0; i < 4; i++) {
			blocks[i] = blocks[i].subtract(pivot);
			blocks[i].RotateClockwise();
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
	}

	void RotateCounterClockwise() {
		Point pivot = blocks[1];

		for (int i = 0; i < 4; i++) {
			blocks[i] = blocks[i].subtract(pivot);
			blocks[i].RotateCounterClockwise();
			blocks[i] = pivot.add(blocks[i]);
		}
	}

	void RotateClockwise() {
		Point pivot = blocks[1];

		for (int i = 0; i < 4; i++) {
			blocks[i] = blocks[i].subtract(pivot);
			blocks[i].RotateClockwise();
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
	}

	void RotateCounterClockwise() {
		Point pivot = blocks[0];

		for (int i = 0; i < 4; i++) {
			blocks[i] = blocks[i].subtract(pivot);
			blocks[i].RotateCounterClockwise();
			blocks[i] = pivot.add(blocks[i]);
		}
	}

	void RotateClockwise() {
		Point pivot = blocks[0];

		for (int i = 0; i < 4; i++) {
			blocks[i] = blocks[i].subtract(pivot);
			blocks[i].RotateClockwise();
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
	}

	void RotateCounterClockwise() {
		Point pivot = blocks[1];

		for (int i = 0; i < 4; i++) {
			blocks[i] = blocks[i].subtract(pivot);
			blocks[i].RotateCounterClockwise();
			blocks[i] = pivot.add(blocks[i]);
		}
	}

	void RotateClockwise() {
		Point pivot = blocks[1];

		for (int i = 0; i < 4; i++) {
			blocks[i] = blocks[i].subtract(pivot);
			blocks[i].RotateClockwise();
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

	public void RotateCounterClockwise() {
		row = -col;
		col = row;
	}

	public void RotateClockwise() {
		row = col;
		col = -row;
	}
}