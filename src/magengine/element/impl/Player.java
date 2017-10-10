package magengine.element.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.badlogic.gdx.math.Polygon;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import magengine.element.BaseElement;
import magengine.element.LimitedByCanvas;
import magengine.element.Moveable;
import magengine.element.Paintable;
import magengine.element.PolygonCollision;
import magengine.util.CollisionTeam;
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
			img = new Image(this.getClass().getResourceAsStream("/img/playerUpdated.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		spritePainter=new SpritePainter(img, 80, 175);
		setX(x);
		setY(y);
	}
	




	private int currentSpriteIndex=0;
	@Override
	public void paint(GraphicsContext gc) {
		if(this.velocityX<0){
			spritePainter.paintSprite(8+(currentSpriteIndex), getX(),getY(), gc);
			currentSpriteIndex=(currentSpriteIndex+1)%(4);
		}
		
		if(this.velocityX==0){
			spritePainter.paintSprite(0+(currentSpriteIndex), getX(), getY(), gc);
			currentSpriteIndex=(currentSpriteIndex+1)%(4);
		}
			
		if(this.velocityX>0){
			spritePainter.paintSprite(4+(currentSpriteIndex), getX(), getY(), gc);
			currentSpriteIndex=(currentSpriteIndex+1)%(4);
		}
	}
	public float[] vertices = new float[4*2];
	private Polygon polygon=new Polygon(vertices);
	
	@Override
	public Polygon getPolygon() {
		vertices[0]=(float) (getX()+30);
		vertices[1]=(float) (getY()+80);
		vertices[2]=(float) (getX()+50);
		vertices[3]=(float) (getY()+80);
		vertices[4]=(float) (getX()+50);
		vertices[5]=(float) (getY()+95);
		vertices[6]=(float) (getX()+30);
		vertices[7]=(float) (getY()+95);
		polygon.setVertices(vertices);
		return polygon;
	}
	@Override
	public CollisionTeam getTeam() {
		return CollisionTeam.PLAYER;
	}
	
	

}
