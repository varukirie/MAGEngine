package magengine.chapter.util;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.scene.shape.MoveTo;
import magengine.bullet.Bullet;
import magengine.bullet.DefaultBullet;
import magengine.element.BaseElement;
import magengine.element.impl.Player;
import magengine.helper.MoveToHelper;
import magengine.launcher.Launcher;
import magengine.util.DI;
import magengine.util.ElementUtils;
import magengine.util.LogicExecutor;
import magengine.util.Transform;

public class QuickDanmuku {

	private static QuickDanmuku quick = null;

	public static QuickDanmuku getQuickDanmuku() {
		if (quick == null) {
			quick = new QuickDanmuku((ElementUtils) DI.di().get("mEU"));
		}
		return quick;
	}
	
	public static void clear(){
		quick=null;
	}
	
	private ElementUtils mEU;
	private ScheduledExecutorService sES = (ScheduledExecutorService) DI.di().get("sES");
	private final double sqrt2d2 = 0.7071;
	private int count = 0;
	private Random r = new Random();

	public QuickDanmuku(ElementUtils mEU) {
		super();
		this.mEU = mEU;
	}

	public void VTransform(BaseElement element, double[][] martix) {
		Transform t = new Transform(martix);
		double[] ans = t.transform(element.getVelocityX(), element.getVelocityY());
		element.setVelocityX(ans[0]);
		element.setVelocityY(ans[1]);
	}

	public void VTo(BaseElement element, double targetX, double targetY) {
		double targetS = Math.sqrt((targetX - element.getX()) * (targetX - element.getX())
				+ (targetY - element.getY()) * (targetY - element.getY()));
		double originS = Math.sqrt(
				element.getVelocityX() * element.getVelocityX() + element.getVelocityY() * element.getVelocityY());
		element.setVelocityX((targetX - element.getX()) * (originS / targetS));
		element.setVelocityY((targetY - element.getY()) * (originS / targetS));
	}

	public void AccTo(BaseElement element, double targetX, double targetY) {
		double targetS = Math.sqrt((targetX - element.getX()) * (targetX - element.getX())
				+ (targetY - element.getY()) * (targetY - element.getY()));
		double originS = Math.sqrt(element.getAccX() * element.getAccX() + element.getAccY() * element.getAccY());
		element.setAccX((targetX - element.getX()) * (originS / targetS));
		element.setAccY((targetY - element.getY()) * (originS / targetS));
	}

	public long getSyncDelayAfterLaunch(Launcher launcher,long delay){
		return launcher.getStartTime()+delay-LogicExecutor.gameTime();
	}
	
	/**
	 * 
	 * @param element
	 * @param direction
	 *            负角
	 */
	public void VToByDirection(BaseElement element, double direction) {
		double originS = Math.sqrt(
				element.getVelocityX() * element.getVelocityX() + element.getVelocityY() * element.getVelocityY());
		element.setVelocityX(Math.cos(direction) * originS);
		element.setVelocityY(Math.sin(direction) * originS);
	}

	public void moveTo(BaseElement element, long timeCost, double targetX, double targetY){
		moveTo(element,timeCost, targetX,targetY,true);
	}
	/**
	 * 让元素在指定时间内运动到目的地
	 * 线程不安全，两次调用应该有足够间隔
	 * @param element
	 * @param timeCost
	 * @param targetX
	 * @param targetY
	 */

	public void moveTo(BaseElement element, long timeCost, double targetX, double targetY,boolean accMode) {
		MoveToHelper helper = new MoveToHelper(element.getX(), element.getY(), targetX, targetY, timeCost);
		helper.setAccMode(accMode);
		element.setVelocityX(0);
		element.setVelocityY(0);
		element.setAccX(0);
		element.setAccY(0);
		element.getxProperty().bind(helper.getxProperty());
		element.getyProperty().bind(helper.getyProperty());
//		element.getxProperty().bind(helper.getxProperty());
//		element.getyProperty().bind(helper.getyProperty());
		mEU.add(r.nextInt() + "", helper);
//		System.out.println(timeCost);
		sES.schedule(() -> {
//			System.out.println("execute unbind");
			// element.getxProperty().unbindBidirectional(helper.getxProperty());
			// element.getyProperty().unbindBidirectional(helper.getyProperty());
			element.getxProperty().unbind();
			element.getyProperty().unbind();
//			element.getxProperty().set(150);
		}, timeCost + 1, TimeUnit.MILLISECONDS);

	}

