package indi.megaastronic.helper;

import application.Main;
import indi.megaastronic.element.ANormalElement;
import javafx.scene.canvas.GraphicsContext;
/**
 * 用于辅助弹幕绘制，运行时不显示的元素
 * @author MegaAstronic
 *
 */
public class Helper extends ANormalElement {

	public Helper(double x, double y) {
		super(x, y);
	}
	public Helper(double x, double y, double velocityX, double velocityY) {
		super(x,y,velocityX,velocityY);
	}
	public Helper(double x, double y, double velocityX, double velocityY,double ax,double ay) {
		super(x,y,velocityX,velocityY,ax,ay);
	}
	
	@Override
	public void paint(GraphicsContext gc) {
		if(Main.DEBUG){
			gc.fillText("x", this.x, this.y);
		}
	}

}
