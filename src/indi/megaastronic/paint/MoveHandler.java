package indi.megaastronic.paint;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import indi.megaastronic.MyCanvas;

public class MoveHandler implements Runnable{
	public static int speed = 1;
	public static final long SLEEP_TIME=20;
	public static final long BLANK = 10;
	private Map<String,Moveable> wantMoveMap = new HashMap<>();
	public Map<String, Moveable> getWantMoveMap() {
		return wantMoveMap;
	}
	
	private long lastTime ;
	private MyCanvas myCanvas=null;
	public MoveHandler(MyCanvas myCanvas) {
		this.lastTime=System.currentTimeMillis();
		this.myCanvas=myCanvas;
	}
	public boolean keepRun=true;
	@Override
	public void run() {
		while (keepRun) {
			long currentTime = System.currentTimeMillis();
			Iterator<Entry<String, Moveable>> iter = wantMoveMap.entrySet().iterator();
			while (iter.hasNext()) {
				Moveable m = iter.next().getValue();
				m.setX((int) (m.getX() + m.getVelocityX() * ((currentTime - this.lastTime)/BLANK) * speed));
				m.setY((int) (m.getY() + m.getVelocityY() * ((currentTime - this.lastTime)/BLANK) * speed));
			}
			this.lastTime=currentTime;
			myCanvas.repaint();
			try {
				Thread.sleep(SLEEP_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		}
	}

}
