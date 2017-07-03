package indi.megaastronic.element;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import indi.megaastronic.paint.Moveable;
import indi.megaastronic.paint.Paintable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
/**
 * 子弹
 * @author MegaAstronic
 *
 */
public class Ball implements Moveable , Paintable{

	private double x;
	private double y;
	private double velocityX=0;
	private double velocityY=0;
	
	public Ball(double x, double y) {
		this.x=x;
		this.y=y;
	}
	
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getVelocityX() {
		return velocityX;
	}
	public void setVelocityX(double velocityX) {
		this.velocityX = velocityX;
	}
	public double getVelocityY() {
		return velocityY;
	}
	public void setVelocityY(double velocityY) {
		this.velocityY = velocityY;
	}
	@Override
	public void paint(GraphicsContext gc) {
		//gc.setFill(Color.RED);
		//gc.fillOval(x, y, 10, 10);
		//gc.fillText("fuckYou", this.x, this.y);
		
		try {
			Image img = new Image(new FileInputStream("gh.png"));
			gc.drawImage(img, this.x, this.y);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.out.println("enemy exist millis="+System.currentTimeMillis());
	}

	
}
