package indi.megaastronic.element;

public abstract class ANormalElement implements Moveable, Paintable,Accelerated {

	protected double x;
	protected double y;
	protected double velocityX=0;
	protected double velocityY=0;
	protected double accX = 0;
	protected double accY = 0;
	
	public ANormalElement(double x, double y) {
			this(x,y,0,0);
	}
	
	public ANormalElement(double x, double y, double velocityX, double velocityY) {
		this(x,y,velocityX,velocityY,0,0);
	}
	
	public ANormalElement(double x, double y, double velocityX, double velocityY,double ax,double ay) {
		super();
		this.x = x;
		this.y = y;
		this.velocityX = velocityX;
		this.velocityY = velocityY;
		this.accX=ax;
		this.accY=ay;
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

	public double getAccX() {
		return accX;
	}

	public void setAccX(double accX) {
		this.accX = accX;
	}

	public double getAccY() {
		return accY;
	}

	public void setAccY(double accY) {
		this.accY = accY;
	}
	
}
