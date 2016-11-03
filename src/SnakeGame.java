import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

@SuppressWarnings("serial")
public class SnakeGame extends Applet implements Runnable, KeyListener {

	public final int WINDOW_WIDTH_X = 600;
	public final int WINDOW_HEIGHT_Y = 600;
	public final int GAME_SPEED = 40; //The lower - the quicker
	
	private Graphics gfx;
	private Image img;
	Thread thread;
	Snake snake;
	Boolean gameOver;

	
	public void init() {
		this.resize(WINDOW_WIDTH_X, WINDOW_HEIGHT_Y);
		img = createImage(WINDOW_WIDTH_X, WINDOW_HEIGHT_Y);
		gfx = img.getGraphics();
		this.addKeyListener(this);
		gameOver = false;
		snake = new Snake();
		thread = new Thread(this);
		thread.start();
	}
	
	public void paint(Graphics g) {
		//Background colour
		gfx.setColor(Color.DARK_GRAY);
		gfx.fillRect(0, 0, WINDOW_WIDTH_X, WINDOW_HEIGHT_Y);
		if(!gameOver) {
			snake.draw(gfx);
		} else {
			gfx.setColor(Color.RED);
			gfx.drawString("Game Over", (WINDOW_WIDTH_X/2), (WINDOW_HEIGHT_Y/2));
		}
		g.drawImage(img, 0, 0, null);
	}
	
	
	public void repaint(Graphics g) {
		paint(g);
	}
	
	public void update(Graphics g) {
		paint(g);
	}
	
	public void run() {
		for(;;) {
			if(!gameOver) {
				snake.move();
				checkGameOver();
			}
			repaint();
			//this.repaint();
			try { Thread.sleep(GAME_SPEED); } catch (InterruptedException e) { e.printStackTrace(); }
		}
	}
	
	public void checkGameOver() {
		if(snake.getXSnakePosition() < 0 || snake.getXSnakePosition() > WINDOW_WIDTH_X - snake.getSizeOfSnakesPoint()) {
			gameOver = true;
		} else if (snake.getYSnakePosition() < 0 || snake.getYSnakePosition() > WINDOW_HEIGHT_Y - snake.getSizeOfSnakesPoint()) {
			gameOver = true;
		} else if (snake.snakeCollisionCheck()) {
			gameOver = true;
		}
	}

	public void keyPressed(KeyEvent e) {
		snake.setIsMoving(true);
		switch (e.getKeyCode()) { 
			case KeyEvent.VK_UP:
				if(snake.getYDirectionOfMovement() != 1) {
					snake.setYDirectionOfMovement(-1);
					snake.setXDirectionOfMovement(0);
				}
				break;
			case KeyEvent.VK_DOWN:
				if(snake.getYDirectionOfMovement() != -1) {
					snake.setYDirectionOfMovement(1);
					snake.setXDirectionOfMovement(0);
				}
				break;
			case KeyEvent.VK_LEFT:
				if(snake.getXDirectionOfMovement() != 1) {
					snake.setXDirectionOfMovement(-1);
					snake.setYDirectionOfMovement(0);
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(snake.getXDirectionOfMovement() != -1) {
					snake.setXDirectionOfMovement(1);
					snake.setYDirectionOfMovement(0);
				}
				break;
		}
	}

	public void keyReleased(KeyEvent arg0) {
		
	}

	public void keyTyped(KeyEvent arg0) {
		
	}
	
	
	
}
