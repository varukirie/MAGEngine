package indi.megaastronic.paint;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import application.Main;
import indi.megaastronic.element.Accelerated;
import indi.megaastronic.element.LimitedByCanvas;
import indi.megaastronic.element.Moveable;
import indi.megaastronic.element.Player;

/**
 * 单独一个线程，不断重复执行： 计算当前时刻所有Moveable元素的坐标
 * 为渲染提供callRepaint方法
 * @see Moveable
 * @author MegaAstronic
 *
 */

public class MoveHandler implements Runnable {

	public static final double DEFAULT_TIME_SPEED = 0.2;
	/**
	 * 全局速度
	 */
	public static double timeSpeed = DEFAULT_TIME_SPEED;
	public static final long SLEEP_TIME = 16;
	public static final long BLANK = 1;
	private Map<String, Moveable> wantMoveMap = new ConcurrentHashMap<>();

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

	public void run() {
		while (keepRun) {
			long currentTime = System.currentTimeMillis();
			Iterator<Entry<String, Moveable>> iter = wantMoveMap.entrySet().iterator();
			while (iter.hasNext()) {
				entry = iter.next();
				m = entry.getValue();
				// 使用加速度计算速度
				if (m instanceof Accelerated) {
					m.setVelocityX(m.getVelocityX() + (currentTime - this.lastTime) * ((Accelerated) m).getAccX()
							* timeSpeed * (1 / DEFAULT_TIME_SPEED) / 1000);
					m.setVelocityY(m.getVelocityY() + (currentTime - this.lastTime) * ((Accelerated) m).getAccY()
							* timeSpeed * (1 / DEFAULT_TIME_SPEED) / 1000);
				}

				nextX = m.getX() + m.getVelocityX() * ((currentTime - this.lastTime) / BLANK) * timeSpeed;
				nextY = m.getY() + m.getVelocityY() * ((currentTime - this.lastTime) / BLANK) * timeSpeed;
				
				if (m instanceof Player) {
					
					// Player不受speed减速影响
					nextX = m.getX() + m.getVelocityX() * ((currentTime - this.lastTime) / BLANK) * DEFAULT_TIME_SPEED;
					nextY = m.getY() + m.getVelocityY() * ((currentTime - this.lastTime) / BLANK) * DEFAULT_TIME_SPEED;
				}

				if (nextX < MyCanvas.CANVAS_WIDTH && nextX > 0 && nextY < MyCanvas.CANVAS_HEIGHT && nextY > 0) {
					m.setX(nextX);
					m.setY(nextY);
				} else {// 出屏幕
					// System.out.println("出界"+System.currentTimeMillis());
					if (m instanceof LimitedByCanvas) {// 如果他是被边界限制的

					} else {// 否则
						m.setX(nextX);
						m.setY(nextY);
						removeElement(entry.getKey());	
					}
				}
			}

//			if(Main.DEBUG){
//				System.out.println("1.cal use "+(currentTime-lastTime)+"ms");
//			}
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
		wantMoveMap.remove(key);
		myCanvas.getWantPaintMap().remove(key);
		
	}
}
