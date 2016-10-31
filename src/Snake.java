import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Snake {
	
	private List<Point> snakePoints;
	private int xDirectionOfMovement, yDirectionOfMovement;
	boolean isMoving, elongate;
	private final int INITIAL_SNAKE_LENGTH = 10;
	private final int SIZE_OF_SNAKES_POINT = 8;
	private final int START_POSITION_X = 290;
	private final int START_POSITION_Y = 290;
	
	public Snake() {
		snakePoints = new ArrayList<Point>();
		xDirectionOfMovement = 0;
		yDirectionOfMovement = 0;
		isMoving = false;
		elongate = false;
		
		//Initialising the snake
		snakePoints.add(new Point (START_POSITION_X, START_POSITION_Y));
		for(int i = 0; i < INITIAL_SNAKE_LENGTH; i++) {
			snakePoints.add(new Point(START_POSITION_X - i * SIZE_OF_SNAKES_POINT, START_POSITION_Y));
		}
	
	}
	
	public void draw (Graphics g) {
		//Colour of the snake
		g.setColor(Color.GREEN);
		
		for(Point point : snakePoints) {
			g.fillRect(point.getX(), point.getY(), SIZE_OF_SNAKES_POINT, SIZE_OF_SNAKES_POINT);
		}
	}
	
	public void move() {
		if(isMoving) {
			Point currentPositionOfHead = snakePoints.get(0);
			//Point currentPositionOfLastPoint = snakePoints.get(snakePoints.size() - 1);
			//Move the head
			Point newPositionOfHead = new Point(currentPositionOfHead.getX() + xDirectionOfMovement * SIZE_OF_SNAKES_POINT,
					currentPositionOfHead.getY() + yDirectionOfMovement * SIZE_OF_SNAKES_POINT);
			//Move the body
			for(int i = snakePoints.size() - 1; i >= 1; i--) {
				snakePoints.set(i, snakePoints.get(i -1 ));
			}
			snakePoints.set(0, newPositionOfHead);
			//isMoving = false;
		}

	}
	
	public int getXDirectionOfMovement() {
		return xDirectionOfMovement;
	}
	
	public int getYDirectionOfMovement() {
		return yDirectionOfMovement;
	}
	
	public void setXDirectionOfMovement(int x) {
		xDirectionOfMovement = x;
	}
	
	public void setYDirectionOfMovement(int y) {
		yDirectionOfMovement = y;
	}
	
	public int getXSnakePosition() {
		return snakePoints.get(0).getX();
	}
	
	public int getYSnakePosition() {
		return snakePoints.get(0).getY();
	}
	
	public boolean getIsMoving() {
		return isMoving;
	}
	
	public void setIsMoving(boolean booleanValue) {
		isMoving = booleanValue;
	}
}
