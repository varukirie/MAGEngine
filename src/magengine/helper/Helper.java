package magengine.helper;

import application.Main;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import magengine.element.BaseElement;
import magengine.element.DurationManage;
/**
 * 用于辅助弹幕绘制，运行时不显示的元素
 * 
 * 使用样例:
 * 将launcher位置绑定到helper上
 * 		helper.getxProperty().bindBidirectional(launcher.getxProperty());
 *		helper.getyProperty().bindBidirectional(launcher.getyProperty());
 *要保证launcher的 速度为0
 * @author MegaAstronic
 *
 */

public class Helper extends BaseElement implements DurationManage {
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
	/**
	 * 设置存活时间
	 * @param DURATION
	 */
	public void setDuration(long duration) {
		this.duration = duration;
	}
	@Override
	public void paint(GraphicsContext gc) {
		if(Main.DEBUG){
			gc.setFill(Color.WHITESMOKE);
			gc.fillText(".Helper x="+this.getX()+" y="+this.getY(), getX(), getY());
		}
	}
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}


}
