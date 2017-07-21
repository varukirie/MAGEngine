package indi.megaastronic.element;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
/**
 * 
 * @author MegaAstronic
 *
 */
public class Ball extends ANormalElement{

	private int switchCount = 0;
	public Ball(double x, double y) {
		super(x, y);
	}
	public Ball(double x,double y,double vx, double vy){
		super(x, y, vx, vy);
	}
	public Ball(double x,double y,double vx, double vy,double ax,double ay) {
		super(x,y,vx,vy,ax,ay);
	}
	
	@Override
	public void paint(GraphicsContext gc) {
		gc.setFont(Font.font("consolas",20));
		gc.setFill(Color.BLUE);
		/*
		if(switchCount == 0){
			gc.setFill(Color.BLUE);
		}else{
			gc.setFill(Color.BLACK);
		}
		*/
		gc.fillText("★", this.x, this.y);
		switchCount=(switchCount+1)%2;
		//gc.setFill(Color.RED);
		//gc.fillOval(x, y, 10, 10);
		
		/*
		try {
			Image img = new Image(new FileInputStream(this.getClass().getResource("/img/gh.png").getFile()));
			gc.drawImage(img, this.x, this.y);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		//System.out.println("enemy exist millis="+System.currentTimeMillis());
	}

	
}
