package magengine.element;

import java.util.function.Consumer;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;

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

	public void setDeleted(boolean isDelete) {
		this.deleted = isDelete;
	}

	public DoubleProperty getxProperty() {
		return xProperty;
	}

	public DoubleProperty getyProperty() {
		return yProperty;
	}

	public void setLambdaModify(Consumer<BaseElement> lambdaModify) {
		this.lambdaModify = lambdaModify;
	}

	public Consumer<BaseElement> getLambdaModify() {
		return lambdaModify;
	}

	public BooleanProperty getWantBeRemovedProperty() {
		return wantBeRemoved;
	}

	public void setWantBeRemovedProperty(BooleanProperty wantBeRemoved) {
		this.wantBeRemoved = wantBeRemoved;
	}

	public boolean getWantBeRemoved() {
		return wantBeRemoved.get();
	}

	public void setWantBeRemoved(boolean wantBeRemoved) {
		this.wantBeRemoved.set(wantBeRemoved);
	}
	
	public void setOnRemoveEvent(Consumer<BaseElement> onRemoveEvent) {
		this.onRemoveEvent = onRemoveEvent;
	}
	public Consumer<BaseElement> getOnRemoveEvent() {
		return onRemoveEvent;
	}
}
