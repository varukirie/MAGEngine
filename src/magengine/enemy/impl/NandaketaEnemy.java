package magengine.enemy.impl;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import magengine.enemy.ALoopDanmukuEnemy;
import magengine.paint.SpritePainter;

public class NandaketaEnemy extends ALoopDanmukuEnemy {
	private static SpritePainter spritePainter = null;
	
	public NandaketaEnemy(double x, double y) {
		super(x, y);
	}
	private static double[][] origin = null;
	private static int enemyWidth = 120;
	private static int enemyHeight = 111;
	private static int halfWidth = enemyWidth/2;
	private static int halfHeight = enemyHeight/2;
	private static Image img=null;
	static {
		try {
			img = new Image(NandaketaEnemy.class.getResourceAsStream("/img/enemies/nandaketaEnemy.png"));
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
		super.paint(gc);
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
