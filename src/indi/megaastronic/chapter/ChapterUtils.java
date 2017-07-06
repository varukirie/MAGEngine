package indi.megaastronic.chapter;

import indi.megaastronic.element.Ball;
import indi.megaastronic.util.ElementUtils;

public class ChapterUtils {
	
	private ElementUtils mEU;
	private final double sqrt2d2=0.7071;
	private int count = 0;
	public ChapterUtils(ElementUtils mEU) {
		super();
		this.mEU = mEU;
	}
	
	public void slashDown(double x,double y,double speed){
		mEU.add("dd"+count++, new Ball(x, y,speed,0));
		mEU.add("aa"+count++, new Ball(x, y,-speed,0));
		mEU.add("ss"+count++, new Ball(x, y,0,speed));
		mEU.add("ww"+count++, new Ball(x, y,0,-speed));
		mEU.add("aw"+count++, new Ball(x, y,-speed*sqrt2d2,-speed*sqrt2d2));
		mEU.add("dw"+count++, new Ball(x, y,speed*sqrt2d2,-speed*sqrt2d2));
		mEU.add("as"+count++, new Ball(x, y,-speed*sqrt2d2,speed*sqrt2d2));
		mEU.add("ds"+count++, new Ball(x, y,speed*sqrt2d2,speed*sqrt2d2));
	}
	

}
