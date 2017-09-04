package indi.megaastronic.helper;

import indi.megaastronic.element.Initializable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * 圆形轨迹运动的Helper
 * 
 * @author MegaAstronic
 *
 */
public class OvalHelper extends Helper implements Initializable {

	private double r;
	private long startTime = 0;
	private DoubleProperty ovalMidX=new SimpleDoubleProperty(0);
	private DoubleProperty ovalMidY=new SimpleDoubleProperty(0);
	private static final double BLANK = 10000;
	private double speed;
	private double delta;
	private DoubleProperty directionProperty = new SimpleDoubleProperty(0);
	private boolean positive = true;
	private boolean rotate = true;

	public OvalHelper(double midx, double midy, double r, double speed) {
		this(midx, midy, r, speed, 0);
	}

	/**
	 * 
	 * @param midx
	 * @param midy
	 * @param r
	 * @param speed
	 * @param startAngle
	 *            单位 弧度，起始角度
	 */
	public OvalHelper(double midx, double midy, double r, double speed, double startAngle) {
		super(midx, midy);
		this.r = r;
		this.ovalMidX.set(midx);
		this.ovalMidY.set(midy);
		this.speed = speed;
		this.delta = startAngle;
	}

	@Override
	public void modify() {
		if (rotate) {
			if (positive) {
				this.directionProperty.set((System.currentTimeMillis() - startTime) * speed / BLANK);
			} else {
				this.directionProperty.set(-(System.currentTimeMillis() - startTime) * speed / BLANK);
			}
			setX(this.ovalMidX.get() + r * Math.cos(getDirection()));
			setY(this.ovalMidY.get() + r * Math.sin(getDirection()));
		}else{
			if (positive) {
				this.directionProperty.set(delta);
			} else {
				this.directionProperty.set(-delta);
			}
			setX(this.ovalMidX.get() + r * Math.cos(getDirection()));
			setY(this.ovalMidY.get() + r * Math.sin(getDirection()));
		}
	}

	@Override
	public void initWhenAdd() {
		this.startTime = (long) (System.currentTimeMillis() + (delta * BLANK / speed));
	}

	private void setDirection(double direction){
		this.directionProperty.set(direction);
	}
	public double getDirection() {
		return directionProperty.get();
	}

	public void setPositive(boolean positive) {
		this.positive = positive;
	}

	public boolean getPositive() {
		return positive;
	}

	public double getR() {
		return r;
	}

	public void setR(double r) {
		this.r = r;
	}

	public double getOvalMidX() {
		return ovalMidX.get();
	}

	public void setOvalMidX(double ovalMidX) {
		this.ovalMidX.set(ovalMidX);
	}

	public double getOvalMidY() {
		return ovalMidY.get();
	}

	public void setOvalMidY(double ovalMidY) {
		this.ovalMidY.set(ovalMidY);
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public boolean getRotate() {
		return rotate;
	}

	public void setRotate(boolean rotate) {
		this.rotate = rotate;
	}
	
	public DoubleProperty getOvalMidXProperty() {
		return this.ovalMidX;
	}
	
	public DoubleProperty getOvalMidYProperty() {
		return this.ovalMidY;
	}

	public DoubleProperty getDirectionProperty() {
		return directionProperty;
	}

	public void setDirectionProperty(DoubleProperty directionProperty) {
		this.directionProperty = directionProperty;
	}

	

}
