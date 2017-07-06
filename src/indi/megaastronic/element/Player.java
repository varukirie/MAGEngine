package indi.megaastronic.element;

import indi.megaastronic.paint.LimitedByCanvas;
import indi.megaastronic.paint.PInfo;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
/**
 * 玩家控制的物体
 * @author MegaAstronic
 *
 */
public class Player extends ANormalElement implements LimitedByCanvas {
	public final int width = 10;
	public final int height = 10;
	public Player(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void setX(double x) {
		PInfo.setPlayerX(x);
		super.setX(x);
	}
	@Override
	public void setY(double y) {
		PInfo.setPlayerY(y);
		super.setY(y);
	}
	@Override
	public void paint(GraphicsContext gc) {
		//gc.strokeOval(x, y, width, height);
		
		gc.setFont(Font.font("consolas",30));
		gc.setFill(Color.RED);
		gc.fillText("♥", this.x, this.y);
	}
	

}
