package magengine.paint;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * 此类为了辅助输出精灵(sprite)动画
 * 
 * @author Astronic
 *
 */
public class SpritePainter {
	private Image sprite = null;
	private int spriteWidth = 0;
	private int spriteHeight = 0;
	private int col = 3;
/**
 * 
 * @param sprite
 * @param spriteWidth
 * @param spriteHeight
 */
	public SpritePainter(Image sprite, int spriteWidth, int spriteHeight) {
		super();
		this.sprite = sprite;
		this.spriteWidth = spriteWidth;
		this.spriteHeight = spriteHeight;
	}
	
	/**
	 * 
	 * @param sprite
	 * @param spriteWidth
	 * @param spriteHeight
	 */
		public SpritePainter(Image sprite, int spriteWidth, int spriteHeight,int col) {
			super();
			this.sprite = sprite;
			this.spriteWidth = spriteWidth;
			this.spriteHeight = spriteHeight;
			this.col=col;
		}

	/**
	 * 输出精灵动画,三个一行切割
	 * 
	 * @param index
	 *            输出第index个精灵动画，index从0开始
	 * @param x
	 *            输在屏幕上的坐标x
	 * @param y
	 *            输在屏幕上的坐标y
	 * @param gc
	 *            Canvas的GraphicsContext
	 */
	public void paintSprite(int index, double x, double y, GraphicsContext gc) {
		gc.drawImage(sprite, (index % col) * this.spriteWidth, (index / col) * this.spriteHeight, 
				this.spriteWidth,this.spriteHeight, 
				x, y, this.spriteWidth, this.spriteHeight);
	}

}
