package magengine.enemy.impl;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import magengine.enemy.ALoopDanmukuEnemy;
import magengine.paint.SpritePainter;

public class YanzhanEnemy extends ALoopDanmukuEnemy {
	
	
	public YanzhanEnemy(double x, double y) {
		super(x, y);
	}
	private static SpritePainter spritePainter = null;
	private static double[][] origin = null;
	private static int enemyWidth = 130;
	private static int enemyHeight = 122;
	private static int halfWidth = enemyWidth/2;
	private static int halfHeight = enemyHeight/2;
	private static Image img=null;
	static {
		try {
			img = new Image(YanzhanEnemy.class.getResourceAsStream("/img/enemies/yanzhanEnemy.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		spritePainter=new SpritePainter(img, enemyWidth,enemyHeight);
		origin = new double[][]{
			{-halfWidth,halfWidth,halfWidth,-halfWidth},
			{-halfHeight,-halfHeight,halfHeight,halfHeight}
		};
	}
	private int spriteDelta=1;
	private int currentSpriteIndex=0;
	@Override
	public void paint(GraphicsContext gc) {
		super.paint(gc);
		int count=12;
		//每隔interval帧换一张图
		int interval = 4;
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
