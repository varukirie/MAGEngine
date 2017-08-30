package indi.megaastronic.bullet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.badlogic.gdx.math.Polygon;

import indi.megaastronic.element.BaseElement;
import indi.megaastronic.element.PolygonCollision;
import indi.megaastronic.util.Transform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
/**
 * 
 * @author MegaAstronic
 *
 */
public abstract class Bullet extends BaseElement{

	private int switchCount = 0;
	
	public Bullet(double x, double y) {
		super(x, y);
	}
	
	public Bullet(double x,double y,double[] VCoodinate){
		super(x, y, VCoodinate[0], VCoodinate[1]);
	}
	public Bullet(double x,double y,double vx, double vy){
		super(x, y,vx, vy);
	}
	
	public Bullet(double x,double y,double vx, double vy,double[] ACoodinate) {
		super(x,y,vx, vy,ACoodinate[0], ACoodinate[1]);
	}
	
	public Bullet(double x,double y,double[] VCoodinate,double ax,double ay) {
		super(x,y,VCoodinate[0], VCoodinate[1],ax,ay);
	}
	
	public Bullet(double x,double y,double vx, double vy,double ax,double ay) {
		super(x,y,vx,vy,ax,ay);
	}


}
