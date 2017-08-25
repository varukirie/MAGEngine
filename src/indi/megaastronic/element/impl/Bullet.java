package indi.megaastronic.element.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import indi.megaastronic.element.BaseElement;
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
public class Bullet extends BaseElement{

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
	
	
	@Override
	public void paint(GraphicsContext gc) {
//		gc.setFont(Font.font("consolas",25));
//		gc.setFill(Color.BLUE);
		/*
		if(switchCount == 0){
			gc.setFill(Color.BLUE);
		}else{
			gc.setFill(Color.BLACK);
		}
		*/
//		gc.fillText("★", this.x, this.y);
//		switchCount=(switchCount+1)%2;
		//gc.setFill(Color.RED);
		//gc.fillOval(x, y, 10, 10);
		
		final double[][] in=new double[][]{
			{
				0,0-3,0-6,0,0+6,0+3
			},
			{
				0+11,0,0+2,0-10,0+2,0
			}
		};
		double s = Math.sqrt(velocityX*velocityX+velocityY*velocityY);
		Transform t= new Transform(new double[][] {
			{-velocityY/s , velocityX/s }, 
			{-velocityX/s,-velocityY/s } });
		double[][] ans= t.transform(in);
		t.delta(ans, x, y);
		gc.fillPolygon(ans[0],ans[1], 6);
		
//		gc.strokePolygon(new double[]{
//				x,x-3,x-6,x,x+6,x+3
//		}, new double[]{
//				y-11,y,y-2,y+10,y-2,y
//		}, 6);
		
//		try {
//			Image img = new Image(new FileInputStream(this.getClass().getResource("/img/Bullet.png").getFile()));
//			gc.drawImage(img, this.x, this.y);
//			
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		//System.out.println("enemy exist millis="+System.currentTimeMillis());
	}

	
}
