package magengine.enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
/**
 * 测试用的 敌人
 * @author MegaAstronic
 *
 */
public class DefaultEnemy extends APolygonEnemy {

	


	public DefaultEnemy(double x, double y) {
		super(x, y);

	}
	private static double[][] origin = null;
	private static Image img=null;
	static {
		try {
			img = new Image(DefaultEnemy.class.getResourceAsStream("/img/enemy.bmp"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		origin = new double[][]{
			{0,img.getWidth(),img.getWidth(),0},{0,0,img.getHeight(),img.getHeight()}
		};
	}
	
	
	@Override
	public void paint(GraphicsContext gc) {
		super.paint(gc);
		gc.drawImage(img, getX(), getY());
	}



	@Override
	protected double[][] getOrigin() {
		return origin;
	}

}
