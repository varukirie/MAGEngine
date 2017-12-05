package magengine.element.impl;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import magengine.element.PolygonCollision;
import magengine.game.GameSession;

public class BombItem extends CircleArea {

	
	private static Image img = new Image(BombItem.class.getResourceAsStream("/img/bomb.png"));    
	public static final double BOMB_R = 50;
	public BombItem(double x, double y) {
		super(x, y, BOMB_R);
		this.setVelocityY(70);
	}
	
	@Override
	public void paint(GraphicsContext gc) {
		super.paint(gc);
		gc.drawImage(img, this.getX()-BOMB_R, this.getY()-BOMB_R);
	}
	
	@Override
	public void onCollision(PolygonCollision m) {
		super.onCollision(m);
		if(m instanceof Player){
			this.setWantBeRemoved(true);
			GameSession.getGameSession().addBomb();
		}
	}
}
