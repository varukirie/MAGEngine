package magengine.enemy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import magengine.danmuku.ADanmuku;
import magengine.element.BaseElement;
import magengine.element.Initializable;
import magengine.game.GameSession;
import magengine.game.GameSession.EmBloodBar;
import magengine.mulplay.MulSync;
import magengine.paint.SpritePainter;

public abstract class AEnemy extends BaseElement implements Initializable{
	public AEnemy(double x, double y, double velocityX, double velocityY, double ax, double ay) {
		super(x, y, velocityX, velocityY, ax, ay);
	}

	public AEnemy(double x, double y, double velocityX, double velocityY) {
		super(x, y, velocityX, velocityY);
	}

	public AEnemy(double x, double y) {
		super(x, y);	
	}
	
	private AtomicInteger HP=new AtomicInteger(10);
	private boolean bloodState = true;
	private int presetHP = 5;
	
	public AEnemy setHP(int HP){
		this.HP.set(HP);
		this.presetHP = HP;
		return this;
	}
	
	public int getHP(){
		return this.HP.get();
	}
	
	public int addAndGetHP(int delta){
		return HP.addAndGet(delta);
	}
	
	public void setNeedPaintBloodBar(GraphicsContext gc, EmBloodBar bb){
		if(bloodState){
		bb.paint(gc,getHP(),this.presetHP);
		}
	}

	
}
