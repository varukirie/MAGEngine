package indi.megaastronic.element.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import indi.megaastronic.element.LimitedByCanvas;
import indi.megaastronic.element.Moveable;
import indi.megaastronic.element.Paintable;
import indi.megaastronic.util.SpritePainter;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
/**
 * 玩家控制的物体
 * 单例设计模式
 * 只能存在一个实例化对象
 * @author MegaAstronic
 *
 */
public class Player implements LimitedByCanvas ,Moveable , Paintable {
	private static Player player = null;
	public final int width = 10;
	public final int height = 10;
	private double x=0;
	private double y=0;
	private double velocityX=0;
	private double velocityY=0;
	private SpritePainter SP = null;
	

	/**
	 * 获取Player对象
	 * @param x 设定坐标x
	 * @param y 设定坐标y
	 * @return 返回Player对象
	 */
	public static Player getPlayer(double x,double y){
		if(player==null){
			player=new Player(x,y);
			return player;
		}else{
			if(!(x==-1&&y==-1)){
				player.setX(x);
				player.setY(y);
			}
			return player;
		}
	}
	public static Player getPlayer(){
		return getPlayer(-1,-1);
	}
	
	private Player(double x, double y) {
		Image img=null;
		try {
			img = new Image(new FileInputStream(this.getClass().getResource("/img/player.bmp").getFile()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SP=new SpritePainter(img, 50, 50);
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

	private int currentSpriteIndex=0;
	@Override
	public void paint(GraphicsContext gc) {
//		gc.strokeOval(x, y, width, height);
//		gc.setFont(Font.font("consolas",30));
//		gc.setFill(Color.RED);
//		gc.fillText("♥", this.x, this.y);
		if(this.velocityX<0)
			SP.paintSprite(0, this.x, this.y, gc);
		
		if(this.velocityX==0){
			if(this.velocityY<0)
				SP.paintSprite(1, this.x, this.y, gc);
			
			if(this.velocityY==0){
				int delayF = 10;
				SP.paintSprite(4+(currentSpriteIndex)/delayF, this.x, this.y, gc);
				currentSpriteIndex=(currentSpriteIndex+1)%(5*delayF);
			}
				
			if(this.velocityY>0)
				SP.paintSprite(3, this.x, this.y, gc);
		}
			
	
		if(this.velocityX>0)
			SP.paintSprite(2, this.x, this.y, gc);
	}
	
	

}
