package indi.megaastronic.object;

import indi.megaastronic.paint.LimitedByCanvas;
import indi.megaastronic.paint.Moveable;
import indi.megaastronic.paint.Paintable;
import javafx.scene.canvas.GraphicsContext;

public class Player implements Paintable , Moveable ,LimitedByCanvas {

	private int x;
	private int y;
	private double VelocityX=0;
	private double VelocityY=0;
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

	public double getVelocityX() {
		return VelocityX;
	}
	public void setVelocityX(double velocityX) {
		VelocityX = velocityX;
	}
	public double getVelocityY() {
		return VelocityY;
	}
	public void setVelocityY(double velocityY) {

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
