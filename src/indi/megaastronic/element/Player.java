package indi.megaastronic.element;

import indi.megaastronic.paint.LimitedByCanvas;
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
	public void paint(GraphicsContext gc) {
		//gc.strokeOval(x, y, width, height);
		
		gc.setFont(Font.font("consolas",30));
		gc.setFill(Color.ORANGE);
		gc.fillText("★", this.x, this.y);
	}
	

}
