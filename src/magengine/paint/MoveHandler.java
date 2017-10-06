package magengine.paint;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import application.Main;
import magengine.element.Accelerated;
import magengine.element.DurationManage;
import magengine.element.LimitedByCanvas;
import magengine.element.Moveable;
import magengine.element.PolygonCollision;
import magengine.element.impl.Player;
import magengine.launcher.Launcher;
import magengine.util.CollisionUtil;
import magengine.util.ElementUtils;

/**
 * 单独一个线程，不断重复执行： 计算当前时刻所有Moveable元素的坐标
 * 为渲染提供callRepaint方法
 * @see Moveable
 * @author MegaAstronic
 *
 */

public class MoveHandler implements Runnable {

	public static final double DEFAULT_TIME_SPEED = 1;
	/**
	 * 全局速度
	 */
	public static double timeSpeed = DEFAULT_TIME_SPEED;
	public static final long SLEEP_TIME = 2;
	public static final long BLANK = 1000;

	private Map<String, Moveable> wantMoveMap = new ConcurrentHashMap<>();
	private ElementUtils mEU = null;
	
	public Map<String, Moveable> getWantMoveMap() {
		return wantMoveMap;
	}

	private long lastTime;
	private MyCanvas myCanvas = null;
	private MyCanvas sCanvas = null;// 辅助画布
	public boolean keepRun = true;

	private Moveable m;
	private Entry<String, Moveable> entry;
	private double nextX;
	private double nextY;
	int switchCount = 0;

	public MoveHandler(MyCanvas myCanvas, MyCanvas sCanvas) {
		this.lastTime = System.currentTimeMillis();
		this.myCanvas = myCanvas;
		this.sCanvas = sCanvas;
	}

	private long benchMax=0;
	public void run() {
		while (keepRun) {
			long currentTime = System.currentTimeMillis();
			Iterator<Entry<String, Moveable>> iter = wantMoveMap.entrySet().iterator();
			while (iter.hasNext()) {
				entry = iter.next();
				m = entry.getValue();
				m.modify();

				if(m instanceof Launcher){
					Launcher l=(Launcher) m;
					if(System.currentTimeMillis()-l.getLastLaunchTime()>l.getInterval()){
						l.setLastLaunchTime(System.currentTimeMillis());
						l.launch();
					}
				}
				
				if(m instanceof DurationManage){
					DurationManage dm = (DurationManage) m;
					if(System.currentTimeMillis()-dm.getStartTime()>dm.getDuration()){
						removeElement(entry.getKey());
						continue;
					}
				}

				if(Main.ACC_ENABLE){
					// 使用加速度计算速度
					if (m instanceof Accelerated) {
						if(((Accelerated) m).getAccX()!=0){
							m.setVelocityX(m.getVelocityX() + (currentTime - this.lastTime)*1.0 * ((Accelerated) m).getAccX()
									* timeSpeed * (1 / DEFAULT_TIME_SPEED) / BLANK);
						}
						if(((Accelerated) m).getAccY()!=0){
							m.setVelocityY(m.getVelocityY() + (currentTime - this.lastTime)*1.0 * ((Accelerated) m).getAccY()
									* timeSpeed * (1 / DEFAULT_TIME_SPEED) / BLANK);
						}
					}
				}

				nextX = m.getX() + m.getVelocityX() * ((currentTime - this.lastTime)*1.0 / BLANK) * timeSpeed;
				nextY = m.getY() + m.getVelocityY() * ((currentTime - this.lastTime)*1.0  / BLANK) * timeSpeed;
				
				if (m instanceof Player) {
					
					// Player不受speed减速影响
					nextX = m.getX() + m.getVelocityX() * ((currentTime - this.lastTime)*1.0  / BLANK) * DEFAULT_TIME_SPEED;
					nextY = m.getY() + m.getVelocityY() * ((currentTime - this.lastTime)*1.0  / BLANK) * DEFAULT_TIME_SPEED;
				}

				if (nextX < MyCanvas.CANVAS_WIDTH && nextX > 0 && nextY < MyCanvas.CANVAS_HEIGHT && nextY > 0) {
					if(m.getVelocityX()!=0)
						m.setX(nextX);
					if(m.getVelocityY()!=0)
						m.setY(nextY);
				} else {// 出屏幕
					 
					if (m instanceof LimitedByCanvas) {// 如果他是被边界限制的 不能移动

					} else {// 否则删除
//						m.setX(nextX);
//						m.setY(nextY);
						removeElement(entry.getKey());	
						continue;
					}
				}
				collisionCheck(m);
			}

			if(Main.DEBUG_BENCH){
				if((System.currentTimeMillis()-lastTime)>benchMax) benchMax=(System.currentTimeMillis()-lastTime);
				System.out.println("1.游戏逻辑 "+(System.currentTimeMillis()-lastTime)+"ms"+" benchMax="+benchMax);
			}
			this.lastTime = currentTime;
			
			try {
				Thread.sleep(SLEEP_TIME);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * 交替调用MyCanvas的repaint
	 */
	public void callRepaint() {
		if (switchCount == 0) {
			myCanvas.repaint();
			myCanvas.setVisible(true);
			sCanvas.setVisible(false);
		} else {
			sCanvas.repaint();
			sCanvas.setVisible(true);
			myCanvas.setVisible(false);
		}
		switchCount = (switchCount + 1) % 2;
	}
	public void removeElement(String key){
		mEU.removeBoth(key);
	}

	public ElementUtils getmEU() {
		return mEU;
	}

	public void setmEU(ElementUtils mEU) {
		this.mEU = mEU;
	}
	
	
	private void collisionCheck(Moveable m){
		if(m instanceof PolygonCollision){
			if(m!=Player.getPlayer()){
				if(CollisionUtil.PolygonDetect(Player.getPlayer(), (PolygonCollision) m)){
					//System.out.println("碰撞! x="+m.getX()+" | y="+m.getY());
					if(Main.DEBUG_COLLISION){
						System.out.println("碰撞! x="+m.getX()+" | y="+m.getY());
					}
				}
					
			}
		}
	}
	
	public int getSize(){
		return this.getWantMoveMap().size();
	}
}
