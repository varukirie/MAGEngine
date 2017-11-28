package magengine.element.impl;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import magengine.paint.MyCanvas;

public class BloodBar {
	
	private int x,y,w,h;
	private Canvas bcanvas = new Canvas(900,700);
	
	public Canvas getBcanvas() {
		return bcanvas;
	}

	public BloodBar(int x, int y, int w, int h){
		this.x = x;
		this.y = y;
		this.h = h;
		this.w = w;
	}

	public void paint(int HP, int presetHP) {
//		int x=600,y=40,w=200,h=30;
		int wt;
		GraphicsContext gc = bcanvas.getGraphicsContext2D();
		gc.setFill(Color.MAGENTA);
		gc.setStroke(Color.RED);
		gc.fillRect(this.x, this.y, this.w, this.h);
		gc.setFill(Color.RED);
		wt=(int)(this.w*((HP+1)*1.0/(presetHP+1)));
		gc.fillRect(this.x+2, this.y+2, wt-4, this.h-4);
	}
}
