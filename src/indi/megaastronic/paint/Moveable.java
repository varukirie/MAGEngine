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
	
	int getVelocityX();
	
	int getVelocityY();
	
	void setVelocityX(int vx);

	void setVelocityY(int vy);
}
