package magengine.paint;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import magengine.game.GameSession;

public class BombPainting {
	
	private int x,y;
	private Canvas canvas = GameSession.getGameSession().bb.getBcanvas();
	private Image img = new Image(this.getClass().getResourceAsStream("/img/bomb.png"));    
	
	public BombPainting(int x, int y){
		this.x=x;
		this.y=y;
	}
	
	public void paint(int bombnum){
		Platform.runLater(()->{
			int xt = this.x;
			GraphicsContext gc = canvas.getGraphicsContext2D();
			gc.clearRect(600, 120, 250, 50);
			for(int i=0;i<bombnum; i++){
			gc.drawImage(img, xt, y);
			xt+=50;
			}
		});
	}
	
}
