/**
 * 
 */
package indi.megaastronic.paint;

/**
 * @author MegaAstronic
 *
 */
public interface Moveable {
	int getX();

	int getY();

	void setX(int x);

	void setY(int y);
	
	double getVelocityX();
	
	double getVelocityY();
	
	void setVelocityX(double vx);

	void setVelocityY(double vy);
}
