package magengine.element.impl;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import magengine.game.GameSession;

public class EmBloodBar {
	
	private int x,y,w,h;
	private Canvas bcanvas = GameSession.getGameSession().bb.getBcanvas();
	
	public Canvas getBcanvas() {
		return bcanvas;
	}

	public EmBloodBar(int x, int y, int w, int h){
		this.x = x;
		this.y = y;
		this.h = h;
		this.w = w;
	}

	public void paint(int HP, int presetHP) {
//		int x=870,y=0,w=30,h,yt=0;
		GraphicsContext gc = bcanvas.getGraphicsContext2D();
		int yt = 0,ht = 0;
		gc.setFill(Color.MAGENTA);
		gc.setStroke(Color.RED);
		gc.fillRect(x, y, w, h);
		gc.setFill(Color.RED);
		ht=(int)(h*((HP+1)*1.0/(presetHP+1)));
		yt+=h-(int)(h*((HP+1)*1.0/(presetHP+1)));
		gc.fillRect(x+2, yt+2, w-4, ht-4);
		if(HP <= 0) gc.clearRect(x, y, w, h);
	}
}
