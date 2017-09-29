package magengine.enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
/**
 * 测试用的 敌人
 * @author MegaAstronic
 *
 */
public class DefaultEnemy extends AEnemy {

	

	public DefaultEnemy(double x, double y, double velocityX, double velocityY, double ax, double ay) {
		super(x, y, velocityX, velocityY, ax, ay);
	}

	public DefaultEnemy(double x, double y, double velocityX, double velocityY) {
		super(x, y, velocityX, velocityY);
	}

	public DefaultEnemy(double x, double y) {
		super(x, y);
	}

	@Override
	public void paint(GraphicsContext gc) {
		Image img=null;
		try {
			img = new Image(this.getClass().getResourceAsStream("/img/enemy.bmp"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		gc.drawImage(img, getX(), getY());
	}

}
