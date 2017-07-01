package indi.megaastronic.paint;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import indi.megaastronic.MyCanvas;

public class MoveHandler implements Runnable {
	public static int speed = 1;
	public static final long SLEEP_TIME = 20;
	public static final long BLANK = 5;
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
		int nextX;
		int nextY;
		while (keepRun) {
			long currentTime = System.currentTimeMillis();
			Iterator<Entry<String, Moveable>> iter = wantMoveMap.entrySet().iterator();
			while (iter.hasNext()) {
				m = iter.next().getValue();
				nextX = (int) (m.getX() + m.getVelocityX() * ((currentTime - this.lastTime) / BLANK) * speed);
				nextY = (int) (m.getY() + m.getVelocityY() * ((currentTime - this.lastTime) / BLANK) * speed);
				if (nextX < MyCanvas.CANVAS_WIDTH && nextX > 0 && nextY < MyCanvas.CANVAS_HEIGHT && nextY > 0) {
					m.setX(nextX);
					m.setY(nextY);
				} else {

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
