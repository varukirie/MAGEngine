package indi.megaastronic.bullet;

import indi.megaastronic.element.BaseElement;
import indi.megaastronic.element.Moveable;
import indi.megaastronic.element.impl.Player;
import javafx.scene.canvas.GraphicsContext;

public class MissileBullet extends Bullet {

	
	private double v;
	private Moveable target;
	public MissileBullet(double x,double y){
		super(x,y);
		this.target=Player.getPlayer();
		this.v=1;
	}
	public MissileBullet(double x, double y,Moveable target,double v) {
		super(x, y);
		this.v=v;
		this.target=target;
	}
	
	@Override
	public void modify() {
		super.modify();
		if(target!=null){
			double dx = target.getX() - getX();
			double dy = target.getY() - getY();
			double s = Math.sqrt(dx * dx + dy * dy);
//			this.setAccX(dx *v/ s);
//			this.setAccY(dy *v/ s);
			this.setVelocityX(dx *v/ s);
			this.setVelocityY(dy *v/ s);
		}
	}
	
	
	
	@Override
	public void paint(GraphicsContext gc) {
		gc.fillOval(getX(), getY(), 10, 10);
	}

	public Moveable getTarget() {
		return target;
	}

	public void setTarget(Moveable target) {
		this.target = target;
	}

	public double getV() {
		return v;
	}

	public void setV(double v) {
		this.v = v;
	}
	

	
	
}
