import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Game extends JFrame {
	JLabel score;

	public Game() {
		Tetris tetris = new Tetris(this);
 		add(tetris);
 		tetris.startGame();
 		score = new JLabel("0");
 		add(score, BorderLayout.SOUTH);
 		setSize(500, 1100);
 		setTitle("Tetris");
 		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.setLocationRelativeTo(null);
		game.setVisible(true);
		game.setResizable(false);
	}
}