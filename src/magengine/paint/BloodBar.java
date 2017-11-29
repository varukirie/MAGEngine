package magengine.paint;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

public class BloodBar {

	private int x, y, w, h;
	private Canvas bcanvas = new Canvas(900, 700);

	public Canvas getBcanvas() {
		return bcanvas;
	}

	public BloodBar(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.h = h;
		this.w = w;
	}

	public void paint(int HP, int presetHP) {
		Platform.runLater(() -> {
			int wt;
			GraphicsContext gc = bcanvas.getGraphicsContext2D();
			gc.setFill(Color.BLACK);
			gc.setStroke(Color.BROWN);
			gc.fillRect(this.x, this.y, this.w, this.h);
			Stop[] stops = new Stop[] { new Stop(0, Color.RED), new Stop(1, Color.GOLD) };
			LinearGradient lg = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);
			gc.setFill(lg);
			wt = (int) (this.w * ((HP + 1) * 1.0 / (presetHP + 1)));
			gc.fillRect(this.x + 2, this.y + 2, wt - 4, this.h - 4);
		});
	}
}