	public void VRotate(BaseElement elem, double angle) {
		double theta = angle;
		Transform t= new Transform(new double[][] {
			{Math.cos(theta) ,Math.sin(theta) }, 
			{-Math.sin(theta),Math.cos(theta) } });
		double[] ans = t.transform(elem.getVelocityX(),elem.getVelocityY());
		elem.setVelocityX(ans[0]);
		elem.setVelocityY(ans[1]);
	}

	public void setSpeed(BaseElement element, double speed) {
		double originS = Math.sqrt(
				element.getVelocityX() * element.getVelocityX() + element.getVelocityY() * element.getVelocityY());
		element.setVelocityX(element.getVelocityX() * (speed / originS));
		element.setVelocityY(element.getVelocityY() * (speed / originS));
	}

	public void stopBullet(BaseElement bullet) {
		bullet.setVelocityX(bullet.getVelocityX() * 0.001);
		bullet.setVelocityY(bullet.getVelocityY() * 0.001);
	}

	public void runBullet(BaseElement bullet) {
		bullet.setVelocityX(bullet.getVelocityX() * 1000);
		bullet.setVelocityY(bullet.getVelocityY() * 1000);
	}

	public void snipePlayer(BaseElement bullet) {
		snipe(bullet, Player.getPlayer().getX(), Player.getPlayer().getY());
	}

	public void snipe(BaseElement bullet, double x, double y) {
		double v = Math
				.sqrt(bullet.getVelocityX() * bullet.getVelocityX() + bullet.getVelocityY() * bullet.getVelocityY());
		double dx = bullet.getX() - x;
		double dy = bullet.getY() - y;
		double s = Math.sqrt(dx * dx + dy * dy);
		bullet.setVelocityX(-dx * v / s);
		bullet.setVelocityY(-dy * v / s);
	}

	public void snipe(double sx, double sy, double tx, double ty, double v) {
		snipe(sx, sy, tx, ty, v, DefaultBullet.class);
	}

	public void snipe(double sx, double sy, double tx, double ty, double v, Class<?> bulletClass) {
		double dx = sx - tx;
		double dy = sy - ty;
		double s = Math.sqrt(dx * dx + dy * dy);
		// DefaultBullet DefaultBullet =new DefaultBullet(sx, sy);
		Bullet bullet = null;
		try {
			bullet = (Bullet) bulletClass.getConstructor(double.class, double.class).newInstance(sx, sy);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			System.out.println("错误的子弹类型");
			e.printStackTrace();
		}
		bullet.setVelocityX(-dx * v / s);
		bullet.setVelocityY(-dy * v / s);
		mEU.add("snipe" + r.nextInt(), bullet);
	}

	public void snipeAcc(double sx, double sy, double tx, double ty, double v) {
		double dx = sx - tx;
		double dy = sy - ty;
		double s = Math.sqrt(dx * dx + dy * dy);
		DefaultBullet bullet = new DefaultBullet(sx, sy);
		bullet.setAccX(-dx * v / s);
		bullet.setAccY(-dy * v / s);
		mEU.add("snipe" + r.nextInt(), bullet);
	}

	
	public void bindToXY(BaseElement elem1,BaseElement elem2){
		elem1.getxProperty().bind(elem2.getxProperty());
		elem1.getyProperty().bind(elem2.getyProperty());
	}
	
	public void unbindXY(BaseElement elem){
		elem.getxProperty().unbind();
		elem.getyProperty().unbind();
	}
	/**
	 * 在angle的范围内随机让速度变化方向 单位弧度
	 * @param angle
	 */
	public void VRotateRandom(BaseElement elem,double angle){
		double theta = angle*r.nextDouble()-(angle/2);
		Transform t= new Transform(new double[][] {
			{Math.cos(theta) ,Math.sin(theta) }, 
			{-Math.sin(theta),Math.cos(theta) } });
		double[] ans = t.transform(elem.getVelocityX(),elem.getVelocityY());
		elem.setVelocityX(ans[0]);
		elem.setVelocityY(ans[1]);
	}
}
