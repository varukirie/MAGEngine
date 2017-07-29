package indi.megaastronic.helper;

import application.Main;
import javafx.scene.canvas.GraphicsContext;
/**
 * 圆形轨迹运动的Helper
 * @author MegaAstronic
 *
 */
public class OvalHelper extends Helper {

	private double r;
	private long startTime = 0;
	private double ovalX;
	private double ovalY;
	private static final double BLANK=10000;
	private double speed;
	
	public OvalHelper(double x, double y,double r,double speed) {
		this(x,y,r,speed,0);
	}
	/**
	 * 
	 * @param x
	 * @param y
	 * @param r
	 * @param speed
	 * @param delta 单位 弧度，起始角度
	 */
	public OvalHelper(double x, double y,double r,double speed,double delta) {
		super(x, y);
		this.r=r;
		this.startTime=System.currentTimeMillis();
		this.ovalX=x;
		this.ovalY=y;
		this.speed=speed;
		
		this.startTime+=delta*BLANK/speed;
		modify();
	}
	
	
	@Override
	public void modify() {
		this.x=this.ovalX+r*Math.cos((System.currentTimeMillis()-startTime)*speed/BLANK);
		this.y=this.ovalY+r*Math.sin((System.currentTimeMillis()-startTime)*speed/BLANK);
	}
	

}
