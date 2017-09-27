package magengine.enemy;

import magengine.element.BaseElement;

public abstract class Enemy extends BaseElement{

	public Enemy(double x, double y, double velocityX, double velocityY, double ax, double ay) {
		super(x, y, velocityX, velocityY, ax, ay);
	}

	public Enemy(double x, double y, double velocityX, double velocityY) {
		super(x, y, velocityX, velocityY);
	}

	public Enemy(double x, double y) {
		super(x, y);
	}

}
