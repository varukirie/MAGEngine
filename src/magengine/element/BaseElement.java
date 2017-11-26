package magengine.element;

import java.util.function.Consumer;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import magengine.util.Transform;

public abstract class BaseElement implements Moveable, Paintable, Accelerated {
	/**
	 * 用于使用时扩展功能
	 */
	public volatile int state = 0;
	protected DoubleProperty xProperty = new SimpleDoubleProperty();
	protected DoubleProperty yProperty = new SimpleDoubleProperty();
	protected volatile double velocityX = 0;
	protected volatile double velocityY = 0;
	protected double accX = 0;
	protected double accY = 0;
	protected boolean deleted = false;
	private BooleanProperty wantBeRemoved = new SimpleBooleanProperty(false);
	private Consumer<BaseElement> lambdaModify = null;
	private Consumer<BaseElement> onRemoveEvent=null;
	
	public BaseElement(double x, double y) {
		this(x, y, 0, 0);
	}

	public BaseElement(double x, double y, double velocityX, double velocityY) {
		this(x, y, velocityX, velocityY, 0, 0);
	}

	public BaseElement(double x, double y, double velocityX, double velocityY, double ax, double ay) {
		super();
		this.xProperty.set(x);
		this.yProperty.set(y);
		this.velocityX = velocityX;
		this.velocityY = velocityY;
		this.accX = ax;
		this.accY = ay;
	}

	public double getX() {
		return xProperty.get();
	}

	public void setX(double x) {
		if (this.xProperty.isBound() == false)
			this.xProperty.set(x);
	}

	public double getY() {
		return yProperty.get();
	}

	public void setY(double y) {
		if (this.yProperty.isBound() == false)
			this.yProperty.set(y);
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

	public boolean getDeleted() {
		return deleted;
	}

	public BaseElement setDeleted(boolean isDelete) {
		this.deleted = isDelete;
		return this;
	}

	public DoubleProperty getxProperty() {
		return xProperty;
	}

	public DoubleProperty getyProperty() {
		return yProperty;
	}

	public BaseElement setLambdaModify(Consumer<BaseElement> lambdaModify) {
		this.lambdaModify = lambdaModify;
		return this;
	}

	public Consumer<BaseElement> getLambdaModify() {
		return lambdaModify;
	}

	public BooleanProperty getWantBeRemovedProperty() {
		return wantBeRemoved;
	}

	public BaseElement setWantBeRemovedProperty(BooleanProperty wantBeRemoved) {
		this.wantBeRemoved = wantBeRemoved;
		return this;
	}

	public boolean getWantBeRemoved() {
		return wantBeRemoved.get();
	}

	public BaseElement setWantBeRemoved(boolean wantBeRemoved) {
		this.wantBeRemoved.set(wantBeRemoved);
		return this;
	}
	
	public BaseElement setOnRemoveEvent(Consumer<BaseElement> onRemoveEvent) {
		this.onRemoveEvent = onRemoveEvent;
		return this;
	}
	public Consumer<BaseElement> getOnRemoveEvent() {
		return onRemoveEvent;
	}
	
	public BaseElement bindToXY(BaseElement elem2) {
		this.getxProperty().bind(elem2.getxProperty());
		this.getyProperty().bind(elem2.getyProperty());
		return this;
	}

	public BaseElement unbindXY(BaseElement elem) {
		this.getxProperty().unbind();
		this.getyProperty().unbind();
		return this;
	}

	public BaseElement bindToWantBeRemoved(BaseElement elem2) {
		this.getWantBeRemovedProperty().bind(elem2.getWantBeRemovedProperty());
		return this;
	}
	
	public void VTo(double targetX, double targetY) {
		double targetS = Math.sqrt((targetX - this.getX()) * (targetX - this.getX())
				+ (targetY - this.getY()) * (targetY - this.getY()));
		double originS = Math.sqrt(
				this.getVelocityX() * this.getVelocityX() + this.getVelocityY() * this.getVelocityY());
		this.setVelocityX((targetX - this.getX()) * (originS / targetS));
		this.setVelocityY((targetY - this.getY()) * (originS / targetS));
	}
	
	public void setSpeed(double speed) {
		double originS = Math.sqrt(
				this.getVelocityX() * this.getVelocityX() + this.getVelocityY() * this.getVelocityY());
		this.setVelocityX(this.getVelocityX() * (speed / originS));
		this.setVelocityY(this.getVelocityY() * (speed / originS));
	}
	
	public void VRotate(double angle) {
		double theta = angle;
		Transform t = new Transform(
				new double[][] { { Math.cos(theta), Math.sin(theta) }, { -Math.sin(theta), Math.cos(theta) } });
		double[] ans = t.transform(this.getVelocityX(), this.getVelocityY());
		this.setVelocityX(ans[0]);
		this.setVelocityY(ans[1]);
	}
	
	/**
	 * 
	 * @param this
	 * @param direction
	 *            负角
	 */
	public void VToByDirection(double direction) {
		double originS = Math.sqrt(
				this.getVelocityX() * this.getVelocityX() + this.getVelocityY() * this.getVelocityY());
		this.setVelocityX(Math.cos(direction) * originS);
		this.setVelocityY(Math.sin(direction) * originS);
	}
	
	public void VTransform(double[][] martix) {
		Transform t = new Transform(martix);
		double[] ans = t.transform(this.getVelocityX(), this.getVelocityY());
		this.setVelocityX(ans[0]);
		this.setVelocityY(ans[1]);
	}
	
	
}
