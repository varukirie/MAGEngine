package indi.megaastronic.element;

/**
 * 
 * @author MegaAstronic
 *
 */
public interface Moveable {
	double getX();

	double getY();

	void setX(double x);

	void setY(double y);
	
	double getVelocityX();
	
	double getVelocityY();
	
	void setVelocityX(double vx);

	void setVelocityY(double vy);
	
}
