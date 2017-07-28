package indi.megaastronic.paint;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import indi.megaastronic.element.Paintable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
/**
 * 本类的实例化对象负责完成绘画
 * @author MegaAstronic
 *
 */
public class MyCanvas extends Canvas {
	//画布大小
	public final static int CANVAS_WIDTH = 600;
	public final static int CANVAS_HEIGHT = 700;
	private GraphicsContext gc=null;
	private Map<String, Paintable> wantPaintMap = null;

	public Map<String, Paintable> getWantPaintMap() {
		return wantPaintMap;
	}
	
	
	public MyCanvas(){
		super(CANVAS_WIDTH,CANVAS_HEIGHT);
		wantPaintMap = new ConcurrentHashMap<>();
		gc = super.getGraphicsContext2D();
	}
	public MyCanvas(Map<String, Paintable> wantPaintMap){
		super(CANVAS_WIDTH,CANVAS_HEIGHT);
		this.wantPaintMap = wantPaintMap;
		gc = super.getGraphicsContext2D();
	}
	public void clear(){
		gc.clearRect(0, 0, this.getWidth(), this.getHeight());
	}
	/**
	 * 将清空屏幕并调用wantPaintMap中所有元素的paint方法，完成绘制
	 */
	public void repaint(){
		//清空屏幕
		this.clear();
		//绘制wantPaint的内容
		Iterator<Entry<String, Paintable>> iter = wantPaintMap.entrySet().iterator();
		while(iter.hasNext()){
			iter.next().getValue().paint(gc);
		}
	}
}
