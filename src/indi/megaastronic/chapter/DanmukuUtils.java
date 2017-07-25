package indi.megaastronic.chapter;

import indi.megaastronic.element.Bullet;
import indi.megaastronic.util.ElementUtils;
import indi.megaastronic.util.Transform;

public class DanmukuUtils {
	
	private ElementUtils mEU;
	private final double sqrt2d2=0.7071;
	private int count = 0;
	public DanmukuUtils(ElementUtils mEU) {
		super();
		this.mEU = mEU;
	}
	
	public void slash(double x,double y,double speed){
		mEU.add("dd"+count++, new Bullet(x, y,speed,0));
		mEU.add("aa"+count++, new Bullet(x, y,-speed,0));
		mEU.add("ss"+count++, new Bullet(x, y,0,speed));
		mEU.add("ww"+count++, new Bullet(x, y,0,-speed));
		mEU.add("aw"+count++, new Bullet(x, y,-speed*sqrt2d2,-speed*sqrt2d2));
		mEU.add("dw"+count++, new Bullet(x, y,speed*sqrt2d2,-speed*sqrt2d2));
		mEU.add("as"+count++, new Bullet(x, y,-speed*sqrt2d2,speed*sqrt2d2));
		mEU.add("ds"+count++, new Bullet(x, y,speed*sqrt2d2,speed*sqrt2d2));
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
		
		
		mEU.add("dd"+count++, new Bullet(x, y,tsf.transform(speed,0)));
		mEU.add("aa"+count++, new Bullet(x, y,tsf.transform(-speed,0)));
		mEU.add("ss"+count++, new Bullet(x, y,tsf.transform(0,speed)));
		mEU.add("ww"+count++, new Bullet(x, y,tsf.transform(0,-speed)));
		mEU.add("aw"+count++, new Bullet(x, y,tsf.transform(-speed*sqrt2d2,-speed*sqrt2d2)));
		mEU.add("dw"+count++, new Bullet(x, y,tsf.transform(speed*sqrt2d2,-speed*sqrt2d2)));
		mEU.add("as"+count++, new Bullet(x, y,tsf.transform(-speed*sqrt2d2,speed*sqrt2d2)));
		mEU.add("ds"+count++, new Bullet(x, y,tsf.transform(speed*sqrt2d2,speed*sqrt2d2)));
	}
	
	/**
	 * 线性变换版本的slash
	 * @param x
	 * @param y
	 * @param accRate
	 * @param t
	 */
	public void slashTransformAcc(double x,double y,double accRate,double[][] t){
		Transform tsf=new Transform(t);
		
		
		mEU.add("dd"+count++, new Bullet(x, y,0,0,tsf.transform(accRate,0)));
		mEU.add("aa"+count++, new Bullet(x, y,0,0,tsf.transform(-accRate,0)));
		mEU.add("ss"+count++, new Bullet(x, y,0,0,tsf.transform(0,accRate)));
		mEU.add("ww"+count++, new Bullet(x, y,0,0,tsf.transform(0,-accRate)));
		mEU.add("aw"+count++, new Bullet(x, y,0,0,tsf.transform(-accRate*sqrt2d2,-accRate*sqrt2d2)));
		mEU.add("dw"+count++, new Bullet(x, y,0,0,tsf.transform(accRate*sqrt2d2,-accRate*sqrt2d2)));
		mEU.add("as"+count++, new Bullet(x, y,0,0,tsf.transform(-accRate*sqrt2d2,accRate*sqrt2d2)));
		mEU.add("ds"+count++, new Bullet(x, y,0,0,tsf.transform(accRate*sqrt2d2,accRate*sqrt2d2)));
	}
	

}
