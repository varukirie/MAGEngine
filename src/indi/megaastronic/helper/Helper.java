package indi.megaastronic.helper;

import application.Main;
import indi.megaastronic.element.BaseElement;
import indi.megaastronic.element.DurationManage;
import javafx.scene.canvas.GraphicsContext;
/**
 * 用于辅助弹幕绘制，运行时不显示的元素
 * @author MegaAstronic
 *
 */
public class Helper extends BaseElement implements DurationManage{
	private long duration=10000000;
	private long startTime = 0;
	public Helper(double x, double y) {
		super(x, y);
	}
	public Helper(double x, double y, double velocityX, double velocityY) {
		super(x,y,velocityX,velocityY);
	}

	public Helper(double x, double y, double velocityX, double velocityY,double ax,double ay) {
		super(x,y,velocityX,velocityY,ax,ay);
	}
	
	
	
	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration = duration;
	}
	@Override
	public void paint(GraphicsContext gc) {
		if(Main.DEBUG){
			gc.fillText("Helper", getX(), getY());
		}
	}
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

}
