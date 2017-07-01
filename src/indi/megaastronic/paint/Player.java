package indi.megaastronic.paint;

import javafx.scene.canvas.GraphicsContext;

public class Player implements Paintable , Moveable {

	private int x;
	private int y;
	private int VelocityX=0;
	private int VelocityY=0;
	public final int width = 10;
	public final int height = 10;
	
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

	public int getVelocityX() {
		return VelocityX;
	}
	public void setVelocityX(int velocityX) {
		VelocityX = velocityX;
	}
	public int getVelocityY() {
		return VelocityY;
	}
	public void setVelocityY(int velocityY) {

		VelocityY = velocityY;
	}
	
	public Player(int initX,int initY) {
		x=initX;
		y=initY;
	}
	
	@Override
	public void paint(GraphicsContext gc) {
		gc.strokeOval(x, y, width, height);
	}
	

}
