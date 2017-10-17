package magengine.enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import magengine.paint.SpritePainter;

public class Enemy1 extends APolygonEnemy {
	private static SpritePainter spritePainter = null;
	
	public Enemy1(double x, double y) {
		super(x, y);

	}
	private static double[][] origin = null;
	private static int enemyWidth = 272;
	private static int enemyHeight = 146;
	private static int halfWidth = enemyWidth/2;
	private static int halfHeight = enemyHeight/2;
	private static Image img=null;
	static {
		try {
			img = new Image(Enemy1.class.getResourceAsStream("/img/enemy1.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		spritePainter=new SpritePainter(img, enemyWidth,enemyHeight);
		origin = new double[][]{
			{-halfWidth,halfWidth,halfWidth,-halfWidth},
			{-halfHeight,-halfHeight,halfHeight,halfHeight}
		};
	}
	private int currentSpriteIndex=0;
	@Override
	public void paint(GraphicsContext gc) {
		//每隔interval帧换一张图
		int interval = 5;
		spritePainter.paintSprite((0+(currentSpriteIndex))/interval, getX()-halfWidth, getY()-halfHeight, gc);
		currentSpriteIndex=(currentSpriteIndex+1)%(12*interval);
		
	}



	@Override
	protected double[][] getOrigin() {
		return origin;
	}

}
