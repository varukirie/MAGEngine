package magengine.element.impl;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import magengine.element.BaseElement;
import magengine.paint.SpritePainter;

public class Spark extends BaseElement {

	public Spark(double x, double y) {
		super(x, y);
	}
	private static SpritePainter spritePainter = null;
	private static int enemyWidth = 64;
	private static int enemyHeight = 64;
	private static int halfWidth = enemyWidth/2;
	private static int halfHeight = enemyHeight/2;
	private static Image img=null;
	static {
		try {
			img = new Image(Spark.class.getResourceAsStream("/img/spark.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		spritePainter=new SpritePainter(img, enemyWidth,enemyHeight,4);
	}
	
	private int currentSpriteIndex=0;
	private int spriteDelta=1;
	@Override
	public void paint(GraphicsContext gc) {
		int count=14;
		//每隔interval帧换一张图
		int interval = 1;
		spritePainter.paintSprite((0+(currentSpriteIndex))/interval, getX()-halfWidth, getY()-halfHeight, gc);
		currentSpriteIndex=(currentSpriteIndex+spriteDelta)%(count*interval);
//		if(currentSpriteIndex==count*interval-1){
//			spriteDelta=-spriteDelta;
//		}
		if(currentSpriteIndex==count*interval-1){
			this.setWantBeRemoved(true);
		}
		
	}

}
