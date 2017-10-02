package magengine.chapter.util;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import magengine.bullet.Bullet;
import magengine.bullet.DefaultBullet;
import magengine.element.BaseElement;
import magengine.element.impl.Player;
import magengine.util.DI;
import magengine.util.ElementUtils;
import magengine.util.Transform;

public class QuickDanmuku {
	
	private static QuickDanmuku quick =null;
	public static QuickDanmuku getQuickDanmuku(){
		if(quick==null){
			quick = new QuickDanmuku((ElementUtils) DI.di().get("mEU"));
		}
		return quick;
	}
	
	private ElementUtils mEU;
	private final double sqrt2d2=0.7071;
	private int count = 0;
	private Random r=new Random();
	public QuickDanmuku(ElementUtils mEU) {
		super();
		this.mEU = mEU;
	}
	
	public void VTransform(BaseElement element,double[][] martix){
		Transform t = new Transform(martix);
		double[] ans = t.transform(element.getVelocityX(),element.getVelocityY());
		element.setVelocityX(ans[0]);
		element.setVelocityY(ans[1]);
	}
	
	public void VTo(BaseElement element,double targetX,double targetY){
		double targetS = Math.sqrt((targetX-element.getX())*(targetX-element.getX())+(targetY-element.getY())*(targetY-element.getY()));
		double originS = Math.sqrt(element.getVelocityX()*element.getVelocityX()+element.getVelocityY()*element.getVelocityY());
		element.setVelocityX((targetX-element.getX())*(originS/targetS));
		element.setVelocityY((targetY-element.getY())*(originS/targetS));
	}
	/**
	 * 
	 * @param element
	 * @param direction 负角
	 */
	public void VToByDirection(BaseElement element,double direction){
		double originS = Math.sqrt(element.getVelocityX()*element.getVelocityX()+element.getVelocityY()*element.getVelocityY());
		element.setVelocityX(Math.cos(direction)*originS);
		element.setVelocityY(Math.sin(direction)*originS);
	}
	
	@Deprecated
	public void VRotate(BaseElement element,double angle){
		//TODO VRotate
	}
	
	public void setSpeed(BaseElement element,double speed){
		double originS = Math.sqrt(element.getVelocityX()*element.getVelocityX()+element.getVelocityY()*element.getVelocityY());
		element.setVelocityX(element.getVelocityX()*(speed/originS));
		element.setVelocityY(element.getVelocityY()*(speed/originS));
	}
	
	public void stopBullet(Bullet bullet){
		bullet.setVelocityX(bullet.getVelocityX()*0.001);
		bullet.setVelocityY(bullet.getVelocityY()*0.001);
	}
	
	public void runBullet(Bullet bullet){
		bullet.setVelocityX(bullet.getVelocityX()*1000);
		bullet.setVelocityY(bullet.getVelocityY()*1000);
	}
	
	public void snipePlayer(Bullet bullet){
		snipe(bullet, Player.getPlayer().getX(), Player.getPlayer().getY());
	}
	public void snipe(Bullet bullet,double x,double y){
		double v=Math.sqrt(bullet.getVelocityX() * bullet.getVelocityX() + bullet.getVelocityY() * bullet.getVelocityY());
		double dx = bullet.getX() - x;
		double dy = bullet.getY() - y;
		double s = Math.sqrt(dx * dx + dy * dy);
		bullet.setVelocityX(-dx *v/ s);
		bullet.setVelocityY(-dy *v/ s);
	}
	
	
	
	

	
	public void snipe(double sx,double sy,double tx,double ty,double v){
		snipe(sx, sy, tx, ty, v, DefaultBullet.class);
	}
	public void snipe(double sx,double sy,double tx,double ty,double v,Class<?> bulletClass){
		double dx = sx - tx;
		double dy = sy - ty;
		double s = Math.sqrt(dx * dx + dy * dy);
//		DefaultBullet DefaultBullet =new DefaultBullet(sx, sy);
		Bullet bullet=null;
		try {
			bullet =(Bullet) bulletClass.getConstructor(double.class,double.class).newInstance(sx,sy);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			System.out.println("错误的子弹类型");
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
		DefaultBullet bullet =new DefaultBullet(sx, sy);
		bullet.setAccX(-dx *v/ s);
		bullet.setAccY(-dy *v/ s);
		mEU.add("snipe"+r.nextInt(), bullet);
	}
	

}
