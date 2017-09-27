package magengine.element.impl;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import magengine.element.BaseElement;

public class DisplayTime extends BaseElement {

	private long startTime;
	public DisplayTime(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		startTime = System.currentTimeMillis();
	}

	@Override
	public void paint(GraphicsContext gc) {
		gc.setFill(Color.WHITE);
		gc.fillText("Time = "+(System.currentTimeMillis()-this.startTime)/1000, getX(), getY());
	}

}
