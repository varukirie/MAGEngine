package indi.megaastronic.element.impl;

import application.Main;
import javafx.scene.canvas.GraphicsContext;

public class OvalHelper extends Helper {

	private double r;
	private long startTime = 0;
	private double ovalX;
	private double ovalY;
	private static final double BLANK=10000;
	private double speed;
	public OvalHelper(double x, double y,double r,double speed) {
		super(x, y);
		this.r=r;
		this.startTime=System.currentTimeMillis();
		this.ovalX=x;
		this.ovalY=y;
		this.speed=speed;
		modify();
	}
	
	
	@Override
	public void modify() {
		this.x=this.ovalX+r*Math.cos((System.currentTimeMillis()-startTime)*speed/BLANK);
		this.y=this.ovalY+r*Math.sin((System.currentTimeMillis()-startTime)*speed/BLANK);
	}
	@Override
	public void paint(GraphicsContext gc) {
		if(Main.DEBUG){
			gc.fillText("x", this.x, this.y);
		}
	}

}
