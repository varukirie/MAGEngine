package magengine.element.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.badlogic.gdx.math.Polygon;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import magengine.bullet.CollisionTeam;
import magengine.element.BaseElement;
import magengine.element.LimitedByCanvas;
import magengine.element.Moveable;
import magengine.element.Paintable;
import magengine.element.PolygonCollision;
import magengine.util.SpritePainter;
/**
 * 玩家控制的物体
 * 单例设计模式
 * 只能存在一个实例化对象
 * @author MegaAstronic
 *
 */
public class Player extends BaseElement implements LimitedByCanvas ,PolygonCollision {
	private static Player player = null;
	public final int width = 10;
	public final int height = 10;
	private SpritePainter spritePainter = null;
	

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
		super(x, y);
		Image img=null;
		try {
			img = new Image(this.getClass().getResourceAsStream("/img/player.bmp"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		spritePainter=new SpritePainter(img, 50, 50);
		setX(x);
		setY(y);
	}
	




	private int currentSpriteIndex=0;
	@Override
	public void paint(GraphicsContext gc) {
		if(this.velocityX<0)
			spritePainter.paintSprite(0, getX(),getY(), gc);
		
		if(this.velocityX==0){
			if(this.velocityY<0)
				spritePainter.paintSprite(1, getX(), getY(), gc);
			
			if(this.velocityY==0){
				int delayF = 10;
				spritePainter.paintSprite(4+(currentSpriteIndex)/delayF, getX(), getY(), gc);
				currentSpriteIndex=(currentSpriteIndex+1)%(5*delayF);
			}
				
			if(this.velocityY>0)
				spritePainter.paintSprite(3, getX(), getY(), gc);
		}
			
	
		if(this.velocityX>0)
			spritePainter.paintSprite(2, getX(), getY(), gc);
	}
	public float[] vertices = new float[4*2];
	private Polygon polygon=new Polygon(vertices);
	
	@Override
	public Polygon getPolygon() {
		vertices[0]=(float) getX();
		vertices[1]=(float) getY();
		vertices[2]=(float) (getX()+50);
		vertices[3]=(float) getY();
		vertices[4]=(float) (getX()+50);
		vertices[5]=(float) (getY()+50);
		vertices[6]=(float) getX();
		vertices[7]=(float) (getY()+50);
		polygon.setVertices(vertices);
		return polygon;
	}
	@Override
	public CollisionTeam getTeam() {
		return CollisionTeam.PLAYER;
	}
	
	

}
