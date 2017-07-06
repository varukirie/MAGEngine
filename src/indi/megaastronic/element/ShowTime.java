package indi.megaastronic.element;

import javafx.scene.canvas.GraphicsContext;

public class ShowTime extends ANormalElement {

	private long startTime;
	public ShowTime(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		startTime = System.currentTimeMillis();
	}

	@Override
	public void paint(GraphicsContext gc) {
		gc.fillText("Time = "+(System.currentTimeMillis()-this.startTime)/1000, x, y);
	}

}
