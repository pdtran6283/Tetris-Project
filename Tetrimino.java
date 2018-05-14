public abstract class Tetrimino {
	Point[] blocks = new Point[4];

	// Move Tetrimino Left One Unit
	void Left() {
		for (Point e : blocks) e.Left();
	}

	// Move Tetrimino Right One Unit
	void Right() {
		for (Point e : blocks) e.Right();
	}

	// Move Tetrimino Down One Unit
	void Down() {
		for (Point e : blocks) e.Down();
	}

	abstract void RotateClockwise();
	abstract void RotateCounterClockwise();

	// return random Tetrimino
	// static Tetrimino RandomTetrimino() {
	// 	switch ((int) (Math.random() * 7)) {
	// 		case 0: return new Square();
	// 		case 1: return new Line();
	// 		case 2: return new LLeft();
	// 		case 3: return new LRight();
	// 		case 4: return new Tee();
	//		case 5: return new LeftN();
	//		case 6: return new RightN();
	// 	}
	// }
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

abstract class Line extends Tetrimino {
	public Line() {
		/* Numbering Convention
			0 1 2 3
		*/
		blocks[0] = new Point(0, 0);
		blocks[1] = new Point(0, 1);
		blocks[2] = new Point(0, 2);
		blocks[3] = new Point(0, 3);
	}
}

abstract class LRight extends Tetrimino {
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
}

abstract class LLeft extends Tetrimino {
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
}

abstract class Tee extends Tetrimino {
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
}

abstract class LeftN extends Tetrimino {
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
}

abstract class RightN extends Tetrimino {
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
}