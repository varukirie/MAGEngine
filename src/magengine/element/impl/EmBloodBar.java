package magengine.element.impl;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import magengine.game.GameSession;

public class EmBloodBar {

	private int x, y, w, h;
	private Canvas bcanvas = GameSession.getGameSession().bb.getBcanvas();

	public Canvas getBcanvas() {
		return bcanvas;
	}

	public EmBloodBar(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.h = h;
		this.w = w;
	}

	public void paint(int HP, int presetHP) {
		Platform.runLater(() -> {
			GraphicsContext gc = bcanvas.getGraphicsContext2D();
			int yt = 0, ht = 0;
			gc.setFill(Color.BLACK);
			gc.setStroke(Color.RED);
			gc.fillRect(x, y, w, h);

			Stop[] stops = new Stop[] { new Stop(0, Color.RED), new Stop(0.5, Color.AZURE), new Stop(1, Color.RED) };
			LinearGradient lg = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);
			gc.setFill(lg);
			ht = (int) (h * ((HP + 1) * 1.0 / (presetHP + 1)));
			yt += h - (int) (h * ((HP + 1) * 1.0 / (presetHP + 1)));
			gc.fillRect(x + 2, yt + 2, w - 4, ht - 4);
			if (HP <= 0)
				gc.clearRect(x, y, w, h);
		});
	}
}
