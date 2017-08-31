package indi.megaastronic.helper;

import indi.megaastronic.element.Initializable;
/**
 * 圆形轨迹运动的Helper
 * @author MegaAstronic
 *
 */
public class OvalHelper extends Helper implements Initializable{

	private double r;
	private long startTime = 0;
	private double ovalX;
	private double ovalY;
	private static final double BLANK=10000;
	private double speed;
	private double delta;
	private double direction=0;
	private boolean positive=true;
	
	public OvalHelper(double x, double y,double r,double speed) {
		this(x,y,r,speed,0);
	}
	/**
	 * 
	 * @param x
	 * @param y
	 * @param r
	 * @param speed
	 * @param startAngle 单位 弧度，起始角度
	 */
	public OvalHelper(double x, double y,double r,double speed,double startAngle) {
		super(x, y);
		this.r=r;
		this.ovalX=x;
		this.ovalY=y;
		this.speed=speed;
		this.delta = startAngle;
	}
	
	
	@Override
	public void modify() {
		if(positive){
			this.direction=(System.currentTimeMillis()-startTime)*speed/BLANK ;
		}else{
			this.direction=-(System.currentTimeMillis()-startTime)*speed/BLANK ;
		}
		setX(this.ovalX+r*Math.cos(direction));
		setY(this.ovalY+r*Math.sin(direction));
	}
	@Override
	public void initWhenAdd() {
		this.startTime=(long) (System.currentTimeMillis()+(delta*BLANK/speed));
	}
	public double getDirection() {
		return direction;
	}
	public void setPositive(boolean positive) {
		this.positive = positive;
	}
	public boolean getPositive() {
		return positive;
	}
	

}
