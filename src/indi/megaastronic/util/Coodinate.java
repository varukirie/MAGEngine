package indi.megaastronic.util;

/**
 * 此类为了描述点Coodinate
 * @author Astronic
 *
 */
public class Coodinate {
	private double x;
	private double y;
	public Coodinate(double x, double y) {
		super();
		this.x = x;
		this.y = y;
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
}
