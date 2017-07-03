package indi.megaastronic.paint;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
/**
 * 单独一个线程，不断重复执行：计算当前时刻所有Moveable元素的坐标，并调用MyCanvas的repaint方法
 * @author MegaAstronic
 *
 */

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
public class MoveHandler implements Runnable {
	public static final double DEFAULT_SPEED=0.2;
	public static double speed = DEFAULT_SPEED;
	public static final long SLEEP_TIME = 21;
	public static final long BLANK = 1;
	private Map<String, Moveable> wantMoveMap = new ConcurrentHashMap<>();

	public Map<String, Moveable> getWantMoveMap() {
		return wantMoveMap;
	}
	
	private ObservableList<Node> OL=null;
	private long lastTime;
	private MyCanvas myCanvas = null;
	private MyCanvas sCanvas = null;//辅助画布
	public boolean keepRun = true;
	
	public MoveHandler(MyCanvas myCanvas,MyCanvas sCanvas) {
		this.lastTime = System.currentTimeMillis();
		this.myCanvas = myCanvas;
		this.sCanvas = sCanvas;
	}
	

	

	@Override
	public void run() {
		ArrayList<String> removeMark = new ArrayList<>();
		Moveable m;
		Entry<String,Moveable> entry;
		double nextX;
		double nextY;
		int switchCount = 0;
		while (keepRun) {
			long currentTime = System.currentTimeMillis();
			Iterator<Entry<String, Moveable>> iter = wantMoveMap.entrySet().iterator();
			while (iter.hasNext()) {
				entry = iter.next();
				m=entry.getValue();
				nextX = m.getX() + m.getVelocityX() * ((currentTime - this.lastTime) / BLANK) * speed;
				nextY = m.getY() + m.getVelocityY() * ((currentTime - this.lastTime) / BLANK) * speed;

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
						removeMark.add(entry.getKey());
					}
				}
			}
			//删除出界元素
			for (String str : removeMark) {
				wantMoveMap.remove(str);
				myCanvas.getWantPaintMap().remove(str);
			}
			removeMark.clear();
			
			this.lastTime = currentTime;
			
			
			//sCanvas.repaint();
			
			
			if(switchCount==0){
				myCanvas.repaint();
				myCanvas.setVisible(true);
				sCanvas.setVisible(false);
			}else{
				sCanvas.repaint();
				sCanvas.setVisible(true);
				myCanvas.setVisible(false);
			}
			switchCount=(switchCount+1)%2;
			
			try {
				Thread.sleep(SLEEP_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
