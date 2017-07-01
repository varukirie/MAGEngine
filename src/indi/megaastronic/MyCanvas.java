package indi.megaastronic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import indi.megaastronic.paint.Paintable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
/**
 * 本类的实例化对象负责完成绘画
 * 将调用wantPaintMap中所有元素的paint方法，完成绘制
 * @author Administrator
 *
 */
public class MyCanvas extends Canvas {
	//画布大小
	public final static int CANVAS_WIDTH = 300;
	public final static int CANVAS_HEIGHT = 250;
	private GraphicsContext gc;
	private Map<String, Paintable> wantPaintMap = new HashMap<>();

	public Map<String, Paintable> getWantPaintMap() {
		return wantPaintMap;
	}
	
	public MyCanvas(){
		super(CANVAS_WIDTH,CANVAS_HEIGHT);
		gc = super.getGraphicsContext2D();
	}
	
	public void repaint(){
		gc.clearRect(0, 0, this.getWidth(), this.getHeight());//清空屏幕
		//绘制wantPaint的内容
		Iterator<Entry<String, Paintable>> iter = wantPaintMap.entrySet().iterator();
		while(iter.hasNext()){
			iter.next().getValue().paint(gc);
		}
	}
}
