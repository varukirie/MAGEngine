package indi.megaastronic.element;

import indi.megaastronic.paint.Moveable;
import indi.megaastronic.paint.Paintable;

public abstract class ANormalElement implements Moveable, Paintable {

	protected double x;
	protected double y;
	protected double velocityX=0;
	protected double velocityY=0;
	public ANormalElement(double x, double y) {
			this.x=x;
			this.y=y;
	}
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

}
