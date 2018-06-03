import java.awt.*;
import javax.swing.*;

import javax.swing.ImageIcon;

public class Intro extends JFrame{
	public static void main (String[] args) {
		Intro i= new Intro();
		i.run();
	}

	private Sprite sprite;
	private Animation a;
	private ScreenManager s;
	private Image bg;
	private static final DisplayMode modes1[] = {
		new DisplayMode (800,600,32,0),
		new DisplayMode (800,600,24,0),
		new DisplayMode (800,600,16,0),
		new DisplayMode (640,480,32,0),
		new DisplayMode (640,480,24,0),
		new DisplayMode (640,480,16,0),



	};

	//load images and add scenes
	public void loadImages() {
		Image walk1 = new ImageIcon("C:\\Users\\Jessica\\Documents\\apcompsci\\TetrisGame\\tetrisinstructions.jpg").getImage();

		a = new Animation ();
		a.addScene(walk1, 300);;

		sprite = new Sprite(a);
		sprite.setVelocityX(0f);
		sprite.setVelocityY(0f);


	} 

	//main engine to run
	public void run() {
		s = new ScreenManager();
		try{
			DisplayMode dm = s.findFirstCompatibleMode(modes1);
			s.setFullScreen(dm);
			loadImages();
			movieLoop();
		}
		finally{ 
			s.restoreScreen();
		}
	}

	//play movie
	public void movieLoop() {
		long startingTime = System.currentTimeMillis();
		long cumTime = startingTime;

		while(cumTime - startingTime < 18000) {
			long timePassed = System.currentTimeMillis() - cumTime;
			cumTime += timePassed;
			update(timePassed);	

			//draw and update screen
			Graphics2D g = s.getGraphics();
			draw(g);
			g.dispose();
			s.update();

			try {
				Thread.sleep(20);
			}catch(Exception ex){}
		}
	}

	public void draw (Graphics g) {
		g.drawImage(bg, 0, 0, null);
		g.drawImage(sprite.getImage(), Math.round(sprite.getX()), Math.round(sprite.getY()), null);
	}

	//update
	public void update(long timePassed) {
		if(sprite.getX() <0) {
			sprite.setVelocityX(Math.abs(sprite.getVelocityX()));
		}

		else if(sprite.getX() + sprite.getWidth() > s.getWidth()) {
			sprite.setVelocityX(-Math.abs(sprite.getVelocityX()));
		}

		if(sprite.getY() <0) {
			sprite.setVelocityY(Math.abs(sprite.getVelocityY()));
		}

		else if(sprite.getY() + sprite.getHeight() > s.getHeight()) {
			sprite.setVelocityY(-Math.abs(sprite.getVelocityY()));
		}

		sprite.update(timePassed);
	}

}