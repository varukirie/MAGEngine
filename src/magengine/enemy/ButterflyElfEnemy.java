package magengine.enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import magengine.paint.SpritePainter;

public class ButterflyElfEnemy extends ALoopDanmukuEnemy {
	private static SpritePainter spritePainter = null;
	
	public ButterflyElfEnemy(double x, double y) {
		super(x, y);
	}
	private static double[][] origin = null;
	private static int enemyWidth = 80;
	private static int enemyHeight = 58;
	private static int halfWidth = enemyWidth/2;
	private static int halfHeight = enemyHeight/2;
	private static Image img=null;
	static {
		try {
			img = new Image(ButterflyElfEnemy.class.getResourceAsStream("/img/enemies/butterFlyElfEnemy.png"));
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
	private int spriteDelta=1;
	@Override
	public void paint(GraphicsContext gc) {
		super.paint(gc);
		int count=3;
		//每隔interval帧换一张图
		int interval = 5;
		spritePainter.paintSprite((0+(currentSpriteIndex))/interval, getX()-halfWidth, getY()-halfHeight, gc);
		currentSpriteIndex=(currentSpriteIndex+spriteDelta)%(count*interval);
		if(currentSpriteIndex==count*interval-1||currentSpriteIndex==0){
			spriteDelta=-spriteDelta;
		}
		
	}



	@Override
	protected double[][] getOrigin() {
		return origin;
	}

}
