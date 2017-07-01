package indi.megaastronic.paint;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import indi.megaastronic.MyCanvas;

public class MoveHandler implements Runnable {
	public static double speed = 0.2;
	public static final long SLEEP_TIME = 16;
	public static final long BLANK = 1;
	private Map<String, Moveable> wantMoveMap = new HashMap<>();

	public Map<String, Moveable> getWantMoveMap() {
		return wantMoveMap;
	}

	private long lastTime;
	private MyCanvas myCanvas = null;

	public MoveHandler(MyCanvas myCanvas) {
		this.lastTime = System.currentTimeMillis();
		this.myCanvas = myCanvas;
	}

	public boolean keepRun = true;

	@Override
	public void run() {
		Moveable m;
		Entry<String,Moveable> entry;
		int nextX;
		int nextY;
		while (keepRun) {
			long currentTime = System.currentTimeMillis();
			Iterator<Entry<String, Moveable>> iter = wantMoveMap.entrySet().iterator();
			while (iter.hasNext()) {
				entry = iter.next();
				m=entry.getValue();
				nextX = (int) (m.getX() + m.getVelocityX() * ((currentTime - this.lastTime) / BLANK) * speed);
				nextY = (int) (m.getY() + m.getVelocityY() * ((currentTime - this.lastTime) / BLANK) * speed);

				if (nextX < MyCanvas.CANVAS_WIDTH && nextX > 0 && nextY < MyCanvas.CANVAS_HEIGHT && nextY > 0) {
					m.setX(nextX);
					m.setY(nextY);
				} else {// 出界
					// System.out.println("出界"+System.currentTimeMillis());
					if (m instanceof LimitedByCanvas) {//如果他是被边界限制的

					} else {//否则
						m.setX(nextX);
						m.setY(nextY);
						/*
						wantMoveMap.remove(entry.getKey());
						myCanvas.getWantPaintMap().remove(entry.getKey());
						*/
					}
				}

			}
			this.lastTime = currentTime;
			myCanvas.repaint();
			try {
				Thread.sleep(SLEEP_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
