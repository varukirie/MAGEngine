package indi.megaastronic.chapter;

import indi.megaastronic.element.Moveable;
import indi.megaastronic.element.impl.Bullet;
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
	 * @param x 坐标
	 * @param y 坐标
	 * @param speed 速度v
	 * @param t 线性变换矩阵
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
	 * 加速线性变换版本的slash
	 * @param x 坐标
	 * @param y 坐标
	 * @param accRate 加速度a
	 * @param t 线性变换矩阵
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
	
	public Bullet snipe(Moveable source,Moveable target,double v){
		double dx = target.getX() - source.getX();
		double dy = target.getY() - source.getY();
		double s = Math.sqrt(dx * dx + dy * dy);
		Bullet bullet =new Bullet(source.getX(), source.getY());
		bullet.setVelocityX(dx *v/ s);
		bullet.setVelocityY(dy *v/ s);
		return bullet;
	}
	
	public Bullet snipe(double sx,double sy,double tx,double ty,double v){
		double dx = sx - tx;
		double dy = sy - ty;
		double s = Math.sqrt(dx * dx + dy * dy);
		Bullet bullet =new Bullet(sx, sy);
		bullet.setVelocityX(dx *v/ s);
		bullet.setVelocityY(dy *v/ s);
		return bullet;
	}
	

}
