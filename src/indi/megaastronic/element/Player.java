package indi.megaastronic.element;

import indi.megaastronic.paint.LimitedByCanvas;
import indi.megaastronic.paint.Moveable;
import indi.megaastronic.paint.Paintable;
import javafx.scene.canvas.GraphicsContext;
/**
 * 玩家控制的物体
 * @author MegaAstronic
 *
 */
public class Player implements Paintable , Moveable ,LimitedByCanvas {

	private double x;
	private double y;
	private double VelocityX=0;
	private double VelocityY=0;
	public final int width = 10;
	public final int height = 10;
	
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
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
