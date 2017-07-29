package indi.megaastronic.chapter;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import indi.megaastronic.element.Moveable;
import indi.megaastronic.element.impl.Bullet;
import indi.megaastronic.util.ElementUtils;
import indi.megaastronic.util.Transform;

public class QuickDanmuku {
	
	private ElementUtils mEU;
	private final double sqrt2d2=0.7071;
	private int count = 0;
	private Random r=new Random();
	public QuickDanmuku(ElementUtils mEU) {
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
	

	
	public void snipe(double sx,double sy,double tx,double ty,double v){
		snipe(sx, sy, tx, ty, v, Bullet.class);
	}
	public void snipe(double sx,double sy,double tx,double ty,double v,Class<? extends Bullet> cls){
		double dx = sx - tx;
		double dy = sy - ty;
		double s = Math.sqrt(dx * dx + dy * dy);
//		Bullet bullet =new Bullet(sx, sy);
		Bullet bullet=null;
		try {
			bullet =cls.getConstructor(double.class,double.class).newInstance(sx,sy);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bullet.setVelocityX(-dx *v/ s);
		bullet.setVelocityY(-dy *v/ s);
		mEU.add("snipe"+r.nextInt(), bullet);
	}
	public void snipeAcc(double sx,double sy,double tx,double ty,double v){
		double dx = sx - tx;
		double dy = sy - ty;
		double s = Math.sqrt(dx * dx + dy * dy);
		Bullet bullet =new Bullet(sx, sy);
		bullet.setAccX(-dx *v/ s);
		bullet.setAccY(-dy *v/ s);
		mEU.add("snipe"+r.nextInt(), bullet);
	}
	

}
