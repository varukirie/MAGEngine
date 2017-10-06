package magengine.helper;

import magengine.element.BaseElement;

public class RelativeAnchorHelper extends Helper {
	private BaseElement bindTarget;
	private double deltaX;
	private double deltaY;
	public RelativeAnchorHelper(BaseElement bindTarget, double deltaX, double deltaY) {
		super(bindTarget.getX(), bindTarget.getY());
		this.bindTarget = bindTarget;
		this.deltaX = deltaX;
		this.deltaY = deltaY;
	}
	
	@Override
	public void modify() {
		super.modify();
		setX(bindTarget.getX()+getDeltaX());
		setY(bindTarget.getY()+getDeltaY());
	}

	@Override
	public void setVelocityX(double velocityX) {
		throw new RuntimeException("RelativeAnchorHelper:该Helper不允许被改变速度");
	}
	
	@Override
	public void setVelocityY(double velocityY) {
		throw new RuntimeException("RelativeAnchorHelper:该Helper不允许被改变速度");
	}
	
	
	public double getDeltaX() {
		return deltaX;
	}

	public void setDeltaX(double deltaX) {
		this.deltaX = deltaX;
	}

	public double getDeltaY() {
		return deltaY;
	}

	public void setDeltaY(double deltaY) {
		this.deltaY = deltaY;
	}
	
	
}
