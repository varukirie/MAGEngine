package indi.megaastronic.chapter;

import indi.megaastronic.element.Ball;
import indi.megaastronic.util.ElementUtils;
import indi.megaastronic.util.Transform;

public class ChapterUtils {
	
	private ElementUtils mEU;
	private final double sqrt2d2=0.7071;
	private int count = 0;
	public ChapterUtils(ElementUtils mEU) {
		super();
		this.mEU = mEU;
	}
	
	public void slash(double x,double y,double speed){
		mEU.add("dd"+count++, new Ball(x, y,speed,0));
		mEU.add("aa"+count++, new Ball(x, y,-speed,0));
		mEU.add("ss"+count++, new Ball(x, y,0,speed));
		mEU.add("ww"+count++, new Ball(x, y,0,-speed));
		mEU.add("aw"+count++, new Ball(x, y,-speed*sqrt2d2,-speed*sqrt2d2));
		mEU.add("dw"+count++, new Ball(x, y,speed*sqrt2d2,-speed*sqrt2d2));
		mEU.add("as"+count++, new Ball(x, y,-speed*sqrt2d2,speed*sqrt2d2));
		mEU.add("ds"+count++, new Ball(x, y,speed*sqrt2d2,speed*sqrt2d2));
	}
	
	public void slashAcc(double x,double y,double speed){
		mEU.add("dd"+count++, new Ball(x, y,0,0,0.5,0));
		mEU.add("aa"+count++, new Ball(x, y,0,0,-0.5,0));
		mEU.add("ss"+count++, new Ball(x, y,0,0,0,0.5));
		mEU.add("ww"+count++, new Ball(x, y,0,0,0,-0.5));
		
	}
	/**
	 * 线性变换版本的slash
	 * @param x
	 * @param y
	 * @param speed
	 * @param t
	 */
	public void slashTransform(double x,double y,double speed,double[][] t){
		Transform tsf=new Transform(t);
		
		
		mEU.add("dd"+count++, new Ball(x, y,tsf.transform(speed,0)));
		mEU.add("aa"+count++, new Ball(x, y,tsf.transform(-speed,0)));
		mEU.add("ss"+count++, new Ball(x, y,tsf.transform(0,speed)));
		mEU.add("ww"+count++, new Ball(x, y,tsf.transform(0,-speed)));
		mEU.add("aw"+count++, new Ball(x, y,tsf.transform(-speed*sqrt2d2,-speed*sqrt2d2)));
		mEU.add("dw"+count++, new Ball(x, y,tsf.transform(speed*sqrt2d2,-speed*sqrt2d2)));
		mEU.add("as"+count++, new Ball(x, y,tsf.transform(-speed*sqrt2d2,speed*sqrt2d2)));
		mEU.add("ds"+count++, new Ball(x, y,tsf.transform(speed*sqrt2d2,speed*sqrt2d2)));
	}
	

}
