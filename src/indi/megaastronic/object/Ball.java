package indi.megaastronic.object;

import indi.megaastronic.paint.Moveable;
import indi.megaastronic.paint.Paintable;
import javafx.scene.canvas.GraphicsContext;

public class Ball implements Moveable , Paintable{

	private int x;
	private int y;
	private double velocityX=0;
	private double velocityY=0;
	
	public Ball(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public double getVelocityX() {
		return velocityX;
	}
	public void setVelocityX(double velocityX) {
		this.velocityX = velocityX;
	}
	public double getVelocityY() {
		return velocityY;
	}
	public void setVelocityY(double velocityY) {
		this.velocityY = velocityY;
	}
	@Override
	public void paint(GraphicsContext gc) {
		gc.fillOval(x, y, 5, 5);
		//System.out.println("enemy exist millis="+System.currentTimeMillis());
	}
	
	
}
